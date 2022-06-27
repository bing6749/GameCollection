package com.snake;

import javax.swing.*;
import java.net.URL;

// 数据类
public class Data {
    // snake-swing 是项目名称, 这些图片资源在项目的 src/static 这个目录下
    // 获取四个头, 相对路径, 相对于src获取这些资源
    public static URL rightURL = Data.class.getResource("/static/right.png");
    public static URL leftURL = Data.class.getResource("/static/left.png");
    public static URL upURL = Data.class.getResource("/static/up.png");
    public static URL downURL = Data.class.getResource("/static/down.png");
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);

    // 获取身体
    public static URL bodyURL = Data.class.getResource("/static/body.png");
    public static ImageIcon body = new ImageIcon(bodyURL);

    // 获取食物
    public static URL foodURL = Data.class.getResource("/static/food.png");
    public static ImageIcon food = new ImageIcon(foodURL);
}
