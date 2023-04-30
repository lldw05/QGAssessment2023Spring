package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.ResultConstants;
import com.lldw.www.po.User;
import com.lldw.www.po.Result;
import com.lldw.www.po.ShopCart;
import com.lldw.www.service.Impl.ShopCartServiceImpl;
import com.lldw.www.vo.ShopCartVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author lldw
 * @date 2023-4-20 15:42:10
 */
@WebServlet("/shopCartServlet/*")
public class ShopCartServlet extends BaseServlet {

    ShopCartServiceImpl shopCartService = new ShopCartServiceImpl();

    /**
     * 添加购物车
     * @param request req
     * @param response resp
     * @param jsonStr userId goodsId amount
     */
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

    /**
     * 批量删除购物车
     * @param request req
     * @param response resp
     * @param jsonStr 购物车id 数组
     */
    public void deleteInBatches(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopCartServlet.deleteByIds---");

        //将JSON字符申转为Shop对象
        int[] ids = JSON.parseObject(jsonStr, int[].class);
        System.out.println("ids:" + Arrays.toString(ids));

        //调用service
        boolean flag = shopCartService.deleteInBatches(ids);


        if (flag) {

            //全部删除成功
            try {
                response.setContentType("text/json;charset=utf-8");

                response.getWriter().write(JSON.toJSONString(Result.success()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.SHOP_CART_DELETE_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 批量删除购物车
     * @param request req
     * @param response resp
     * @param jsonStr 购物车id 数组
     */
    public void deleteShopCart(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopCartServlet.deleteByIds---");

        //将JSON字符申转为Shop对象

        int id = JSON.parseObject(jsonStr, int.class);
        int[] ids = new int[1];
        ids[0]=id;
        System.out.println("ids:" + Arrays.toString(ids));

        //调用service
        boolean flag = shopCartService.deleteInBatches(ids);


        if (flag) {

            //全部删除成功
            try {
                response.setContentType("text/json;charset=utf-8");

                response.getWriter().write(JSON.toJSONString(Result.success()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.SHOP_CART_DELETE_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 查询购物车
     * @param request req
     * @param response resp
     * @param jsonStr userId
     */
    public void queryShopCart(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---ShopCartServlet.queryShopCart---");

        //将JSON字符申转为Shop对象
        User u = JSON.parseObject(jsonStr, User.class);


        //调用service  获取ShopCartVo
        ArrayList<ShopCartVo> shopCartVoList = shopCartService.queryShopCart(u);


        if (shopCartVoList!=null) {
            try {

                System.out.println("得到ShopCartVo");
                response.setContentType("text/json;charset=utf-8");

                //将Vo传输给前端
                response.getWriter().write(JSON.toJSONString(Result.success(shopCartVoList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("error");
            try {
                //返回购物车为空的信息
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.SHOP_CART_QUERY_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
