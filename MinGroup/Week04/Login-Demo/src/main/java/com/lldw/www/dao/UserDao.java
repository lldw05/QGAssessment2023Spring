package com.lldw.www.dao;

import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LLDW
 * @date 2023-04-10 13:54:42
 */
public interface UserDao {
    /**
     *  用于添加用户
     * @param user 用户对象
     * @return 返回添加数据的条数
     */
    int insertUser(User user);

    /**
     * 用于删除用户
     * @param user 用户对象
     * @return 返回删除用户的个数
     */
    int deleteUser(User user);

    /**
     * 用于更新用户
     * @param user 用户对象
     * @return 返回修改的行数
     */
    int updateUser(User user);

    /**
     * 用于查询用户
     * @param user 用户对象
     * @return 返回封装的user对象
     */
    User getUser(User user);

    /**
     * //用于查询用户列表
     * @return 用户集合
     */
    ArrayList<Object> getUserList();

    /**
     * //用于查询用户列表数量
     * @return 用户列表数量
     */
    int getUserCount();
}
