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
import java.util.regex.Pattern;

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

        if (userById != null && userById.getPassword().equals(user.getPassword())) {
            //返回userById对象不为空且用户名和密码正确
            return userById;
        } else {
            //返回对象为空 即用户名或密码错误
            return null;
        }
    }

    @Override
    public User loginByPhoneNumber(User user) {

        //校对手机号码
        if (!this.checkPhoneNumber(user.getPhoneNumber())) {
            //不正确 返回null
            return null;
        }

        //通过username查询user
        User userByName = userDao.getUserByUsername(user);

        if (userByName != null && userByName.getPhoneNumber().equals(user.getPhoneNumber())) {
            //返回userById对象不为空且用户名和手机号码正确
            return userByName;
        } else {
            //返回对象为空 即用户名或密码错误
            return null;
        }
    }

    @Override
    public User register(User user) {
        System.out.println("---UserService.register---");

        //健壮性:判断密码和用户名是否为null值
        if (user.getPassword() == null || user.getUsername() == null) {
            return null;
        }

        User userByUsername = userDao.getUserByUsername(user);
        //判断是否查询到结果
        if (userByUsername != null) {
            //查询到了 说明用户名已经被使用 返回null表示注册失败
            System.out.println("ERROR:用户名存在");
            return null;
        }

        //查询不到 进行信息加密
        user = this.encryptUser(user);
        user.setRoleId(RoleId.USER);
        //查询不到 注册 返回影响行数
        //影响行数大于0 返回新添加的user对象 否则返回null

        return userDao.insertUser(user) > 0 ? userDao.getUserByUsername(user) : null;
    }

    @Override
    public User queryUserByUsername(User user) {
        return userDao.getUserByUsername(user);
    }

    @Override
    public User queryUserByUserId(User user) {
        return userDao.getUserById(user);
    }

    /**
     * 对用户的对用户的密码和支付密码进行加密
     *
     * @param user 未加密的user对象
     * @return 返回已加密的user对象
     */
    public User encryptUser(User user) {

        //对用户的密码进行加密 连续加密两次
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));
        user.setPassword(EncryptUtil.encrypt(user.getPassword()));

        /*user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
        user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));*/
        return user;
    }

    public User updateUser(User user) {

        //判断手机号码是否符合规则
        if (user.getPhoneNumber() != null && !"".equals(user.getPhoneNumber())) {

            //手机号码不为空 且不为空字符串 说明需要修改

            // 则判断是否满足正则表达式
            if (!checkPhoneNumber(user.getPhoneNumber())) {
                //不满足正则表达式 返回null
                return null;
            }

            //满足正则表达式 继续后续操作
        }

        //判断密码和支付密码是否需要修改

        //判断password不为空 且不为空字符串
        if (user.getPassword() != null && !"".equals(user.getPassword())) {
            //密码需要修改 加密一下下先
            user = this.encryptUser(user);
        }

        //判断payPassword不为空 且不为空字符串
        if (user.getPayPassword() != null && !"".equals(user.getPayPassword())) {
            //支付密码需要修改 加密一下下先
            user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
            user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
        }

        //进行更新 返回修改的行数
        //修改行数大于0 返回修改完的user对象 否则返回null
        return userDao.updateUser(user) > 0 ? userDao.getUserById(user) : null;

    }

    @Override
    public boolean checkPayPassword(User user) {

        //根据id查询user
        User user1 = userDao.getUserById(user);

        //判断是否初次设置原密码
        if(user1.getPayPassword()==null){
            //说明没设置过支付密码
            return true;
        }


        //支付密码 加密一下下先
        user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));
        user.setPayPassword(EncryptUtil.encrypt(user.getPayPassword()));

        return user1.getPayPassword().equals(user.getPayPassword());
    }

    @Override
    public boolean checkPayPasswordIsNull(User user) {

        //根据id查询user
        User user1 = userDao.getUserById(user);

        return user1.getPayPassword() == null;
    }

    @Override
    public boolean checkPhoneNumber(String phoneNumber) {
        if ((phoneNumber != null) && (!phoneNumber.isEmpty())) {
            return Pattern.matches("^1[3-9]\\d{9}$", phoneNumber);
        }
        return false;
    }


}
