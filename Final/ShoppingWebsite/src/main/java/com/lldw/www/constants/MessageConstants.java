package com.lldw.www.constants;

/**
 * @author lldw
 * @date 2023-04-19 16:56:09
 */
public class MessageConstants {
    /**
     * 信息类型:商品投诉举报
     */
    public static final int MESSAGE_TYPE_GOODS_COMPLAINT = 1;
    /**
     *信息类型:商店注册申请
     */
    public static final int MESSAGE_TYPE_STORE_REGISTRATION = 2;
    /**
     *信息类型:新品上市申请
     */
    public static final int MESSAGE_TYPE_NEW_PRODUCT_LAUNCH = 3;
    /**
     *信息类型:提醒信息:商品下架
     */
    public static final int MESSAGE_TYPE_REMINDER_GOODS_PULL_OFF = 10;
    /**
     *信息类型:提醒信息:商品发布
     */
    public static final int MESSAGE_TYPE_REMINDER_GOODS_LAUNCH = 9;
    /**
     *信息类型:提醒信息:user关注的商店发布动态
     */
    public static final int MESSAGE_TYPE_REMINDER_POST = 4;
    /**
     *信息类型:用户与用户聊天信息
     */
    public static final int MESSAGE_TYPE_USER_CHAT_USER = 5;
    /**
     *信息类型:用户和商店聊天信息
     */
    public static final int MESSAGE_TYPE_USER_CHAT_SHOP = 6;
    /**
     *信息类型:商品评论
     */
    public static final int MESSAGE_TYPE_COMMENT = 7;
    /**
     *发布动态
     */
    public static final int MESSAGE_TYPE_POST = 8;
    /**
     * 信息发送者:商店
     */
    public static final String SENDER_SHOP = "shop";
    /**
     *信息发送者:用户
     */
    public static final String SENDER_USER = "user";
    /**
     *
     */
    public static final boolean MESSAGE_IS_ACTIVE = true;
    /**
     *
     */
    public static final boolean MESSAGE_NO_ACTIVE = false;
    /**
     *信息是否处理:true代表已经处理
     */
    public static final boolean MESSAGE_IS_PROCESSED = true;
    /**
     *信息是否处理:false代表未处理
     */
    public static final boolean MESSAGE_NO_PROCESSED = false;
}
