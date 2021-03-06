package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lazyfish.codeshare.entity.SnippetList;
import com.lazyfish.codeshare.entity.User;
import com.lazyfish.codeshare.service.UserService;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultBuild login(String phone, String password) {
        User user = userService.getUserByLogin(phone, password);
        if (user != null) {
            StpUtil.setLoginId(user.getId());
            // 获取当前会话的token信息参数
            return new ResultBuild(200, StpUtil.getTokenInfo().tokenValue);
        } else {
            return new ResultBuild(401, "账号密码验证失败");
        }
    }

    @RequestMapping("/api/getUserInfo")
    @ResponseBody
    public ResultBuild getUserInfo(@RequestHeader("token") String token) {
        String id = (String) StpUtil.getLoginIdByToken(token);
        if (id == null) {
            return new ResultBuild(401, "无有效id。");
        }
        User user = userService.getUser(Integer.parseInt(id));
        if (user == null) {
            return new ResultBuild(401, "用户信息不存在。");
        } else {
            return new ResultBuild(200, user);
        }
    }

    @RequestMapping("/api/updateUser")
    @ResponseBody
    public ResultBuild updateUser(@RequestHeader("token") String token, User modify_user) throws Exception {
        String id = (String) StpUtil.getLoginIdByToken(token);
        if (id == null) {
            return new ResultBuild(401, "无有效id。");
        }
        User user = userService.getUser(Integer.parseInt(id));
        if (user == null) {
            return new ResultBuild(401, "用户信息不存在。");
        } else {
            modify_user.setId(Integer.parseInt(id));
            return new ResultBuild(200, userService.updateUser(modify_user));
        }
    }

    @RequestMapping("/common/insertUser")
    @ResponseBody
    public ResultBuild insertUser(String phone, String password,String email, String code) throws Exception {
        if (phone != null && phone.equals("") != true && password != null && password.equals("") != true) {
            if (userService.getUserByEmail(email) == null) {
                return new ResultBuild(200, userService.insertUser(phone, password, email, code));
            } else {
                return new ResultBuild(-1, "邮箱已经注册。");
            }
        } else {
            return new ResultBuild(-1, "账号或密码为空。");
        }
    }
    @RequestMapping("/common/sendSignCode")
    @ResponseBody
    public ResultBuild sendSignCode(HttpServletRequest httpServletRequest, String email) throws Exception {
        userService.getRandomString(4, email, httpServletRequest.getRemoteAddr());
        return new ResultBuild(200, "您的邮件已发送，请查收。");
    }

    @SaCheckRole("admin")
    @RequestMapping("/api/getAllUser")
    @ResponseBody
    public ResultBuild getAllUser(int pageNum){
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 12);
        PageInfo<User> pageInfo = new PageInfo<>(userService.getAllUser());
        return new ResultBuild(200,pageInfo);
    }
}
