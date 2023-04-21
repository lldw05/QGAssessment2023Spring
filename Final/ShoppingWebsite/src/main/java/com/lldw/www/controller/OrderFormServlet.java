package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.OrderForm;
import com.lldw.www.service.Impl.OrderFormServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author lldw
 * @date 2023-4-21 13:43:48
 */
@WebServlet("/orderFormServlet/*")
public class OrderFormServlet extends BaseServlet {

    OrderFormServiceImpl orderFormService = new OrderFormServiceImpl();

    public  void addOrderInBulk(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---OrderFormServlet.addOrderInBulk---");

        //将JSON字符申转为goods对象
        OrderForm[] orderForms = JSON.parseObject(jsonStr, OrderForm[].class);
        System.out.println("orderForms:" + Arrays.toString(orderForms));

        //调用service
        boolean flag = orderFormService.addOrderInBulk(orderForms);
        if(flag){
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString("添加成功"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("添加出错，请您留意您的订单是否添加成功~");
            try {
                response.getWriter().write("添加出错，请您留意您的订单是否添加成功~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
