package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.UserApiTableInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
@Primary
public interface UserApiTableInfoDao {
    @Select("SELECT * FROM user_api_table_info WHERE realName = #{realName}")
    UserApiTableInfo getTableInfo(@Param("realName")String realName);
}
