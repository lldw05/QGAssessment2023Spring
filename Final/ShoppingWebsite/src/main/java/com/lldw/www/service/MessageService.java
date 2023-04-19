package com.lldw.www.service;

import com.lldw.www.po.Message;

/**
 * @author
 * @date
 */
public interface MessageService {
    /**
     *  添加信息 传入messageType 以及与type相关的内容
     * @param message 封装的message对象
     * @return 添加成功则返回message对象 否则返回null
     */
    public Message addMessage(Message message);
}