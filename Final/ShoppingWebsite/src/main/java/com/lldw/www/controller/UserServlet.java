package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lldw.www.po.Result;
import com.lldw.www.po.Token;
import com.lldw.www.po.User;
import com.lldw.www.service.Impl.UserServiceImpl;
import com.lldw.www.utils.JwtUtil;
import com.lldw.www.vo.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LLDW
 * @date 2023-04-10 14:30:04
 */
@WebServlet("/userServlet/*")
public class UserServlet extends BaseServlet {


    UserServiceImpl userService = new UserServiceImpl();

    /**
     * 传入封装好的username和手机号码 还有验证码
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  username password
     */
    public void login(HttpServletRequest request, HttpServletResponse response, String jsonStr) throws IOException, ServletException {


        System.out.println("---UserServlet.login---");

        //将JSON字符申转为Java对象
        User loginUser = JSON.parseObject(jsonStr, User.class);
        System.out.println("loginUser:" + loginUser);

        //校对验证码是否正确 不正确则直接返回错误提示
        //得到验证码
        String verifyCode = JSONObject.parseObject(jsonStr).getString("verifyCode");
        System.out.println("loginVerifyCode:" + verifyCode);


        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");
        System.out.println("verifyCodeGen" + verifyCodeGen);

        if (!verifyCodeGen.equalsIgnoreCase(verifyCode)) {
            //验证码错误 无法注册
            response.getWriter().write(JSON.toJSONString(Result.error("验证码错误")));
            return;
        }


        //验证码正确

        //2.将loginUser对象传入service 返回user对象
        User user = userService.login(loginUser);
        System.out.println("user:" + user);
        /*String remember = request.getParameter("remember");*/

        PrintWriter writer = response.getWriter();

        if (user != null) {
            //返回user对象不为空且用户名和密码正确
            System.out.println("返回user对象不为空且用户名和密码正确");
            //准备token 将userId，username，roleId放入token中
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("roleId", user.getRoleId());

            Token token = new Token(JwtUtil.getToken(map));
            response.setContentType("text/json;charset=utf-8");

            response.getWriter().write(JSON.toJSONString(Result.success(user.getUserId().toString(), token)));
            System.out.println("完成返回信息");
        } else {
            //结果为空 即用户名or密码错误
            System.out.println("结果为空 即用户名or密码错误");
            writer.write(JSON.toJSONString(Result.error("用户名或密码错误")));

        }

    }

    /**
     * 注册
     *
     * @param request  req
     * @param response resp
     * @throws IOException      呃呃呃
     * @throws ServletException 呃呃呃
     */
    public void register(HttpServletRequest request, HttpServletResponse response, String jsonStr) throws IOException, ServletException {
        System.out.println("---UserServlet.register---");

        //0.校对验证码

        //得到验证码
        String verifyCode = JSONObject.parseObject(jsonStr).getString("verifyCode");
        System.out.println("verifyCode" + verifyCode);

        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");
        System.out.println("verifyCodeGen" + verifyCodeGen);

        if (!verifyCodeGen.equalsIgnoreCase(verifyCode)) {
            //验证码错误 无法注册
            response.getWriter().write(JSON.toJSONString(Result.error("验证码错误")));
            return;
        }

        //验证码正确 进行注册

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:" + user);
        User registerUser = userService.register(user);
        System.out.println("registerUser:" + registerUser);

        //响应数据

        if (registerUser != null) {
            response.setContentType("text/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(Result.success()));
        } else {
            System.out.println("注册失败,用户名存在");
            response.getWriter().write(JSON.toJSONString(Result.error("用户名已存在(或用户名或密码为空)")));

        }

    }


    /**
     * 通过username查找user对象 主要用于判断username是否被使用
     *
     * @param request
     * @param response
     * @param jsonStr  username
     */
    public void queryUsername(HttpServletRequest request, HttpServletResponse response, String jsonStr) throws IOException {
        System.out.println("---UserServlet.selectUser---");
        PrintWriter writer = response.getWriter();

        User loginUser = JSON.parseObject(jsonStr, User.class);
        User user = userService.queryUserByUsername(loginUser);
        if (user == null) {
            //查询不到 说明username未使用过
            writer.write("" + true);
//            response.getWriter().write();
        } else {
            //username已经被占用
            writer.write("" + false);
        }
    }

    /**
     * 使用手机号码和用户名登录
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  username和手机号码
     */
    public void loginByPhoneNumber(HttpServletRequest request, HttpServletResponse response, String jsonStr) throws IOException, ServletException {


        System.out.println("---UserServlet.loginByPhoneNumber---");

        //将JSON字符申转为Java对象
        User loginUser = JSON.parseObject(jsonStr, User.class);
        System.out.println("loginUser:" + loginUser);

        //得到验证码
        String verifyCode = JSONObject.parseObject(jsonStr).getString("verifyCode");
        System.out.println("loginVerifyCode:" + verifyCode);

        //获取程序生成的验证码，从Session获得
        HttpSession session = request.getSession();
        String verifyCodeGen = (String) session.getAttribute("verifyCodeGen");
        System.out.println("verifyCodeGen" + verifyCodeGen);

        if (!verifyCodeGen.equalsIgnoreCase(verifyCode)) {
            //验证码错误 无法登录
            response.getWriter().write(JSON.toJSONString(Result.error("验证码错误")));
            return;
        }

        //2.将loginUser对象传入service 返回user对象
        User user = userService.loginByPhoneNumber(loginUser);
        System.out.println("user:" + user);
        /*String remember = request.getParameter("remember");*/

        PrintWriter writer = response.getWriter();

        if (user != null) {
            //返回user对象不为空且用户名和手机号码正确
            System.out.println("返回user对象不为空且用户名和手机号码正确");
            //准备token 将userId，username，roleId放入token中
            Map<String, Object> map = new HashMap<>();
            map.put("userId", user.getUserId());
            map.put("username", user.getUsername());
            map.put("roleId", user.getRoleId());

            Token token = new Token(JwtUtil.getToken(map));
            response.setContentType("text/json;charset=utf-8");

            response.getWriter().write(JSON.toJSONString(Result.success(user.getUserId().toString(), token)));

        } else {
            //结果为空 即用户名or密码错误
            System.out.println("结果为空 即用户名或手机号码错误");
            writer.write(JSON.toJSONString(Result.error("用户名或手机号码错误")));

        }

        System.out.println("完成返回信息");
    }

    /**
     * 修改用户信息 前端传入要修改的属性 注意:会传入空字符串 后台需要进行判断
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  user
     */
    public void updateUserData(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---UserServlet.updateUserData---");

        //将JSON字符申转为Java对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:" + user);
        User resultUser = userService.updateUser(user);
        System.out.println("resultUser:" + resultUser);

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

    /**
     * 展示用户信息 前端传入userId
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  userId
     */
    public void showUserInformation(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---UserServlet.showUserInformation---");

        //将JSON字符申转为Java对象 前端传入userId
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("user:" + user);
        User resultUser = userService.queryUserByUserId(user);
        System.out.println("resultUser:" + resultUser);

        //响应数据

        if (resultUser != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                ArrayList<UserVo> users = new ArrayList<>();
                users.add(new UserVo(resultUser));
                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(users)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("展示信息失败~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error("展示信息失败~")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
