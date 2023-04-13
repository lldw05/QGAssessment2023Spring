package com.lldw.www.controller;

import com.lldw.www.utils.VerifyCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
