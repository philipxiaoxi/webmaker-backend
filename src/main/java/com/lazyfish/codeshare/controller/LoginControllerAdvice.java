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
        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            //未提供token
            message = "请重新登录。";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            //token无效
            message = "您的认证无效，请重新登录。";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            //token已过期
            message = "您的登录已过期。";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            //token已被顶下线
            message = "您的账号已在其他地方登录，您已被强制下线。";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            //token已被踢下线
            message = "您被管理员强制下线。";
        } else {
            message = "当前会话未登录";
        }
        // 返回给前端
        return new ResultBuild(401, message);
    }


    /**
     * IllegalArgumentException错误返回给前端
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultBuild illegalArgument(Exception e) {
        // 返回给前端
        return new ResultBuild(422, e.getMessage());
    }

    /**
     * RequireIdNotExistException 返回给前端
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({RequireIdNotExistException.class})
    public ResultBuild NotFound(Exception e) {
        // 返回给前端
        return new ResultBuild(404, e.getMessage());
    }

    /**
     * Exception 错误 返回给前端
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultBuild Error(Exception e) {
        // 返回给前端
        System.out.println(e);
        return new ResultBuild(500, e.getMessage());
    }
}

