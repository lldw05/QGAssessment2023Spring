package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Shop;
import com.lldw.www.service.Impl.GoodsServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lldw
 * @date 2023-04-19 19:20:47
 */
@WebServlet("/goodsServlet/*")
public class GoodsServlet extends BaseServlet {

    GoodsServiceImpl goodsService = new GoodsServiceImpl();

    /**
     *  添加goods
     * @param request req
     * @param response resp
     * @param jsonStr 已经转成string的json数据
     */
    public void addGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---GoodsServlet.addGoods---");

        //将JSON字符申转为Shop对象
        Goods addGoods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("addGoods:" + addGoods);

        //调用service
        Goods resultGoods = goodsService.addGoods(addGoods);

        //2.将集合转换为JSON数据 序列化


        if (resultGoods != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString(resultGoods));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("添加商品失败~");
            try {
                response.getWriter().write("添加商品失败，我也不知道为啥~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
