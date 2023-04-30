package com.lldw.www.dao;

import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-04-18 20:10:02
 */
public interface ShopDao {
    /**
     *  用于添加shop
     * @param shop shop对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertShop(Shop shop);

    /**
     * 用于删除shop
     * @param shop shop对象
     * @return 返回影响的行数
     */
    int deleteShop(Shop shop);

    /**
     * 用于更新shop
     * @param shop shop对象
     * @return 返回影响的行数
     */
    int updateShop(Shop shop);

    /**
     * 用于查询shop
     * @param shop shop对象
     * @return 返回封装的shop对象
     */
    Shop getShopByShopId(Shop shop);

    /**
     *  通过shopName查询shop
     * @param shop shop对象
     * @return  如果查询到了 返回shop对象 查询不到 返回null
     */
     Shop getShopByShopName(Shop shop);
    /**
     * //用于查询shop列表
     * @return shop集合
     */
    ArrayList<Object> getShopList();

    /**
     * //用于查询shop列表数量
     * @return shop列表数量
     */
    int getShopCount();

    /**
     * 从map中获取打包好的shop
     * @param map map
     * @return 打包好的shop对象
     */
    Shop getShopFromMap(Map<String,Object> map);

    /**
     * 通过店主Id查询商店
     * @param user userId
     * @return 如果查询到了 返回shop对象 查询不到 返回null
     */
    Shop getShopByShopkeeperId(User user);
}
