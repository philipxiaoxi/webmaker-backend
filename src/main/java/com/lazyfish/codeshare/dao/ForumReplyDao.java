package com.lazyfish.codeshare.dao;

import com.lazyfish.codeshare.entity.ForumReply;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
@Primary
public interface ForumReplyDao {
    @Select("SELECT A.*,C.name AS replyName,D.name AS name from forum_reply A LEFT JOIN forum_reply B ON A.replyId = B.id LEFT JOIN user D ON A.userId = D.id LEFT JOIN user C ON B.userId = C.id where A.forumId = #{id} ORDER BY A.time DESC")
    List<Map<String, Object>> getForumReply(@Param("id")int id);

    @Insert("INSERT INTO `forum_reply` (`forumId`, `content`,`replyId`, `userId`, `time`) VALUES (#{forumId}, #{content}, #{replyId}, #{userId}, #{time})")
    void insertForumReply(ForumReply forumReply);
}
