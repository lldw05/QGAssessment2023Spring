package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;

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
     *  展示商店的商品 返回商店所有商品
     * @param shop shopName(shopId) 一定要有shopName
     * @return 查询到了 则返回装有goods的集合 否则返回null
     */
    ArrayList<Goods> showShopGoods(Shop shop);




}
