package com.lldw.www.utils.connectionPool;

/**
 * @author lldw
 * @date
 */
public class DbConfig {
    public static final String driverName = "com.mysql.cj.jdbc.Driver";

    public static final String url = "jdbc:mysql:///db1?useUnicode=true&characterEncoding=UTF-8&userSSL=false&serverTimezone=GMT%2B8&useServerPrepStmts=true";

    public static final String userName = "root";

    public static final String password = "1234";
    /**
     * 空闲池，最小连接数
     */
    public static final int minConnections = 1;
    /**
     * 空闲池，最大连接数
     */
    public static final int maxConnections = 50;
    /**
     * 初始化连接数
     */
    public static final int initConnections = 5;
    /**
     * 重复获得连接的频率
     */
    public static final long connTimeOut = 1000;

    /**
     * 最大允许的连接数，和数据库对应
     */
    public static final int maxActiveConnections = 100;

    /**
     * 连接超时时间，默认20分钟
     */
    public static final long connectionTimeOut = 1000 * 60 * 20;
}
