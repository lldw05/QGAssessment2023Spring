package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public interface MessageService {
    /**
     *  添加信息 传入messageType 以及与type相关的内容
     * @param message 封装的message对象
     * @return 添加成功则返回完整的message信息(包括id )否则返回null
     */
    public Message addMessage(Message message);

    /**
     *  通过goodsId查询商品的评价信息
     * @param goods goodsId
     * @return 查找到了则返回message 否则返回null
     */
    ArrayList<Message> getCommentByGoodsId(Goods goods);


}
