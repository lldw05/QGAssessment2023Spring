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
     * 返回信息success
     */
    public static final String SUCCESS = "success";

}
