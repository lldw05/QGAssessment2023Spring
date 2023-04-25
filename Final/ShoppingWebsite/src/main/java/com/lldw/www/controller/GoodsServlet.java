package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.MessageConstants;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Result;
import com.lldw.www.po.Shop;
import com.lldw.www.service.Impl.GoodsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author lldw
 * @date 2023-04-19 19:20:47
 */
@WebServlet("/goodsServlet/*")
public class GoodsServlet extends BaseServlet {

    GoodsServiceImpl goodsService = new GoodsServiceImpl();

    public  void searchGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---MessageServlet.addComment---");

        //将JSON字符申转为String对象
        String s = JSON.parseObject(jsonStr, String.class);
        System.out.println("搜索关键词:" + s);



        //调用service
        ArrayList<Goods> goods = goodsService.searchGoods(s);


        if (goods != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(goods));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("没有找到您想要的商品哦~");
            try {
                response.getWriter().write("没有找到您想要的商品哦~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---GoodsServlet.addGoods---");

        //将JSON字符申转为String对象
        Goods goods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("Goods:" + goods);

        //调用service
        Goods resultGoods = goodsService.addGoods(goods);
        if (resultGoods!=null){
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将goods传给前端
                response.getWriter().write(JSON.toJSONString(resultGoods));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("添加商品失败~");
            try {
                response.getWriter().write("添加商品失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 更新商品
     * @param request req
     * @param response resp
     * @param jsonStr goodsId isActive = false
     */
    public void updateGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---GoodsServlet.updateGoods---");

        //将JSON字符申转为String对象
        Goods goods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("Goods:" + goods);

        //调用service
        Boolean result = goodsService.updateGoods(goods);
        if (result){
            //修改成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将goods传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write("failed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     *  前端传入shopName或者shopId 查询店铺并返回shop对象
     * @param request req
     * @param response resp
     * @param jsonStr shop
     */
    public void showShopGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---GoodsServlet.showShopGoods---");

        //将JSON字符申转为Shop对象
        Shop shop = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("shop:" + shop);

        //调用service
        ArrayList<Goods> resultGoodsList = goodsService.queryGoodsOfShop(shop);


        if (resultGoodsList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultGoodsList转换为JSON数据 序列化 将goods集合传给前端
                response.getWriter().write(JSON.toJSONString(resultGoodsList));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("商品查询失败，肿么回事？？~");
            try {
                response.getWriter().write("商品查询失败，肿么回事？？~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void randomGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        //调用service
        ArrayList<Goods> resultGoodsList = goodsService.getRandomGoods();
//        ArrayList<Goods> resultGoodsList = null;

        if (resultGoodsList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultGoodsList转换为JSON数据 序列化 将goods集合传给前端

//                response.getWriter().write(JSON.toJSONString(Result.success(resultGoodsList).toString()));
                response.getWriter().write(JSON.toJSONString(resultGoodsList));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("商品查询失败，肿么回事？？~");
            try {
                response.setContentType("text/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(Result.error("商品查询失败").toString()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
