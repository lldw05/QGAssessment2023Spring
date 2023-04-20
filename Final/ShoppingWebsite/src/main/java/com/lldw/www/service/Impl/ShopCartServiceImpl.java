package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.ShopCartDaoImpl;
import com.lldw.www.dao.Impl.ShopDaoImpl;
import com.lldw.www.dao.ShopCartDao;
import com.lldw.www.po.ShopCart;
import com.lldw.www.service.ShopCartService;

/**
 * @author
 * @date
 */
public class ShopCartServiceImpl implements ShopCartService {

    ShopCartDaoImpl shopCartDao = new ShopCartDaoImpl();

    @Override
    public ShopCart addIntoShopCart(ShopCart shopCart) {
        int id = shopCartDao.insertShopCart(shopCart);
        if(id>0){
            shopCart.setShopCartId(id);
            shopCart = shopCartDao.queryShopCartById(shopCart);
            System.out.println("返回完整的shopCart对象(包括id):"+shopCart);
        }
        return id>0?shopCart:null;
    }

    @Override
    public boolean deleteInBatches(int[] ids) {
        System.out.println("---ShopCartServiceImpl.deleteInBatches---");
        int cnt = shopCartDao.deleteByIds(ids);

        return cnt == ids.length;
    }
}
