package com.ways2u.server;

import com.ways2u.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanglong on 16/9/7.
 */

public interface UserService {
    public User selectUserById(Integer userId);

    public List<User> selectUser();
    public void insertUser (User user);
    public void updateUser (User user);
    public void deleteUser (User user);
}
