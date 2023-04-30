package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.ShopCartDaoImpl;
import com.lldw.www.dao.Impl.ShopDaoImpl;
import com.lldw.www.dao.ShopCartDao;
import com.lldw.www.po.ShopCart;
import com.lldw.www.po.User;
import com.lldw.www.service.ShopCartService;
import com.lldw.www.vo.ShopCartVo;

import java.util.ArrayList;

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

    @Override
    public ArrayList<ShopCartVo> queryShopCart(User user) {
        System.out.println("---ShopCartService.queryShopCart");
        //判断user是否为空
        if(user==null){
            return null;
        }
        ArrayList<ShopCart> shopCartArrayList = shopCartDao.queryShopCartByUserId(user);

        //判断查询结果是否为null
        if(shopCartArrayList==null){
            return null;
        }

        System.out.println("得到ShopCartList:"+shopCartArrayList);
        //不为空 返回Vo
        ArrayList<ShopCartVo> voList = new ArrayList<>();
        for (ShopCart sc :
                shopCartArrayList) {
            voList.add(new ShopCartVo(sc));
        }
        return voList;
    }
}
