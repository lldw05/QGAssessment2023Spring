package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.UserDaoImpl;
import com.lldw.www.po.User;
import com.lldw.www.service.UserService;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @author
 * @date
 */
public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        //user.setPassword();
        User userById = userDao.getUserByUsername(user);

        if (userById!=null&&userById.getPassword().equals(user.getPassword())){
            //返回userById对象不为空且用户名和密码正确
            return userById;
        }else {
            //返回对象为空 即用户名或密码错误
            return null;
        }
    }

    @Override
    public User register(User user) {
//        Optional<User> userOptional = Optional.of(userDao.getUserByUsername(user));
//        userOptional.ifPresent(user1 -> );
//        if(){
//            return null;
//        }
//        userOptional.ifPresent();
        return null;
    }

    @Override
    public User queryUser(User user) {
        return userDao.getUserByUsername(user);
    }

}
