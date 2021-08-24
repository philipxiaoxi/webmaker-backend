package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.ForumPageDao;
import com.lazyfish.codeshare.dao.ForumReplyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ForumReplyServiceImpl implements ForumReplyService{
    @Autowired
    ForumReplyDao forumReplyDao;
    @Override
    public List<Map<String, Object>> getForumReply(int id) {
        return forumReplyDao.getForumReply(id);
    }

}
