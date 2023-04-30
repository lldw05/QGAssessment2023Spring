package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class Goods {
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 商品介绍
     */
    private String goodsIntroduction;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品图片id
     */
    private Integer pictureId;
    /**
     * 商店id
     */
    private Integer shopId;
    /**
     * 月销量
     */
    private Integer monthlySales;
    /**
     * 库存数量
     */
    private Integer amount;
    /**
     * 商品是否启用
     */
    private boolean isActive;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public void setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Integer getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(Integer monthlySales) {
        this.monthlySales = monthlySales;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return this.getVo();
    }

    public String getVo() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsIntroduction='" + goodsIntroduction + '\'' +
                ", price=" + price +
                ", pictureId=" + pictureId +
                ", shopId=" + shopId +
                ", monthlySales=" + monthlySales +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }

    public String getDto() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsIntroduction='" + goodsIntroduction + '\'' +
                ", price=" + price +
                ", pictureId=" + pictureId +
                ", shopId=" + shopId +
                ", monthlySales=" + monthlySales +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }
}

