package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;

import java.util.ArrayList;

/**
 * @author
 * @date
 */
public interface MessageService {


    /**
     *  通过goodsId查询商品的评价信息
     * @param goods goodsId
     * @return 查找到了则返回message 否则返回null
     */
    ArrayList<Message> getCommentByGoodsId(Goods goods);

    /**
     * 添加提醒信息 店铺发动态 给粉丝发提醒
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addReminderPost(Message message);

    /**
     * 添加提醒信息(user关注的店铺新品上市)
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addReminderGoodsLaunch(Message message);

    /**
     * 添加商品下架提醒信息
     * @param message goodsId,shopId,shopKeeperId(即userId),messageContent
     * @return 添加成功则返回true 否则返回null
     */
    boolean addReminderGoodsPullOff(Message message);

    /**
     * 商店发布动态
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addPost(Message message);


    /**
     * 商品投诉举报
     * @param message m
     * @return 添加成功则返回true 否则返回null
     */
    boolean addGoodsComplaint(Message message);


    /**
     * 新店铺注册
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addShopRegistration(Message message);


    /**
     * 商品评论
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addComment(Message message);


    /**
     * 店铺上市新商品
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addMessageNewProductLaunch(Message message);


    /**
     * 用户聊天信息
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addMessageUserCharUser(Message message);


    /**
     * 用户和商店聊天信息
     * @param message message
     * @return 添加成功则返回true 否则返回null
     */
    boolean addChatRoomMessage(Message message);

    /**
     *  根据商店id查询商店聊天室的信息
     * @param shop shopId
     * @return 查询到了则返回Message集合 否则返回null
     */
    ArrayList<Message> queryChatMessageInShop(Shop shop);

    /**
     * 查询商店注册的审核信息
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryShopRegistration();

    /**
     * 更新信息
     * @param message message对象
     * @return 返回是否更新成功 如果不存在该信息 也会更新不成功
     */
    boolean updateMessage(Message message);

    /**
     * 查询商品上市审核信息
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message>  queryGoodsLaunch();

    /**
     * 查询投诉信息
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryComplaint();

    /**
     * 查询用户的信息
     * @param user userId
     * @return 查询到了 返回message集合 否则返回null
     */
    ArrayList<Message> queryMessageOfUser(User user);

    /**
     * 查询所有评论信息
     * @return 返回所有评论信息
     */
    ArrayList<Message> queryAllComment();
}
