package com.lazyfish.codeshare.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserTableService {
    List<Map<String, Object>> getUserTable(String fields,String tableName);
}
