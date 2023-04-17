package com.lldw.www.po;

import java.time.LocalDateTime;

/**
 * @author
 * @date
 */
public class Message {
    private Integer messageId;
    private Integer type;
    private String senderType;
    private Integer userId;
    private Integer goodsId;
    private Integer shopId;
    private String messageContent;
    private LocalDateTime createTime;
    private boolean isProcessed;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", type=" + type +
                ", senderType='" + senderType + '\'' +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", shopId=" + shopId +
                ", messageContent='" + messageContent + '\'' +
                ", createTime=" + createTime +
                ", isProcessed=" + isProcessed +
                '}';
    }
}
