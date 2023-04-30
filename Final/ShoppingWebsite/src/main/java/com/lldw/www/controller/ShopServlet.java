package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.Result;
import com.lldw.www.po.Shop;
import com.lldw.www.service.Impl.ShopServiceImpl;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * @author LLDW
 * @date 2023-04-18 17:33:17
 */
@WebServlet("/shopServlet/*")
public class ShopServlet extends BaseServlet {

    ShopServiceImpl shopService = new ShopServiceImpl();

    public void register(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---ShopServlet.register---");

        //将JSON字符申转为Shop对象
        Shop registerShop = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("registerShop:" + registerShop);

        //调用service
        Shop resultShop = shopService.register(registerShop);


        if (resultShop != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString(Result.success()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("店铺注册失败，店铺名太受欢迎啦~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error("店铺注册失败，店铺名太受欢迎啦~")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    /**
     *  前端传入shopName 通过shopName查询店铺并返回shop对象
     * @param request req
     * @param response resp
     * @param jsonStr 已转成字符串的json数据
     */
    public void showShopMessage(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopServlet.showShopMessage---");

        //将JSON字符申转为Shop对象
        Shop shopName = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("shopName:" + shopName);

        //调用service
        Shop resultShop = shopService.showShopMessage(shopName);


        if (resultShop != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(resultShop));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("店铺查询失败，可能是店铺名不见啦~");
            try {
                response.getWriter().write("店铺查询失败，可能是店铺名不见啦~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
