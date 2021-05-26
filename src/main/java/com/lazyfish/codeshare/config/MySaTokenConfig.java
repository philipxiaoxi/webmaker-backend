package com.lazyfish.codeshare.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.interceptor.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MySaTokenConfig implements WebMvcConfigurer {
    // 注册sa-token的登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器，并排除登录接口地址
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            System.out.println("---------- 进入自定义认证 --------------- ");
            System.out.println("执行OPTIONS拦截器,解决跨域验证");
            //判断请求方式，排除OPTIONS请求
            if (request.getMethod().toUpperCase().equals("OPTIONS")) {

            } else {
                SaRouterUtil.match("/**", "/login", () -> StpUtil.checkLogin());
            }
        })).addPathPatterns("/**").excludePathPatterns("/api/insertUser").excludePathPatterns("/common/**");
        // 注册一个自定义认证拦截器 (可以写任意认证代码)
    }
}
