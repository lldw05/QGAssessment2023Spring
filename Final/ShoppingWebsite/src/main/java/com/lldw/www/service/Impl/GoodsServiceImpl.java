package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.GoodsDaoImpl;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Shop;
import com.lldw.www.service.GoodsService;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-19 19:18:51
 */
public class GoodsServiceImpl implements GoodsService {
    GoodsDaoImpl goodsDao = new GoodsDaoImpl();
    @Override
    public Goods addGoods(Goods goods) {
        int cnt = goodsDao.insertGoods(goods);
        return cnt>0?goods:null;
    }

    @Override
    public ArrayList<Goods> queryGoodsOfShop(Shop shop) {
       return goodsDao.selectGoodsByShopId(shop);
    }


}
