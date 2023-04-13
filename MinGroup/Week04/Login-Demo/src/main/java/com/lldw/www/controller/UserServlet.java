package com.lldw.www.controller;

import com.lldw.www.po.User;
import com.lldw.www.service.Impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author LLDW
 * @date 2023-04-10 14:30:04
 */
@WebServlet("/userServlet")
public class UserServlet extends com.lldw.www.controller.BaseServlet {


    UserServiceImpl userService = new UserServiceImpl();

    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {


        System.out.println("---UserServlet.login---");
        String remember = request.getParameter("remember");
        PrintWriter writer = response.getWriter();
        //1.获取信息 封装成loginUser对象
        User loginUser = this.packagingUser(request,response);

        //2.将loginUser对象传入service 返回user对象
        User user = userService.login(loginUser);


        if(user!=null){
            //返回user对象不为空且用户名和密码正确
            writer.write("login successfully!");

            //判断用户是否勾选记住我
            if("1".equals(remember)){
                //勾选了

                //new cookie对象
                Cookie cUsername = new Cookie("username", user.getUserName());
                Cookie cPassword = new Cookie("password", user.getPassword());

                //设置存活时间 7days
                cUsername.setMaxAge(60*60*24*7);
                cPassword.setMaxAge(60*60*24*7);

                //发送
                response.addCookie(cUsername);
                response.addCookie(cPassword);
            }

            //将登陆成功后的user对象,存储到session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user",user);


            //登录成功 跳转到别的页面
//            String contextPath = request.getContextPath();
//            response.sendRedirect(contextPath+"");
        }else{
            //结果为空 即用户名or密码错误
            writer.write("用户名或密码错误~");

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
    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        System.out.println("---UserServlet.register---");

        //0.校对验证码
        //获取用户输入的验证码
        String verifyCode = request.getParameter("verifyCode");

        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");

        //校对验证码
        if(!verifyCodeGen.equalsIgnoreCase(verifyCode)){
              //验证码错误 无法注册

            request.setAttribute("verifyCode_msg","验证码错误");
            request.getRequestDispatcher("register.html").forward(request,response);

            return;
        }

        PrintWriter writer = response.getWriter();

        //1.获取信息 封装成loginUser对象
        User loginUser = this.packagingUser(request,response);

        //2.调用service 注册成功返回user对象 失败则返回null
        User user = userService.register(loginUser);

        if(user==null){
            //注册失败
            request.setAttribute("register_msg","用户名已存在哈哈哈哈");
            request.getRequestDispatcher("register.html").forward(request,response);
        }else{
            //注册成功 跳转到登录页面
            writer.write("注册成功！");
            request.setAttribute("register_msg","注册成功,请登录");
            request.getRequestDispatcher("login.html").forward(request,response);
        }
    }

    /**
     *  将request里面username和password打包成user对象
     * @param request req
     * @param response resp
     * @return user 对象
     */
    public User  packagingUser(HttpServletRequest request,HttpServletResponse response) throws IOException {

        //1.获取信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.封装成user对象
        User loginUser = new User();
        loginUser.setUserName(username);
        loginUser.setPassword(password);


        return loginUser;
    }

    /**
     *  通过username查找user对象 主要用于判断username是否被使用
     * @param request
     * @param response
     */
    public void selectUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("---UserServlet.selectUser---");
        PrintWriter writer = response.getWriter();

        User loginUser = packagingUser(request,response);
        User user = userService.selectUser(loginUser);
        if(user==null){
            //查询不到 说明username未使用过
            writer.write(""+true);
        }else{
            //username已经被占用
            writer.write(""+false);
        }
    }



}
