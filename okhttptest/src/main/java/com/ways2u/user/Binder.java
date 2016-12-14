package com.ways2u.user;

import com.ways2u.ApplicationScope;

import javax.inject.Inject;

/**
 * Created by huanglong on 2016/12/12.
 */
@ApplicationScope
public class Binder {
    private final User user;
    @Inject
    public Binder(User user){
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
