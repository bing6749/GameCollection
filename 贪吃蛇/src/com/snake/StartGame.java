package com.snake;

import javax.swing.*;

// ��Ϸ��������
public class StartGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("̰����");

        // �����Ϸ���
        frame.add(new GamePanel());

        frame.setVisible(true);
        frame.setResizable(false); // ��ֹ������С
        frame.setBounds(10, 10, 900, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
