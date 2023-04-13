package com.lldw.www.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        //判断访问资源路径是否和登录注册相关
        String[] urls = {"/login.html","userServlet","/register.html","verifyCodeServlet"};

        //获取当前访问的资源路径
        String url = req.getRequestURL().toString();

        //循环遍历
        for (String u:urls){
            if(url.contains(u)){
                //找到了
                chain.doFilter(request,response);


                return;
            }
        }
        HttpSession  session= req.getSession();
        //1.判断session中是否有user
        Object user = session.getAttribute("user");

        if(user!=null){
            //登录过了 放行
            chain.doFilter(request,response);
        }else{
            //没有登录 存储提示信息 跳转到登录页面
            req.setAttribute("login_msg","您尚未登录~");
            //重定向
            req.getRequestDispatcher("login.html").forward(req,response);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}