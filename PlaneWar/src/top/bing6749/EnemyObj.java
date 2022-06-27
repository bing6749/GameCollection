package top.bing6749;

import javax.swing.*;
import java.awt.*;

/**
 * �з�ս����
 */
public class EnemyObj extends GameObject{
    //�з�ս��������ʱx����
    int x = (int)(Math.random()*12) * 50;
    //ս�����ƶ��ٶ�
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

    //�رյ�����
    class MyOptionPane {
        public MyOptionPane(JFrame jframe) {
            int userOption =  JOptionPane.showConfirmDialog(null,"��������");	//ȷ�϶Ի���
            jframe.dispose();
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        y += speed;
        //Խ��ĵз��ɻ�ɾ��
        if (y > 600){
            //�з��ɻ��ı����� -150 150
            this.x = -150;
            this.y = 150;
            this.frame.removeList.add(this);
        }
        //�з��ɻ����ҷŷɻ���ײ,��Ϸ����
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            this.frame.state = 3;
            this.frame.explode_x = this.frame.planeObj.x -11;
            this.frame.explode_y = this.frame.planeObj.y -16;
            new MyOptionPane(this.frame);
        }
        //��ÿһ���ҷ��ڵ�������ײ���
        for(ShellObj shellObj:this.frame.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){

                ExplodeObj explodeObj = new ExplodeObj(this.x,this.y);
                //��ӱ�ըЧ��ͼ
                this.frame.explodeObjList.add(explodeObj);
                //ɾ����ըЧ��ͼ
                this.frame.removeList.add(explodeObj);
                //�ҷ��ӵ�ɾ��ǰ�ı�����Ϊ-100 100   �з��ɻ��ı����� -150 150
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
