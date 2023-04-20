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
        int id = goodsDao.insertGoods(goods);
        if(id>0){
            //返回完整的goods对象(包括id)
            goods.setGoodsId(id);
            goods = goodsDao.selectGoodsByGoodsId(goods);
            System.out.println("返回完整的goods对象(包括id):"+goods);
        }
        return id>0?goods:null;
    }

    @Override
    public ArrayList<Goods> queryGoodsOfShop(Shop shop) {
       return goodsDao.selectGoodsByShopId(shop);
    }

    @Override
    public Goods queryGoodsByGoodsId(Goods goods) {
        return goodsDao.selectGoodsByGoodsId(goods);
    }

    /*@Override
    public Goods queryGoodsByGoodsId(Integer goodsId) {
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        return goodsDao.selectGoodsByGoodsId(goods);
    }*/
}
