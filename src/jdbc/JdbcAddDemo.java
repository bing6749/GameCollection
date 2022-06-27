package jdbc;

import java.sql.*;

/**
 * @author MaRui
 */
public class JdbcAddDemo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = JdbcUtils.getConnection();
        Statement sta = conn.createStatement();

        String sql = "SELECT * FROM user";
        ResultSet resultSet = sta.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getObject("id"));
            System.out.println(resultSet.getObject("username"));
            System.out.println(resultSet.getObject("password"));
        }

        JdbcUtils.release(conn,sta,resultSet);
    }
}
