package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.UserApiTableInfoDao;
import com.lazyfish.codeshare.dao.UserApiTableItemDao;
import com.lazyfish.codeshare.dao.UserTableDao;
import com.lazyfish.codeshare.entity.UserApiTableInfo;
import com.lazyfish.codeshare.entity.UserApiTableItem;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserApiTableServiceImpl implements UserApiTableService{
    @Autowired
    UserApiTableInfoDao userApiTableInfoDao;
    @Autowired
    UserApiTableItemDao userApiTableItemDao;
    @Autowired
    UserTableDao userTableDao;
    @Override
    public List<Map<String, Object>> getUserApiTable(Map<String, Object> map) {
        //获取用户传来的真表名
        String realName = (String) map.get("tableName");
        //查询数据库创建的代名表信息
        UserApiTableInfo userApiTableInfo = userApiTableInfoDao.getTableInfo(realName);
        //查询所需参数的代名项目信息
        Map<String, Object> keyMap= new HashMap<>();
        keyMap.put("id","id");
        List<UserApiTableItem> itemList = new ArrayList<>();
        String items_str = (String)map.get("items");
        String[] items = items_str.split(",");
        for (int i = 0; i < items.length; i++) {
            UserApiTableItem userApiTableItem = userApiTableItemDao.getTableItemInfo(items[i]);
            //设置代字段名对应的真字段名
            keyMap.put(userApiTableItem.getTableItemName(),items[i]);
            itemList.add(userApiTableItem);
        }
        //至此获取到全部代表名、代项目名 可以直接查询。
        System.out.println(userApiTableInfo.getTableName());
        //构造fields
        String fields ="id";

        for (UserApiTableItem item:itemList) {
            fields=fields+","+item.getTableItemName();
        }
        //构造sql语句
        String finalFields = fields;
        String sql = new SQL(){{
            SELECT(finalFields);
            FROM(userApiTableInfo.getTableName());
        }}.toString();
        System.out.println(sql);
        //执行sql语句
        List<Map<String, Object>> list = userTableDao.getUserTable(sql);
        //替换key值
        List<Map<String, Object>> finalList = new ArrayList<>();
        for ( Map<String, Object> item :list) {
            Map<String, Object> new_item = new HashMap<>();
            for(String key:item.keySet()){
                new_item.put((String) keyMap.get(key), item.get(key));
            }
            finalList.add(new_item);
        }
        //打印查询效果
//        for ( Map<String, Object> item :finalList) {
//            System.out.println(item);
//        }
        return finalList;
    }
}
