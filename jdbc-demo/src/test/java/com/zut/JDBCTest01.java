package com.zut;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.*;

public class JDBCTest01 {
    @Test
    public void testUpdate() throws Exception{
        //1.准备工作
        //注册驱动 --> 链接对象  ---->创建链接对象----》执行sql
        Class.forName("com.mysql.cj.jdbc.Driver");

       Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zut01","root","123456");

        Statement statement = connection.createStatement();
      //2.执行sql
        statement.executeUpdate("update user set password= '666666' where id  =1");
     //释放资源  断开链接对象  链接关闭
        statement.close();
        connection.close();

    }



    @ParameterizedTest
    @CsvSource("daqiao,666666")
    public void testSelect(String _username,String _password) throws Exception{
        //1.准备工作
        //注册驱动 --> 链接对象  ---->创建链接对象----》执行sql
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zut01","root","123456");

        Statement statement = connection.createStatement();
        //2.执行sql
        //获取查询结果
        ResultSet resultSet = statement.executeQuery("select * from user where username='"+_username+"' and password='"+_password+"'");
        while (resultSet.next()){//一行一行走 有数据
            int id= resultSet.getInt("id");
            String  username =resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            User user = new User(id,username,password,name,age);
            System.out.println(user);
        }
        //释放资源  断开链接对象  链接关闭
        statement.close();
        connection.close();

    }


    @ParameterizedTest
    @CsvSource("daqiao,666666")
    public void testPreparedStatement(String _username,String _password) throws Exception{
        //1.准备工作
        //注册驱动 --> 链接对象  ---->创建链接对象----》执行sql
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zut01","root","123456");

        PreparedStatement ps = connection.prepareStatement("select * from user where username = ? and password=?");

        ps.setString(1,_username);
        ps.setString(2,_password);

        //2.执行sql
        //获取查询结果
        //获取查询结果
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){//一行一行走 有数据
            int id= resultSet.getInt("id");
            String  username =resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            User user = new User(id,username,password,name,age);
            System.out.println(user);
        }
        //释放资源  断开链接对象  链接关闭
        ps.close();
        connection.close();

    }

}
