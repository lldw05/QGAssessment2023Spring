package com.lldw.www.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        try{

            //获取JsonString
            String jsonString = getJsonString(request);

            //获取请求路径
            String uri = request.getRequestURI();

            //2.获取最后一段路径 即方法名
            int index = uri.lastIndexOf('/');
            String methodName = uri.substring(index+1);

            //  获取请求标识
            Class<? extends BaseServlet> actionClass = this.getClass();
            Method method = actionClass.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class,String.class);
            method.invoke(this,request,response,jsonString);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     *  获取前端传来的json数据 转换成字符串
     * @param request request
     * @return jsonString 返回json数据转换后的字符串
     */
    protected String getJsonString(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder() ;
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String s = "" ;
        while((s=br.readLine())!=null){
            sb.append(s) ;
        }
        String jsonStr =sb.toString();

        //去除空格 不去除也行 只不过是多此一举
        jsonStr = jsonStr.replaceAll(" ","");


        //处理时间
        //jsonStr:{"messageContent":"测试发布动态","createTime":"2023-04-209:32:57","shopId":"6","goodsId":"8"}
        StringBuilder stringBuffer = new StringBuilder(jsonStr);
        int index = stringBuffer.indexOf("createTime");
        if (index != -1) {
            stringBuffer.insert(index + 23, "T");
            jsonStr = stringBuffer.toString();
//            System.out.println("jsonStr:" + jsonStr);
        }

        //输出到控制台
        System.out.println("jsonStr:"+jsonStr);
        return jsonStr;
    }
}
