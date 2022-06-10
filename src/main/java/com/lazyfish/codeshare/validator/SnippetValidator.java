package com.lazyfish.codeshare.validator;
import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.User;
import com.lazyfish.codeshare.exception.RequireIdNotExistException;
import com.lazyfish.codeshare.service.SnippetService;
import com.lazyfish.codeshare.service.UserService;
import com.lazyfish.codeshare.vo.SnippetVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SnippetValidator {
    @Resource
    SnippetService snippetService;
    @Resource
    UserService userService;
    public SnippetVo validate(int userId, int snippetId){
        SnippetVo snippet = snippetService.getSnippet(snippetId);
        User user = userService.getUser(userId);
        if (snippet == null) {
            throw new RequireIdNotExistException("代码段ID不存在。");
        }
        if(userId == snippet.getUserid() || user.getType() ==1 ){
            return snippet;
        }else{
            throw new IllegalArgumentException("不是你的代码片段。");
        }
    }
}
