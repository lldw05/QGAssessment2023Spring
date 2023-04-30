package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lldw.www.constants.ResultConstants;
import com.lldw.www.po.*;
import com.lldw.www.service.Impl.GoodsServiceImpl;
import com.lldw.www.service.Impl.ShopServiceImpl;
import com.lldw.www.service.ShopService;
import com.lldw.www.po.Result;

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

    public void searchGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---MessageServlet.searchGoods---");


        //将JSON字符申转为Java对象
        String searchKeyword = JSON.parseObject(jsonStr).getString("keyword");

        System.out.println("搜索关键词:" + searchKeyword);


        //调用service
        ArrayList<Goods> goods = goodsService.searchGoods(searchKeyword);


        if (goods != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(goods)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("没有找到您想要的商品哦~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(null)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---GoodsServlet.addGoods---");

        //将JSON字符申转为String对象
        Goods goods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("Goods:" + goods);


        //得到userId 查询商店是否存在
        User user = JSON.parseObject(jsonStr, User.class);

        ShopService shopService = new ShopServiceImpl();
        Shop shop = shopService.queryShopByUserId(user);


        //判断该用户是否注册了商店
        if (shop == null) {
            //未注册
            try {

                response.getWriter().write(JSON.toJSONString(Result.error("您还没有注册商店哦")));

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
            return;
        }

        //商店存在 添加商品
        goods.setShopId(shop.getShopId());

        //调用service
        Goods resultGoods = goodsService.addGoods(goods);

        if (resultGoods != null) {

            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将goods传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(resultGoods)));

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            System.out.println("添加商品失败~");

            try {

                response.getWriter().write(JSON.toJSONString(Result.error("添加商品失败~")));

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 更新商品
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  goodsId isActive = false
     */
    public void updateGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---GoodsServlet.updateGoods---");

        //将JSON字符申转为String对象
        Goods goods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("Goods:" + goods);

        //调用service
        Boolean result = goodsService.updateGoods(goods);
        if (result) {
            //修改成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将goods传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.getWriter().write("failed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /**
     * 前端传入shopName或者shopId 查询店铺并返回shop对象
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  shop
     */
    public void showShopGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---GoodsServlet.showShopGoods---");

        //将JSON字符申转为Shop对象
        Shop shop = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("shop:" + shop);

        //调用service 得到集合
        ArrayList<Goods> resultGoodsList = goodsService.queryGoodsOfShop(shop);

        //查询商店名称
        ShopService shopService = new ShopServiceImpl();
        shop = shopService.getShopByShopId(shop);


        if (resultGoodsList != null) {
            System.out.println("查询商店商品成功");
            try {
                response.setContentType("text/json;charset=utf-8");

                //将shopName和goods集合传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(shop.getShopName(), resultGoodsList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("商品查询失败，肿么回事？？~");
            try {

                response.getWriter().write(JSON.toJSONString(Result.error("商品查询失败，肿么回事？？~")));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 得到随机推送商品
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  null
     */
    public void randomGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        //调用service
        ArrayList<Goods> resultGoodsList = goodsService.getRandomGoods();

        if (resultGoodsList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultGoodsList转换为JSON数据 序列化 将goods集合传给前端

                response.getWriter().write(JSON.toJSONString(Result.success(resultGoodsList)));

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

    /**
     * 通过 店主id查询商店 再查询商品
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  userId
     */
    public void queryGoodsByUserId(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---GoodsServlet.queryGoodsByUserId---");

        //将JSON字符申转为Shop对象
        User user = JSON.parseObject(jsonStr, User.class);

        //根据userId 查询商店名称
        ShopService shopService = new ShopServiceImpl();
        Shop shop = shopService.queryShopByUserId(user);

        if (shop == null) {
            System.out.println("没有注册店铺");
            try {

                response.getWriter().write(JSON.toJSONString(Result.error("您还没有申请商店哦")));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //调用service 得到集合
        ArrayList<Goods> resultGoodsList = goodsService.queryGoodsOfShop(shop);

        if (resultGoodsList != null) {
            System.out.println("查询商店商品成功");
            try {
                response.setContentType("text/json;charset=utf-8");

                //将shopName和goods集合传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(shop.getShopName(), resultGoodsList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("商品查询失败，肿么回事？？~");
            try {

                response.getWriter().write(JSON.toJSONString(Result.error("您的商店暂时没有商品哦~")));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
