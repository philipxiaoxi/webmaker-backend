package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.entity.User;

public interface UserService {
    int getTotal();

    User getUser(int id);

    User getUserByLogin(String phone, String password);

    int updateUser(User user);

    int insertUser(String phone, String password);

    User getUserByPhone(String phone);
}
