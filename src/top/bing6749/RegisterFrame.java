package top.bing6749;

import dao.UserDao;
import jdbc.JdbcUtils;
import table.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {

    private JTextField userTextField; //用户名文本框
    private JPasswordField passwordField1; //密码文本框
    private JPasswordField passwordField2; //确认密码文本框
    private JButton registerButton; //注册按钮

    public RegisterFrame(){
        this.setResizable(Boolean.FALSE);
        this.setTitle("用户注册"); // 标题名称
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/游戏.png"));
        this.setBounds(100, 100, 450, 300);
        this.setLayout(null);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                RegisterFrame.this.setVisible(false);
                new LoginFrame().setVisible(true);
            }
        });

        //用户名标签
        JLabel userLabel = new JLabel("用户名");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        userLabel.setBounds(110, 60, 60, 25);
        this.add(userLabel);

        //用户名文本框
        userTextField = new JTextField();
        userTextField.setColumns(10);
        userTextField.setBounds(170, 60, 180, 25);
        this.add(userTextField);

        //密码标签
        JLabel passwordLabel1 = new JLabel("密   码：");
        passwordLabel1.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel1.setBounds(110, 100, 60, 25);
        this.add(passwordLabel1);

        //密码文本框
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(170, 100, 180, 25);
        this.add(passwordField1);

        //密码标签
        JLabel passwordLabel2 = new JLabel("确认密码：");
        passwordLabel2.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel2.setBounds(90, 140, 80, 25);
        this.add(passwordLabel2);

        //确认密码文本框
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(170, 140, 180, 25);
        this.add(passwordField2);

        //注册按钮
        registerButton = new JButton("注  册");
        registerButton.setFont(new Font("Serif", Font.PLAIN, 15));
        registerButton.setBounds(130, 200, 200, 25);
        registerButton.setForeground(Color.white);
        registerButton.setBackground(new Color(8,188,255));
        registerButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerActionPerformed(e);
            }
        });
        this.add(registerButton);

        //背景图
        JLabel imagebackground = new JLabel();
        imagebackground.setBounds(0,0,450,300);
        imagebackground.setIcon(new ImageIcon("src/images/登录背景.png"));
        this.add(imagebackground);

        //窗口居中
        this.setLocationRelativeTo(null);
    }

    private void registerActionPerformed(ActionEvent e){
        //从文本框获取密码
        String username = userTextField.getText().trim();
        //从文本框获取密码
        String password = String.valueOf(passwordField1.getPassword()).trim();
        //从文本框获取密码
        String confirmPassword = String.valueOf(passwordField2.getPassword()).trim();
        //用户名不能为空或空字符串,否则结束事件处理
        if (username == null || "".equals(username)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空","信息",2);
            userTextField.requestFocus();
            return;
        }
        //用户名不能为空或空字符串否则结束事件处理
        if (password == null || "".equals(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空","信息",2);
            passwordField1.requestFocus();
            return;
        }
        //用户名不能为空或空字符串否则结束事件处理
        if (confirmPassword == null || "".equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "确认密码不能为空","信息",2);
            passwordField2.requestFocus();
            return;
        }
        User user = new User(username, password);
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            if(UserDao.register(con, user)){
                JOptionPane.showMessageDialog(null,"注册成功","信息",1);
                RegisterFrame.this.setVisible(Boolean.FALSE);
                new LoginFrame().setVisible(Boolean.TRUE);
            }else{
                JOptionPane.showMessageDialog(null, "用户名已存在","错误",0);
                userTextField.setText("");
                passwordField1.setText("");
                passwordField2.setText("");
                userTextField.requestFocus();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(con);
        }
    }

    public static void main(String[] args) {
        new RegisterFrame().setVisible(Boolean.TRUE);
    }
}
