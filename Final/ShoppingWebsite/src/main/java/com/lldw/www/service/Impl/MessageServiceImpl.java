package com.lldw.www.service.Impl;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.dao.Impl.MessageDaoImpl;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.service.MessageService;

import java.util.ArrayList;

import static com.lldw.www.constants.MessageConstants.*;

/**
 * @author lldw
 * @date 2023-04-19 17:46:29
 */
public class MessageServiceImpl implements MessageService {

    MessageDaoImpl messageDao = new MessageDaoImpl();


    @Override
    public Message addMessage(Message message) {
        System.out.println("---MessageService.addMessage---");
        int id = 0;
        switch (message.getType()){
            case MESSAGE_TYPE_GOODS_COMPLAINT:
                id = messageDao.insertMessage1(message);
                break;
            case MESSAGE_TYPE_STORE_REGISTRATION:
                id =messageDao.insertMessage2(message);
                break;
            case MESSAGE_TYPE_NEW_PRODUCT_LAUNCH:
                System.out.println("insertMessage3");
                id =messageDao.insertMessage3(message);
                break;
            case MESSAGE_TYPE_REMINDER:
                id =messageDao.insertMessage4(message);
                break;
            case MESSAGE_TYPE_USER_CHAT_USER:
                id =messageDao.insertMessage5(message);
                break;
            case MESSAGE_TYPE_USER_CHAT_SHOP:
                id =messageDao.insertMessage6(message);
                break;
            case MESSAGE_TYPE_COMMENT:
                System.out.println("insertMessage7");

                //通过goodId查询goods 进而得到shopId
                GoodsServiceImpl goodsService = new GoodsServiceImpl();

                Goods goods = new Goods();
                goods.setGoodsId(message.getGoodsId());

                Goods resultGoods = goodsService.queryGoodsByGoodsId(goods);
                if (resultGoods!=null){
                    message.setShopId(resultGoods.getShopId());
                }
                id =messageDao.insertMessage7(message);
                break;
            case MESSAGE_TYPE_POST:
                System.out.println("insertMessage8");
                id =messageDao.insertMessage8(message);
                break;
            default:
                System.out.println("根据信息类型调用MessageDao.insert失败~");
                break;
        }
        if(id>0){
            //返回完整的message信息 包括id
            message.setMessageId(id);
            message = messageDao.getMessageByMessageId(message);
        }
        return id>0?message:null;
    }

    @Override
    public ArrayList<Message> getCommentByGoodsId(Goods goods) {
        return messageDao.getMessageByGoodsId(goods);
    }


}
