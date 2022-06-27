package top.bing6749;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

/*
 * �����������ܣ�����������
 */
public class StartChessJFrame extends JFrame {
    private ChessBoard chessBoard;//��ս���
    private Panel toolbar;//���������
    private JButton startJButton;//���ÿ�ʼ��ť
    private JButton backJButton;//���û��尴ť
    //private JButton exitJButton;//�����˳���ť


    public StartChessJFrame() {
        //���ڵ�λ��
        this.setLocationRelativeTo(null);
        this.setLocation(800,500);
        setTitle("������������");//���ñ���
        chessBoard = new ChessBoard();//��ʼ�������󣬴�������Ӳ˵�
        MyItemListener lis = new MyItemListener();//��ʼ����ť�¼��������ڲ���
        toolbar = new Panel();//���������ʵ����
        startJButton = new JButton("���¿�ʼ");
        backJButton = new JButton("����");
        //exitJButton = new JButton("�˳�");//������ť��ʼ��
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));//��������尴ť��FlowLayout����
        toolbar.add(backJButton);
        toolbar.add(startJButton);
        //toolbar.add(exitJButton);//��������ť��ӵ����������
        startJButton.addActionListener(lis);
        backJButton.addActionListener(lis);
        //exitJButton.addActionListener(lis);//��������ť�¼�ע������¼�
        add(toolbar, BorderLayout.SOUTH);//��������岼�ֵ������Ϸ�Ҳ��������
        add(chessBoard);//����������ӵ�������
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ý���ر��¼�
        pack();//����Ӧ��С

    }

    private class MyItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();//��ȡ�¼�Դ
            if (obj == startJButton) {
                System.out.println("���¿�ʼ...");//���¿�ʼ
                //JFiveFrame.this�ڲ��������ⲿ��
                chessBoard.restartGame();
            } else if (obj == backJButton) {
                System.out.println("����...");//����
                chessBoard.goback();
            }
        }
    }

    public static void main(String[] args) {
        StartChessJFrame f = new StartChessJFrame();//���������
        f.setVisible(true);//��ʾ�����
    }
}

