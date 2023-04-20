package com.lldw.www.dao.Impl;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.utils.JdbcUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-04-19 17:46:36
 */
public class MessageDaoImpl implements MessageDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertMessage(Message message, MessageConstants messageType) {
        return 0;
    }

    @Override
    public int deleteMessage(Message message) {
        return 0;
    }

    @Override
    public int updateMessage(Message message) {
        return 0;
    }

    @Override
    public Message getMessageByMessageId(Message message) {
        ArrayList<Map<String, Object>> mapList = ju.execQueryList("select * from message where message_id = ?"
                , new Object[]{message.getMessageId()});
        if (mapList == null) {
            return null;
        }
        Message resultMessage = this.getMessageFromMap(mapList.get(0));
        return resultMessage;
    }

    @Override
    public ArrayList<Message> getMessageList() {
        return null;
    }

    @Override
    public int getMessageCount() {
        return 0;
    }

    @Override
    public int insertMessage1(Message message) {
        return 0;
    }

    @Override
    public int insertMessage2(Message message) {
        return ju.insert("insert into message (type, user_id,shop_id,message_content) values (?,?,?,?) "
                , message.getType(), message.getUserId(), message.getShopId(), message.getMessageContent());
    }

    @Override
    public int insertMessage3(Message message) {
        return ju.insert("insert into message (type, goods_id,shop_id,message_content) values (?,?,?,?) "
                , message.getType(), message.getGoodsId(), message.getShopId(), message.getMessageContent());
    }

    @Override
    public int insertMessage4(Message message) {
        return 0;
    }

    @Override
    public int insertMessage5(Message message) {
        return 0;
    }

    @Override
    public int insertMessage6(Message message) {
        return 0;
    }

    @Override
    public int insertMessage7(Message message) {
        return ju.insert("insert into message (type,goods_id,shop_id,message_content,user_id,create_time) values (?,?,?,?,?,?) "
                , message.getType(), message.getGoodsId(), message.getShopId()
                , message.getMessageContent(), message.getUserId(), message.getCreateTime());
    }

    @Override
    public int insertMessage8(Message message) {
        return ju.insert("insert into message (type,sender_type,goods_id,shop_id,message_content,user_id,create_time) values (?,?,?,?,?,?,?) "
                , message.getType(), message.getSenderType(), message.getGoodsId(), message.getShopId()
                , message.getMessageContent(), message.getUserId(), message.getCreateTime());
    }

    public Message getMessageFromMap(Map<String, Object> map) {
        System.out.println("---MessageDao.getMessageFromMap---");

        //获取数据
        Message message = new Message();
        message.setMessageId((Integer) map.get("message_id"));
        message.setType((Integer) map.get("type"));
        message.setSenderType((String) map.get("sender_type"));
        message.setUserId((Integer) map.get("user_id"));
        message.setGoodsId((Integer) map.get("goods_id"));
        message.setShopId((Integer) map.get("shop_id"));
        message.setMessageContent((String) map.get("message_content"));
        message.setCreateTime((LocalDateTime) map.get("create_time"));
        message.setProcessed((Boolean) map.get("is_processed"));

        return message;

    }

    @Override
    public ArrayList<Message> getMessageByGoodsId(Goods goods) {
        System.out.println("---MessageDao.getMessageByGoodsId---");
        ArrayList<Message> messageArrayList = new ArrayList<>();
        ArrayList<Map<String, Object>> mapList = ju.execQueryList("select * from message where type = ? and goods_id = ?"
                , new Object[]{MessageConstants.MESSAGE_TYPE_COMMENT, goods.getGoodsId()});
        //判断集合是否为空 为空则直接返回null
        if (mapList == null) {
            return null;
        }
        for (Map<String, Object> map :
                mapList) {
            messageArrayList.add(getMessageFromMap(map));
        }
        System.out.println("messageArrayList:" + messageArrayList);
        return messageArrayList;
    }
}
