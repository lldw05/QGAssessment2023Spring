package com.lldw.www.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author LLDW
 * @date 2023-04-10 13:30:47
 */
@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {

    /*@Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();

        //2.获取最后一段路径 即方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index+1);


    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("this is baseServlet");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        try{

            //获取请求路径
            String uri = request.getRequestURI();

            //2.获取最后一段路径 即方法名
            int index = uri.lastIndexOf('/');
            String methodName = uri.substring(index+1);

            //  获取请求标识
//            String methodName = request.getParameter("methodOfUserServlet");
            Class<? extends BaseServlet> actionClass = this.getClass();
            Method method = actionClass.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
