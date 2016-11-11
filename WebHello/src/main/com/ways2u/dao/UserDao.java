package com.ways2u.dao;

import com.ways2u.model.User;

import java.util.List;

/**
 * Created by huanglong on 16/9/7.
 */
public interface UserDao {
    public User selectUserById(Integer userId);
    public List<User> selectUser();
    public void insertUser (User user);
    public void updateUser (User user);
    public void deleteUser (User user);
}
