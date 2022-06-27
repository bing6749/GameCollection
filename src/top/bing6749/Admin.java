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
    //�������
    JButton jb1, jb2;
    //rowData�������������
    //columnNames�������
    JTable jt = new JTable();
    JScrollPane jsp = null;
    //�������ݿ���Ҫȫ�ֱ���
    Statement st = null;
    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;
    DefaultTableModel model = null;


    public static void main(String[] args) throws SQLException {
        new Admin().setVisible(Boolean.TRUE);

    }

    public Admin() throws SQLException {

        this.setTitle("����Ա����");
        this.setLayout(null);

        jb1 = new JButton("�޸�");
        jb2 = new JButton("ɾ��");

        jb1.addActionListener(e -> {
            Object username = null;
            int selectedRow = jt.getSelectedRow();
            int selectedColumn = jt.getSelectedColumn();
            username = jt.getValueAt(selectedRow, 0);
            System.out.println("ѡ����ǵ�" + (selectedRow + 1) + "�У���" + (selectedColumn + 1) + "��");
            String sql = "update user set password = ? where username = ?";
            try {
                ps = ct.prepareStatement(sql);
                String input = JOptionPane.showInputDialog(null, "�����޸ĺ������\n", "��ʾ", JOptionPane.PLAIN_MESSAGE);
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

        //ɾ���û�
        jb2.addActionListener(e ->

        {
            int selectedRow = jt.getSelectedRow();
            int selectedColumn = jt.getSelectedColumn();
            Object username = jt.getValueAt(selectedRow, 0);
            System.out.println("ѡ����ǵ�" + (selectedRow + 1) + "�У���" + (selectedColumn + 1) + "��");
            System.out.println("ѡ����ǣ�" + username);
            String sql = "delete from user where username = ?";
            try {
                ct = JdbcUtils.getConnection();
                ps = ct.prepareStatement(sql);
                if (JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ��ɾ��", "��ʾ", JOptionPane.YES_NO_OPTION) == 0) {
                    ps.setString(1, (String) username);
                    int count = ps.executeUpdate();
                    if (count == 1) {
                        JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��Ϣ", 1);
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


        //rowData���Դ�Ŷ��У���ʼ�����ݿ���ȡ
        loading();

        jt = new JTable(model);
        //��ʼ��jsp
        jsp = new JScrollPane(jt);
        this.add(jsp);

        //��jsp���뵽jframe
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
        String colFire[] = {"�û���", "����"};
        String sql = "select * from user";

        try {
            ct = JdbcUtils.getConnection();
            st = ct.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
            rs.last();
            // �����α�ָ����к�
            int row = rs.getRow();
            Object[][] rowDate = new Object[row][2];
            rs.beforeFirst();

            rs.next();
            for (int i = 0; i < row; i++) {
                rowDate[i][0] = rs.getString("username");
                rowDate[i][1] = rs.getString("password");
                rs.next();
            }
            //��ʼ��Jtable
            model = new DefaultTableModel();
            model.setDataVector(rowDate, colFire);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

