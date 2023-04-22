package com.lldw.www.service.Impl;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.constants.RoleId;
import com.lldw.www.dao.Impl.ShopDaoImpl;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.service.ShopService;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public class ShopServiceImpl implements ShopService {
    ShopDaoImpl shopDao = new ShopDaoImpl();
    MessageServiceImpl messageService = new MessageServiceImpl();

    @Override
    public Shop register(Shop shop) {
        System.out.println("---ShopService.register---");

        //shopName不可以重复 查询shopName是否被使用
        Shop shopByShopName = shopDao.getShopByShopName(shop);

        //判断是否查询到结果
        if (shopByShopName != null) {
            //查询到了 说明shopName已经被使用 返回null表示注册失败
            System.out.println("ERROR:shopName存在");
            return null;
        }

        //shopName未被使用 进行注册 返回值为id 或 0
        int resultId = shopDao.insertShop(shop);
        System.out.println("shopRegister,resultId:" + resultId);

        shop = shopDao.getShopByShopName(shop);
        if (resultId > 0) {
            //成功注册 将店主的roleId设为2
            User user = new User();
            user.setRoleId(RoleId.SHOPKEEPER);
            user.setUserId(shop.getShopKeeperId());
            UserServiceImpl userService = new UserServiceImpl();
            user = userService.updateUser(user);
            if(user!=null){
                System.out.println("将店主的roleId设为2:设置成功");
            }

            //新增一条 新店铺注册的信息 等网管通过
            Message message = new Message();
            //设置message 传入messageType shopId shopKeepId
            message.setType(MessageConstants.MESSAGE_TYPE_STORE_REGISTRATION);
            message.setShopId(shop.getShopId());
            message.setUserId(shop.getShopKeeperId());
            message.setMessageContent("新店铺注册!");

            //调用messageService
            MessageServiceImpl messageService = new MessageServiceImpl();
            message = messageService.addMessage(message);
            if(message!=null){
                System.out.println("添加一条申请店铺待审核信息:添加成功");
            }
        }


        //影响行数大于0 返回新添加的shop对象 否则返回null
        return resultId > 0 ? shopDao.getShopByShopName(shop) : null;
    }

    @Override
    public Shop showShopMessage(Shop shop) {
        return shopDao.getShopByShopName(shop);
    }

    @Override
    public ArrayList<Goods> showShopGoods(Shop shop) {

        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        if(shop.getShopId()==null){
            //如果shopId没传进来 先通过shopName查询shopId
            shop.setShopId(shopDao.getShopByShopName(shop).getShopId());
        }
        return goodsService.queryGoodsOfShop(shop);
    }


    @Override
    public Goods addGoods(Goods goods) {
        System.out.println("---ShopService.addGoods---");

        //从ShooService到GoodsService
        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        Goods resultGoods = goodsService.addGoods(goods);
        System.out.println("resultGoods:"+resultGoods);
        if(resultGoods!=null){

            //添加成功 新增一条添加商品的审核信息
            //从从ShooService到MessageService

            //添加数据
            Message message = new Message();
            message.setType(MessageConstants.MESSAGE_TYPE_NEW_PRODUCT_LAUNCH);
            message.setGoodsId(resultGoods.getGoodsId());
            message.setShopId(resultGoods.getShopId());
            message.setMessageContent("新品上市!");

            //调用messageService
            if(messageService.addMessage(message)!=null){
                System.out.println("添加商品审核信息成功");
            }
        }
        return resultGoods;
    }

    @Override
    public Message sendPost(Message message) {
        System.out.println("---ShopService.sendPost---");

        Message resultMessage = messageService.addMessage(message);
        if(resultMessage!=null){
            System.out.println("发布动态成功");
        }
        return resultMessage;
    }
}
