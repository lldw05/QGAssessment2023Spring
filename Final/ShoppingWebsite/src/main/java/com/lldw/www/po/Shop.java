package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class Shop {
    /**
     * 商店id
     */
    private Integer id;
    /**
     * 店主id即userId
     */
    private Integer shopKeeperId;
    /**
     * 商店名称
     */
    private String shopName;
    /**
     * 商店介绍
     */
    private String shopIntroduction;
    /**
     * 粉丝数量
     */
    private Integer fansNum;
    /**
     * 月均销量
     */
    private Integer averageMonthlySales;
    /**
     * 是否启用
     */
    private boolean status;

    public Shop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShopKeeperId() {
        return shopKeeperId;
    }

    public void setShopKeeperId(Integer shopKeeperId) {
        this.shopKeeperId = shopKeeperId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopIntroduction() {
        return shopIntroduction;
    }

    public void setShopIntroduction(String shopIntroduction) {
        this.shopIntroduction = shopIntroduction;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getAverageMonthlySales() {
        return averageMonthlySales;
    }

    public void setAverageMonthlySales(Integer averageMonthlySales) {
        this.averageMonthlySales = averageMonthlySales;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopKeeperId=" + shopKeeperId +
                ", shopName='" + shopName + '\'' +
                ", shopIntroduction='" + shopIntroduction + '\'' +
                ", fansNum=" + fansNum +
                ", averageMonthlySales=" + averageMonthlySales +
                ", status=" + status +
                '}';
    }
}
