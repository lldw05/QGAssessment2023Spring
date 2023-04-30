package com.lldw.www.dao.Impl;

import com.lldw.www.dao.UserDao;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * @author
 * @date
 */
public class UserDaoImpl implements UserDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertUser(User user) {
        int update = ju.insert("insert into user (username,password,phone_number) values(?,?,?)"
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
        //根据userId 先查出修改前的user数据
        User preUser = this.getUserById(user);

        //判断是否存在该user
        if (preUser==null){
            return 0;
        }

        System.out.println("preUser:"+preUser);

        //用户名不可修改
        //修改昵称
        if(user.getNickname()!=null&& !"".equals(user.getNickname())){
            System.out.println(1);
            preUser.setNickname(user.getNickname());
        }

        //修改密码
        if(user.getPassword()!=null&& !"".equals(user.getPassword())){
            System.out.println(2);
            preUser.setPassword(user.getPassword());
        }

        //修改地址
        if(user.getAddress()!=null&& !"".equals(user.getAddress())){
            System.out.println(3);
            preUser.setAddress(user.getAddress());
        }

        //修改手机号码
        if(user.getPhoneNumber()!=null&& !"".equals(user.getPhoneNumber())){
            System.out.println(4);
            preUser.setPhoneNumber(user.getPhoneNumber());
        }

        //修改收件姓名
        if(user.getRealName()!=null&& !"".equals(user.getRealName())){
            System.out.println(5);
            preUser.setRealName(user.getRealName());
        }

        //修改支付密码
        if(user.getPayPassword()!=null&& !"".equals(user.getPayPassword())){
            System.out.println(6);
            preUser.setPayPassword(user.getPayPassword());
        }

        //修改头像
        if(user.getPictureId()!=null){
            System.out.println(7);
            preUser.setPictureId(user.getPictureId());
        }

        //修改角色id
        if(user.getRoleId()!=null){
            System.out.println(8);
            preUser.setRoleId(user.getRoleId());
        }
        System.out.println("afterUser:");
        System.out.println(preUser);

        return ju.update("update user set nickname = ?,password= ?,address= ?,phone_number= ?" +
                        ",real_name=?,pay_password= ?,picture_id= ?,role_id= ? where user_id = ?",
                preUser.getNickname(), preUser.getPassword(),preUser.getAddress(), preUser.getPhoneNumber(), preUser.getRealName()
                , preUser.getPayPassword(), preUser.getPictureId(), preUser.getRoleId(), preUser.getUserId());
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
        ArrayList<Map<String, Object>> list = ju.execQueryList("select * from user where username = ?", new Object[]{user.getUsername()});

        //判断查询结果是否为空
        if(list==null) {
            return null;
        }

        //取出第一个map
        User user1 = getUserFromMap( list.get(0));

        System.out.println(user1);

        return user1;

    }

    @Override
    public User getUserById(User user) {
        System.out.println("---UserDao.getUserById---");
        //根据userId查询user
        ArrayList<Map<String, Object>> list = ju.execQueryList("select * from user where user_id = ?", new Object[]{user.getUserId()});

        if(list==null) {
            return null;
        }

        //System.out.println(list);

        //取出list集合里的第一个map 即索引为0的元素
        User user1 = getUserFromMap( list.get(0));


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
