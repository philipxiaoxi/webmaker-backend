package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserDao;
import com.lazyfish.codeshare.dao.UserTableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserTableServiceImpl implements UserTableService{
    @Autowired
    private UserTableDao userTableDao;
    @Override
    public List<Map<String, Object>> getUserTable(String fields, String tableName) {
        return userTableDao.getUserTable(fields,tableName);
    }
}
