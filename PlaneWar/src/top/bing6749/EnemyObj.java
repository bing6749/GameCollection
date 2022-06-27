package top.bing6749;

import javax.swing.*;
import java.awt.*;

/**
 * 敌方战斗机
 */
public class EnemyObj extends GameObject{
    //敌方战斗机生成时x坐标
    int x = (int)(Math.random()*12) * 50;
    //战斗机移动速度
    int speed = 5;
    public EnemyObj() {
        super();
    }

    public EnemyObj(String img, GameWin frame) {
        super(img, frame);
    }

    public EnemyObj(String img, int x, int y, double speed, GameWin frame) {
        super(img, x, y, speed, frame);
    }

    //关闭弹窗类
    class MyOptionPane {
        public MyOptionPane(JFrame jframe) {
            int userOption =  JOptionPane.showConfirmDialog(null,"您已死亡");	//确认对话框
            jframe.dispose();
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        y += speed;
        //越界的敌方飞机删除
        if (y > 600){
            //敌方飞机改变坐标 -150 150
            this.x = -150;
            this.y = 150;
            this.frame.removeList.add(this);
        }
        //敌方飞机与我放飞机碰撞,游戏结束
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            this.frame.state = 3;
            this.frame.explode_x = this.frame.planeObj.x -11;
            this.frame.explode_y = this.frame.planeObj.y -16;
            new MyOptionPane(this.frame);
        }
        //与每一发我方炮弹进行碰撞检测
        for(ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){

                ExplodeObj explodeObj = new ExplodeObj(this.x,this.y);
                //添加爆炸效果图
                this.frame.explodeObjList.add(explodeObj);
                //删除爆炸效果图
                this.frame.removeList.add(explodeObj);
                //我方子弹删除前改变坐标为-100 100   敌方飞机改变坐标 -150 150
                shellObj.x = -100;
                shellObj.y = 100;
                this.x = -150;
                this.y = 150;
                this.frame.removeList.add(shellObj);
                this.frame.removeList.add(this);
                this.frame.bgObj.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x,y,46,35);
    }
}
