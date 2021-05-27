package com.lazyfish.codeshare.sqlprovider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class UserTableSqlProvider {
    /**
     * 获取自定义字段的表格全部内容
     * @param fields
     * @param tableName
     * @return
     */
    public String select(String fields,String tableName){
        return new SQL(){{
            SELECT(fields);
            FROM(tableName);
        }}.toString();
    }

    /**
     * 获取表格全部内容
     * @param tableName
     * @return
     */
    public String select(String tableName){
        return new SQL(){{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }
    /**
     * 获取条件相等列表格全部内容
     * @return
     */
    public String select(){
        return new SQL(){{
            SELECT("*");
            FROM("#{tableName}");
            WHERE("#{field} = #{data}");
        }}.toString();
    }
}
