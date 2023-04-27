package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lldw.www.po.OrderForm;
import com.lldw.www.po.Result;
import com.lldw.www.po.Token;
import com.lldw.www.po.User;
import com.lldw.www.service.Impl.UserServiceImpl;
import com.lldw.www.utils.JwtUtil;
import com.lldw.www.utils.OthersUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lldw.www.constants.ResultConstants.TOKEN;

/**
 * @author LLDW
 * @date 2023-04-10 14:30:04
 */
@WebServlet("/userServlet/*")
public class UserServlet extends BaseServlet {


    UserServiceImpl userService = new UserServiceImpl();

    public void login(HttpServletRequest request,HttpServletResponse response,String jsonStr) throws IOException, ServletException {


        System.out.println("---UserServlet.login---");

        //将JSON字符申转为Java对象
        User loginUser = JSON.parseObject(jsonStr, User.class);
        System.out.println("loginUser:"+loginUser);

        //得到验证码
        String verifyCode = JSONObject.parseObject(jsonStr).getString("verifyCode");
        System.out.println("verifyCode"+verifyCode);

        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");
        System.out.println("verifyCodeGen"+verifyCodeGen);

        if(!verifyCodeGen.equalsIgnoreCase(verifyCode)){
            //验证码错误 无法注册
            response.getWriter().write(JSON.toJSONString(Result.error("验证码错误")));
            return;
        }

        //2.将loginUser对象传入service 返回user对象
        User user = userService.login(loginUser);
        /*String remember = request.getParameter("remember");*/

        PrintWriter writer = response.getWriter();

        if(user!=null){
            //返回user对象不为空且用户名和密码正确


            //登录成功 跳转到别的页面
//            String contextPath = request.getContextPath();
//            response.sendRedirect(contextPath+"");
            //准备token 将userId，username，roleId放入token中
            Map<String,Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("username",user.getUsername());
            map.put("roleId",user.getRoleId());

            Token token = new Token(JwtUtil.getToken(map));
            response.setContentType("text/json;charset=utf-8");

            response.getWriter().write(JSON.toJSONString(Result.success(token)));

            //response.getWriter().write("登录成功test2023-04-18 17:29:43");
        }else{
            //结果为空 即用户名or密码错误
            writer.write(JSON.toJSONString(Result.error("用户名或密码错误")));

//            //存储错误信息到request
//            request.setAttribute("login_msg","用户名或密码错误~");
//
//            //跳转到Login.jsp 使用转发的方式
//            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    /**
     * 注册
     * @param request req
     * @param response resp
     * @throws IOException 呃呃呃
     * @throws ServletException 呃呃呃
     */
    public void register(HttpServletRequest request,HttpServletResponse response,String jsonStr) throws IOException, ServletException {
        System.out.println("---UserServlet.register---");

        //0.校对验证码

        //得到验证码
        String verifyCode = JSONObject.parseObject(jsonStr).getString("verifyCode");
        System.out.println("verifyCode"+verifyCode);

        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");
        System.out.println("verifyCodeGen"+verifyCodeGen);

        if(!verifyCodeGen.equalsIgnoreCase(verifyCode)){
            //验证码错误 无法注册
            response.getWriter().write(JSON.toJSONString(Result.error("验证码错误")));
            return;
        }

        //验证码正确 进行注册

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:"+user);
        User registerUser = userService.register(user);
        System.out.println("registerUser:"+registerUser);

        //响应数据

        if(registerUser!=null){
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Result.success(registerUser)));
        }else{
            System.out.println("注册失败,用户名存在");
            response.getWriter().write(JSON.toJSONString(Result.error("用户名已存在(或用户名或密码为空)")));

        }
//
//        String s = JSON.toJSONString(user);
//
//        //3.响应数据
//        response.setContentType("text/json;charset=utf-8");
//        writer.write(s);

//        //1.获取信息 封装成loginUser对象
//        User loginUser = this.packagingUser(request,response);
//
//        //2.调用service 注册成功返回user对象 失败则返回null
//        User user = userService.register(loginUser);
//
//        if(user==null){
//            //注册失败
//            request.setAttribute("register_msg","用户名已存在哈哈哈哈");
//            request.getRequestDispatcher("register.html").forward(request,response);
//        }else{
//            //注册成功 跳转到登录页面
//            writer.write("注册成功！");
//            request.setAttribute("register_msg","注册成功,请登录");
//            request.getRequestDispatcher("login.html").forward(request,response);
//        }
    }

    /**
     *  将request里面username和password打包成user对象
     * @param request req
     * @param response resp
     * @return user 对象
     */
    public User  packagingUser(HttpServletRequest request,HttpServletResponse response,String jsonStr) throws IOException {

//        //1.获取信息
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        //2.封装成user对象
//        User loginUser = new User();
//        loginUser.setUserName(username);
//        loginUser.setPassword(password);
//
//
//        return loginUser;


        //1．接收数据, request.getParameter不能接收json的数据

        //2.获取请求体数据
        BufferedReader br = request.getReader();
        //请求体只有一行 所以读一行
        String params = br.readLine();


        //将JSON字符申转为Java对象
        User user = JSON.parseObject(params, User.class);

        return user;
    }

    /**
     *  通过username查找user对象 主要用于判断username是否被使用
     * @param request
     * @param response
     */
    public void queryUsername(HttpServletRequest request,HttpServletResponse response,String jsonStr) throws IOException {
        System.out.println("---UserServlet.selectUser---");
        PrintWriter writer = response.getWriter();

        User loginUser = packagingUser(request,response,jsonStr);
        User user = userService.queryUser(loginUser);
        if(user==null){
            //查询不到 说明username未使用过
            writer.write(""+true);
//            response.getWriter().write();
        }else{
            //username已经被占用
            writer.write(""+false);
        }
    }

    public void forgetPassword(HttpServletRequest request,HttpServletResponse response){

    }

    public void updateUserData(HttpServletRequest request,HttpServletResponse response,String jsonStr){
        System.out.println("---UserServlet.updateUserData---");

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:"+user);
        User resultUser = userService.updateUser(user);
        System.out.println("resultUser:"+resultUser);

        //响应数据

        if (resultUser != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(resultUser));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("修改信息失败~");
            try {
                response.getWriter().write("修改信息失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showUserInformation(HttpServletRequest request,HttpServletResponse response,String jsonStr){
        System.out.println("---UserServlet.showUserInformation---");

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:"+user);
        User resultUser = userService.queryUser(user);
        System.out.println("resultUser:"+resultUser);

        //响应数据

        if (resultUser != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(resultUser));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("展示信息失败~");
            try {
                response.getWriter().write("展示信息失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }




}
