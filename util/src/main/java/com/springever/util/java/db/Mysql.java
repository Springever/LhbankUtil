package com.springever.util.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Mysql {

    public Object connectMysql(){
        String url = "jdbc:mysql://127.0.0.1/student";
        String name = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "root";
        PreparedStatement pst = null;
        Connection conn = null;
        String sql = "select * from test";
        Object localObject1 = null;
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            conn.setAutoCommit(true);
            pst = conn.prepareStatement(sql);//准备执行语句
            ResultSet rs = pst.executeQuery();
            if (rs.next())
            {
                localObject1 = rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localObject1;
    }
}
