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
    public Shop register(Shop shop);
}
