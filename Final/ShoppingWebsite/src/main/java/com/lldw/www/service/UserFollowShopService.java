package com.lldw.www.service;

import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.po.UserFollowShop;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-4-22 13:05:34
 */
public interface UserFollowShopService {
    /**
     * 用户关注shop
     * @param ufs userId shopId
     * @return 是否关注成功
     */
    boolean follow(UserFollowShop ufs);

    /**
     * 查询商店的粉丝
     * @param shop shopId
     * @return 查询到了返回userId集合 否则返回null
     */
    ArrayList<Integer> queryFansOfShop(Shop shop);
}
