package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class OrderForm {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer shopId;
    private Integer amount;
    private String status;

    @Override
    public String toString() {
        return "OrderForm{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", shopId=" + shopId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }

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

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
