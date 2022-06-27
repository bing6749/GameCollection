package top.bing6749.plane;

import java.awt.*;

/**
 * @author MaRui
 */
public class Shell extends GameObject {

    double degree;   //角度，炮弹沿着指定的角度飞行

    public Shell(){
        x = 200;
        y =200;

        degree = Math.random()*Math.PI * 2;

        width = 5;
        height = 5;

        speed = 3;
    }

    @Override
    public void drawMyself(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int)x,(int)y,width,height);
        g.setColor(c);

        //根据自己的算法指定移动的路径
        x += speed *Math.cos(degree);
        y += speed *Math.sin(degree);

        //碰到边界改变方向
        if(y>Constant.GAME_HEIGHT-20 || y<40){
            degree = - degree;
        }
        if(x>Constant.GAME_WIDTH-20 || x<10){
            degree = Math.PI-degree;
        }
    }
}
