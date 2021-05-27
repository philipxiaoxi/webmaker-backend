package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    /**
     * 查询一个表的一项内容，支持所选字段，搜索条件
     * @param fields
     * @param field
     * @param data
     * @return
     */
    @Select("SELECT #{fields} FROM #{tableName} where #{field} = #{data}")
    Map<String, Object> getUserTableByField(@Param("fields")String fields, @Param("field")String field,@Param("data")String data);

    @Update("UPDATE user SET ${fields} where ${field} = ${data}")
    int updateUserTableByField(@Param("fields")String fields,@Param("field")String field,@Param("data")String data);
}
