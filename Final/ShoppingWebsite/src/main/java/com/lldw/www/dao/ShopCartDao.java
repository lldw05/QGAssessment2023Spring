package com.lldw.www.dao;

import com.lldw.www.po.ShopCart;
import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-4-20 16:12:32
 */
public interface ShopCartDao {
    /**
     *  添加购物车
     * @param shopCart shopCart对象
     * @return 添加成功返回主键id的值  插入失败时返回0
     */
    int insertShopCart(ShopCart shopCart);

    /**
     *  批量删除购物车
     * @param ids ids
     * @return 返回影响的行数
     */
    int deleteByIds(int[] ids);

    /**
     * 更新购物车 一般为更新数量
     * @param shopCart amount
     * @return 返回影响的行数
     */
    int updateShopCart(ShopCart shopCart);

    /**
     * 查询用户的购物车
     * @param user userId
     * @return 返回shopCart集合
     */
    ArrayList<ShopCart> queryShopCartByUserId(User user);

    /**
     *  通过shopCartId查询shopCart
     * @param shopCart shopCartId
     * @return 查询到了则返回shopCart对象 否则返回null
     */
    ShopCart queryShopCartById(ShopCart shopCart);

    /**
     * 将放到map中的查询结果封装成shopCart对象返回
     * @param map map
     * @return shopCart对象
     */
    ShopCart getShopCartFromMap(Map<String, Object> map);

    /**
     *从mapList集合中获取shopCart 并放到list集合中返回
     * @param maps maps
     * @return 返回list集合
     */
    ArrayList<ShopCart> getShopCartListFromMapList(ArrayList<Map<String, Object>> maps);
}
