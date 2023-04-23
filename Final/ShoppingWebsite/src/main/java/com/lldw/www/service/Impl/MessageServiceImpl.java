package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.MessageDaoImpl;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.service.MessageService;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-19 17:46:29
 */
public class MessageServiceImpl implements MessageService {



    MessageDaoImpl messageDao = new MessageDaoImpl();

    @Override
    public ArrayList<Message> getCommentByGoodsId(Goods goods) {
        return messageDao.getCommentByGoodsId(goods);
    }

    @Override
    public boolean addReminderPost(Message message) {
        //提醒信息(user关注的商店发动态，给店铺的所有粉丝发提醒)

        //先查询店铺的粉丝
        Shop shop = new Shop();
        shop.setShopId(message.getShopId());
        UserFollowShopServiceImpl ufsService = new UserFollowShopServiceImpl();

        //得到粉丝id  userIds
        ArrayList<Integer> userIds = ufsService.queryFansOfShop(shop);
        System.out.println("userIds:"+userIds);
        return messageDao.insertReminderPost(message,userIds)>0;
    }


    @Override
    public boolean addReminderGoodsLaunch(Message message) {

        //先查询店铺的粉丝
        Shop shop = new Shop();
        shop.setShopId(message.getShopId());
        UserFollowShopServiceImpl ufsService = new UserFollowShopServiceImpl();

        //得到fansIds
        ArrayList<Integer>userIds = ufsService.queryFansOfShop(shop);

        return messageDao.insertReminderGoodsLaunch(message,userIds)>0;
    }

    @Override
    public boolean addReminderGoodsPullOff(Message message){
//            return messageDao.insertReminderGoodsPullOff(message)

            return false;
        }

    @Override
    public boolean addPost(Message message) {
        System.out.println("addPost");
        //调用dao进行添加
        return messageDao.insertPost(message)>0;

    }

    @Override
    public boolean addGoodsComplaint(Message message) {
        return messageDao.insertGoodsComplaint(message)>0;
    }

    @Override
    public boolean addShopRegistration(Message message) {
        return messageDao.insertStoreRegistration(message)>0;
    }

    @Override
    public boolean addComment(Message message) {
        //商品评论
        System.out.println("addComment");
        //评论信息包含type,goods_id,shop_id,message_content,user_id,create_time
        //前端没有传入shopId 现在通过goodId查询goods对象 进而得到shopId
        GoodsServiceImpl goodsService = new GoodsServiceImpl();

        //new goods对象 设置参数
        Goods goods = new Goods();
        goods.setGoodsId(message.getGoodsId());

        Goods resultGoods = goodsService.queryGoodsByGoodsId(goods);
        if (resultGoods!=null){
            //查询到了商品 设置shopId
            message.setShopId(resultGoods.getShopId());
        }

        //调用dao进行添加
        int id =messageDao.insertComment(message);
        return id>0;
    }

    @Override
    public boolean addMessageNewProductLaunch(Message message) {
        return messageDao.insertMessageNewProductLaunch(message)>0;
    }

    @Override
    public boolean addMessageUserCharUser(Message message) {
        return messageDao.insertMessageUserChatUser(message)>0;
    }

    @Override
    public boolean addChatRoomMessage(Message message) {
        return messageDao.insertMessageUserChatShop(message)>0;
    }

    @Override
    public ArrayList<Message> queryChatMessageInShop(Shop shop) {
        return messageDao.getCharMessageByShopId(shop);
    }

    @Override
    public ArrayList<Message> queryShopRegistration() {
        return messageDao.queryShopRegistration();
    }

    @Override
    public boolean updateMessage(Message message) {
        return messageDao.updateMessage(message)>0;
    }

    @Override
    public ArrayList<Message> queryGoodsLaunch() {
        return messageDao.queryGoodsLaunch();
    }


}
