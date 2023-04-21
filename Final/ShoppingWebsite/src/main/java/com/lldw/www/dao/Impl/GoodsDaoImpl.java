package com.lldw.www.dao.Impl;

import com.lldw.www.dao.GoodsDao;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Shop;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public class GoodsDaoImpl implements GoodsDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertGoods(Goods goods) {
        System.out.println("---GoodsDaoImpl.insertGoods---");
        return ju.insert("insert into goods (goods_introduction,price,shop_id,amount) values(?,?,?,?)"
                , goods.getGoodsIntroduction(), goods.getPrice(), goods.getShopId(), goods.getAmount());
    }

    @Override
    public int deleteGoods(Goods goods) {
        return 0;
    }

    @Override
    public int updateGoods(Goods goods) {
        return 0;
    }

    @Override
    public ArrayList<Goods> selectGoodsByShopId(Shop shop) {
        System.out.println("---GoodsDaoImpl.selectGoodsByShopId---");
        ArrayList<Goods> goodsList = new ArrayList<>();
        ArrayList<Map<String, Object>> mapList = ju.execQueryList("select * from goods where shop_id = ?", new Object[]{shop.getShopId()});
        if(mapList==null){
            return null;
        }
        for (Map<String, Object> map :
                mapList) {
            goodsList.add(getGoodsFromMap(map));
        }
        return goodsList;
    }

    @Override
    public Goods selectGoodsByGoodsId(Goods goods) {

        ArrayList<Map<String, Object>> mapList = ju.execQueryList("select * from goods where goods_id = ?", new Object[]{goods.getGoodsId()});

        //判断返回mapList集合是否为空
        if (mapList == null) {
            //为空 直接返回null
            return null;
        }

        //不为空 取出list中第一个map
        Goods resultGoods = getGoodsFromMap(mapList.get(0));

        return resultGoods;
    }

    @Override
    public ArrayList<Goods> slectGoodsByGoodsBame(Goods goods) {
        return null;
    }

    @Override
    public Goods getGoodsFromMap(Map<String, Object> map) {
        System.out.println("---GoodsDao.getGoodsFromMap---");

        //从map中获取值
        Goods goods = new Goods();
        goods.setGoodsId((Integer) map.get("goods_id"));
        goods.setGoodsIntroduction((String) map.get("goods_introduction"));
        goods.setPrice((Double) map.get("price"));
        goods.setPictureId((Integer) map.get("picture_id"));
        goods.setShopId((Integer) map.get("shop_id"));
        goods.setMonthlySales((Integer) map.get("monthly_sales"));
        goods.setAmount((Integer) map.get("amount"));
        goods.setActive((Boolean) map.get("is_active"));
        System.out.println("---GoodsDao.getGoodsFromMap END---");
        return goods;
    }
}