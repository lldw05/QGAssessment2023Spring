package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.vo.Result;
import com.lldw.www.po.ShopCart;
import com.lldw.www.service.Impl.ShopCartServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author lldw
 * @date 2023-4-20 15:42:10
 */
@WebServlet("/shopCartServlet/*")
public class ShopCartServlet extends BaseServlet {

    ShopCartServiceImpl shopCartService = new ShopCartServiceImpl();

    public void addIntoShopCart(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopCartServlet.addIntoShopCart---");

        //将JSON字符申转为Shop对象
        ShopCart shopCart = JSON.parseObject(jsonStr, ShopCart.class);
        System.out.println("shopCart:" + shopCart);

        //调用service
        ShopCart resultShopCart = shopCartService.addIntoShopCart(shopCart);


        if (resultShopCart != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(Result.success()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("添加购物车失败~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(null)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteInBatches(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopCartServlet.deleteByIds---");

        //将JSON字符申转为Shop对象
        int[] ids = JSON.parseObject(jsonStr, int[].class);
        System.out.println("ids:" + Arrays.toString(ids));

        //调用service
        boolean flag = shopCartService.deleteInBatches(ids);


        if (flag) {
            try {
//                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error");
            try {
                response.getWriter().write("error");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
