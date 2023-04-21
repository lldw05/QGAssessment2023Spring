package com.lldw.www.controller;

import com.lldw.www.service.Impl.GoodsServiceImpl;

import javax.servlet.annotation.WebServlet;

/**
 * @author lldw
 * @date 2023-04-19 19:20:47
 */
@WebServlet("/goodsServlet/*")
public class GoodsServlet extends BaseServlet {

    GoodsServiceImpl goodsService = new GoodsServiceImpl();


}
