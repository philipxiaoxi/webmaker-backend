package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserDao;
import com.lazyfish.codeshare.dao.UserTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserTableServiceImpl{
    @Autowired
    private UserTableDao userTableDao;
}
