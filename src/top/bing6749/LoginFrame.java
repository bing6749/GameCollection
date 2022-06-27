package top.bing6749;

import dao.UserDao;
import jdbc.JdbcUtils;
import table.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JTextField userTextField; //用户名文本框
    private JPasswordField passwordField; //密码文本框
    private JButton loginButton; //登录按钮
    private JLabel registerLabel; //注册
    private JLabel forgotLabel; //忘记密码
    private JCheckBox admin;

    public LoginFrame() {
        this.setResizable(Boolean.FALSE);
        this.setTitle("用户登录"); // 标题名称
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/游戏.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 450, 300);
        this.setLayout(null);


        JLabel jLabel = new JLabel("欢迎登录");
        jLabel.setFont(new Font("宋体", Font.BOLD, 30));
        jLabel.setBounds(150, 50, 150, 40);
        this.add(jLabel);

        //用户名标签
//        JLabel userLabel = new JLabel("用户名：");
        JLabel userLabel = new JLabel("");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        userLabel.setBounds(130, 100, 60, 25);
        userLabel.setIcon(new ImageIcon("src/images/用户名.png"));
        this.add(userLabel);

        //用户名文本框
        userTextField = new JTextField();
        userTextField.setColumns(10);
        userTextField.setBounds(160, 100, 180, 25);
        this.add(userTextField);

        //密码标签
//        JLabel passwordLabel = new JLabel("密   码：");
        JLabel passwordLabel = new JLabel("");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel.setBounds(130, 142, 60, 25);
        passwordLabel.setIcon(new ImageIcon("src/images/密码.png"));
        this.add(passwordLabel);

        //密码框
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 140, 180, 25);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER){
                    loginButton.doClick();
                }
            }
        });
        this.add(passwordField);

        //登录按钮
        loginButton = new JButton("登  录");
        loginButton.setFont(new Font("Serif", Font.PLAIN, 15));
        loginButton.setBounds(150, 200, 180, 25);
        loginButton.setForeground(Color.white);
        loginButton.setBackground(new Color(8, 188, 255));
        loginButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        this.add(loginButton);

        //注册
        registerLabel = new JLabel("注册账号>>");
        registerLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        registerLabel.setBounds(130, 175, 60, 20);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                registerLabel.setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                registerLabel.setForeground(Color.black);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                LoginFrame.this.setVisible(Boolean.FALSE);
                new RegisterFrame().setVisible(Boolean.TRUE);
            }
        });
        this.add(registerLabel);

        //忘记密码
        forgotLabel = new JLabel("修改密码>>");
        forgotLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        forgotLabel.setBounds(290, 175, 60, 20);
        forgotLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                forgotLabel.setForeground(Color.yellow);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                forgotLabel.setForeground(Color.black);
            }

        });
        this.add(forgotLabel);

        //管理员复选框
        admin = new JCheckBox("管理员");
        admin.setFont(new Font("Serif", Font.PLAIN, 10));
        admin.setBounds(210, 175, 80, 20);
        //不填充所占的矩形区域
        admin.setContentAreaFilled(Boolean.FALSE);
        this.add(admin);

        //背景图
        JLabel imagebackground = new JLabel();
        imagebackground.setBounds(0, 0, 450, 300);
        imagebackground.setIcon(new ImageIcon("src/images/登录背景.png"));
        this.add(imagebackground);

        //设置窗口居中
        this.setLocationRelativeTo(null);
    }

    private void loginActionPerformed(ActionEvent e) {
        //从输入框获取密码
        String username = userTextField.getText().trim();
        //从输入框获取密码
        String password = String.valueOf(passwordField.getPassword()).trim();
        if (username.equals("admin") && password.equals("12345") && admin.isSelected()){
            //管理员界面显示
            this.setVisible(false);
            try {
                new Admin().setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return;
        }
        //用户名不能为空或空字符串,否则结束事件处理
        if (username == null || "".equals(username)) {
            JOptionPane.showMessageDialog(null, "用户名不能为空", "信息", 2);
            userTextField.requestFocus();
            return;
        }
        //用户名不能为空或空字符串否则结束事件处理
        if (password == null || "".equals(password)) {
            JOptionPane.showMessageDialog(null, "密码不能为空", "信息", 2);
            passwordField.requestFocus();
            return;
        }
        User user = new User(username, password);
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            User currUser = UserDao.login(con, user);
            if (currUser != null) {
                JOptionPane.showMessageDialog(null, "登录成功,欢迎你，同学", "信息", 1);
                System.out.println("登录成功");
                this.setVisible(false);
                //进入主界面，并设置可见

                SwingUtilities.invokeLater(() -> {
                    new GameHill().init(currUser);
                });
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误", "错误", 0);
                userTextField.setText("");
                passwordField.setText("");
                userTextField.requestFocus();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(con);
        }


    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(Boolean.TRUE);
    }
}
