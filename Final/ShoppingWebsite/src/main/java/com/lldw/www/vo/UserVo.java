package com.lldw.www.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lldw.www.po.User;

/**
 * 比user类少了 password和支付密码
 * @author lldw
 * @date 2023-04-30 15:24:26
 */
public class UserVo extends User {

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * 地址
     */
    private String address;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 真是姓名
     */
    private String realName;

    /**
     * 支付密码
     */
    @JSONField(serialize = false)
    private String payPassword;

    /**
     * 图片id
     */
    private Integer pictureId;
    /**
     * 商店id
     */
    private Integer  shopId;
    /**
     * 是否启用
     */
    private boolean isActive;
    /**
     * 角色id
     */
    private Integer roleId;

    public UserVo() {
    }

    /**
     * user转userVo
     * @param user
     */
    public UserVo(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.address = user.getAddress();
        this.phoneNumber = user.getPhoneNumber();
        this.realName = user.getRealName();
        this.pictureId = user.getPictureId();
        this.shopId = user.getShopId();
        this.isActive = user.isActive();
        this.roleId = user.getRoleId();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", realName='" + realName + '\'' +
                ", pictureId=" + pictureId +
                ", roleId=" + roleId +
                '}';
    }
}
