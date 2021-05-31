package com.lazyfish.codeshare.service;

import java.util.List;
import java.util.Map;

public interface UserApiTableService {
    List<Map<String, Object>> getUserApiTable(Map<String,Object> map) throws Exception;
    int updateUserApiTable(Map<String, Object> map) throws Exception;
}
