package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

// 游戏面板类
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // 定义小蛇的数据结构
    int length; // 小蛇的长度
    int[] snakeX = new int[600]; // X坐标
    int[] snakeY = new int[600]; // Y坐标

    // 定义食物数据结构
    int foodX; // 食物X坐标
    int foodY; // 食物Y坐标
    Random random = new Random();

    // 定义分数
    int score;

    // 小蛇方向, Up 上, Down 下, Left 左, Right 右
    String fx;

    // 游戏状态
    boolean isStart = false; // 默认是暂停的

    // 游戏结束状态
    boolean isFail = false; // 默认是正常的

    // 定时器对象
    Timer timer = new Timer(100,this); // 刷新频率, 监听对象

    // 构造方法
    public GamePanel() {
        init();
        // 获取屏幕聚焦
        this.setFocusable(true);
        // 添加键盘监听
        this.addKeyListener(this);
    }

    // 初始化
    public void init() {
        // 初始化小蛇数据
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100; // 初始化小蛇头部坐标
        snakeX[1] = 75; snakeY[1] = 100; // 初始化小蛇身体坐标
        snakeX[2] = 50; snakeY[2] = 100; // 初始化小蛇身体坐标

        // 初始化小蛇方向
        fx = "Right";

        // 初始化食物坐标
        foodX = 25 + 25*random.nextInt(34); // 850/25=34
        foodY = 75 + 25*random.nextInt(24); // 600/25=24
    }

    // 画笔方法, 游戏上所有东西都在这里画
    @Override
    protected void paintComponent(Graphics g) {
        // 清屏
        super.paintComponent(g);

        // 画游戏面板
        g.fillRect(25,75,850,600);

        // 广告位
        g.setColor(Color.pink); // 设置画笔颜色
        g.setFont(new Font("微软雅黑",Font.BOLD,20));
        g.drawString("广告位招租",25,35);

        // 分数和长度
        g.setColor(Color.cyan); // 设置画笔颜色
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度: " + length,750,35);
        g.drawString("分数: " + score,750,55);

        // 画小蛇头
        if (fx.equals("Up")) {
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Down")) {
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Left")) {
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Right")) {
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        // 画小蛇身体
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        // 画游戏状态
        if (isStart == false) {
            g.setColor(Color.white); // 设置画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏!",300,300);
        }

        // 游戏失败状态
        if (isFail) {
            g.setColor(Color.red); // 设置画笔颜色
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败, 按下空格重新开始游戏!",150,300);
        }

        // 画食物
        Data.food.paintIcon(this,g,foodX,foodY);
    }

    // 键盘按下事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // 按下空格开始/暂停游戏
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
            } else {
                isStart = !isStart;
            }

            // 游戏开始, 开启定时器, 暂停关闭定时器
            if (isStart) {
                timer.start();
            } else {
                timer.stop();
            }

            // 重绘页面
            this.repaint();
        }

        // 游戏是开始的才能改变方向
        if (isStart) {
            // 控制小蛇方向
            if (keyCode == KeyEvent.VK_UP && !fx.equals("Down")) {
                fx = "Up";
            } else if (keyCode == KeyEvent.VK_DOWN && !fx.equals("Up")) {
                fx = "Down";
            } else if (keyCode == KeyEvent.VK_LEFT && !fx.equals("Right")) {
                fx = "Left";
            } else if (keyCode == KeyEvent.VK_RIGHT && !fx.equals("Left")) {
                fx = "Right";
            }
        }
    }

    // 定时器事件
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isStart && isFail == false) {
            // 让小蛇动起来, 先移动身体, 再移动头
            for (int i = length - 1; i > 0; i--) {
                // 每次让小蛇身体往前移动
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            if (fx.equals("Up")) {
                snakeY[0] -= 25;
            } else if (fx.equals("Down")) {
                snakeY[0] += 25;
            } else if (fx.equals("Left")) {
                snakeX[0] -= 25;
            } else if (fx.equals("Right")) {
                snakeX[0] += 25;
            }

            // 判断与墙的距离
            if (snakeX[0] > 850) {
                snakeX[0] = 25;
            } else if (snakeX[0] < 25) {
                snakeX[0] = 850;
            } else if (snakeY[0] > 650) {
                snakeY[0] = 75;
            } else if (snakeY[0] < 75) {
                snakeY[0] = 650;
            }

            // 判断与身体的距离
            for (int i = length - 1; i > 0; i--) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    // 改变游戏失败状态
                    isFail = true;
                    // 停止定时器
                    timer.stop();
                    // 重新开始游戏
                    init();
                }
            }

            // 吃食物
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                // 小蛇长度+1
                length++;
                // 分数 +10
                score += 10;
                // 初始化食物坐标
                foodX = 25 + 25*random.nextInt(34); // 850/25=34
                foodY = 75 + 25*random.nextInt(24); // 600/25=24
            }

            // 重绘页面
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
