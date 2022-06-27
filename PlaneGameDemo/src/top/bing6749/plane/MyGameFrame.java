package top.bing6749.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * @author MaRui
 */
public class MyGameFrame extends JFrame {

    int planeX = 100;
    int planeY = 100;

    Image planeImg = GameUtil.getImage("images/plane.png");
    Image bg = GameUtil.getImage("images/bg.jpg");

    Plane plane = new Plane(planeImg,planeX,planeY,5);  //创建飞机对象
    Shell[] shells = new Shell[50];                            //创建炮弹们的对象
    Explode explode = null;                                     //创建爆炸对象
    Date start= new Date();                                     //创建游戏开始时间
    Date end;                                                   //创建游戏结束时间（飞机死亡时刻）
    long period = 0;                                             //玩了多少秒

    @Override
    public void paint(Graphics g) {     //把g当作是一支画笔

        Color color = g.getColor();
        //画背景
        g.drawImage(bg, 0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, null);

        //画时间
        drawTime(g);

        //画小飞机
        plane.drawMyself(g);
        //画小炮弹
        for (Shell shell : shells) {
            shell.drawMyself(g);

            //实现碰撞检测，将所有的炮弹和飞机进行飞行检测，看有没有碰到
            boolean peng = shell.getRec().intersects(plane.getRec());
            if(peng){

                plane.live = false;

                //处理爆炸效果
                if(explode == null) {
                    explode = new Explode(plane.x, plane.y);
                }
                explode.drawMySelf(g);
            }
        }

        //添加水印
        g.setColor(new Color(253, 255, 246));
        g.drawString("www.bing6749.top", 380, 480);
        g.setColor(color);
    }



    //画时间
    public void drawTime(Graphics g){
        g.setColor(new Color(234,231,222));
        if(plane.live){
            period = (System.currentTimeMillis()-start.getTime())/1000;
            g.drawString("坚持了" + period + "秒", 15, 50);

        }else{
            if(end == null) {
                end = new Date();
                period = (end.getTime()-start.getTime())/1000;
            }
            g.setColor(Color.RED);
            Font font = g.getFont();
            g.setFont(new Font("微软雅黑",Font.BOLD,50));
            g.drawString("最终时间:"+period+"秒",100,250);
            g.setFont(font);


        }
    }
    //关闭类
    public void close(){
        this.dispose();
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //初始化窗口
    public void launchFrame() {
        this.setTitle("是男人就坚持30秒");     //窗口标题
        setVisible(true);                   //窗口是否可见
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);      //窗口大小

        setLocation(400, 500);         //窗口位置

        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //初始化创建50个炮弹对象
        for (int i = 0; i < 50; i++) {
            shells[i] = new Shell();
        }
        new PaintThread().start();      //启动重画方法的线程



        this.addKeyListener(new KeyMonitor());  //启动键盘监听
    }

    /**
     * 定义另一个重画窗口的线程类
     * 定义内部类调用外部方法方便
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();      //内部类可以直接使用外部类的成员
                //条件符合显示弹窗
                if(end!= null&&end.getTime()+2000 <= System.currentTimeMillis()&&!plane.live){
                    new MyOptionPane();
                    plane.live= true;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //弹窗类
    class MyOptionPane {
        public MyOptionPane() {
            int userOption =  JOptionPane.showConfirmDialog(null,"是否重新开始游戏？","您已死亡",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);	//确认对话框
            //如果用户选择的是OK
            if (userOption == JOptionPane.OK_OPTION) {
                close();
                new MyGameFrame().launchFrame();
            }else {
                close();
            }
        }
    }

    //内部类，实现键盘的监听处理
    class KeyMonitor extends KeyAdapter {
        //调用飞机类中的键盘控制方法

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);

        }
    }

    //双缓冲技术
    private Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        }

        Graphics goff = offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
    }



}
