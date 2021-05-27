package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.UserApiTableItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
@Primary
public interface UserApiTableItemDao {
    @Select("SELECT * FROM user_api_table_item WHERE realItemName = #{realItemName}")
    UserApiTableItem getTableItemInfo(@Param("realItemName")String realItemName);
}
