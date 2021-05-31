package com.lazyfish.codeshare.sqlprovider;

import com.lazyfish.codeshare.entity.UserApiTableItem;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UserTableSqlProvider {
    public String update(String tableName,Map<String, Object> data){
        return new SQL(){{
            UPDATE(tableName);
            for(String key:data.keySet()){
                if(key.equals("id")){
                    continue;
                }
                SET(key + " = #{data."+key+"}");
            }
            WHERE("id = #{data.id}");
        }}.toString();
    }
}
