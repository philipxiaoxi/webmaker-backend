package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.exception.NotLoginException;
import com.lazyfish.codeshare.exception.RequireIdNotExistException;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常捕获处理
 */
@ControllerAdvice //controller增强器
public class LoginControllerAdvice {
    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ResponseBody
    @ExceptionHandler(NotLoginException.class)
    public ResultBuild handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        //nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return new ResultBuild(401, message);
    }



    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultBuild illegalArgument(Exception e) {
        // 返回给前端
        return new ResultBuild(422, e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({RequireIdNotExistException.class})
    public ResultBuild NotFound(Exception e) {
        // 返回给前端
        return new ResultBuild(404, e.getMessage());
    }
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultBuild Error(Exception e) {
        // 返回给前端
        return new ResultBuild(500, e.getMessage());
    }
}

