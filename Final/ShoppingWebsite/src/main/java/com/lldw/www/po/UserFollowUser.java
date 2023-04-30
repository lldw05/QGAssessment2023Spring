package com.lldw.www.po;

/**
 * @author
 * @date
 */
public class UserFollowUser {
    private Integer id;
    private Integer userId;
    private Integer beFollowedUserId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBeFollowedUserId() {
        return beFollowedUserId;
    }

    public void setBeFollowedUserId(Integer beFollowedUserId) {
        this.beFollowedUserId = beFollowedUserId;
    }

    @Override
    public String toString() {
        return "UserFollowUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", beFollowedUserId=" + beFollowedUserId +
                '}';
    }
}
