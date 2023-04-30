package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public interface ShopService {
    /**
     * 注册
     * @param shop
     * @return 注册成功则返回shop对象 否则返回null
     */
    Shop register(Shop shop);

    /**
     *  展示商店信息 拥有粉丝数量，月均销量
     * @param shop shopName
     * @return 查询到了 则返回封装好的shop对象 否则返回null
     */
    Shop showShopMessage(Shop shop);


    /**
     * 通过shopId 查询shop
     * @param shop shopId
     * @return  查询到了 则返回shop 否则返回null
     */
    Shop getShopByShopId(Shop shop);

    /**
     * 通过ShopName 查询shop
     * @param shop ShopName
     * @return  查询到了 则返回shop 否则返回null
     */
    Shop queryShopByShopName(Shop shop);

    /**
     *  通过userId查询商店
     * @param user userId
     * @return 查询到了 则返回shop 否则返回null
     */
    Shop queryShopByUserId(User user);
}
