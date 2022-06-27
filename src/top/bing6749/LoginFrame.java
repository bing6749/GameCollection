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

    private JTextField userTextField; //�û����ı���
    private JPasswordField passwordField; //�����ı���
    private JButton loginButton; //��¼��ť
    private JLabel registerLabel; //ע��
    private JLabel forgotLabel; //��������
    private JCheckBox admin;

    public LoginFrame() {
        this.setResizable(Boolean.FALSE);
        this.setTitle("�û���¼"); // ��������
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/��Ϸ.png"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 450, 300);
        this.setLayout(null);


        JLabel jLabel = new JLabel("��ӭ��¼");
        jLabel.setFont(new Font("����", Font.BOLD, 30));
        jLabel.setBounds(150, 50, 150, 40);
        this.add(jLabel);

        //�û�����ǩ
//        JLabel userLabel = new JLabel("�û�����");
        JLabel userLabel = new JLabel("");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        userLabel.setBounds(130, 100, 60, 25);
        userLabel.setIcon(new ImageIcon("src/images/�û���.png"));
        this.add(userLabel);

        //�û����ı���
        userTextField = new JTextField();
        userTextField.setColumns(10);
        userTextField.setBounds(160, 100, 180, 25);
        this.add(userTextField);

        //�����ǩ
//        JLabel passwordLabel = new JLabel("��   �룺");
        JLabel passwordLabel = new JLabel("");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel.setBounds(130, 142, 60, 25);
        passwordLabel.setIcon(new ImageIcon("src/images/����.png"));
        this.add(passwordLabel);

        //�����
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

        //��¼��ť
        loginButton = new JButton("��  ¼");
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

        //ע��
        registerLabel = new JLabel("ע���˺�>>");
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

        //��������
        forgotLabel = new JLabel("�޸�����>>");
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

        //����Ա��ѡ��
        admin = new JCheckBox("����Ա");
        admin.setFont(new Font("Serif", Font.PLAIN, 10));
        admin.setBounds(210, 175, 80, 20);
        //�������ռ�ľ�������
        admin.setContentAreaFilled(Boolean.FALSE);
        this.add(admin);

        //����ͼ
        JLabel imagebackground = new JLabel();
        imagebackground.setBounds(0, 0, 450, 300);
        imagebackground.setIcon(new ImageIcon("src/images/��¼����.png"));
        this.add(imagebackground);

        //���ô��ھ���
        this.setLocationRelativeTo(null);
    }

    private void loginActionPerformed(ActionEvent e) {
        //��������ȡ����
        String username = userTextField.getText().trim();
        //��������ȡ����
        String password = String.valueOf(passwordField.getPassword()).trim();
        if (username.equals("admin") && password.equals("12345") && admin.isSelected()){
            //����Ա������ʾ
            this.setVisible(false);
            try {
                new Admin().setVisible(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return;
        }
        //�û�������Ϊ�ջ���ַ���,��������¼�����
        if (username == null || "".equals(username)) {
            JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "��Ϣ", 2);
            userTextField.requestFocus();
            return;
        }
        //�û�������Ϊ�ջ���ַ�����������¼�����
        if (password == null || "".equals(password)) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ��", "��Ϣ", 2);
            passwordField.requestFocus();
            return;
        }
        User user = new User(username, password);
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            User currUser = UserDao.login(con, user);
            if (currUser != null) {
                JOptionPane.showMessageDialog(null, "��¼�ɹ�,��ӭ�㣬ͬѧ", "��Ϣ", 1);
                System.out.println("��¼�ɹ�");
                this.setVisible(false);
                //���������棬�����ÿɼ�

                SwingUtilities.invokeLater(() -> {
                    new GameHill().init(currUser);
                });
            } else {
                JOptionPane.showMessageDialog(null, "�û������������", "����", 0);
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
