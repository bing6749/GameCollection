package top.bing6749;

import java.awt.*;

/**
 * 我方炮弹
 */
public class ShellObj extends GameObject {

    public ShellObj() {
        super();
    }

    public ShellObj(String img, GameWin frame) {
        super(img, frame);
    }

    public ShellObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //子弹移动
        y -= speed;
        //我方子弹越界消失,消失位置为-100,100
        if (y <= 0){
            this.x = -100;
            this.y = 100;
            this.frame.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,15,15);
    }
}
