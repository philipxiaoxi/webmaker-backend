package com.lazyfish.codeshare.service;
import java.util.List;
import java.util.Map;

public interface ForumReplyService {
    List<Map<String, Object>> getForumReply(int id);
}
