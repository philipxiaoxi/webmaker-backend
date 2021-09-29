package com.lazyfish.codeshare.service;

import java.util.ArrayList;
import java.util.List;

import com.lazyfish.codeshare.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpInterface;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
@Slf4j
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    UserService userService;


    @Override
    public List<String> getPermissionList(Object o, String s) {
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userService.getUser(Integer.parseInt((String) loginId));
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        System.out.println(user.getName());
        if(user.getType() == 1) {
            list.add("admin");
            log.info("添加了admin");
        }
        return list;
    }

}
