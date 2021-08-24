package com.lazyfish.codeshare.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
@Primary
public interface ForumReplyDao {
    @Select("SELECT A.*,C.name AS replyName,D.name AS name from forum_reply A\n" +
            "LEFT JOIN forum_reply B ON A.replyId = B.id\n" +
            "LEFT JOIN user D ON A.userId = D.id\n" +
            "LEFT JOIN user C ON B.userId = C.id\n" +
            "where A.forumId = #{id}")
    List<Map<String, Object>> getForumReply(@Param("id")int id);
}
