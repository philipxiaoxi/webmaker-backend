package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.ForumPageDao;
import com.lazyfish.codeshare.dao.ForumReplyDao;
import com.lazyfish.codeshare.entity.ForumReply;
import com.lazyfish.codeshare.vo.ForumReplyVo;
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
    public List<ForumReplyVo> getForumReply(int id) {
        return forumReplyDao.getForumReply(id);
    }

    @Override
    public void insertForumReply(ForumReply forumReply) {
        Date time = new Date();
        forumPageService.updateForumPageTime(time, forumReply.getForumId());
        forumReply.setTime(time);
        forumReplyDao.insertForumReply(forumReply);
    }

    @Override
    public ForumReply getForumReplyById(int id) {
        return forumReplyDao.getForumReplyById(id);
    }

}
