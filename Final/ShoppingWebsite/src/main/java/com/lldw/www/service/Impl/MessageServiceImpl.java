package com.lldw.www.service.Impl;

import com.lldw.www.dao.Impl.MessageDaoImpl;
import com.lldw.www.dao.MessageDao;
import com.lldw.www.po.Message;
import com.lldw.www.service.MessageService;

/**
 * @author lldw
 * @date
 */
public class MessageServiceImpl implements MessageService {
    MessageDaoImpl messageDao = new MessageDaoImpl();
    @Override
    public Message addMessage(Message message) {
        System.out.println("---MessageService.addMessage---");
        int cnt = 0;
        switch (message.getType()){
            case 1:
                cnt = messageDao.insertMessage1(message);
                break;
            case 2:
                cnt =messageDao.insertMessage2(message);
                break;
            case 3:
                cnt =messageDao.insertMessage3(message);
                break;
            case 4:
                cnt =messageDao.insertMessage4(message);
                break;
            case 5:
                cnt =messageDao.insertMessage5(message);
                break;
            case 6:
                cnt =messageDao.insertMessage6(message);
                break;
            case 7:
                cnt =messageDao.insertMessage7(message);
                break;
            default:
                System.out.println("根据信息类型调用Dao失败~");
                break;
        }
        return cnt>0?message:null;
    }
}
