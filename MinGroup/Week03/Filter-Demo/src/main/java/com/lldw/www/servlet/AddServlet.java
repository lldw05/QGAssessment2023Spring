package com.lldw.www.servlet;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.Brand;
import com.lldw.www.service.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    BrandServiceImpl brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //防止乱码
        request.setCharacterEncoding("UTF-8");
        //1．接收数据, request.getParameter不能接收json的数据

        //2.获取请求体数据
        BufferedReader br = request.getReader();
        //请求体只有一行 所以读一行
        String params = br.readLine();


        //将JSON字符申转为Java对象
        Brand brand = JSON.parseObject(params, Brand.class);
//        System.out.println(brand);

        //2.调用service 添加
        brandService.add(brand);

        //3.响应成功标识
        response.getWriter().write("succeed");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
