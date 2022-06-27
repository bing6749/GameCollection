package top.bing6749;

import java.awt.*;

/**
 * 敌方boss
 */
public class BossObj extends GameObject{

    //boss生命值
    int life = 10;

    public BossObj() {
        super();
    }

    public BossObj(String img, GameWin frame) {
        super(img, frame);
    }

    public BossObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        //控制boss运行方向
        if (x >= 500){
            speed = -5;
        }
        if (x <= 0){
            speed = 5;
        }
        x += speed;
        //boss与我方子弹进行碰撞检测
        for (ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){
                //我方子弹打中boss
                shellObj.x = -100;
                shellObj.y = 100;
                this.frame.removeList.add(shellObj);
                life--;
            }
            if (life <= 0){
                //boss阵亡,游戏胜利
                this.frame.explode_x = x -11;
                this.frame.explode_y = y -16;
                this.frame.state = 4;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,80,76);
    }
}
