package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class Goods {
    private Integer goodsId;
    private String goodsIntroduction;
    private double price;
    private Integer pictureId;
    private Integer shopId;
    private Integer monthlySales;
    private Integer amount;
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
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsIntroduction='" + goodsIntroduction + '\'' +
                ", pirce=" + price +
                ", pictureId=" + pictureId +
                ", shopId=" + shopId +
                ", monthlySales=" + monthlySales +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }
}
