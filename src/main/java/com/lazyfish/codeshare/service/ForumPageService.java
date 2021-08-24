package com.lazyfish.codeshare.service;
import com.lazyfish.codeshare.entity.ForumPage;
import java.util.List;
import java.util.Map;

public interface ForumPageService {
    List<Map<String, Object>> getAllForumPage();
    Map<String, Object> getForumPage(int id);
}
