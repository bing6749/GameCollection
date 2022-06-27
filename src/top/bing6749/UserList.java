package top.bing6749;

import jdbc.JdbcUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MaRui
 */
public class UserList extends JFrame {
    private static HashMap<String,String> user;

    public static Map<String,String> getUserList() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        Statement sta = conn.createStatement();

        String sql = "SELECT * FROM user";
        ResultSet resultSet = sta.executeQuery(sql);
        while(resultSet.next()){
            user.put((String)resultSet.getObject("id"),(String)resultSet.getObject("password"));
        }

        JdbcUtils.release(conn,sta,resultSet);
        return user;
    }
    public UserList() throws SQLException {
        getUserList();
    }
}
