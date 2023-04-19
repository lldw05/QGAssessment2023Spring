package com.lldw.www.dao.Impl;

import com.lldw.www.dao.UserDao;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

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
        System.out.println("--userDao.updateUser---");
        String sql = "";
        //根据userId 先查出修改前的user数据
        User preUser = this.getUserById(user);

        //
        if(user.getNickname()!=null){
            System.out.println(1);
            preUser.setNickname(user.getNickname());
//            ju.update("update user set nickname = ? where user_id = ?",preUser.getNickname(),preUser.getUserId());
        }
        if(user.getPassword()!=null){
            System.out.println(2);
            preUser.setPassword(user.getPassword());
//            ju.update("update user set password = ? where user_id = ?",preUser.getPassword(),preUser.getUserId());
        }
        if(user.getAddress()!=null){
            System.out.println(3);
            preUser.setAddress(user.getAddress());
//            ju.update("update user set address = ? where user_id = ?",preUser.getAddress(),preUser.getUserId());
        }
        if(user.getPhoneNumber()!=null){
            System.out.println(4);
            preUser.setPhoneNumber(user.getPhoneNumber());
//            ju.update("update user set phone_number = ? where user_id = ?",preUser.getPhoneNumber(),preUser.getUserId());
        }
        if(user.getRealName()!=null){
            System.out.println(5);
            preUser.setRealName(user.getRealName());
//            ju.update("update user set real_name = ? where user_id = ?",preUser.getRealName(),preUser.getUserId());
        }
        if(user.getPayPassword()!=null){
            System.out.println(6);
            preUser.setPayPassword(user.getPayPassword());
//            ju.update("update user set pay_password = ? where user_id = ?",preUser.getPayPassword(),preUser.getUserId());
        }
        if(user.getPictureId()!=null){
            System.out.println(7);
            preUser.setPictureId(user.getPictureId());
//            ju.update("update user set picture_id = ? where user_id = ?",preUser.getPictureId(),preUser.getUserId());
        }
        if(user.getRoleId()!=null){
            System.out.println(8);
            preUser.setRoleId(user.getRoleId());
            ju.update("update user set role_id = ? where user_id = ?",preUser.getRoleId(),preUser.getUserId());
        }
        System.out.println("preUser:");
        System.out.println(preUser);
        //出错
        return ju.update("update user set nickname = ?,password= ?,address= ?,phone_number= ?" +
                        ",real_name=?,pay_password= ?,picture_id= ?,role_id= ? where user_id = ?",
                preUser.getNickname(), preUser.getPassword(),preUser.getAddress(), preUser.getPhoneNumber(), preUser.getRealName()
                , preUser.getPayPassword(), preUser.getPictureId(), preUser.getRoleId(), preUser.getUserId());
//        return 1;
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
        //System.out.println(list==null);
        if(list==null) {
            return null;
        }

        //System.out.println(list);
//        Map<String,Object> map = (Map<String, Object>) list.get(0);
        //System.out.println(map);

        User user1 = getUserFromMap((Map<String, Object>) list.get(0));

        System.out.println(user1);

        return user1;

    }

    @Override
    public User getUserById(User user) {
        System.out.println("---UserDao.getUserById---");
        //根据userId查询user
        ArrayList<Object> list = ju.execQueryList("select * from user where user_id = ?", new Object[]{user.getUserId()});

        if(list==null) {
            return null;
        }

        //System.out.println(list);

        //取出list集合里的第一个map 即索引为0的元素
        User user1 = getUserFromMap((Map<String, Object>) list.get(0));


        System.out.println(user1);
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

    @Override
    public User getUserFromMap(Map<String, Object> map) {
        User user =new User();
        System.out.println(map.get("real_name")==null);


        user.setUserId((Integer) map.get("user_id"));
        user.setUsername((String) map.get("username"));
        user.setNickname((String) map.get("nickname"));
        user.setPassword((String) map.get("password"));
        user.setAddress((String) map.get("address"));
        user.setPhoneNumber((String) map.get("phone_number"));
        user.setRealName((String) map.get("real_name"));
        user.setPayPassword((String) map.get("pay_password"));
        user.setPictureId((Integer) map.get("picture_id"));
        user.setShopId((Integer) map.get("shop_id"));
        user.setActive("1".equals(map.get("is_active")));
        user.setRoleId((Integer) map.get("role_id"));


        return user;
    }

}
