package top.bing6749;


import com.snake.GamePanel;
import table.User;
import top.bing6749.plane.MyGameFrame;
import top.bing6749.plane.MyMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

/**
 * @author MaRui
 */
public class GameHill extends JFrame {
    /**
     * 面板
     */
    private JFrame jFrame;
    /**
     * 上容器
     */
    private JPanel topPanel;
    /**
     * 下容器
     */
    private JPanel bottomPanel;
    /**
     * 最小化按钮
     */
    private JButton min;
    /**
     * 关闭按钮
     */
    private JButton exit;

    private JLabel logo;
    private JLabel game2048;
    private JLabel game20481;
    private JLabel plane;
    private JLabel plane1;
    private JLabel man;
    private JLabel man1;
    private JLabel snake;
    private JLabel snake1;
    private JLabel gobang;
    private JLabel gobang1;
    private JLabel username;
    private JLabel jkxt;    //健康系统
    private JLabel jkxt2;    //健康系统


    public void init(User user) {
        // 设置主题
        this.setTitle("游戏大厅");
        // 设置窗口图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imgs/游戏.jpg"));
        // 退出时关闭资源
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗口尺寸
        this.setSize(1024, 768);
        // 设置窗口屏幕居中
        this.setLocationRelativeTo(null);
        // 设置窗口不能改变大小
        this.setResizable(false);
        this.setLayout(null);


        // 上容器
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 1050, 200);
        topPanel.setLayout(null);
        topPanel.setBackground(new Color(0, 0, 0));
        // 下容器
        bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 200, 1050, 600);
        bottomPanel.setLayout(null);
        bottomPanel.setBackground(new Color(225, 225, 255));
        // 最小化按钮
        min = new JButton(new ImageIcon("src/images/m3.png"));
        min.setBounds(900, 10, 50, 50);
        min.setBorder(null);
        min.setRolloverIcon(new ImageIcon("src/images/m2.png"));
        // 关闭按钮
        exit = new JButton(new ImageIcon("src/images/3.png"));
        exit.setBounds(960, 10, 50, 50);
        exit.setBorder(null);
        exit.setRolloverIcon(new ImageIcon("src/images/2.png"));
        // gameLogo
        logo = new JLabel(new ImageIcon("src/images/logo1.jpg"));
        logo.setBounds(340, 23, 297, 161);
        //username
        username = new JLabel("尊敬的用户:" + user.getUsername());
        username.setForeground(Color.BLUE);
        username.setFont(new Font("微软雅黑", Font.BOLD, 20));
        username.setBounds(50, 90, 500, 40);
