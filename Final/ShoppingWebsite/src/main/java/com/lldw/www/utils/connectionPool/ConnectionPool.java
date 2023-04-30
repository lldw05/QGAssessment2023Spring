package com.lldw.www.utils.connectionPool;

import java.sql.Connection;

/**
 * @author
 * @date
 */
public interface ConnectionPool {
    /**
     * 获取连接
     * @return 返回一个Connection
     */
    public Connection getConnection();

    /**
     *  释放连接
     * @param connection 传入要销毁的Connection
     */
    public void releaseConnection(Connection connection);
}
