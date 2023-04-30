package com.lldw.www.vo;

import com.lldw.www.po.Goods;
import com.lldw.www.po.OrderForm;
import com.lldw.www.service.GoodsService;
import com.lldw.www.service.Impl.GoodsServiceImpl;

/**
 * 缓缓飘落的枫叶像思念 重开了曹
 * 订单的vo类 比订单多了goodsIntroduction
 * @author lldw
 * @date 2023-04-30 15:20:49
 */
public class OrderFormVo extends OrderForm {
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

    private String goodsIntroduction;


    public OrderFormVo(OrderForm orderForm) {
        this.id = orderForm.getId();
        this.userId = orderForm.getUserId();
        this.goodsId = orderForm.getGoodsId();

        this.shopId = orderForm.getShopId();
        this.amount = orderForm.getAmount();
        this.status = orderForm.getStatus();

        //查询goods对象 得到商品简介
        Goods g = new Goods();
        g.setGoodsId(orderForm.getGoodsId());
        GoodsService goodsService = new GoodsServiceImpl();

        //通过goodsId查询
        g=goodsService.queryGoodsByGoodsId(g);

        this.goodsIntroduction = g.getGoodsIntroduction();

    }

    @Override
    public String toString() {
        return "OrderFormVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", shopId=" + shopId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", goodsIntroduction='" + goodsIntroduction + '\'' +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public Integer getGoodsId() {
        return goodsId;
    }

    @Override
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public void setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
    }

    @Override
    public Integer getShopId() {
        return shopId;
    }

    @Override
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }
}
