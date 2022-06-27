package top.bing6749.plane;

import java.awt.*;

/**
 * @author MaRui
 * 游戏物体(子弹、飞机)的根类
 */
public class GameObject {
    public GameObject() {
    }

    //图片
    Image img;

    //物体的XY位置
    double x,y;

    //物体移动的速度
    int speed;

    //物体的宽度和高度
    int width,height;

    public GameObject(Image img, double x, double y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;

        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }



    public void drawMyself(Graphics g){
        g.drawImage(img,(int)x,(int)y,width,height,null);
    }

    //获得物体的属性，用于后续的碰撞检测
    public Rectangle getRec(){
        return new Rectangle((int)x,(int)y,width,height);
    }


}
