package com.lldw.www.dao.Impl;

import com.lldw.www.dao.ShopDao;
import com.lldw.www.po.Shop;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public class ShopDaoImpl implements ShopDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertShop(Shop shop) {
        System.out.println("---ShopDao.insertShop---");
        return ju.insert("insert into shop (shopkeeper_id,shop_name,shop_introduction) value(?,?,?)"
                , shop.getShopKeeperId(), shop.getShopName(), shop.getShopIntroduction());
    }

    @Override
    public int deleteShop(Shop shop) {
        return 0;
    }

    @Override
    public int updateShop(Shop shop) {
        return 0;
    }

    @Override
    public Shop getShopByShopId(Shop shop) {
        ArrayList<Map<String, Object>> list = ju.execQueryList("select * from shop where shop_id = ?", new Object[]{shop.getShopId()});

        if (list == null) {
            return null;
        }

        //从ArrayList<Map>中获取map
        return getShopFromMap(list.get(0));
    }

    @Override
    public Shop getShopByShopName(Shop shop) {
        ArrayList<Map<String, Object>> list = ju.execQueryList("select * from shop where shop_name = ?", new Object[]{shop.getShopName()});

        if (list == null) {
            return null;
        }

        //从ArrayList<Map>中获取map


        return getShopFromMap(list.get(0));
    }

    @Override
    public ArrayList<Object> getShopList() {
        return null;
    }

    @Override
    public int getShopCount() {
        return 0;
    }

    @Override
    public Shop getShopFromMap(Map<String, Object> map) {
        Shop shop = new Shop();
        shop.setShopId((Integer) map.get("shop_id"));
        shop.setShopKeeperId((Integer) map.get("shopkeeper_id"));
        shop.setShopName((String) map.get("shop_name"));
        shop.setShopIntroduction((String) map.get("shop_introduction"));
        shop.setFansNum((Integer) map.get("fans_num"));
        shop.setAverageMonthlySales((Integer) map.get("average_monthly_sales"));
        shop.setStatus((Boolean) map.get("status"));
        System.out.println("shop" + shop);
        return shop;
    }
}
