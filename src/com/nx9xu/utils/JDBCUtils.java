package com.nx9xu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.JdbcUtils;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DruidDataSource dataSource; //数据库连接池

    static {
        Properties properties = new Properties();
        try {
            // 读取jdbc.properties
//            System.out.println("获取JdbcUtils类的class对象的绝对路径: " + JdbcUtils.class.getClassLoader().getResource("/").getPath()); // 获取JdbcUtils类的class对象的绝对路径

//            FileInputStream fileInputStream = new FileInputStream("src/jdbc.properties"); // 报500错误找不到 "jdbc.properties"

            // 以下两种方式二选一
//            FileInputStream fileInputStream = new FileInputStream("D:\\IdeaProjects\\Book-atguigu\\src\\jdbc.properties"); //改成绝对路径，这样就不报错了，但是感觉部署到其他机器上时会有路径相关的问题
            InputStream fileInputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"); // 推荐使用这种方式

            // 从流中加载数据
            properties.load(fileInputStream);
            // 创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 获取数据库的连接
     *
     * @return 如果返回 null 说明获取连接失败
     */
    public static Connection getConnection() {

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    /**
     * 关闭连接，放回到数据库连接池
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
