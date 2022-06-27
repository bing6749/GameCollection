package com.snake;

import javax.swing.*;

// 游戏的启动类
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("贪吃蛇");

        // 添加游戏面板
        frame.add(new GamePanel());

        frame.setVisible(true);
        frame.setResizable(false); // 禁止调整大小
        frame.setBounds(10, 10, 900, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
