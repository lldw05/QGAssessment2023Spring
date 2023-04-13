package com.lldw.www.servlet;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.Brand;
import com.lldw.www.service.BrandServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止乱码
        request.setCharacterEncoding("UTF-8");

        //2.获取请求体数据
        BufferedReader br = request.getReader();
        //请求体只有一行 所以读一行
        String params = br.readLine();

        //将JSON字符申转为Java对象
        int ids[] = JSON.parseObject(params, int[].class);
        System.out.println(ids);
        BrandServiceImpl brandService = new BrandServiceImpl();
        //调用service 返回影响的行数
        int i = brandService.deleteByIds(ids);
        if(i==ids.length){
            response.getWriter().write("succeed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
