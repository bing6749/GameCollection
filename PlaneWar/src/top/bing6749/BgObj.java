package top.bing6749;

import java.awt.*;

/**
 * 背景的实体类
 */
public class BgObj extends GameObject {

    //得分
    int score = 0;

    public BgObj() {
        super();
    }

    public BgObj(String img, GameWin frame) {
        super(img, frame);
    }

    public BgObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //控制背景图循环
        if (y >= 0){
            y = -2000;
        }
        //背景图移动
        y += speed;

        //计分面板的编写
        //改变字体颜色
        g.setColor(Color.green);
        //修改字体
        g.setFont(new Font("仿宋",Font.BOLD,40));
        //写到窗口
        g.drawString(score + "分",30,100);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
