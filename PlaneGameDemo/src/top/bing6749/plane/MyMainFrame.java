package top.bing6749.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author MaRui
 */
public class MyMainFrame extends JFrame {

    Image bg = GameUtil.getImage("images/bg.jpg");

    JButton botton1 = new JButton("����Ϸ");
    JButton botton2 = new JButton("��ʷ��¼");
    JButton botton3 = new JButton("�˳���Ϸ");

    @Override
    public void paint(Graphics g) {
        //������
        g.drawImage(bg, 0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, null);
        //���ư�ť
        botton1.requestFocus();
        botton2.requestFocus();
        botton3.requestFocus();
    }

    //��ʼ������
    public void launchFrame() {
        this.setTitle("�����˾ͼ��30��");
        setVisible(true);                   //�����Ƿ�ɼ�
        init();
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);      //���ڴ�С

        setLocation(400, 500);         //����λ��

        //�ѷ���
//        ���ӹرմ��ڵĶ���
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });

        // ���ô��ڲ��ܸı��С
        this.setResizable(false);



        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//����ָ���Ĵ���װ�η��

        this.addKeyListener(new MyMainFrame.KeyMonitor());  //�������̼���
    }


    //�ڲ��࣬ʵ�ּ��̵ļ�������
    class KeyMonitor extends KeyAdapter {

    }

    public MyMainFrame() {
    }

    void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); //Ĭ��Ϊ����;ˮƽ���10����ֱ���5
        //�������JBotton
        this.add(botton1);
        //��ӽ�������Ϸ����
        botton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyGameFrame frame = new MyGameFrame();
                //������Ϸ��ĳ�ʼ����
                frame.launchFrame();
            }
        });
        this.add(botton2);
        botton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryRecord historyRecord = new HistoryRecord();
                historyRecord.init();
            }
        });
        this.add(botton3);
        botton3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    public String[] str = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public JList list;

    public void ListTest1() {
        list = new JList(str);
        list.setVisible(true);
        this.add(list);
    }


}
