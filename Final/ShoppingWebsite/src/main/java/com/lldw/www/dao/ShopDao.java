package com.lldw.www.dao;

import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-18 20:10:02
 */
public interface ShopDao {
    /**
     *  用于添加shop
     * @param shop shop对象
     * @return 返回添加数据的条数
     */
    int insertShop(Shop shop);

    /**
     * 用于删除shop
     * @param shop shop对象
     * @return 返回删除shop的个数
     */
    int deleteShop(Shop shop);

    /**
     * 用于更新shop
     * @param shop shop对象
     * @return 返回修改的行数
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
}
