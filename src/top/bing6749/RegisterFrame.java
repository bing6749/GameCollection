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

    private JTextField userTextField; //�û����ı���
    private JPasswordField passwordField1; //�����ı���
    private JPasswordField passwordField2; //ȷ�������ı���
    private JButton registerButton; //ע�ᰴť

    public RegisterFrame(){
        this.setResizable(Boolean.FALSE);
        this.setTitle("�û�ע��"); // ��������
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/��Ϸ.png"));
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

        //�û�����ǩ
        JLabel userLabel = new JLabel("�û���");
        userLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        userLabel.setBounds(110, 60, 60, 25);
        this.add(userLabel);

        //�û����ı���
        userTextField = new JTextField();
        userTextField.setColumns(10);
        userTextField.setBounds(170, 60, 180, 25);
        this.add(userTextField);

        //�����ǩ
        JLabel passwordLabel1 = new JLabel("��   �룺");
        passwordLabel1.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel1.setBounds(110, 100, 60, 25);
        this.add(passwordLabel1);

        //�����ı���
        passwordField1 = new JPasswordField();
        passwordField1.setBounds(170, 100, 180, 25);
        this.add(passwordField1);

        //�����ǩ
        JLabel passwordLabel2 = new JLabel("ȷ�����룺");
        passwordLabel2.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel2.setBounds(90, 140, 80, 25);
        this.add(passwordLabel2);

        //ȷ�������ı���
        passwordField2 = new JPasswordField();
        passwordField2.setBounds(170, 140, 180, 25);
        this.add(passwordField2);

        //ע�ᰴť
        registerButton = new JButton("ע  ��");
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

        //����ͼ
        JLabel imagebackground = new JLabel();
        imagebackground.setBounds(0,0,450,300);
        imagebackground.setIcon(new ImageIcon("src/images/��¼����.png"));
        this.add(imagebackground);

        //���ھ���
        this.setLocationRelativeTo(null);
    }

    private void registerActionPerformed(ActionEvent e){
        //���ı����ȡ����
        String username = userTextField.getText().trim();
        //���ı����ȡ����
        String password = String.valueOf(passwordField1.getPassword()).trim();
        //���ı����ȡ����
        String confirmPassword = String.valueOf(passwordField2.getPassword()).trim();
        //�û�������Ϊ�ջ���ַ���,��������¼�����
        if (username == null || "".equals(username)) {
            JOptionPane.showMessageDialog(null, "�û�������Ϊ��","��Ϣ",2);
            userTextField.requestFocus();
            return;
        }
        //�û�������Ϊ�ջ���ַ�����������¼�����
        if (password == null || "".equals(password)) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ��","��Ϣ",2);
            passwordField1.requestFocus();
            return;
        }
        //�û�������Ϊ�ջ���ַ�����������¼�����
        if (confirmPassword == null || "".equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "ȷ�����벻��Ϊ��","��Ϣ",2);
            passwordField2.requestFocus();
            return;
        }
        User user = new User(username, password);
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            if(UserDao.register(con, user)){
                JOptionPane.showMessageDialog(null,"ע��ɹ�","��Ϣ",1);
                RegisterFrame.this.setVisible(Boolean.FALSE);
                new LoginFrame().setVisible(Boolean.TRUE);
            }else{
                JOptionPane.showMessageDialog(null, "�û����Ѵ���","����",0);
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
