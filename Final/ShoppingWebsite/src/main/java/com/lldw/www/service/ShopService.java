package com.lldw.www.service;

import com.lldw.www.po.Shop;

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
     * @param shop shopId
     * @return 查询到了 则返回封装好的shop对象 否则返回null
     */
    Shop showShop(Shop shop);
}
