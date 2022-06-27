package dao;

import table.User;

import java.sql.*;

public class UserDao {
    public static User login(Connection con,User user) throws SQLException {
        //定义一个返回用户对象
        User resultUser=null;
        //拼写sql查询语句
        String sql ="select * from user where username = ? and password = ?";
        //获取sql语句预编译对象
        PreparedStatement ps = con.prepareStatement(sql);
        //设置ps参数
        ps.setString(1,user.getUsername());
        ps.setString(2, user.getPassword());
        //ps执行sql查询语句返回结果集
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            //初始化返回用户对象
            resultUser = new User();
            resultUser.setId(rs.getInt("id"));  //设置用户id
            resultUser.setUsername(rs.getString("username"));  //设置用户名
            resultUser.setPassword(rs.getString("password"));  //设置密码
        }
        //返回用户对象
        return resultUser;
    }

    public static Boolean register(Connection con,User user) throws SQLException {
        //拼写sql查询语句
        String sqlselect ="select * from user";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlselect);
       if (new UserDao().isSame(rs,user)){
           String sql = "insert into user(username,password) values(?,?)";
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, user.getUsername());
           ps.setString(2, user.getPassword());
           int count = ps.executeUpdate();
           if (count>0){
               return true;
           }
       }
        return false;
    }

    private Boolean isSame(ResultSet rs,User user) throws SQLException {
        while (rs.next()) {
            String username = user.getUsername();
            if(rs.getString(2).equals(username)) {
                return false;
            }
        }
        return true;
    }



}
