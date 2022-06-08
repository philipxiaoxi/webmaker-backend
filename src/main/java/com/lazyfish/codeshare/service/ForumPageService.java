package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumPage;
import com.lazyfish.codeshare.vo.ForumListItem;
import com.lazyfish.codeshare.vo.ForumPageVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ForumPageService {
    List<ForumListItem> getAllForumPage();
    ForumPageVo getForumPage(int id);
    void updateForumPageTime(Date time, int id);
    void insertForumPage(ForumPage forumPage);
}
