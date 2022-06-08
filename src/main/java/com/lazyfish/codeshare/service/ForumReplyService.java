package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumReply;
import com.lazyfish.codeshare.vo.ForumReplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ForumReplyService {
    List<ForumReplyVo> getForumReply(int id);
    void insertForumReply(ForumReply forumReply);
    ForumReply getForumReplyById(int id);
}
