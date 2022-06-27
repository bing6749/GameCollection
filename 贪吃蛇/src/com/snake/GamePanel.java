package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

// ��Ϸ�����
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    // ����С�ߵ����ݽṹ
    int length; // С�ߵĳ���
    int[] snakeX = new int[600]; // X����
    int[] snakeY = new int[600]; // Y����

    // ����ʳ�����ݽṹ
    int foodX; // ʳ��X����
    int foodY; // ʳ��Y����
    Random random = new Random();

    // �������
    int score;

    // С�߷���, Up ��, Down ��, Left ��, Right ��
    String fx;

    // ��Ϸ״̬
    boolean isStart = false; // Ĭ������ͣ��

    // ��Ϸ����״̬
    boolean isFail = false; // Ĭ����������

    // ��ʱ������
    Timer timer = new Timer(100,this); // ˢ��Ƶ��, ��������

    // ���췽��
    public GamePanel() {
        init();
        // ��ȡ��Ļ�۽�
        this.setFocusable(true);
        // ��Ӽ��̼���
        this.addKeyListener(this);
    }

    // ��ʼ��
    public void init() {
        // ��ʼ��С������
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100; // ��ʼ��С��ͷ������
        snakeX[1] = 75; snakeY[1] = 100; // ��ʼ��С����������
        snakeX[2] = 50; snakeY[2] = 100; // ��ʼ��С����������

        // ��ʼ��С�߷���
        fx = "Right";

        // ��ʼ��ʳ������
        foodX = 25 + 25*random.nextInt(34); // 850/25=34
        foodY = 75 + 25*random.nextInt(24); // 600/25=24
    }

    // ���ʷ���, ��Ϸ�����ж����������ﻭ
    @Override
    protected void paintComponent(Graphics g) {
        // ����
        super.paintComponent(g);

        // ����Ϸ���
        g.fillRect(25,75,850,600);

        // ���λ
        g.setColor(Color.pink); // ���û�����ɫ
        g.setFont(new Font("΢���ź�",Font.BOLD,20));
        g.drawString("���λ����",25,35);

        // �����ͳ���
        g.setColor(Color.cyan); // ���û�����ɫ
        g.setFont(new Font("΢���ź�",Font.BOLD,18));
        g.drawString("����: " + length,750,35);
        g.drawString("����: " + score,750,55);

        // ��С��ͷ
        if (fx.equals("Up")) {
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Down")) {
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Left")) {
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        } else if (fx.equals("Right")) {
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        // ��С������
        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        // ����Ϸ״̬
        if (isStart == false) {
            g.setColor(Color.white); // ���û�����ɫ
            g.setFont(new Font("΢���ź�",Font.BOLD,40));
            g.drawString("���¿ո�ʼ��Ϸ!",300,300);
        }

        // ��Ϸʧ��״̬
        if (isFail) {
            g.setColor(Color.red); // ���û�����ɫ
            g.setFont(new Font("΢���ź�",Font.BOLD,40));
            g.drawString("��Ϸʧ��, ���¿ո����¿�ʼ��Ϸ!",150,300);
        }

        // ��ʳ��
        Data.food.paintIcon(this,g,foodX,foodY);
    }

    // ���̰����¼�
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // ���¿ո�ʼ/��ͣ��Ϸ
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
            } else {
                isStart = !isStart;
            }

            // ��Ϸ��ʼ, ������ʱ��, ��ͣ�رն�ʱ��
            if (isStart) {
                timer.start();
            } else {
                timer.stop();
            }

            // �ػ�ҳ��
            this.repaint();
        }

        // ��Ϸ�ǿ�ʼ�Ĳ��ܸı䷽��
        if (isStart) {
            // ����С�߷���
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

    // ��ʱ���¼�
    @Override
    public void actionPerformed(ActionEvent e) {

        if (isStart && isFail == false) {
            // ��С�߶�����, ���ƶ�����, ���ƶ�ͷ
            for (int i = length - 1; i > 0; i--) {
                // ÿ����С��������ǰ�ƶ�
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

            // �ж���ǽ�ľ���
            if (snakeX[0] > 850) {
                snakeX[0] = 25;
            } else if (snakeX[0] < 25) {
                snakeX[0] = 850;
            } else if (snakeY[0] > 650) {
                snakeY[0] = 75;
            } else if (snakeY[0] < 75) {
                snakeY[0] = 650;
            }

            // �ж�������ľ���
            for (int i = length - 1; i > 0; i--) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    // �ı���Ϸʧ��״̬
                    isFail = true;
                    // ֹͣ��ʱ��
                    timer.stop();
                    // ���¿�ʼ��Ϸ
                    init();
                }
            }

            // ��ʳ��
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                // С�߳���+1
                length++;
                // ���� +10
                score += 10;
                // ��ʼ��ʳ������
                foodX = 25 + 25*random.nextInt(34); // 850/25=34
                foodY = 75 + 25*random.nextInt(24); // 600/25=24
            }

            // �ػ�ҳ��
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
