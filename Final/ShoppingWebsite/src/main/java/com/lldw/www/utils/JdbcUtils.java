package com.lldw.www.utils;


import com.lldw.www.utils.connectionPool.ConnectionPoolImpl;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lldw
 * @date 2023年3月22日21:52:16
 */
public class JdbcUtils {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private static JdbcUtils ju;

    ConnectionPoolImpl connectionPool = ConnectionPoolImpl.getInstance();


    /**
     * 私有化无参构造器
     */
    private JdbcUtils() {
    }

    /**
     * 懒汉单例
     *
     * @return Jdbc工具类的对象
     */
    public static JdbcUtils getInstance() {

        if (ju == null) {
            ju = new JdbcUtils();
        }
        return ju;
    }


//    //静态代码块 与类一起加载 比main方法先执行一次
//    static {
//        System.out.println("----JDBCUtils----");
//        System.out.println(">>>>开始加载配置...");
//        try {
//            //new对象
//            Properties pro = new Properties();
//
//            //读取配置 properties文件与src同级
//            pro.load(new FileReader(new File("conn.properties")));
//            url = pro.getProperty("url");
//            userName = pro.getProperty("userName");
//            password = pro.getProperty("password");
//            driver = pro.getProperty("driver");
//
//            System.out.println(">>>>配置加载完成");
//
//            //加载驱动
//            Class.forName(driver);
//            System.out.println(">>>驱动加载完成");
//        } catch (Exception e) {
//            //throw new RuntimeException(e);
//            e.printStackTrace();
//        }
//
//    }

    /**
     * 获取连接
     *
     * @return conn
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
//            conn = DriverManager.getConnection(url, userName, password);
            //通过connPool 获取conn
            conn = connectionPool.getConnection();
            System.out.println("数据库已连接喵~");

        } catch (Exception e) {
            e.getStackTrace();
        }
        return conn;
    }

    /**
     * 获取PreparedStatement对象
     *
     * @param sql  sql语句
     * @param args 问号内容(一个或多个)
     * @return ps
     * @throws SQLException 不懂
     */
    public PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException {
        conn = getConnection();
        //获取prepareStatement对象
        ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        if (args != null) {
            //sql语句中存在问号时 不存在则不用插入
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
        }
        return ps;
    }

    /**
     * PreparedStatement，增删改
     *
     * @param sql  sql
     * @param args 问号具体内容
     * @return cnt 影响的行数
     */
    public int update(String sql, Object... args) {
        int cnt = 0;
        try {
            ps = getPreparedStatement(sql, args);
            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        ju.close();
        return cnt;

    }
    //jdbcClose(conn,ps);

    /**
     * insert
     *
     * @param sql  sql
     * @param args 问号具体内容
     * @return id 影响的行数大于0时 返回主键id的值   插入失败时返回0
     */
    public int insert(String sql, Object... args) {
        int id = 0;
        try {
            ps = getPreparedStatement(sql, args);
            if (ps.executeUpdate() > 0) {
                //获取主键
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
                System.out.println("id:"+id);
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        ju.close();
        return id;

    }


    /**
     * 查询 并以链表形式返回结果
     *
     * @param sql  sql
     * @param data 问号的值
     * @return list 返回集合（集合中是map 字段与值相对应）查询不到(size=0)时返回null
     */
    public ArrayList<Map<String, Object>> execQueryList(String sql, Object[] data) {

        //定义字段个数初始值0
        int colCount = 0;

        //定义ResultSetMetaData对象 ResultSetMetaData为 描述数据的类
        ResultSetMetaData rsmd = null;
        try {
            //获取PreparedStatement对象
            ps = getPreparedStatement(sql, data);
            //PreparedStatement已经进行过预处理 获取ResultSet对象
            rs = ps.executeQuery();
            //PreparedStatement已经进行过预处理
            rsmd = rs.getMetaData();
            //返回所有字段的数目
            colCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        //new链表
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            while (rs.next()) {
                //new一个map 将字段以及其对应的值存入键值对
                Map<String, Object> map = new HashMap<>();

                for (int i = 1; i <= colCount; i++) {
                    //getColumnLabel(i):第i个字段，getObject(i):第i个字段对应的记录
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }

                //将map放进链表
                list.add(map);

            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        ju.close();
        return list.size() > 0 ? list : null;
    }

    /*    *//**
     * 查询 得到User对象
     *
     * @param sql  sql
     * @param data 问号的值
     * @return user 返回User对象
     *//*
    public User getUser(String sql, Object[] data) {
        User user = new User();

        try {
            //获取PreparedStatement对象
            ps = getPreparedStatement(sql, data);
            //PreparedStatement已经进行过预处理 获取ResultSet对象
            rs = ps.executeQuery();

            //最重要的一步 耗费30min 寄
            rs.next();

            //封装对象
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("password"));

        } catch (SQLException e) {
            e.getStackTrace();
        }
        return user;
    }*/


    /**
     * 释放资源
     *
     * @param conn conn
     * @param ps   preparedStatement
     */
    public void jdbcClose(Connection conn, PreparedStatement ps) {
        try {
            //释放PreparedStatement
            ps.close();

            /*if (ps != null) {
                ps = null;
            }*/

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {

            //释放Connection
            //conn.close(); 将conn归还到conn池
            connectionPool.releaseConnection(conn);

            /*if (conn != null) {
                conn = null;
            }*/

        }
    }

    /**
     * 释放资源 conn,ps,rs
     *
     * @param conn Connection对象
     * @param ps   PreparedStatement
     * @param rs   ResultSet
     */
    public void jdbcClose(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            //释放resultSet
            rs.close();

            /*if (rs != null) {
                rs = null;
            }*/

        } catch (SQLException e) {
            e.getStackTrace();
        } finally {
            jdbcClose(conn, ps);
        }
    }

    /**
     * 总释放资源方法 自行判断释放哪些资源
     */
    public void close() {
        if (this.rs != null) {

            jdbcClose(this.conn, this.ps, this.rs);

        } else {
            jdbcClose(this.conn, this.ps);
        }
    }


    /*public void query(String sql, Object[] data) {
        //定义字段个数初始值0
        int colCount = 0;

        //定义ResultSetMetaData对象 ResultSetMetaData为 描述数据的类
        ResultSetMetaData rsmd = null;
        try {
            //获取PreparedStatement对象
            ps = getPreparedStatement(sql, data);
            //PreparedStatement已经进行过预处理 获取ResultSet对象
            rs = ps.executeQuery();
            //PreparedStatement已经进行过预处理
            rsmd = rs.getMetaData();
            //返回所有字段的数目
            colCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.getStackTrace();
        }

        try {
            while (rs.next()) {
                //对于每一行的结果 new一个链表来存放
                ArrayList<Object> list = new ArrayList<>();

                for (int i = 1; i <= colCount; i++) {

                    //有多少个字段数 就getObject多少次
                    Object o = rs.getObject(i);
                    list.add(o);

                }
                //输出每一行的结果
                System.out.println(list);

            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }*/
}
