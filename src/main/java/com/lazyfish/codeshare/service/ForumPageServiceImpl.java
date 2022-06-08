package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.ForumPageDao;
import com.lazyfish.codeshare.entity.ForumPage;
import com.lazyfish.codeshare.vo.ForumListItem;
import com.lazyfish.codeshare.vo.ForumPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ForumPageServiceImpl implements ForumPageService{
    @Autowired
    ForumPageDao forumPageDao;
    @Override
    public List<ForumListItem> getAllForumPage() {
        return forumPageDao.getAllForumPage();
    }

    @Override
    public ForumPageVo getForumPage(int id) {
        return forumPageDao.getForumPage(id);
    }

    @Override
    public void updateForumPageTime(Date time, int id) {
        forumPageDao.updateForumPageTime(time, id);
    }

    @Override
    public void insertForumPage(ForumPage forumPage) {
        forumPageDao.insertForumPage(forumPage);
    }
}
