package com.lldw.www.service;

import com.lldw.www.po.User;

public interface UserService {
    /**
     *  登录
     * @param user 传入用户名 密码
     * @return  返回封装的User对象
     */
    User login(User user);

    /**
     *  注册
     * @param user 传入loginUser
     * @return 返回封装的User对象
     */
    User register(User user);

    User selectUser(User loginUser);
}
