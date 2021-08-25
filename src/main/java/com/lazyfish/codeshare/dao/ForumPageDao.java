package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.ForumPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
@Primary
public interface ForumPageDao {
    @Select("select `user`.name as author,`user`.identity,forum_page.id,forum_page.preface,forum_page.topic,forum_page.time,forum_page.title from user,forum_page where `user`.id=forum_page.userId order by updateTime desc")
    List<Map<String, Object>> getAllForumPage();
    @Select("select `user`.name as author,`user`.identity,forum_page.* from user,forum_page where `user`.id=forum_page.userId AND forum_page.id = #{id} order by time desc")
    Map<String, Object> getForumPage(@Param("id")int id);
    @Update("UPDATE `forum_page` SET `updateTime`=#{time} WHERE (`id`=#{id})")
    void updateForumPageTime(@Param("time") Date time, @Param("id")int id);
}
