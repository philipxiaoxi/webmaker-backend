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
    public ResultBuild DockerBuild(@RequestHeader("token") String token, String password) throws Exception {
        String id = (String) StpUtil.getLoginIdByToken(token);
        int docker_num = 1;
        if(redisTemplate.opsForValue().get("docker_num")!=null){
            docker_num = (int) redisTemplate.opsForValue().get("docker_num");
        }
        int docker_port = 8000 + docker_num;
        Object object = dockerClientService.createContainer("testContainer"+id,id,password,String.valueOf(docker_port));
        redisTemplate.opsForValue().set("docker_num",docker_num + 1);
        return new ResultBuild(200,object);
    }
    @RequestMapping("/api/dockerCheck")
    @ResponseBody
    public ResultBuild dockerCheck(@RequestHeader("token") String token) throws Exception {
        String id = (String) StpUtil.getLoginIdByToken(token);
        Long createTime = (Long) redisTemplate.opsForValue().get("docker"+id+"_time");
        return new ResultBuild(200,createTime);
    }

}
