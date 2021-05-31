package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.User;
import com.lazyfish.codeshare.sqlprovider.UserTableSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
@Primary
public interface UserTableDao {
    /**
     * 查询语句
     * @param sql
     * @return
     */
    @Select("${sql}")
    List<Map<String, Object>> getUserTable(@Param("sql")String sql);

    @UpdateProvider(type= UserTableSqlProvider.class,method="update")
    int updateUserTableByField(String tableName,@Param("data") Map<String, Object> data);
}