package top.bing6749;

import java.awt.*;

/**
 * ������ʵ����
 */
public class BgObj extends GameObject {

    //�÷�
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
        //���Ʊ���ͼѭ��
        if (y >= 0){
            y = -2000;
        }
        //����ͼ�ƶ�
        y += speed;

        //�Ʒ����ı�д
        //�ı�������ɫ
        g.setColor(Color.green);
        //�޸�����
        g.setFont(new Font("����",Font.BOLD,40));
        //д������
        g.drawString(score + "��",30,100);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
}
