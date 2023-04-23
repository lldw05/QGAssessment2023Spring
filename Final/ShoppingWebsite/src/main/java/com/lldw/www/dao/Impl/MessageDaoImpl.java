package com.lldw.www.dao.Impl;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
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
    public int insertGoodsComplaint(Message message) {
        return 0;
    }

    @Override
    public int insertStoreRegistration(Message message) {
        return ju.insert("insert into message (type, user_id,shop_id,message_content) values (?,?,?,?) "
                , message.getType(), message.getUserId(), message.getShopId(), message.getMessageContent());
    }

    @Override
    public int insertMessageNewProductLaunch(Message message) {
        return ju.insert("insert into message (type, goods_id,shop_id,message_content) values (?,?,?,?) "
                , message.getType(), message.getGoodsId(), message.getShopId(), message.getMessageContent());
    }

    @Override
    public int insertReminderGoodsLaunch(Message message, ArrayList<Integer> userIds) {
        int cnt =0;
        for (Integer id :
                userIds) {
            if(ju.insert("insert into message (type,goods_id,user_id,message_content) values(?,?,?,?)"
                    ,message.getType(),message.getGoodsId(),message.getUserId(),message.getMessageContent())>0){
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public int insertReminderPost(Message message,ArrayList<Integer> userIds) {
        int cnt =0;
        for (Integer id :
                userIds) {
            if(ju.insert("insert into message (type,shop_id,user_id,message_content) values(?,?,?,?)"
                    ,message.getType(),message.getShopId(), id,message.getMessageContent())>0){
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public int insertReminderGoodsPullOff(Message message) {
        return 0;
    }

    @Override
    public int insertMessageUserChatUser(Message message) {
        return 0;
    }

    @Override
    public int insertMessageUserChatShop(Message message) {
        return ju.insert("insert into message (type,sender_type,shop_id,user_id,message_content,create_time) values (?,?,?,?,?,?)",
                message.getType(),message.getSenderType(),message.getShopId()
                ,message.getUserId(),message.getMessageContent(),message.getCreateTime());
    }

    @Override
    public int insertComment(Message message) {
        return ju.insert("insert into message (type,goods_id,shop_id,message_content,user_id,create_time) values (?,?,?,?,?,?) "
                , message.getType(), message.getGoodsId(), message.getShopId()
                , message.getMessageContent(), message.getUserId(), message.getCreateTime());
    }

    @Override
    public int insertPost(Message message) {
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
    public ArrayList<Message> getMessagesFromMapList(ArrayList<Map<String, Object>> maps) {
        //判断集合是否为空 为空则直接返回null
        if (maps == null) {
            return null;
        }

        ArrayList<Message> messageArrayList = new ArrayList<>();
        for (Map<String, Object> map :
                maps) {
            messageArrayList.add(getMessageFromMap(map));
        }
        System.out.println("messageArrayList:" + messageArrayList);
        return messageArrayList;
    }

    @Override
    public ArrayList<Message> getCommentByGoodsId(Goods goods) {
        System.out.println("---MessageDao.getCommentByGoodsId---");
        ArrayList<Map<String, Object>> mapList = ju.execQueryList("select * from message where type = ? and goods_id = ?"
                , new Object[]{MessageConstants.MESSAGE_TYPE_COMMENT, goods.getGoodsId()});


        return getMessagesFromMapList(mapList);
    }

    @Override
    public ArrayList<Message> getCharMessageByShopId(Shop shop) {
        System.out.println("---MessageDao.getCharMessageByShopId---");

        ArrayList<Map<String, Object>> maps = ju.execQueryList("select * from message where type = ? and sender_type = ? and shop_id = ?"
                , new Object[]{MessageConstants.MESSAGE_TYPE_USER_CHAT_SHOP, MessageConstants.SENDER_SHOP, shop.getShopId()});

        return getMessagesFromMapList(maps);
    }
}
