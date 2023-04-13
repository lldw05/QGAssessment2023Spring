package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.UserDaoImpl;
import com.lldw.www.po.User;
import com.lldw.www.service.UserService;

/**
 * @author
 * @date
 */
public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public User login(User loginUser) {

        User user = userDao.getUser(loginUser);
        if(user!=null&&user.getPassword().equals(loginUser.getPassword())){
            //返回user对象不为空且用户名和密码正确
            return user;
        }else {
            //对象为空 即用户名或密码错误
            return null;

        }


    }

    /**
     *  注册
     * @param user 传入loginUser
     * @return 如注册成功 返回该user对象 注册失败 则返回null
     */
    @Override
    public User register(User user) {
        //1.查询用户名是否已经存在
        User u = userDao.getUser(user);
        int result = 0;
        if(u==null){
            //查询不到 用户名未注册 进行注册
            result = userDao.insertUser(user);


        }
        //result=0说明注册失败   result=1说明注册成功 返回user对象
        return result==0?null:user;
    }

    /**
     *  根据username查询user对象
     * @param loginUser
     * @return  查询到了 返回user对象 否则返回null
     */
    @Override
    public User selectUser(User loginUser) {

       return userDao.getUser(loginUser);

//       if(user==null){
//           //根据用户名查询不到用户 说明用户名未被使用
//           return null;
//       }else{
//           return user;
//       }
    }
}
