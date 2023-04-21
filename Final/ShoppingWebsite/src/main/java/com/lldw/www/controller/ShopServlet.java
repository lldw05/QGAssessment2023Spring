package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.MessageConstants;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
import com.lldw.www.service.Impl.ShopServiceImpl;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

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
    /**
     *  前端传入shopName或者shopId 查询店铺并返回shop对象
     * @param request req
     * @param response resp
     * @param jsonStr 已转成字符串的json数据
     */
    public void showShopGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopServlet.showShopGoods---");

        //将JSON字符申转为Shop对象
        Shop shopName = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("shopName:" + shopName);

        //调用service
        ArrayList<Goods> resultGoodsList = shopService.showShopGoods(shopName);


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



    /**
     *  发布动态
     * @param request req
     * @param response resp
     * @param jsonStr 已经转成string的json数据
     */
    public void sendPost(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopServlet.sendPost---");

        //jsonStr:{"messageContent":"测试发布动态","createTime":"2023-04-209:32:57","shopId":"6","goodsId":"8"}
        //处理字符串 时间类
        StringBuffer stringBuffer = new StringBuffer(jsonStr);
        int index = stringBuffer.indexOf("createTime");
        stringBuffer.insert(index+23,"T");
        jsonStr = stringBuffer.toString();
        System.out.println("jsonStr:"+jsonStr);

        //将JSON字符申转为Shop对象
        Message post = JSON.parseObject(jsonStr, Message.class);

        //设置messageType和sender
        post.setType(MessageConstants.MESSAGE_TYPE_POST);
        post.setSenderType(MessageConstants.SENDER_SHOP);
        System.out.println("post:" + post);

        //调用service
        Message resultMessage = shopService.sendPost(post);


        if (resultMessage != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString(resultMessage));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("发布动态失败~");
            try {
                response.getWriter().write("发布动态失败，我也不知道为啥~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
