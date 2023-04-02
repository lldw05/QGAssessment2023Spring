package com.lldw.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //获取status 200 302 403 404 500
        String status = request.getParameter("status");
        if("200".equals(status)){
            response.setStatus(200);
        }else if("302".equals(status)){
            response.setStatus(302);
        }else if("403".equals(status)){
            response.setStatus(403);
        }else if("404".equals(status)){
            response.setStatus(404);
        }else if("500".equals(status)){
            response.setStatus(500);
        }

        //1.获取字符输出流
        PrintWriter writer = response.getWriter();
        //2.写入数据
        writer.write("我落泪情绪零碎");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
