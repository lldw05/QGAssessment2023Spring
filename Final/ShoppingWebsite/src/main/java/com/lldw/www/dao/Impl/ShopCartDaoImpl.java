package com.lldw.www.dao.Impl;

import com.lldw.www.dao.ShopCartDao;
import com.lldw.www.po.ShopCart;
import com.lldw.www.po.User;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public class ShopCartDaoImpl implements ShopCartDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertShopCart(ShopCart shopCart) {
        if (shopCart.getAmount() == null) {
            return 0;
        }
        return ju.insert("insert into shop_cart (goods_id,user_id,amount) values (?,?,?)",
                shopCart.getGoodsId(), shopCart.getUserId(), shopCart.getAmount());
    }

    @Override
    public int deleteByIds(int[] ids) {
        System.out.println("---ShopCartDaoImpl.deleteByIds---");
        //手动写sql语句
        StringBuilder sql = new StringBuilder("(");
        for (int i = 0; i < ids.length; i++) {
            sql.append(ids[i]);
            sql.append(",");

        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        System.out.println("删除范围:" + sql);
        return ju.update("delete from shop_cart where shop_cart_id in " + sql);
    }

    @Override
    public int updateShopCart(ShopCart shopCart) {
        return 0;
    }

    @Override
    public ArrayList<ShopCart> queryShopCartByUserId(User user) {
        ArrayList<Map<String, Object>> maps =
                ju.execQueryList("select * from shop_cart where user_id =?", new Object[]{user.getUserId()});
        return getShopCartListFromMapList(maps);
    }

    @Override
    public ShopCart queryShopCartById(ShopCart shopCart) {

        System.out.println("---ShopCartDao.queryShopCartById");

        ArrayList<Map<String, Object>> maps =
                ju.execQueryList("select * from shop_cart where shop_cart_id =?", new Object[]{shopCart.getShopCartId()});
        //判断集合是否为空
        if (maps == null) {
            return null;
        }
        //不为空 取出第一个
        ShopCart resultShopCart = getShopCartFromMap(maps.get(0));

        System.out.println("resultShopCart" + resultShopCart);
        return resultShopCart;
    }

    @Override
    public ShopCart getShopCartFromMap(Map<String, Object> map) {

        //从map中获取值
        ShopCart shopCart = new ShopCart();
        shopCart.setShopCartId((Integer) map.get("shop_cart_id"));
        shopCart.setGoodsId((Integer) map.get("goods_id"));
        shopCart.setAmount((Integer) map.get("amount"));
        shopCart.setUserId((Integer) map.get("user_id"));

        return shopCart;
    }

    @Override
    public ArrayList<ShopCart> getShopCartListFromMapList(ArrayList<Map<String, Object>> maps) {

        //判断集合是否为空
        if (maps == null) {
            return null;
        }
        //遍历maps 取出shopCart
        ArrayList<ShopCart> shopCartArrayList = new ArrayList<>();
        for (Map<String, Object> map :
                maps) {
            shopCartArrayList.add(getShopCartFromMap(map));
        }

        return shopCartArrayList;
    }


}
