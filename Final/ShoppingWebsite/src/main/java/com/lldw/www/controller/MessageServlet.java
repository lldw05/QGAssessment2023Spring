package com.lldw.www.controller;

import com.alibaba.fastjson.JSON;
import com.lldw.www.constants.MessageConstants;
import com.lldw.www.constants.ResultConstants;
import com.lldw.www.po.*;
import com.lldw.www.service.Impl.GoodsServiceImpl;
import com.lldw.www.service.Impl.MessageServiceImpl;
import com.lldw.www.po.Result;

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
                response.getWriter().write(JSON.toJSONString(Result.success(messageList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("评论查询失败，可能是还没有评论呢~");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error("评论查询失败，可能是还没有评论呢~")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 添加评论
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  已经转成string的json数据
     */
    public void addComment(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("---MessageServlet.addComment---");

        //将JSON字符申转为goods对象
        Message message = JSON.parseObject(jsonStr, Message.class);
        System.out.println("message:" + message);

        //设置message类型为评论
        message.setType(MessageConstants.MESSAGE_TYPE_COMMENT);

        //调用service
        boolean result = messageService.addComment(message);


        if (result) {
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

    /**
     * 发送聊天信息
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  存储着message的json字符串
     */
    public void sendChat(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.sendChat---");


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

    /**
     * 查询商店发送的聊天信息
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  存储着shopId的json字符串
     */
    public void queryChatInShop(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
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

    /**
     * 查询商店注册申请信息
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  null
     */
    public void queryShopRegistration(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.queryShopRegistration---");
        ArrayList<Message> messageArrayList = messageService.queryShopRegistration();

        if (messageArrayList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(messageArrayList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("暂时没有审核申请哦");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.QUERY_SHOP_REGISTRATION_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 通过商店注册申请 messageId
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  存储着messageId的json字符串 isProcessed
     */
    public void updateShopRegistration(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.updateShopRegistration---");

        //将JSON字符申转为Shop对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        boolean flag = messageService.updateMessage(m);

        //判断是否修改成功
        if (flag) {
            //修改成功
            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString(Result.success()));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("修改失败");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.MESSAGE_UPDATE_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 查询商品上市申请
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  null
     */
    public void queryGoodsLaunch(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.updateShopRegistration---");

        ArrayList<Message> messageArrayList = messageService.queryGoodsLaunch();
        if (messageArrayList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将将messageList传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(messageArrayList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("暂时没有新品上市哦");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.QUERY_GOODS_LAUNCH_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 通过商店新品上市申请 messageId
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  存储着messageId的json字符串 isProcessed
     */
    public void updateGoodsLaunch(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.updateGoodsLaunch---");

        //将JSON字符申转为Message对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        boolean flag = messageService.updateMessage(m);

        //判断是否修改成功
        if (flag) {
            //修改成功

            //将goods状态设为active
            Goods goods = new Goods();
            goods.setGoodsId(m.getGoodsId());
            goods.setActive(true);
            GoodsServiceImpl goodsService = new GoodsServiceImpl();
            Boolean result = goodsService.updateGoods(goods);

            try {
                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));
                if (result) {
                    //如果商品状态更新成功
                    response.getWriter().write(JSON.toJSONString("商品状态更新成功"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("修改失败");
            try {
                response.getWriter().write("修改失败");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param request  req
     * @param response resp
     * @param jsonStr  userId,goodsId,shopId,createTime,messageContent
     */
    public void addGoodsComplaint(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.addGoodsComplaint---");
        //将JSON字符申转为Message对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        //调用service
        boolean rs = messageService.addGoodsComplaint(m);
        try {
            if (rs) {
                //添加成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));
            } else {
                response.getWriter().write("添加失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询投诉信息
     * @param request req
     * @param response resp
     * @param jsonStr null
     */
    public void queryComplaint(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.queryComplaint---");
        ArrayList<Message> messageArrayList = messageService.queryComplaint();
        try {
            if (messageArrayList!=null) {
                //查询成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(messageArrayList)));
            } else {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.QUERY_NO_COMPLAINT_ERROR)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 处理投诉信
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  messageId isProcessed
     */
    public void handleComplaint(HttpServletRequest request, HttpServletResponse response, String jsonStr) {
        System.out.println("MessageServlet.handleComplaint---");

        //将JSON字符申转为Message对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        //调用service
        boolean rs = messageService.updateMessage(m);
        try {
            if (rs) {
                //修改成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString(Result.success()));
            } else {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.MESSAGE_UPDATE_ERROR)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 管理评论：删除等等
     *
     * @param request  req
     * @param response resp
     * @param jsonStr  messageId isProcessed
     */
    public void manageComment(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.manageComment---");

        //将JSON字符申转为Message对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        //调用service
        boolean rs = messageService.updateMessage(m);
        try {
            if (rs) {
                //修改成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));
            } else {
                response.getWriter().write("修改失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 给店主添加商品下架提醒
     *
     * @param request  req
     * @param response resp
         * @param jsonStr  goodsId,messageContent,shopId,createTime
     */
    public void addReminderGoodsPullOff(HttpServletRequest request, HttpServletResponse response, String jsonStr){

        System.out.println("MessageServlet.addReminderGoodsPullOff---");

        //将JSON字符申转为Message对象
        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("Message:" + m);

        //调用service
        boolean rs = messageService.addReminderGoodsPullOff(m);
        try {
            if (rs) {
                //修改成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString("succeed"));
            } else {
                response.getWriter().write("修改失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询用户的消息
     * @param request req
     * @param response resp
     * @param jsonStr userId
     */
    public void queryMessageOfUser(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.queryMessageOfUser---");

        //将JSON字符申转为Message对象
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println("User:" + user);

        //调用service
        ArrayList<Message> messages = messageService.queryMessageOfUser(user);
        try {
            if (messages!=null) {
                //修改成功

                response.setContentType("text/json;charset=utf-8");

                //将resultShop对象转换为JSON数据 序列化 将message传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(messages)));
            } else {
                response.getWriter().write(JSON.toJSONString(Result.error(null)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有评论信息
     * @param request req
     * @param response resp
     * @param jsonStr none
     */
    public void queryAllComment(HttpServletRequest request, HttpServletResponse response, String jsonStr){
        System.out.println("MessageServlet.queryAllComment---");

        ArrayList<Message> messageArrayList = messageService.queryAllComment();
        if (messageArrayList != null) {
            try {
                response.setContentType("text/json;charset=utf-8");

                //将将messageList传给前端
                response.getWriter().write(JSON.toJSONString(Result.success(messageArrayList)));

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("暂时没哟任何评论");
            try {
                response.getWriter().write(JSON.toJSONString(Result.error(ResultConstants.QUERY_NO_COMMENT_ERROR)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
