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

    Plane plane = new Plane(planeImg,planeX,planeY,5);  //�����ɻ�����
    Shell[] shells = new Shell[50];                            //�����ڵ��ǵĶ���
    Explode explode = null;                                     //������ը����
    Date start= new Date();                                     //������Ϸ��ʼʱ��
    Date end;                                                   //������Ϸ����ʱ�䣨�ɻ�����ʱ�̣�
    long period = 0;                                             //���˶�����

    @Override
    public void paint(Graphics g) {     //��g������һ֧����

        Color color = g.getColor();
        //������
        g.drawImage(bg, 0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT, null);

        //��ʱ��
        drawTime(g);

        //��С�ɻ�
        plane.drawMyself(g);
        //��С�ڵ�
        for (Shell shell : shells) {
            shell.drawMyself(g);

            //ʵ����ײ��⣬�����е��ڵ��ͷɻ����з��м�⣬����û������
            boolean peng = shell.getRec().intersects(plane.getRec());
            if(peng){

                plane.live = false;

                //����ըЧ��
                if(explode == null) {
                    explode = new Explode(plane.x, plane.y);
                }
                explode.drawMySelf(g);
            }
        }

        //���ˮӡ
        g.setColor(new Color(253, 255, 246));
        g.drawString("www.bing6749.top", 380, 480);
        g.setColor(color);
    }



    //��ʱ��
    public void drawTime(Graphics g){
        g.setColor(new Color(234,231,222));
        if(plane.live){
            period = (System.currentTimeMillis()-start.getTime())/1000;
            g.drawString("�����" + period + "��", 15, 50);

        }else{
            if(end == null) {
                end = new Date();
                period = (end.getTime()-start.getTime())/1000;
            }
            g.setColor(Color.RED);
            Font font = g.getFont();
            g.setFont(new Font("΢���ź�",Font.BOLD,50));
            g.drawString("����ʱ��:"+period+"��",100,250);
            g.setFont(font);


        }
    }
    //�ر���
    public void close(){
        this.dispose();
        //this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //��ʼ������
    public void launchFrame() {
        this.setTitle("�����˾ͼ��30��");     //���ڱ���
        setVisible(true);                   //�����Ƿ�ɼ�
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);      //���ڴ�С

        setLocation(400, 500);         //����λ��

        //���ӹرմ��ڵĶ���
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
        //��ʼ������50���ڵ�����
        for (int i = 0; i < 50; i++) {
            shells[i] = new Shell();
        }
        new PaintThread().start();      //�����ػ��������߳�



        this.addKeyListener(new KeyMonitor());  //�������̼���
    }

    /**
     * ������һ���ػ����ڵ��߳���
     * �����ڲ�������ⲿ��������
     */
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();      //�ڲ������ֱ��ʹ���ⲿ��ĳ�Ա
                //����������ʾ����
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

    //������
    class MyOptionPane {
        public MyOptionPane() {
            int userOption =  JOptionPane.showConfirmDialog(null,"�Ƿ����¿�ʼ��Ϸ��","��������",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);	//ȷ�϶Ի���
            //����û�ѡ�����OK
            if (userOption == JOptionPane.OK_OPTION) {
                close();
                new MyGameFrame().launchFrame();
            }else {
                close();
            }
        }
    }

    //�ڲ��࣬ʵ�ּ��̵ļ�������
    class KeyMonitor extends KeyAdapter {
        //���÷ɻ����еļ��̿��Ʒ���

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);

        }
    }

    //˫���弼��
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
