package com.lldw.www.constants;

/**
 * @author
 * @date
 */
public class ResultConstants {
    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 1;
    /**
     * 错误状态码
     */
    public static final int ERROR_CODE = 0;
    /**
     * 返回jwt令牌时所插入字符串
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 验证码错误
     */
    public static final String VERIFY_CODE_ERROR = "验证码错误";
    /**
     * 修改用户信息
     */
    public static final String UPDATE_USER_INFORMATION_ERROR = "修改信息失败(可能是手机号码格式不对)";
    /**
     * 注册时 用户名重名
     */
    public static final String REGISTER_ERROR = "用户名已存在(或用户名或密码为空)";
    /**
     * 登录失败时返回的消息
     */
    public static final String LOGIN_ERROR = "用户名或密码错误";
    /**
     * 校验支付密码是否正确时 返回的消息
     */
    public static final String USER_PAY_PASSWORD_ERROR = "支付密码错误";
    /**
     * 查询购物车 返回的消息
     */
    public static final String SHOP_CART_QUERY_ERROR = "您的购物车暂时是空的哦~";
    /**
     * 查询订单结果为空 返回消息
     */
    public static final String ORDER_QUERY_ERROR = "您暂时还没有订单哦~";
    /**
     * 无商店注册申请 返回消息
     */
    public static final String QUERY_SHOP_REGISTRATION_ERROR = "暂时还没有未处理的注册店铺申请哦~";
    /**
     * 无架商品申请 返回消息
     */
    public static final String QUERY_GOODS_LAUNCH_ERROR = "暂时还没有未处理的上架商品申请哦~";
    public static final String QUERY_NO_COMMENT_ERROR = "暂时还没有任何评论哦~";
    public static final String QUERY_NO_COMPLAINT_ERROR = "暂时还没有任何投诉信息哦~";
    /**
     * 删除购物车失败 返回消息
     */
    public static final String SHOP_CART_DELETE_ERROR = "删除购物车失败~";
    /**
     * 修改信息失败 返回消息
     */
    public static final String MESSAGE_UPDATE_ERROR = "修改信息失败~";
    /**
     * 返回信息success
     */
    public static final String SUCCESS = "success";

}
