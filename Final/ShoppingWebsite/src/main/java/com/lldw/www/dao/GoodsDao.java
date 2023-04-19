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
     * @return  返回影响的行数
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
    Goods selectGoodsByGoodsId(Goods goods);
    ArrayList<Goods> slectGoodsByGoodsBame(Goods goods);

    /**
     * 将放到map中的查询结果封装成goods对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回goods对象
     */
    Goods getGoodsFromMap(Map<String,Object> map);
}
