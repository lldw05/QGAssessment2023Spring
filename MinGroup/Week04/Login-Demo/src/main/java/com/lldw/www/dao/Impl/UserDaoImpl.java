package com.lldw.www.dao.Impl;

import com.lldw.www.dao.UserDao;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-04-10 14:44:08
 */
public class UserDaoImpl implements UserDao {
    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertUser(User user) {
        int cnt =ju.update("insert into tb_user values(?,?,?)",null,user.getUserName(),user.getPassword());
        System.out.println(cnt);
        return cnt;
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
     *   根据username查询user对象
     * @param user 用户对象
     * @return 如果有 则返回用户名所对应的user对象  如果无 则返回null
     */
    @Override
    public User getUser(User user) {
        User ans;

        // 根据用户名查询用户
        ans = ju.getUser("select * from tb_user where username = ?", new Object[]{user.getUserName()});
        ju.close();
        return  ans.getUserName()==null?null:ans;


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
