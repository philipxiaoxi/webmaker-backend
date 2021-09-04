package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumPage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ForumPageService {
    List<Map<String, Object>> getAllForumPage();
    Map<String, Object> getForumPage(int id);
    void updateForumPageTime(Date time, int id);
    void insertForumPage(ForumPage forumPage);
}
