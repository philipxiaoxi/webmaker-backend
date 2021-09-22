package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lazyfish.codeshare.entity.ForumReply;
import com.lazyfish.codeshare.service.DockerClientService;
import com.lazyfish.codeshare.service.ForumReplyService;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class DockerController {
    @Resource
    DockerClientService dockerClientService;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;
    @RequestMapping("/api/dockerBuild")
    @ResponseBody
    public ResultBuild DockerBuild(String password) throws Exception {
        dockerClientService.createContainer("testContainer","1",password,"8443");
        return new ResultBuild(200,"8443");
    }
    @RequestMapping("/api/dockerCheck")
    @ResponseBody
    public ResultBuild dockerCheck(String password) throws Exception {
        Long createTime = (Long) redisTemplate.opsForValue().get("docker1_time");
        return new ResultBuild(200,createTime);
    }

}
