package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class OrderForm {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品id
     */
    private Integer goodsId;

    /**
     * 商店id
     */
    private Integer shopId;
    /**
     * 购买数量
     */
    private Integer amount;
    /**
     * 订单状态
     */
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
