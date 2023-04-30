package com.lldw.www.dao;

import com.lldw.www.po.Shop;
import com.lldw.www.po.UserFollowShop;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-4-22 13:46:25
 */
public interface UserFollowShopDao {
    /**
     * 新增关注
     * @param ufs  shopId userId
     * @return 插入成功则返回主键id 插入失败则返回0
     */
    int insert(UserFollowShop ufs);

    /**
     * 通过userId 和shopId查询 判断user是否已经关注shop
     * @param ufs userId shopId
     * @return 查询到了 则返回ufs 查询不到则返回null
     */
    UserFollowShop queryByUserIdShopId(UserFollowShop ufs);

    /**
     * 将放到map中的查询结果封装成ufs对象返回
     * @param map map
     * @return ufs对象
     */
    UserFollowShop getUfsFromMap(Map<String, Object> map);

    /**
     * 通过shopId查询所有userId
     * @param shop shopId
     * @return 查询到了则返回userId集合 否则返回null
     */
    ArrayList<Integer> queryFansIdByShopId(Shop shop);
}
