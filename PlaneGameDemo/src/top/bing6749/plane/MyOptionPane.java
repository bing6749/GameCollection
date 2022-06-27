package top.bing6749.plane;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author MaRui
 * 游戏结束弹窗
 */
class MyOptionPane {

    public MyOptionPane() {
        int userOption =  JOptionPane.showConfirmDialog(null,"是否重新开始游戏？","提示",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE);	//确认对话框
        //如果用户选择的是OK
        if (userOption == JOptionPane.OK_OPTION) {

        }else {
            System.out.println("否");
        }
    }
}
