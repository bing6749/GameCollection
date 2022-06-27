package top.bing6749;

import jdbc.JdbcUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.HashMap;
import java.util.Vector;

public class Admin extends JFrame {
    //定义组件
    JButton jb1, jb2;
    //rowData用来存放行数据
    //columnNames存放列名
    JTable jt = new JTable();
    JScrollPane jsp = null;
    //定义数据库需要全局变量
    Statement st = null;
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;
    DefaultTableModel model = null;


    public static void main(String[] args) throws SQLException {
        new Admin().setVisible(Boolean.TRUE);

    }

    public Admin() throws SQLException {

        this.setTitle("管理员界面");
        this.setLayout(null);

        jb1 = new JButton("修改");
        jb2 = new JButton("删除");

        jb1.addActionListener(e -> {
            Object username = null;
            int selectedRow = jt.getSelectedRow();
            int selectedColumn = jt.getSelectedColumn();
            username = jt.getValueAt(selectedRow, 0);
            System.out.println("选择的是第" + (selectedRow + 1) + "行，第" + (selectedColumn + 1) + "列");
            String sql = "update user set password = ? where username = ?";
            try {
                ps = ct.prepareStatement(sql);
                String input = JOptionPane.showInputDialog(null, "输入修改后的密码\n", "提示", JOptionPane.PLAIN_MESSAGE);
                if (input != null) {
                    System.out.println(input);
                    ps.setString(1, input);
                    ps.setString(2, (String) username);
                    ps.executeUpdate();
                    this.loading();
                    jt.setModel(model);
//                    jsp = new JScrollPane(jt);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        //删除用户
        jb2.addActionListener(e ->

        {
            int selectedRow = jt.getSelectedRow();
            int selectedColumn = jt.getSelectedColumn();
            Object username = jt.getValueAt(selectedRow, 0);
            System.out.println("选择的是第" + (selectedRow + 1) + "行，第" + (selectedColumn + 1) + "列");
            System.out.println("选择的是：" + username);
            String sql = "delete from user where username = ?";
            try {
                ct = JdbcUtils.getConnection();
                ps = ct.prepareStatement(sql);
                if (JOptionPane.showConfirmDialog(null, "是否确认删除", "提示", JOptionPane.YES_NO_OPTION) == 0) {
                    ps.setString(1, (String) username);
                    int count = ps.executeUpdate();
                    if (count == 1) {
                        JOptionPane.showMessageDialog(null, "删除成功", "信息", 1);
                        DefaultTableModel model = (DefaultTableModel) jt.getModel();
                        model.removeRow(jt.getSelectedRow());
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        this.setBounds(0, 0, 450, 300);
        jb1.setBounds(300, 50, 80, 30);
        jb2.setBounds(300, 100, 80, 30);

        this.add(jb1);
        this.add(jb2);


        //rowData可以存放多行，开始从数据库里取
        loading();

        jt = new JTable(model);
        //初始化jsp
        jsp = new JScrollPane(jt);
        this.add(jsp);

        //把jsp放入到jframe
        jsp.setBounds(0, 20, 300, 200);
        jsp.setVisible(true);


//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Admin.this.setVisible(false);
                new LoginFrame().setVisible(true);
            }
        });
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    public void loading() {
        String colFire[] = {"用户名", "密码"};
        String sql = "select * from user";

        try {
            ct = JdbcUtils.getConnection();
            st = ct.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
            rs.last();
            // 返回游标指向的行号
            int row = rs.getRow();
            Object[][] rowDate = new Object[row][2];
            rs.beforeFirst();

            rs.next();
            for (int i = 0; i < row; i++) {
                rowDate[i][0] = rs.getString("username");
                rowDate[i][1] = rs.getString("password");
                rs.next();
            }
            //初始化Jtable
            model = new DefaultTableModel();
            model.setDataVector(rowDate, colFire);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

