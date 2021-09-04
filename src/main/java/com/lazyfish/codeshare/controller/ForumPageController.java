package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lazyfish.codeshare.entity.ForumPage;
import com.lazyfish.codeshare.service.ForumPageService;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class ForumPageController {
    @Resource
    ForumPageService forumPageService;
    @RequestMapping("/common/getAllForumPage")
    @ResponseBody
    public ResultBuild getAllForumPage(int pageNum) {
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 12);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(forumPageService.getAllForumPage());
        return new ResultBuild(200,pageInfo);
    }
    @RequestMapping("/common/getForumPage")
    @ResponseBody
    public ResultBuild getForumPage(Integer id) {
        Map<String, Object> forumPage = forumPageService.getForumPage(id);
        return new ResultBuild(200,forumPage);
    }
    @RequestMapping("/api/insertForumPage")
    @ResponseBody
    public ResultBuild insertForumPage(@RequestHeader("token") String token, ForumPage forumPage) {
        String userId = (String) StpUtil.getLoginIdByToken(token);
        forumPage.setUserId(Integer.parseInt(userId));
        Date time = new Date();
        forumPage.setTime(time);
        forumPage.setUpdateTime(time);
        forumPageService.insertForumPage(forumPage);
        return new ResultBuild(200,forumPage.getId());
    }
}
