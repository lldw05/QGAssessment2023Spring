package com.lldw.www.service.Impl;

import com.lldw.www.constants.RoleId;
import com.lldw.www.dao.Impl.UserDaoImpl;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.service.UserService;
import com.lldw.www.utils.EncryptUtil;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author
 * @date
 */
public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public User login(User user) {


        User userById = userDao.getUserByUsername(user);

        //对用户的密码进行加密
        user = this.encryptUser(user);

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
        System.out.println("---UserService.register---");
        User userByUsername = userDao.getUserByUsername(user);
        //判断是否查询到结果
        if(userByUsername!=null){
            //查询到了 说明用户名已经被使用 返回null表示注册失败
            System.out.println("ERROR:用户名存在");
            return null;
        }

        //查询不到 进行信息加密
        user = this.encryptUser(user);
        user.setRoleId(RoleId.USER);
        //查询不到 注册 返回影响行数
        //影响行数大于0 返回新添加的user对象 否则返回null

        return userDao.insertUser(user)>0?userDao.getUserByUsername(user):null;
    }

    @Override
    public User queryUser(User user) {
        return userDao.getUserByUsername(user);
    }

    /**
     * 对用户的对用户的密码和支付密码进行加密
     * @param user 未加密的user对象
     * @return 返回已加密的user对象
     */
    public User encryptUser(User user){
        //对用户的密码进行加密 连续加密两次
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));

        /*user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
        user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));*/
        return user;
    }

    public User updateUser(User user){
        //判断密码和支付密码是否需要修改

        if(user.getPassword()!=null){
            //密码需要修改 加密一下下先
            user = this.encryptUser(user);
        }

        if(user.getPayPassword()!=null){
            //支付密码需要修改 加密一下下先
            user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
            user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
        }
        //进行更新 返回修改的行数
        //修改行数大于0 返回修改完的user对象 否则返回null
        return userDao.updateUser(user) >0?userDao.getUserById(user):null;

    }




}
