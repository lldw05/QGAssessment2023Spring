package com.lldw.www.service.Impl;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.dao.Impl.GoodsDaoImpl;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
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
    public ArrayList<Goods> queryGoodsOfShop(Shop shop) {

        //最终dao层使用shopId查询商店下的商品
        if(shop.getShopId()==null){
            //如果shopId没传进来 先通过shopName查询shopId
            ShopServiceImpl shopService  = new ShopServiceImpl();
            shop.setShopId(shopService.queryShopByShopName(shop).getShopId());
        }
       return goodsDao.selectGoodsByShopId(shop);
    }

    @Override
    public Goods queryGoodsByGoodsId(Goods goods) {
        return goodsDao.selectGoodsByGoodsId(goods);
    }

    @Override
    public ArrayList<Goods> searchGoods(String s) {
        return goodsDao.selectGoodsByGoodsName(s);
    }

    @Override
    public Boolean updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods)>0;
    }

    @Override
    public ArrayList<Goods> getRandomGoods() {
        ArrayList<Goods> goodsArrayList = goodsDao.selectAll();
        if (goodsArrayList.size()<=10){
            return goodsArrayList;
        }
        ArrayList<Goods> result = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//
//        }
        return goodsArrayList;
    }


    @Override
    public Goods addGoods(Goods goods) {
        System.out.println("---ShopService.addGoods---");

        //从ShooService到GoodsService 添加商品
        GoodsServiceImpl goodsService = new GoodsServiceImpl();
//        Goods resultGoods = goodsService.addGoods(goods);

        //调用dao 添加goods
        int id = goodsDao.insertGoods(goods);
        if(id>0){

            //查询完整的goods对象(包括goodsId)
            goods.setGoodsId(id);
            goods = goodsDao.selectGoodsByGoodsId(goods);
            System.out.println("返回完整的goods对象(包括id):"+goods);


            //添加成功 新增一条添加商品的审核信息
            //从从ShooService到MessageService

            //添加数据
            Message message = new Message();
            message.setType(MessageConstants.MESSAGE_TYPE_NEW_PRODUCT_LAUNCH);
            message.setGoodsId(goods.getGoodsId());
            message.setShopId(goods.getShopId());
            message.setMessageContent("新品上市!");

            //调用messageService 添加商品审核信息
            MessageServiceImpl messageService = new MessageServiceImpl();
            if(messageService.addMessageNewProductLaunch(message)){
                System.out.println("添加商品审核信息成功");
            }
        }

        //添加商品成功则返回goods对象 否则返回null  id返回主键时必定大于0 id返回0时说明添加失败
        return id>0?goods:null;
    }
}