//        System.out.println(user.getUsername());
        //2048
        game2048 = new JLabel(new ImageIcon("src/images/2048.jpg"));
        game2048.setBounds(140, 113, 100, 100);
        //20481
        game20481 = new JLabel(new ImageIcon("src/images/20481.png"));
        game20481.setBounds(140, 113, 100, 100);
        game20481.setVisible(false);
        //plane
        plane = new JLabel(new ImageIcon("src/images/plane.jpg"));
        plane.setBounds(440, 113, 100, 100);
        //plane1
        plane1 = new JLabel(new ImageIcon("src/images/plane1.png"));
        plane1.setBounds(440, 113, 100, 100);
        plane1.setVisible(false);
        //man
        man = new JLabel(new ImageIcon("src/images/man.png"));
        man.setBounds(740, 113, 100, 100);
        //man1
        man1 = new JLabel(new ImageIcon("src/images/man1.png"));
        man1.setBounds(740, 113, 100, 100);
        man1.setVisible(false);
        //snake
        snake = new JLabel(new ImageIcon("src/images/snake.jpg"));
        snake.setBounds(140, 363, 100, 100);
        //snake1
        snake1 = new JLabel(new ImageIcon("src/images/snake1.png"));
        snake1.setBounds(140, 363, 100, 100);
        snake1.setVisible(false);
        //gobang
        gobang = new JLabel(new ImageIcon("src/images/gobang.png"));
        gobang.setBounds(440, 363, 100, 100);
        //gobang1
        gobang1 = new JLabel(new ImageIcon("src/images/gobang1.png"));
        gobang1.setBounds(440, 363, 100, 100);
        gobang1.setVisible(false);

        //健康系统
        jkxt = new JLabel("抵制不良游戏，拒绝盗版游戏。注意自我保护，谨防受骗上当。");
        jkxt.setForeground(Color.WHITE);
        jkxt.setFont(new Font("微软雅黑", Font.BOLD, 11));
        jkxt.setBounds(710, 492, 800, 80);
        jkxt2 = new JLabel("适度游戏益脑，沉迷游戏伤身。合理安排时间，享受健康生活。");
        jkxt2.setForeground(Color.WHITE);
        jkxt2.setFont(new Font("微软雅黑", Font.BOLD, 11));
        jkxt2.setBounds(710, 510, 800, 80);

        //1图片的加载

        //添加面板
        topPanel.add(exit);
        topPanel.add(min);
        topPanel.add(logo);
        topPanel.add(username);
        this.add(topPanel);

        bottomPanel.add(game2048);
        bottomPanel.add(game20481);
        bottomPanel.add(plane);
        bottomPanel.add(plane1);
        bottomPanel.add(man);
        bottomPanel.add(man1);
        bottomPanel.add(snake);
        bottomPanel.add(snake1);
        bottomPanel.add(gobang);
        bottomPanel.add(gobang1);
        bottomPanel.add(jkxt);
        bottomPanel.add(jkxt2);
        this.add(bottomPanel);


        this.setUndecorated(true); // 去掉窗口的装饰

        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格

        {
            /**
             * 最小化
             */
            min.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setExtendedState(JFrame.ICONIFIED);
                }
            });
            /**
             * 关闭
             */
            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

        }

        // 设置是否可见
        this.setVisible(true);
        Lister();
    }


    /**
     * 监听器方法
     */

    public void Lister() {
        {
            /**
             * 鼠标移动到plane    label上
             */
            game2048.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    game2048.setVisible(false);
                    game20481.setVisible(true);
                    System.out.println("点击了");
                }
            });

            /**
             * 鼠标移除plane1   label
             */
            game20481.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    game2048.setVisible(true);
                    game20481.setVisible(false);

                    System.out.println("离开");
                }

            });
            //2048载入的鼠标方法
            game2048.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                        JFrame f = new JFrame();
                        f.setTitle("game2048");
                        f.add(new Game2048(), BorderLayout.CENTER);
                        f.pack();
                        f.setLocationRelativeTo(null);  //窗口将置于屏幕的中央
                        f.setVisible(true);
                        f.setResizable(false);      //此窗体不可由用户调整大小
                }
            });
            game20481.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                        System.out.println(Thread.currentThread().getName());
                        JFrame f = new JFrame();
                        f.setTitle("game2048");
                        f.add(new Game2048(), BorderLayout.CENTER);
                        f.pack();
                        f.setLocationRelativeTo(null);  //窗口将置于屏幕的中央
                        f.setVisible(true);
                        f.setResizable(false);      //此窗体不可由用户调整大小
                }
            });
        }

        {
            /**
             * 鼠标移动到plane    label上
             */
            plane.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    plane.setVisible(false);
                    plane1.setVisible(true);
                    System.out.println("点击了");
                }
            });

            /**
             * 鼠标移除plane1   label
             */
            plane1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    plane.setVisible(true);
                    plane1.setVisible(false);

                    System.out.println("离开");
                }

            });
            plane1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingWorker swingWorker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            new GameWin().launch();
                            return null;
                        }
                    };
                    swingWorker.execute();
                }
            });

            plane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SwingWorker swingWorker = new SwingWorker() {
                        @Override
                        protected Object doInBackground() throws Exception {
                            new GameWin().launch();
                            return null;
                        }
                    };
                    swingWorker.execute();
                }
            });
        }
        {
            /**
             * 鼠标移动到man    label上
             */
            man.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    man.setVisible(false);
                    man1.setVisible(true);
                    System.out.println("点击了");
                }
            });

            /**
             * 鼠标移除man1   label
             */
            man1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    man.setVisible(true);
                    man1.setVisible(false);

                    System.out.println("离开");
                }

            });
            man1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    MyGameFrame frame = new MyGameFrame();
                    //调用游戏类的初始方法
                    frame.launchFrame();

                }
            });

            man.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    MyGameFrame frame = new MyGameFrame();
                    //调用游戏类的初始方法
                    frame.launchFrame();
                }
            });
        }
        {
            /**
             * 鼠标移动到snake    label上
             */
            snake.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    snake.setVisible(false);
                    snake1.setVisible(true);
                    System.out.println("点击了");
                }
            });

            /**
             * 鼠标移除snake1   label
             */
            snake1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    snake.setVisible(true);
                    snake1.setVisible(false);

                    System.out.println("离开");
                }

            });
            snake.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame("贪吃蛇");

                    // 添加游戏面板
                    frame.add(new GamePanel());

                    frame.setVisible(true);
                    frame.setResizable(false); // 禁止调整大小
                    frame.setBounds(10, 10, 900, 720);
                }
            });

            snake1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFrame frame = new JFrame("贪吃蛇");

                    // 添加游戏面板
                    frame.add(new GamePanel());

                    frame.setVisible(true);
                    frame.setResizable(false); // 禁止调整大小
                    frame.setBounds(10, 10, 900, 720);
                }
            });
        }
        {
            /**
             * 鼠标移动到gobang    label上
             */
            gobang.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    gobang.setVisible(false);
                    gobang1.setVisible(true);
                    System.out.println("点击了");
                }
            });

            /**
             * 鼠标移除gobang1   label
             */
            gobang1.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    gobang.setVisible(true);
                    gobang1.setVisible(false);

                    System.out.println("离开");
                }

            });
            gobang.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StartChessJFrame f = new StartChessJFrame();//创建主框架
                    f.setVisible(true);//显示主框架
                }
            });

            gobang1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    StartChessJFrame f = new StartChessJFrame();//创建主框架
                    f.setVisible(true);//显示主框架
                }
            });
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameHill().init(new User("123", "123"));
        });
    }
}
