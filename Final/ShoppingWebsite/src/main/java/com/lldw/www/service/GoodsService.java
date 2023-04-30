package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-19 19:13:03
 */
public interface GoodsService {
    /**
     *  添加商品
     * @param goods 封装的商品对象
     * @return 添加成功则返回该商品对象(包括id) 否则返回null
     */
    Goods addGoods(Goods goods);

    /**
     *  查询商店下所有商品
     * @param shop shopId 只能通过shopId查！
     * @return 返回装有goods的集合 或者null
     */
    ArrayList<Goods> queryGoodsOfShop(Shop shop);

    /**
     * 根据goodId查询goods
     * @param goods goodsId
     * @return 查询到了 返回goods对象 否则返回null
     */
    Goods queryGoodsByGoodsId(Goods goods);

    /**
     * 根据关键词搜索商品
     * @param s 关键词
     * @return 搜索到了 返回搜索结果 否则返回null
     */
    ArrayList<Goods> searchGoods(String s);

    /**
     * 更新商品
     * @param goods goodsId
     * @return 返回是否更新成功
     */
    Boolean updateGoods(Goods goods);

    /**
     * 随机获取商品
     * @return  商品集合
     */
    ArrayList<Goods> getRandomGoods();

    ArrayList<Goods> queryGoodsByUserId(User user);
}
