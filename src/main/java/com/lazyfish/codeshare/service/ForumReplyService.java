package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumReply;

import java.util.List;
import java.util.Map;

public interface ForumReplyService {
    List<Map<String, Object>> getForumReply(int id);
    void insertForumReply(ForumReply forumReply);
}
