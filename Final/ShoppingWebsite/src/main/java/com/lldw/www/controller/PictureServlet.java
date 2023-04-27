package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.po.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author
 * @date
 */
@WebServlet("/pictureServlet/*")
public class PictureServlet extends BaseServlet {
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("PictureServlet.uploadPicture");

        //将JSON字符申转为Java对象
        byte byteee = JSON.parseObject(jsonStr, byte.class);
        System.out.println("byteee:"+byteee);
    }
}
