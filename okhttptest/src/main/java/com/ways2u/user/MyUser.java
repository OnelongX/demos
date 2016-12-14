package com.ways2u.user;

import com.ways2u.ApplicationScope;

import javax.inject.Inject;

/**
 * Created by huanglong on 2016/12/12.
 */
@ApplicationScope
public class MyUser implements User{

    public String name = "MyUser";

    @Inject
    public MyUser(){
    }


    public String getName() {
        return name;
    }
}
