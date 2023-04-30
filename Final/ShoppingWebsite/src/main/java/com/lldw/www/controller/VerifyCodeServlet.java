package com.lldw.www.controller;

import com.lldw.www.utils.VerifyCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author lldw
 * @date 2023-04-11 15:27:36
 */
@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成验证码
        VerifyCodeUtil code = new VerifyCodeUtil();


        BufferedImage image = code.createImage();
        System.out.println("code:"+code.getText());
        ImageIO.write(image,"jpg",response.getOutputStream());

        //存入Session
        HttpSession session = request.getSession();
        session.setAttribute("verifyCodeGen",code.getText());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
