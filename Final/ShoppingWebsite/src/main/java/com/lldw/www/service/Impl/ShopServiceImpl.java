package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.ShopDaoImpl;
import com.lldw.www.dao.ShopDao;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.service.ShopService;
import com.lldw.www.service.UserService;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author
 * @date
 */
public class ShopServiceImpl implements ShopService {
    ShopDaoImpl shopDao = new ShopDaoImpl();



    @Override
    public Shop register(Shop shop) {
        System.out.println("---ShopService.register---");

        //shopName不可以重复 查询shopName是否被使用
        Shop shopByShopName = shopDao.getShopByShopName(shop);

        //判断是否查询到结果
        if(shopByShopName!=null){
            //查询到了 说明shopName已经被使用 返回null表示注册失败
            System.out.println("ERROR:shopName存在");
            return null;
        }

        //shopName未被使用 进行注册
        int cnt = shopDao.insertShop(shop);
        System.out.println("shopRegister,cnt:"+cnt);

        if(cnt>0){
            //成功注册 将店主的roleId设为2
            User user =new User();
            user.setRoleId(2);
            user.setUserId(shop.getShopKeeperId());
            UserServiceImpl userService = new UserServiceImpl();
            userService.updateUser(user);
        }
        //影响行数大于0 返回新添加的shop对象 否则返回null
        return cnt>0?shopDao.getShopByShopName(shop):null;
    }
}
