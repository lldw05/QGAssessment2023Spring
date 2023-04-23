package com.lldw.www.dao;

import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public interface UserDao {
    /**
     *  用于添加用户
     * @param user 用户对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertUser(User user);

    /**
     * 用于删除用户
     * @param user 用户对象
     * @return 返回影响的行数
     */
    int deleteUser(User user);

    /**
     * 用于更新用户
     * @param user 用户对象 要修改的数据以及userId传进来 不修改的数据设为null
     * @return 返回影响的行数
     */
    int updateUser(User user);

    /**
     * 用于查询用户
     * @param user 用户对象
     * @return 返回封装的user对象
     */
    User getUserByUsername(User user);
    /**
     * 通过用户id查询用户
     * @param user 用户对象
     * @return 返回封装的user对象
     */
    User getUserById(User user);

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

    /**
     * 将放到map中的查询结果封装成user对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回user对象
     */
    User getUserFromMap(Map<String,Object> map);


}
