package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class UserFollowShop {
    private Integer id;
    private Integer userId;
    private Integer shopId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "UserFollowShop{" +
                "id=" + id +
                ", userId=" + userId +
                ", shopId=" + shopId +
                '}';
    }
}
