package com.lldw.www.utils.connectionPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author lldw
 * @date
 */
public class DbConfig {
    public static String driverName;

    public static String url;

    public static String userName;

    public static String password;
    /**
     * 空闲池，最小连接数
     */
    public static int minConnections;
    /**
     * 空闲池，最大连接数
     */
    public static int maxConnections;
    /**
     * 初始化连接数
     */
    public static int initConnections;
    /**
     * 重复获得连接的频率
     */
    public static long connTimeOut;

    /**
     * 最大允许的连接数，和数据库对应
     */
    public static int maxActiveConnections;

    /**
     * 连接超时时间，默认20分钟
     */
    public static long connectionTimeOut;


    static {
        try {
            System.out.println(">>>>开始读取connPool文件配置");
            //new对象
            Properties pro = new Properties();

            //读取配置 properties文件与src同级
            pro.load(new FileReader(new File("src/main/resources/connPool.properties")));

            driverName = pro.getProperty("driverName");
            url = pro.getProperty("url");
            userName = pro.getProperty("userName");
            password = pro.getProperty("password");
            minConnections = Integer.parseInt(pro.getProperty("minConnections"));
            maxConnections = Integer.parseInt(pro.getProperty("maxConnections"));
            initConnections = Integer.parseInt(pro.getProperty("initConnections"));
            connTimeOut = Long.parseLong(pro.getProperty("connTimeOut"));
            maxActiveConnections = Integer.parseInt(pro.getProperty("maxActiveConnections"));
            connectionTimeOut = Long.parseLong(pro.getProperty("connectionTimeOut"));
            System.out.println(">>>>读取配置文件成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
