package cn.net.astoria.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.support.spring.stat.annotation.Stat;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DruidUtils
 * @Description 数据库连接池的工具类，可以获取数据库连接对象和DataSource对象
 * @Author Astoria
 * @Date 2020/3/1 17:37
 * @Version 1.0
 */
public class DruidUtils {

    /**
     * 数据源
     */
    public static DataSource dataSource;


    /**
     * 静态初始化块，初始化数据源
     */
    static {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }


    /**
     * 用于释放数据库资源
     * @param resultSet
     * @param preparedStatement
     * @param connection
     */
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放数据库相关资源
     * @param preparedStatement
     * @param connection
     */
    public static void close(PreparedStatement preparedStatement,Connection connection){
        DruidUtils.close(null,preparedStatement,connection);
    }

}
