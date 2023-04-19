package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.MessageDaoImpl;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Message;
import com.lldw.www.service.MessageService;

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
            case 1:
                id = messageDao.insertMessage1(message);
                break;
            case 2:
                id =messageDao.insertMessage2(message);
                break;
            case 3:
                System.out.println("insertMessage3");
                id =messageDao.insertMessage3(message);
                break;
            case 4:
                id =messageDao.insertMessage4(message);
                break;
            case 5:
                id =messageDao.insertMessage5(message);
                break;
            case 6:
                id =messageDao.insertMessage6(message);
                break;
            case 7:
                id =messageDao.insertMessage7(message);
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
}
