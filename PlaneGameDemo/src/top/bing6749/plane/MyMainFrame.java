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

    JButton botton1 = new JButton("新游戏");
    JButton botton2 = new JButton("历史记录");
    JButton botton3 = new JButton("退出游戏");

    @Override
    public void paint(Graphics g) {
        //画背景
        g.drawImage(bg, 0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, null);
        //绘制按钮
        botton1.requestFocus();
        botton2.requestFocus();
        botton3.requestFocus();
    }

    //初始化窗口
    public void launchFrame() {
        this.setTitle("是男人就坚持30秒");
        setVisible(true);                   //窗口是否可见
        init();
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);      //窗口大小

        setLocation(400, 500);         //窗口位置

        //已废弃
//        增加关闭窗口的动作
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });

        // 设置窗口不能改变大小
        this.setResizable(false);



        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格

        this.addKeyListener(new MyMainFrame.KeyMonitor());  //启动键盘监听
    }


    //内部类，实现键盘的监听处理
    class KeyMonitor extends KeyAdapter {

    }

    public MyMainFrame() {
    }

    void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10)); //默认为居中;水平间距10，垂直间距5
        //添加三个JBotton
        this.add(botton1);
        //添加进入新游戏界面
        botton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyGameFrame frame = new MyGameFrame();
                //调用游戏类的初始方法
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
