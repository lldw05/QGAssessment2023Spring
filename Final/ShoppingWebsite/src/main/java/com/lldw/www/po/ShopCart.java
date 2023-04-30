package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class ShopCart {
    private Integer shopCartId;
    private Integer goodsId;
    private Integer userId;
    private Integer amount;

    public ShopCart() {
    }

    public Integer getShopCartId() {
        return shopCartId;
    }

    public void setShopCartId(Integer shopCartId) {
        this.shopCartId = shopCartId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "shopCartId=" + shopCartId +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
