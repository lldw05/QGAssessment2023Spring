package com.lldw.www.dao;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Shop;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public interface GoodsDao {
    /**
     *  新增goods
     * @param goods goods对象
     * @return  插入成功返回主键id的值  插入失败时返回0
     */
    int insertGoods(Goods goods);

    /**
     *  删除goods
     * @param goods goods对象
     * @return 返回影响的行数
     */
    int deleteGoods(Goods goods);

    /**
     *  修改商品信息
     * @param goods goods对象
     * @return 返回影响的行数
     */
    int updateGoods(Goods goods);

    /**
     *  展示商店下所有商品
     * @param shop shopId
     * @return 返回装有goods的集合 或者null
     */
    ArrayList<Goods> selectGoodsByShopId(Shop shop);

    /**
     *
     * @param goods goodsId
     * @return 查找成功则返回goods对象 否则返回null
     */
    Goods selectGoodsByGoodsId(Goods goods);

    /**
     *  根据关键词 搜索商品
     * @param s 关键词
     * @return 搜索到了 返回搜索结果 否则返回null
     */
    ArrayList<Goods> selectGoodsByGoodsName(String s);

    /**
     * 将放到map中的查询结果封装成goods对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回goods对象
     */
    Goods getGoodsFromMap(Map<String,Object> map);

    /**
     * 随机获取商品
     * @return goods集合
     */
    ArrayList<Goods> selectAll();

    /**
     * 从map集合中获取goods 并放到list集合中返回
     * @param mapList
     * @return 有则返回goods集合 否则返回null
     */
    ArrayList<Goods> getGoodsListFromMapList(ArrayList<Map<String, Object>> mapList);
}
