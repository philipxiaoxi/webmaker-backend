package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserDao;
import com.lazyfish.codeshare.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int getTotal() {
        return userDao.getTotal();
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByLogin(String phone, String password) {
        return userDao.getUserByLogin(phone, password);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int insertUser(String phone, String password) {
        return userDao.insertUser(phone, password);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }
}
