package com.lldw.www.service;

import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;

import java.util.ArrayList;
import java.util.Arrays;

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
     * 添加提醒信息(user的店铺 商品被下架了)
     * @param message message
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
}
