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
        // 根据用户名查询一个用户
        ArrayList<Object> list = ju.execQueryList("select * from user where username = ?", new Object[]{user.getUsername()});
        if(list==null) {
            return null;
        }

        Map<String,String> map = (Map<String, String>) list.get(0);
        User user1 =new User();

        user1.setUserId(Integer.parseInt(map.get("user_id")));
        user1.setUsername(map.get("username"));
        user1.setNickname(map.get("nickname"));
        user1.setPassword(map.get("password"));
        user1.setAddress(map.get("password"));
        user1.setPhoneNumber(map.get("password"));
        user1.setRealName(map.get("password"));
        user1.setPayPassword(map.get("password"));
        user1.setPictureId(Integer.parseInt(map.get("user_id")));
        user1.setShopId(Integer.parseInt(map.get("user_id")));
        user1.setActive("1".equals(map.get("is_active")));
        user1.setRoleId(Integer.parseInt(map.get("user_id")));

        ju.close();
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
