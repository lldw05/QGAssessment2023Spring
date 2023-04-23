package com.lldw.www.service;

import com.lldw.www.po.OrderForm;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;

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
     * 通过username查找user对象
     * @param user username
     * @return 查询到了 则返回User对象 查询不到则返回null
     */
    User queryUser(User user);

    /**
     * 更新user数据
     * @param user 将需要更新的数据和userId封装打包传进来，不修改的数据统统为null
     * @return 修改成功返回User对象 修改失败返回null
     */
    User updateUser(User user);




}
