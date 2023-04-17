package com.lldw.www.service;

import com.lldw.www.po.User;

/**
 * @author
 * @date
 */
public interface UserService {
    /**
     * 登录功能
     * @param user 传入用户名 密码
     * @return  登录成功返回封装的User对象  登录失败则返回null
     */
    User login(User user);

    /**
     * 注册功能
     * @param user 传入用户名 密码
     * @return 注册成功返回封装的User对象  注册失败则返回null
     */
    User register(User user);

    /**
     * 通过username查找user对象 主要用于判断username是否被使用
     * @param user 传入用户名
     * @return 查询到了 则返回User对象 查询不到则返回null
     */
    User queryUser(User user);
}