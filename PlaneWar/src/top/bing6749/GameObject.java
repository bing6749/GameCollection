package top.bing6749;

import java.awt.*;

public abstract class GameObject {

    Image img;
    //游戏元素的横坐标
    int x;
    //游戏元素的纵坐标
    int y;
    //游戏元素的宽
    int width;
    //游戏元素的高
    int height;
    //速度
    double speed;
    //引入主界面
    GameWin frame;

    public GameObject(){

    }

    public GameObject(String img, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.frame = frame;
    }

    public GameObject(String img, int x, int y, double speed, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.frame = frame;
    }

    public GameObject(String img, int x, int y, int width, int height, double speed, GameWin frame) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.frame = frame;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }
    //继承元素绘制自己的方法
    public abstract void paintSelf(Graphics g);

    //获取当前游戏元素的矩形,是为碰撞检测而写
    public abstract Rectangle getRec();
}
