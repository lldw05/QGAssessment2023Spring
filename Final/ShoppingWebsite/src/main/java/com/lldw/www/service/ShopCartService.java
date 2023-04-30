package com.lldw.www.service;

import com.lldw.www.po.ShopCart;
import com.lldw.www.po.User;
import com.lldw.www.vo.ShopCartVo;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author
 * @date
 */
public interface ShopCartService {
    /**
     * 添加商品进购物车
     * @param shopCart shopCart对象
     * @return 添加成功则返回shopCart对象(包括id) 否则返回null
     */
    ShopCart addIntoShopCart(ShopCart shopCart);

    /**
     * 批量删除shopCart
     * @param ids shopCart的id集合
     * @return 完全删除成功则返回true 否则返回false(包括有删除但没完全删除的情况)
     */
    boolean deleteInBatches(int[] ids);

    /**
     * 查询用户的购物车
     * @param user userId
     * @return 查询到了返回ShopCartVo集合 否则返回null
     */
    ArrayList<ShopCartVo> queryShopCart(User user);


}
