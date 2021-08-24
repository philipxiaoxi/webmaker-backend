package com.lazyfish.codeshare.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lazyfish.codeshare.service.ForumPageService;
import com.lazyfish.codeshare.service.ForumReplyService;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class ForumReplyController {
    @Resource
    ForumReplyService forumReplyService;
    @RequestMapping("/common/getForumReply")
    @ResponseBody
    public ResultBuild getForumReply(Integer id,Integer pageNum) {
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 12);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(forumReplyService.getForumReply(id));
        return new ResultBuild(200,pageInfo);
    }
}
