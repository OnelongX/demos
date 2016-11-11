package com.ways2u.model;

import java.util.Date;

import static javax.swing.text.StyleConstants.Size;

/**
 * Created by huanglong on 16/9/7.
 */
public class User {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;

    private Date myDate = new Date();

    public Date getMyDate() {
        return myDate;
    }

    public void setMyDate(Date myDate) {
        this.myDate = myDate;
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName
                + ", userPassword=" + userPassword + ", userEmail=" + userEmail
                + "]";
    }
}
