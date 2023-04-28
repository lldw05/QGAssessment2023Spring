package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.Result;
import com.lldw.www.po.User;
import com.lldw.www.service.Impl.OrderFormServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
                response.getWriter().write(JSON.toJSONString(Result.success()));

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

    public  void purchaseNow(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---OrderFormServlet.purchaseNow---");

        //将JSON字符申转为goods对象
        OrderForm orderForm = JSON.parseObject(jsonStr, OrderForm.class);
        User user = JSON.parseObject(jsonStr,User.class);
        System.out.println("orderForm:" + orderForm);

        //调用service
        boolean flag = orderFormService.purchaseNow(orderForm,user);
        if(flag){
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(Result.success()));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("添加出错，请您留意您的订单是否添加成功~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error("购买出错,支付密码错误或支付密码还未设置")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void checkOrderForm(HttpServletRequest request,HttpServletResponse response,String jsonStr){
        System.out.println("---UserServlet.checkOrderForm---");

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:"+user);
        ArrayList<OrderForm> result = orderFormService.queryOrderFormByUserId(user);
        System.out.println("resultUser:"+result);

        //响应数据

        if (result != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(result));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("展示订单失败~");
            try {
                response.getWriter().write("展示订单失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateOrderForm(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---UserServlet.updateOrderForm---");

        //将JSON字符申转为Java对象
        OrderForm orderForm = JSON.parseObject(jsonStr, OrderForm.class);
        System.out.println("orderForm:"+orderForm);
        boolean flag = orderFormService.updateOrderForm(orderForm);

        System.out.println("flag:"+flag);

        //响应数据

        if (flag) {
            try {
                OrderForm result = orderFormService.queryOrderFormById(orderForm);
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(result));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("修改订单失败~");
            try {
                response.getWriter().write("修改订单失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
