package com.ways2u.server;

import com.ways2u.dao.UserDao;
import com.ways2u.model.User;
import com.ways2u.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanglong on 16/9/7.
 */
//@Service
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User selectUserById(Integer userId) {
        return userDao.selectUserById(userId);
    }

    public List<User> selectUser() {
        return userDao.selectUser();
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
