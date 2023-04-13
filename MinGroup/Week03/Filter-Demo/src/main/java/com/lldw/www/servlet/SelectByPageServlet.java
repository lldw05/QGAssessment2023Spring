package com.lldw.www.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lldw.www.po.Brand;
import com.lldw.www.po.PageBean;
import com.lldw.www.service.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectByPageServlet")
public class SelectByPageServlet extends HttpServlet {

    BrandServiceImpl brandService = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止乱码
        request.setCharacterEncoding("UTF-8");

        //1.接收 当前页码 和 每页展示条数    url?currentPage=&pageSize=5
        String tempCurrentPage = request.getParameter("currentPage");
        String tempPageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(tempCurrentPage);
        int pageSize = Integer.parseInt(tempPageSize);

        //1.调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2.将集合转换为JSON数据 序列化
        String jsonString = JSON.toJSONString(pageBean);

        //3.响应数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
