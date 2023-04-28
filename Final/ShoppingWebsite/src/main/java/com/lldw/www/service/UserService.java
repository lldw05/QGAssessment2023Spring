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
     * 通过username查找user对象
     * @param user username
     * @return 查询到了 则返回User对象 查询不到则返回null
     */
    User queryUserByUsername(User user);
    /**
     * 通过userId查找user对象
     * @param user userid
     * @return 查询到了 则返回User对象 查询不到则返回null
     */
    User queryUserByUserId(User user);

    /**
     * 更新user数据
     * @param user 将需要更新的数据和userId封装打包传进来，不修改的数据统统为null
     * @return 修改成功返回User对象 修改失败返回null
     */
    User updateUser(User user);


    /**
     * 核实支付密码是否正确
     * @param user userId payPassword
     * @return 返回密码是否正确
     */
    boolean checkPayPassword(User user);

    /**
     * 查询支付密码是否为null
     * @param user userId
     * @return 返回支付密码是否为null
     */
    boolean checkPayPasswordIsNull(User user);


}
