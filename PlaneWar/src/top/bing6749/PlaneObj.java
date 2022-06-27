package top.bing6749;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 我方战斗机
 */
public class PlaneObj extends GameObject{
    public PlaneObj() {
        super();
    }

    public PlaneObj(String img, GameWin frame) {
        super(img, frame);
    }

    public PlaneObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    public PlaneObj(String img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //添加鼠标事件,飞机跟随鼠标移动
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                //飞机坐标为鼠标坐标
                x = e.getX() - 11;
                y = e.getY() - 16;
            }
        });
        //我方飞机与敌方Boss的碰撞检测
        if (this.frame.bossObj != null){
            if (this.getRec().intersects(this.frame.bossObj.getRec())){
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 3;
            }
        }

        //我方飞机与敌方子弹的碰撞检测
        for (BulletObj bulletObj:this.frame.bulletObjList){
            if (this.getRec().intersects(bulletObj.getRec())){
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 3;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,20,30);
    }
}
