package com.lldw.www.dao;

import com.lldw.www.constants.MessageType;
import com.lldw.www.po.Message;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public interface MessageDao {
    /**
     *  用于添加message
     * @param message message对象
     * @param messageType message类型
     * @return 返回添加数据的条数
     */
    int insertMessage(Message message, MessageType messageType);

    /**
     * 用于删除message
     * @param message message对象
     * @return 返回删除message的个数
     */
    int deleteMessage(Message message);

    /**
     * 用于更新message
     * @param message message对象
     * @return 返回修改的行数
     */
    int updateMessage(Message message);

    /**
     * 用于查询message
     * @param message message对象
     * @return 返回封装的message对象
     */
    Message getMessageByMessageId(Message message);


    /**
     * //用于查询message列表
     * @return message集合
     */
    ArrayList<Object> getMessageList();

    /**
     * //用于查询message列表数量
     * @return message列表数量
     */
    int getMessageCount();



    /**
     *  新增 举报信息
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage1(Message message);

    /**
     *  新增 新增店铺信息
     * @param message  type, user_id,shop_id,message_content
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage2(Message message);
    /**
     *  新增 新品上市的信息
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage3(Message message);
    /**
     *  新增 提醒信息
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage4(Message message);
    /**
     *  新增 用户聊天信息
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage5(Message message);
    /**
     *  新增 用户和商店聊天信息
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage6(Message message);
    /**
     *  新增 商品评论
     * @param message 封装的message对象
     * @return 添加成功 则返回影响行数 添加失败 返回null
     */
    int insertMessage7(Message message);
}
