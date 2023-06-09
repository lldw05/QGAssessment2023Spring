package com.lldw.www.dao;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author lldw
 * @date 2023-04-19 17:46:23
 */
public interface MessageDao {


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
     * //用于查询user的提醒信息 如Reminder信息一类
     * @param user userId
     * @return message集合
     */
    ArrayList<Message> getReminderMessageListOfUser(User user);

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
    int insertGoodsComplaint(Message message);

    /**
     *  新增 新增店铺信息
     * @param message  type, user_id,shop_id,message_content
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertStoreRegistration(Message message);
    /**
     *  新增 新品上市的信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessageNewProductLaunch(Message message);

    /**
     *  新增 提醒信息(user关注的商店发布新商品,给店铺的所有粉丝发提醒)
     * @param message 封装的message对象
     * @param userIds 粉丝Ids
     * @return 插入成功返回插入条数  插入失败时返回0
     */
    int insertReminderGoodsLaunch(Message message,ArrayList<Integer> userIds);


    /**
     *  新增 提醒信息  (user关注的商店发动态，给店铺的所有粉丝发提醒)
     * @param message 封装的message对象
     * @param userIds 粉丝Ids
     * @return 插入成功返回插入条数   插入失败时返回0
     */
    int insertReminderPost(Message message,ArrayList<Integer> userIds);


    /**
     *  新增 提醒信息  商品下架
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertReminderGoodsPullOff(Message message);


    /**
     *  新增 用户聊天信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessageUserChatUser(Message message);


    /**
     *  新增 用户和商店聊天信息
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertMessageUserChatShop(Message message);


    /**
     *  新增 商品评论
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertComment(Message message);


    /**
     *  新增 发布动态
     * @param message 封装的message对象
     * @return 插入成功返回主键id的值  插入失败时返回0
     */
    int insertPost(Message message);


    /**
     *  将放到map中的查询结果封装成message对象返回
     * @param map 将jdbc查询到的每个map传入
     * @return 返回message对象
     */
    Message getMessageFromMap(Map<String, Object> map);

    /**
     * 将存有map的list集合传入 转化为装有message对象的list集合 该函数会进行判断传进来的mapList是否为空
     * @param maps 将jdbc查询到的每个map传入
     * @return mapList为空则返回null 否则返回message对象的集合
     */
    ArrayList<Message> getMessageListFromMapList(ArrayList<Map<String, Object>> maps);


    /**
     * 根据goodsId查询商品评论
     * @param goods GoodsId
     * @return 查询到了则返回Message集合 否则返回null
     */
    ArrayList<Message> getCommentByGoodsId(Goods goods);
    /**
     * 根据shopId查询聊天室信息
     * @param shop shopId
     * @return 查询到了则返回Message集合 否则返回null
     */
    ArrayList<Message> getCharMessageByShopId(Shop shop);

    /**
     *  查询商店注册的审核信息
     *  @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryShopRegistration();

    /**
     * 查询商品上市的审核信息
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryGoodsLaunch();

    /**
     * 查询 商品上市的审核信息 商店注册的审核信息 投诉信息
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryComplaint();

    /**
     * 查询所有评论
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryAllComment();
}
