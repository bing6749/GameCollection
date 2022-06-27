package top.bing6749.plane;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author MaRui
 */
public class Plane extends GameObject {

    boolean left,right,up,down;     //飞机的方向控制

    boolean live =true;      //飞机存活状态
    @Override
    public void drawMyself(Graphics g) {

        if(live){
            super.drawMyself(g);

            //控制飞机的方向
            //飞机飞行的算法，可以自行设定
            if(left){
                x -= speed;
            }if(right){
                x += speed;
            }if(up){
                y -= speed;
            }if(down){
                y += speed;
            }
        }

    }

    //按上下左右飞机的移动
    public void addDirection(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            left = true;
        }if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right = true;
        }if(e.getKeyCode() == KeyEvent.VK_UP){
            up = true;
        }if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = true;
        }
    }

    //抬起上下左右飞机的静止
    public void minusDirection(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
    }

    public Plane(Image img, double x, double y, int speed) {
        super(img, x, y, speed);
    }
}
