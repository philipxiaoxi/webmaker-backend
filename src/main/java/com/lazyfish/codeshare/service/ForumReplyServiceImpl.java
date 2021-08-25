package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.ForumPageDao;
import com.lazyfish.codeshare.dao.ForumReplyDao;
import com.lazyfish.codeshare.entity.ForumReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ForumReplyServiceImpl implements ForumReplyService{
    @Autowired
    ForumReplyDao forumReplyDao;
    @Resource
    ForumPageService forumPageService;
    @Override
    public List<Map<String, Object>> getForumReply(int id) {
        return forumReplyDao.getForumReply(id);
    }

    @Override
    public void insertForumReply(ForumReply forumReply) {
        Date time = new Date();
        forumPageService.updateForumPageTime(time, forumReply.getForumId());
        forumReply.setTime(time);
        forumReplyDao.insertForumReply(forumReply);
    }

}
