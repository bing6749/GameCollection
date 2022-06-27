package top.bing6749;

import java.awt.*;

/**
 * 爆炸实体类
 */
public class ExplodeObj extends GameObject {

    static Image[] imgs = new Image[16];

    int explodeCount = 0;

    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = Toolkit.getDefaultToolkit().getImage("src/images/explode/e" +(i + 1)+".gif");
        }
    }

    public ExplodeObj() {
        super();
    }

    public ExplodeObj(int x, int y) {
       this.x = x;
       this.y = y;
    }

    @Override
    public void paintSelf(Graphics g) {
        //绘制点击爆炸效果
        if (explodeCount < 16){
            g.drawImage(imgs[explodeCount],x,y,null);
            explodeCount++;
        }
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
