package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.MessageConstants;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.service.Impl.MessageServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author
 * @date
 */
@WebServlet("/messageServlet/*")
public class MessageServlet extends BaseServlet {

    MessageServiceImpl messageService = new MessageServiceImpl();


    public void queryCommentOfGoods(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---MessageServlet.getComment---");

        //将JSON字符申转为goods对象
        Goods goods = JSON.parseObject(jsonStr, Goods.class);
        System.out.println("goods:" + goods);

        //调用service
        ArrayList<Message> messageList = messageService.getCommentByGoodsId(goods);


        if (messageList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(messageList));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("评论查询失败，可能是还没有评论呢~");
            try {
                response.getWriter().write("评论查询失败，可能是还没有评论呢~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void addComment(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("---MessageServlet.addComment---");

        //将JSON字符申转为goods对象
        Message message = JSON.parseObject(jsonStr, Message.class);
        System.out.println("message:" + message);

        //设置message类型为评论
        message.setType(MessageConstants.MESSAGE_TYPE_COMMENT);

        //调用service
        Message rusultMessage = messageService.addMessage(message);


        if (message != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(rusultMessage));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("添加评论失败~");
            try {
                response.getWriter().write("添加评论失败~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
