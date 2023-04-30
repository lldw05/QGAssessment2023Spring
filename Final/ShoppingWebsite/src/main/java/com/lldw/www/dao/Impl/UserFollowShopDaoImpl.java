package com.lldw.www.dao.Impl;

import com.lldw.www.dao.UserFollowShopDao;
import com.lldw.www.po.Shop;
import com.lldw.www.po.UserFollowShop;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public class UserFollowShopDaoImpl implements UserFollowShopDao {
    JdbcUtils ju = JdbcUtils.getInstance();
    @Override
    public int insert(UserFollowShop ufs) {
        return ju.insert("insert into user_follow_shop (user_id,shop_id) values (?,?)",ufs.getUserId(),ufs.getShopId());
    }

    @Override
    public UserFollowShop queryByUserIdShopId(UserFollowShop ufs) {
        System.out.println("---UserFollowShopDao.queryByUserIdShopId---");
        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from user_follow_shop where user_id = ? and shop_id = ?"
                , new Object[]{ufs.getUserId(), ufs.getShopId()});
        if(maps==null){
            return null;
        }
        UserFollowShop resultUfs = getUfsFromMap(maps.get(0));
        return resultUfs;
    }

    @Override
    public UserFollowShop getUfsFromMap(Map<String, Object> map) {
        System.out.println("---UserFollowShopDao.getUfsFromMap---");
        UserFollowShop ufs = new UserFollowShop();

        ufs.setId((Integer) map.get("id"));
        ufs.setShopId((Integer) map.get("shop_id"));
        ufs.setUserId((Integer) map.get("user_id"));

        return ufs;
    }

    @Override
    public ArrayList<Integer> queryFansIdByShopId(Shop shop) {
        System.out.println("---UserFollowShopDao.queryFansIdByShopId---");

        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from user_follow_shop where shop_id = ?", new Object[]{shop.getShopId()});

        //判断查询结果是否为null
        if(maps==null){
            return null;
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (Map<String, Object> m :
                maps) {
            ans.add((Integer) m.get("user_id"));
        }
        return ans;
    }
}
