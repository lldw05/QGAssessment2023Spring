package com.lldw.www.dao;

import com.lldw.www.constants.MessageConstants;
import com.lldw.www.po.Message;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-04-19 17:46:23
 */
public interface MessageDao {
    /**
     *  用于添加message
     * @param message message对象
     * @param messageType message类型
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage(Message message, MessageConstants messageType);

    /**
     * 用于删除message
     * @param message message对象
     * @return 返回影响的行数
     */
    int deleteMessage(Message message);

    /**
     * 用于更新message
     * @param message message对象
     * @return 返回影响的行数
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
    ArrayList<Message> getMessageList();

    /**
     * //用于查询message列表数量
     * @return message列表数量
     */
    int getMessageCount();



    /**
     *  新增 举报信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage1(Message message);

    /**
     *  新增 新增店铺信息
     * @param message  type, user_id,shop_id,message_content
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage2(Message message);
    /**
     *  新增 新品上市的信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage3(Message message);
    /**
     *  新增 提醒信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage4(Message message);
    /**
     *  新增 用户聊天信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage5(Message message);
    /**
     *  新增 用户和商店聊天信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage6(Message message);
    /**
     *  新增 商品评论
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage7(Message message);
    /**
     *  新增 发布动态
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessage8(Message message);

    /**
     *  将放到map中的查询结果封装成message对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回message对象
     */
    Message getMessageFromMap(Map<String, Object> map);
}
