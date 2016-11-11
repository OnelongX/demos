package com.agu.utils;

public class TUser {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userEmail;

    /*
     * 设置
     * @params Integer userId
     */
    public void setUserId(Integer userId)
    {
        this.userId=userId;
    }
    /*
     * 获取
     *
     * @return Integer
     */
    public Integer getUserId()
    {
        return this.userId;
    }

    /*
     * 设置
     * @params String userName
     */
    public void setUserName(String userName)
    {
        this.userName=userName;
    }
    /*
     * 获取
     *
     * @return String
     */
    public String getUserName()
    {
        return this.userName;
    }

    /*
     * 设置
     * @params String userPassword
     */
    public void setUserPassword(String userPassword)
    {
        this.userPassword=userPassword;
    }
    /*
     * 获取
     *
     * @return String
     */
    public String getUserPassword()
    {
        return this.userPassword;
    }

    /*
     * 设置
     * @params String userEmail
     */
    public void setUserEmail(String userEmail)
    {
        this.userEmail=userEmail;
    }
    /*
     * 获取
     *
     * @return String
     */
    public String getUserEmail()
    {
        return this.userEmail;
    }


}