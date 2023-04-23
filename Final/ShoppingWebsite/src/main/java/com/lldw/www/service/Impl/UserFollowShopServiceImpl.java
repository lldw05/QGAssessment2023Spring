package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.UserFollowShopDaoImpl;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.po.UserFollowShop;
import com.lldw.www.service.UserFollowShopService;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-4-22 13:05:42
 */
public class UserFollowShopServiceImpl implements UserFollowShopService {

    UserFollowShopDaoImpl userFollowShopDao = new UserFollowShopDaoImpl();

    @Override
    public boolean follow(UserFollowShop ufs) {
        //先判断user是否已经关注shop
        if(userFollowShopDao.queryByUserIdShopId(ufs)!=null){
            //已关注 返回false 关注失败
            return false;
        }
        return userFollowShopDao.insert(ufs)>0;
    }

    @Override
    public ArrayList<Integer> queryFansOfShop(Shop shop) {
        if(shop==null||shop.getShopId()==null){
            return null;
        }
        return userFollowShopDao.queryFansIdByShopId(shop);
    }
}
