package com.lldw.www.dao.Impl;

import com.lldw.www.dao.UserDao;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

/**
 * @author
 * @date
 */
public class UserDaoImpl implements UserDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertUser(User user) {
        int update = ju.update("insert into user (username,password,phone_number) values(?,?,?)"
                , user.getUsername(), user.getPassword(), user.getPhoneNumber());
        return update;
    }

    @Override
    public int deleteUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    /**
     * 根据username查询user
     * @param user 用户对象
     * @return 查询到了 则返回user对象 查询不到则返回null
     */
    @Override
    public User getUserByUsername(User user) {
        System.out.println("---UserDao.getUserByUsername---");
        // 根据用户名查询一个用户
        ArrayList<Object> list = ju.execQueryList("select * from user where username = ?", new Object[]{user.getUsername()});
        System.out.println(list==null);
        if(list==null) {
            return null;
        }

        System.out.println(list);
        Map<String,Object> map = (Map<String, Object>) list.get(0);
        System.out.println(map);
        User user1 =new User();
        System.out.println(map.get("real_name")==null);


        user1.setUserId((Integer) map.get("user_id"));
        user1.setUsername((String) map.get("username"));
        user1.setNickname((String) map.get("nickname"));
        user1.setPassword((String) map.get("password"));
        user1.setAddress((String) map.get("address"));
        user1.setPhoneNumber((String) map.get("phone_number"));
        user1.setRealName((String) map.get("real_name"));
        user1.setPayPassword((String) map.get("pay_password"));
        user1.setPictureId((Integer) map.get("picture_id"));
        user1.setShopId((Integer) map.get("shop_id"));
        user1.setActive("1".equals(map.get("is_active")));
        user1.setRoleId((Integer) map.get("role_id"));
        System.out.println(user1);
//        ju.close();
        return user1;

    }

    @Override
    public ArrayList<Object> getUserList() {
        return null;
    }

    @Override
    public int getUserCount() {
        return 0;
    }
}
