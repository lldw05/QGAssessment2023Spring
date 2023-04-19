package com.lldw.www.dao.Impl;

import com.lldw.www.constants.MessageType;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Message;
import com.lldw.www.utils.JdbcUtils;

import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-19 17:46:36
 */
public class MessageDaoImpl implements MessageDao {

    JdbcUtils ju = JdbcUtils.getInstance();

    @Override
    public int insertMessage(Message message, MessageType messageType) {
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
        return null;
    }

    @Override
    public ArrayList<Object> getMessageList() {
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
        return ju.update("insert into message (type, user_id,shop_id,message_content) values (?,?,?,?) "
        ,message.getType(),message.getUserId(),message.getShopId(),message.getMessageContent());
    }

    @Override
    public int insertMessage3(Message message) {
        return 0;
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
        return 0;
    }
}
