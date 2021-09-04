package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserDao;
import com.lazyfish.codeshare.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    final static private String SALT = "1145141919810233";
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
        password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        System.out.println(password);
        return userDao.getUserByLogin(phone, password);
    }

    @Override
    public int updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int insertUser(String phone, String password) {
        password = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        return userDao.insertUser(phone, password);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }
}
