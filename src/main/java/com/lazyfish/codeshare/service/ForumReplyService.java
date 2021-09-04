package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ForumReplyService {
    List<Map<String, Object>> getForumReply(int id);
    void insertForumReply(ForumReply forumReply);
    ForumReply getForumReplyById(int id);
}
