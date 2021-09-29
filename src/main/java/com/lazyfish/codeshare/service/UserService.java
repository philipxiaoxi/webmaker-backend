package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.entity.User;

import java.util.List;

public interface UserService {
    int getTotal();

    User getUser(int id);

    List<User> getAllUser();

    User getUserByLogin(String phone, String password);

    int updateUser(User user) throws Exception;

    int insertUser(String phone, String password, String email, String code) throws Exception;

    User getUserByPhone(String phone);

    User getUserByEmail(String email);

    void getRandomString(int len, String email, String ip) throws Exception;
}
