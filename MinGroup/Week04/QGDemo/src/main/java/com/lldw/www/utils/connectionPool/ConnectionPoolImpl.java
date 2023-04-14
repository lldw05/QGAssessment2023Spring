package com.lldw.www.utils.connectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 穿梭时间的画面的钟 从反方向开始移动
 * @author
 * @date
 */
public class ConnectionPoolImpl implements ConnectionPool{

    static {
        freeConnection = new Vector<>();
        //自动初始化
        init();
    }

    /**
     * 空闲连接容器
     */
//    private static List<Connection> freeConnection = new Vector<>();
    private static List<Connection> freeConnection;
    /**
     * 活动连接容器
     */
    private List<Connection> activeConnection = new Vector<>();

    /**
     * 当前连接数
     */
    private AtomicInteger countConn = new AtomicInteger(0);



    /**
     * 单例模式 一个pool 私有化构造器 通过getConnectionPool()方法获取
     */
    public static ConnectionPoolImpl connectionPool ;

    public static ConnectionPoolImpl getInstance(){
        if(connectionPool==null){
            connectionPool = new ConnectionPoolImpl();
        }
        return connectionPool;
    }
    private ConnectionPoolImpl() {
    }


    /**
     * 吃屎化
     *  初始化几个conn 放入freeConnection容器中
     */
    private static void init(){
        if(DbConfig.initConnections>0){
            for (int i = 0; i < DbConfig.initConnections; i++) {
                //生成conn 放入freeConn容器中
                Connection connection = newConnection();
                //conn不为空
                addConnToFree(connection);
            }
        }
    }

    /**
     * newConnection 放入freeConnection容器中
     * @param connection 将传入的conn房租freeConn容器中
     */
    private static void addConnToFree(Connection connection) {
        //Connection connection = newConnection();
        if(connection!=null){
            freeConnection.add(connection);
        }
    }

    /**
     *  创建Connection对象
     * @return 返回创建好的conn对象
     */
    private static Connection newConnection() {
        try{
            Class.forName(DbConfig.driverName);
            Connection connection = DriverManager.getConnection(DbConfig.url,DbConfig.userName,DbConfig.password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Connection getConnection() {
        try{
            Connection connection = null;


            //判断当前是否达到maxActiveConn数
            if(countConn.get()<DbConfig.maxActiveConnections){
                //当前连接数小于最大连接数

                //判断freeConn是否还有空闲连接
                if(freeConnection.size()>0){
                    //有 拿出索引为0的conn
                    connection = freeConnection.remove(0);
                }else{
                    //无空闲连接 new新的conn
                    connection = newConnection();
                }

                //将conn存入active连接池
                if(connection!=null&&!connection.isClosed()){
                    //conn不为空且 conn没关闭 将conn存入active连接池里
                    activeConnection.add(connection);

                    //当前连接数++  下列方法相当于 countConn++ 且多线程安全
                    countConn.getAndIncrement();
                }else{
                    //conn坏了 递归
                    connection = getConnection();
                }


            }else{
                //超过maxActiveConn数 等待5min
                wait(1000*60*5);
                //再次递归调用
                connection = getConnection();
            }


            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 归还conn
     * @param connection 传入要销毁的Connection
     */
    @Override
    public synchronized void releaseConnection(Connection connection) {
        try{
            if(connection!=null&&!connection.isClosed()){

                //判断freeConn集合是否达到最大
                if(freeConnection.size()<DbConfig.maxConnections){
                    //没达到最大 入vec
                    freeConnection.add(connection);
                }else{
                    //达到最大 关闭
                    connection.close();
                }
                //在activeConn集合中 移除该对象
                activeConnection.remove(connection);

                //当前连接数countConn--
                countConn.getAndDecrement();
            }
            //唤醒正在wait()同一个锁的所有线程
            notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
