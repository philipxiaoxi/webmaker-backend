package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.ForumPage;
import com.lazyfish.codeshare.vo.ForumListItem;
import com.lazyfish.codeshare.vo.ForumPageVo;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
@Primary
public interface ForumPageDao {
    @Select("select `user`.name as author,`user`.identity,`user`.email,forum_page.id,forum_page.preface,forum_page.topic,forum_page.time,forum_page.title from user,forum_page where `user`.id=forum_page.userId order by updateTime desc")
    List<ForumListItem> getAllForumPage();
    @Select("select `user`.name as author,`user`.identity,`user`.email,forum_page.* from user,forum_page where `user`.id=forum_page.userId AND forum_page.id = #{id} order by time desc")
    ForumPageVo getForumPage(@Param("id")int id);
    @Update("UPDATE `forum_page` SET `updateTime`=#{time} WHERE (`id`=#{id})")
    void updateForumPageTime(@Param("time") Date time, @Param("id")int id);
    @Insert("INSERT INTO `forum_page` (`userId`, `preface`, `title`, `topic`, `content`, `time`, `updateTime`) VALUES (#{userId}, #{preface}, #{title}, #{topic}, #{content}, #{time}, #{updateTime})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insertForumPage(ForumPage forumPage);
}
