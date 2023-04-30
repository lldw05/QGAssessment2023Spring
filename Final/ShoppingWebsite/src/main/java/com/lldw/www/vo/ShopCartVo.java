package com.lldw.www.vo;

import com.lldw.www.controller.GoodsServlet;
import com.lldw.www.po.Goods;
import com.lldw.www.po.ShopCart;
import com.lldw.www.service.Impl.GoodsServiceImpl;

/**
 * 比购物车po多了 商品介绍 价格 图片id 商店id 商品是否启用
 * @author lldw
 * @date 2023-04-30 15:22:33
 */
public class ShopCartVo extends ShopCart {
    //订单信息
    /**
     * id
     */
    private Integer shopCartId;
    /**
     * 商品id
     */
    private Integer goodsId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 数量
     */
    private Integer amount;

    //上面为购物车中间表信息 下面为商品信息

    /**
     * 商品介绍
     */
    private String goodsIntroduction;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 图片id
     */
    private Integer pictureId;
    /**
     * 商店id
     */
    private Integer shopId;
    /**
     * 商品是否启用
     */
    private boolean isActive;


    public ShopCartVo() {
    }

    public ShopCartVo(ShopCart shopCart) {

        //记录购物车信息
        this.shopCartId = shopCart.getShopCartId();
        this.goodsId = shopCart.getGoodsId();
        this.userId = shopCart.getUserId();
        this.amount = shopCart.getAmount();

        //查询商品信息 记录下来
        GoodsServiceImpl goodsService = new GoodsServiceImpl();
        Goods goods = new Goods();
        goods.setGoodsId(shopCart.getGoodsId());

        //根据goodsId查询完整的goods信息
        goods = goodsService.queryGoodsByGoodsId(goods);

        this.goodsIntroduction = goods.getGoodsIntroduction();
        this.price = goods.getPrice();
        this.pictureId = goods.getPictureId();
        this.shopId = goods.getShopId();
        this.isActive = goods.isActive();
    }

    public ShopCartVo(Integer shopCartId, Integer goodsId, Integer userId, Integer amount, String goodsIntroduction, double price, Integer pictureId, Integer shopId, boolean isActive) {
        this.shopCartId = shopCartId;
        this.goodsId = goodsId;
        this.userId = userId;
        this.amount = amount;

        this.goodsIntroduction = goodsIntroduction;
        this.price = price;
        this.pictureId = pictureId;
        this.shopId = shopId;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
        return "{" +
                "shopCartId=" + shopCartId +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", goodsIntroduction='" + goodsIntroduction + '\'' +
                ", price=" + price +
                ", pictureId=" + pictureId +
                ", shopId=" + shopId +
                ", isActive=" + isActive +
                '}';
    }
}
