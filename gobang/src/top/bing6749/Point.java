package top.bing6749;

import java.awt.*;

public class Point {
    private int x;//�����������е�x����ֵ
    private int y;//�����������е�y����ֵ
    private Color color;//��ɫ
    public static int DIAMETER=30;//ֱ��
    public Point(int x,int y,Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    //�õ������������е�x����ֵ
    public int getX(){
        return x;
    }
    //�õ������������е�y����ֵ
    public int getY(){
        return y;
    }
    //�õ�������ɫ
    public Color getColor(){
        return color;
    }
}

