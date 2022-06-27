package top.bing6749;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 * @author MaRui
 */
public class Game2048 extends JPanel {
    /*
    游戏的状态，枚举类型
     */
    enum State {
        start, won, running, over
    }

    //颜色库
    final Color[] colorTable = {
            new Color(0x701710), new Color(0xFFE4C3), new Color(0xfff4d3),
            new Color(0xffdac3), new Color(0xe7b08e), new Color(0xe7bf8e),
            new Color(0xffc4c3), new Color(0xE7948e), new Color(0xbe7e56),
            new Color(0xbe5e56), new Color(0x9c3931), new Color(0x701710)
    };
    final static int target = 2048; //目标分数
    static int highest;     //游戏高度
    static int score;       //游戏分数
    private Color gridColor = new Color(0xBBADA0);
    private Color emptyColor = new Color(0xCDC1B4);
    private Color startColor = new Color(0xFFEBCD);
    private Random rand = new Random();
    private Tile[][] tiles;    //数字快数组
    private int side = 4;   //方块是4*4排列
    private State gamestate = State.start;  //游戏的状态
    private boolean checkingAvailableMoves;     //检测游戏是否失败就无法移动

    public Game2048() {
        setPreferredSize(new Dimension(900, 700));
        setBackground(new Color(0xFAF8EF));
        setFont(new Font("SansSerif", Font.BOLD, 48));
        setFocusable(true);
        //鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startGame();
                repaint();  //刷新画板
            }
        });
        //键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        break;
                }
                repaint();
            }
        });

    }

    //初始化画图
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        //抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawGrid(g);
    }

    void startGame() {
        if (gamestate != State.running) {
            score = 0;
            highest = 0;
            gamestate = State.running;
            tiles = new Tile[side][side];
            addRandomTile();
            addRandomTile();
        }
    }

    //绘画游戏状态
    void drawGrid(Graphics2D g) {
        g.setColor(gridColor);
        g.fillRoundRect(200, 100, 499, 499, 15, 15);
        if (gamestate == State.running) {
            for (int r = 0; r < side; r++) {
                for (int c = 0; c < side; c++) {
                    if (tiles[r][c] == null) {  //判断格子中的元素是否为空
                        g.setColor(emptyColor);
                        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
                    } else {
                        drawTile(g, r, c);
                    }
                }
            }
        } else {
            g.setColor(startColor);
            g.fillRoundRect(215, 115, 469, 469, 7, 7);
            g.setColor(gridColor.darker());
            g.setFont(new Font("SansSerif", Font.BOLD, 80));
            g.drawString("game2048", 250, 270);
            g.setFont(new Font("SansSerif", Font.BOLD, 20));
            if (gamestate == State.won) {
                g.drawString("you made it!", 390, 350);
            } else if (gamestate == State.over) {
                g.drawString("game over", 400, 350);
            }
            g.setColor(gridColor);
            g.drawString("click to start a new game", 330, 470);
            g.drawString("(use arrow keys to move tiles)", 310, 530);
        }   
    }

    //绘制数字块
    void drawTile(Graphics2D g, int r, int c) {
        int value = tiles[r][c].getValue();
        g.setColor(colorTable[(int) (Math.log(value) / Math.log(2)) + 1]);
        g.fillRoundRect(215 + c * 121, 115 + r * 121, 106, 106, 7, 7);
        String s = String.valueOf(value);
        g.setColor(value < 128 ? colorTable[0] : colorTable[1]);
        FontMetrics fm = g.getFontMetrics();
        int asc = fm.getAscent();
        int dec = fm.getDescent();
        int x = 215 + c * 121 + (106 - fm.stringWidth(s)) / 2;
        int y = 115 + r * 121 + (asc + (106 - (asc + dec)) / 2);
        g.drawString(s, x, y);
    }

    /*
    添加随机数字块
     */
    private void addRandomTile() {
        int pos = rand.nextInt(side * side);
        int row, col;
        do {
            pos = (pos + 1) % (side * side);
            row = pos / side;
            col = pos % side;
        } while (tiles[row][col] != null);
        int val = rand.nextInt(10) == 0 ? 4 : 2;
        tiles[row][col] = new Tile(val);
    }


    private boolean move(int countDownFrom, int yIncr, int xIncr) {
        boolean moved = false;     //设置flag默认没有发生移动
        for (int i = 0; i < side * side; i++) {
            int j = Math.abs(countDownFrom - i);
            int r = j / side;
            int c = j % side;
            if (tiles[r][c] == null)
                continue;
            int nextR = r + yIncr;
            int nextC = c + xIncr;
            while (nextR >= 0 && nextR < side && nextC >= 0 && nextC < side) {
                Tile next = tiles[nextR][nextC];
                Tile curr = tiles[r][c];
                //下一个方块为空时，自动移动
                if (next == null) {
                    if (checkingAvailableMoves) {
                        return true;
                    }
                    tiles[nextR][nextC] = curr;
                    tiles[r][c] = null;
                    r = nextR;
                    c = nextC;
                    nextR += yIncr;
                    nextC += xIncr;
                    moved = true;
                } else if (next.canMergeWith(curr)) {
                    if (checkingAvailableMoves) {
                        return true;
                    }
                    int value = next.mergeWith(curr);
                    if (value > highest) {
                        highest = value;
                    }
                    score += value;
                    tiles[r][c] = null;
                    moved = true;
                    break;
                } else {
                    break;
                }
            }
        }
        if (moved) {
            if (highest < target) {
                clearMerged();
                addRandomTile();
                if (!movesAvailable()) {
                    gamestate = State.over;
                }
            } else if (highest == target) {
                gamestate = State.won;
            }
        }
        return moved;
    }

    boolean moveUp() {
        return move(0, -1, 0);
    }

    boolean moveDown() {
        return move(side * side - 1, 1, 0);
    }

    boolean moveLeft() {
        return move(0, 0, -1);
    }

    boolean moveRight() {
        return move(side * side - 1, 0, 1);
    }

    //清除面板
    void clearMerged() {
        for (Tile[] row : tiles)
            for (Tile tile : row)
                if (tile != null)
                    tile.setMerged(false);
    }

    /*
    判断是否可以移动
     */
    boolean movesAvailable() {
        checkingAvailableMoves = true;
        boolean hasMoves = moveUp() || moveDown() || moveLeft() || moveRight();
        checkingAvailableMoves = false;
        return hasMoves;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("game2048");
            f.add(new Game2048(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);  //窗口将置于屏幕的中央
            f.setVisible(true);
            f.setResizable(false);      //此窗体不可由用户调整大小
        });
    }
}