package com.ways2u.test;

/**
 * Created by huanglong on 2016/12/14.
 */
public class MyRespone<T>{

    public MyHeader header;
    public T body;

    public static  class MyHeader{
        String code;
        String msg;
    }
}
