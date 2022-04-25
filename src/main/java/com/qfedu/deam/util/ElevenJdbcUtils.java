package com.qfedu.deam.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @ClassName JdbcUtils
 * @Description TODO 连接MySQL 的工具类
 * @Author ELeven
 * @Date 2022年4月22日 16点15分
 * @Version 1.0.1
 **/
public class ElevenJdbcUtils {
    private static DataSource dataSource;

    static {
        try {

            //读取配置文件到输入流
            FileInputStream in = new FileInputStream("jdbc.properties");

            Properties properties = new Properties();
            //加载配置文件中的数据到Properties中
            properties.load(in);
            //创建DataSource
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 从连接池内获取连接
     * @return 新的连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
    /**
     * 从连接池内获取连接
     * @return 新的连接
     */
    public static Statement getStatement(String sql) {
        PreparedStatement statement = null;
        try {
            statement = dataSource.getConnection().prepareStatement(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return statement;
    }
    public static void close(Connection connection, Statement statement){
        close(connection,statement,null);
    }
    public static void close(Statement statement){
        close(statement,null);
    }
    public static void close(Statement statement,ResultSet rSet){

        try {
            if(statement != null) {
                close(statement.getConnection(),statement,rSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放资源
     * @param connection 关闭连接
     * @param statement 关闭的声明
     * @param rSet 关闭的结果集
     */
    public static void close(Connection connection, Statement statement, ResultSet rSet) {
        try {
            if(rSet != null) {
                rSet.close();
            }

            if(statement != null) {
                statement.close();
            }

            if(connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
