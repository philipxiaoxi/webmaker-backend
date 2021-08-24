package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.ForumPageDao;
import com.lazyfish.codeshare.entity.ForumPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ForumPageServiceImpl implements ForumPageService{
    @Autowired
    ForumPageDao forumPageDao;
    @Override
    public List<Map<String, Object>> getAllForumPage() {
        return forumPageDao.getAllForumPage();
    }

    @Override
    public Map<String, Object> getForumPage(int id) {
        return forumPageDao.getForumPage(id);
    }
}