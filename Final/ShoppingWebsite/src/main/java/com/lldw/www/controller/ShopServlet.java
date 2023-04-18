package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.Shop;
import com.lldw.www.po.User;
import com.lldw.www.service.Impl.ShopServiceImpl;
import com.lldw.www.service.ShopService;

import javax.servlet.*;
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

        //2.将集合转换为JSON数据 序列化


        if (resultShop != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString(resultShop));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("店铺注册失败，店铺名太受欢迎啦~");
            try {
                response.getWriter().write("店铺注册失败，店铺名太受欢迎啦~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
