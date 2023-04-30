package com.lldw.www.filter;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.ResultConstants;
import com.lldw.www.po.Result;
import com.lldw.www.utils.JwtUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lldw
 * @date 2023-04-26 20:54:52
 */

//@WebFilter("/*")
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求ur1
        String url = req.getRequestURI().toString();

        //2.判断请求ur1中是否包含login，如果包含，说明是登录换作，放行。
        if (url.contains("login")||url.contains("aaa")||url.contains("verifyCode")
                ||url.contains("register")||url.contains("jpg")||url.contains("randomGoods")) {

            //放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        //3.获取请求头中的令牌 (token)。
        String jwt = req.getHeader(ResultConstants.AUTHORIZATION);


        //4.判断令牌是否存在，如果不存在，返回错误结(未登录)
        if (jwt.length() == 0) {
            resp.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));

//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath+"/login.html");
//            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }


        //5.解析token，如解析失败，返回错误结(未登灵)

        try {
            JwtUtil.decode(jwt);
        } catch (Exception e) {
            e.getStackTrace();
            resp.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath+"/login.html");
//            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        //6.放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}
