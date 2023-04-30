package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;

import com.lldw.www.po.UserFollowShop;
import com.lldw.www.service.Impl.UserFollowShopServiceImpl;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lldw
 * @date 2023-4-22 12:58:33
 */
@WebServlet("/userFollowShopServlet/*")
public class UserFollowShopServlet extends BaseServlet {
        public void follow(HttpServletRequest request, HttpServletResponse response, String jsonStr){
            System.out.println("---UserFollowShopServlet.follow---");

            //将JSON字符申转为Shop对象
            UserFollowShop ufs = JSON.parseObject(jsonStr, UserFollowShop.class);
            System.out.println("UserFollowShop:" + ufs);

            //调用service
            UserFollowShopServiceImpl userFollowShopService = new UserFollowShopServiceImpl();
            boolean flag = userFollowShopService.follow(ufs);

            if (flag) {
                try {
                    response.setContentType("text/json;charset=utf-8");

                    //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                    response.getWriter().write(JSON.toJSONString("succeed"));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("error~");
                try {
                    response.getWriter().write("error");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}
