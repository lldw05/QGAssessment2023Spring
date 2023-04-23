package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.MessageConstants;
import com.lldw.www.po.Goods;
import com.lldw.www.po.Message;
import com.lldw.www.po.Shop;
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

    public void addComment(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---MessageServlet.addComment---");

        //将JSON字符申转为goods对象
        Message message = JSON.parseObject(jsonStr, Message.class);
        System.out.println("message:" + message);

        //设置message类型为评论
        message.setType(MessageConstants.MESSAGE_TYPE_COMMENT);

        //调用service
        boolean rusult = messageService.addComment(message);


        if (rusult) {
            //添加成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));

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

    /**
     * 发布动态
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  已经转成string的json数据
     */
    public void sendPost(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---MessageServlet.sendPost---");

        //jsonStr:{"messageContent":"测试发布动态","createTime":"2023-04-209:32:57","shopId":"6","goodsId":"8"}
        //处理字符串 时间类
        StringBuffer stringBuffer = new StringBuffer(jsonStr);
        int index = stringBuffer.indexOf("createTime");
        stringBuffer.insert(index + 23, "T");
        jsonStr = stringBuffer.toString();
        System.out.println("jsonStr:" + jsonStr);

        //将JSON字符申转为Message对象
        Message post = JSON.parseObject(jsonStr, Message.class);
        System.out.println("prePost:" + post);
        //设置messageType和sender
        post.setType(MessageConstants.MESSAGE_TYPE_POST);
        post.setSenderType(MessageConstants.SENDER_SHOP);
        System.out.println("post:" + post);

        //调用service
        boolean result = messageService.addPost(post);


        if (result) {
            //发布动态成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString("succeed"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("发布动态失败~");
            try {
                response.getWriter().write("发布动态失败，我也不知道为啥~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        //店铺发送动态之后 给粉丝发送提醒信息
        if (result) {
            //发布动态成功
            Message m = new Message();
            //设置 信息类型 信息内容 shopId
            m.setType(MessageConstants.MESSAGE_TYPE_REMINDER_POST);
            m.setShopId(post.getShopId());
            m.setMessageContent("您关注的店铺发布动态啦~");
            boolean rs = messageService.addReminderPost(m);
            if (rs) {
                try {

                    System.out.println("添加提醒信息成功");
                    //将resultShop对象转换为JSON数据 序列化
                    response.getWriter().write(JSON.toJSONString("添加提醒信息成功"));

                } catch (IOException e) {
                    System.out.println("添加提醒信息失败");
                    e.printStackTrace();
                }
            }
        }

    }


    public void sendChat(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.sendChat---");


        //jsonStr:{"messageContent":"测试发布动态","createTime":"2023-04-209:32:57","shopId":"6","goodsId":"8"}
        //处理字符串 时间类
        StringBuilder stringBuffer = new StringBuilder(jsonStr);
        int index = stringBuffer.indexOf("createTime");
        if(index!=-1)
        {
            stringBuffer.insert(index + 23, "T");
            jsonStr = stringBuffer.toString();
            System.out.println("jsonStr:" + jsonStr);
        }

        //将JSON字符申转为Message对象
        Message chatMessage = JSON.parseObject(jsonStr, Message.class);
        System.out.println("chatMessage:" + chatMessage);

        //设置messageType和sender  前端已经设置好sender是shop还是user
        chatMessage.setType(MessageConstants.MESSAGE_TYPE_USER_CHAT_SHOP);
        boolean result = messageService.addChatRoomMessage(chatMessage);

        if (result) {
            //发送消息成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化
                response.getWriter().write(JSON.toJSONString("succeed"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("发送消息失败~");
            try {
                response.getWriter().write("发送消息失败，我也不知道为啥~");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void queryChatInShop(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.queryChatInShop---");

        //将JSON字符申转为Shop对象
        Shop shop = JSON.parseObject(jsonStr, Shop.class);
        System.out.println("shop:" + shop);

        ArrayList<Message> messages = messageService.queryChatMessageInShop(shop);

        if (messages != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将shop传给前端
                response.getWriter().write(JSON.toJSONString(messages));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("暂时没有消息哦");
            try {
                response.getWriter().write("暂时没有消息哦");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
