package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.service.SnippetService;
import com.lazyfish.codeshare.utils.DirOperation2;
import com.lazyfish.codeshare.utils.FileUtils;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lazyfish.codeshare.validator.SnippetValidator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class SnippetProjectController {
    @Resource
    SnippetService snippetService;
    @Resource
    SnippetValidator snippetValidator;
    @RequestMapping("/common/getSnippetProject")
    @ResponseBody
    public ResultBuild getSnippetProject(Integer id) {
        return new ResultBuild(200, DirOperation2.getTree(id));
    }
    @RequestMapping("/common/getSnippetProjectFile/**")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getSnippetProjectFile(HttpServletRequest httpServletRequest) {
        String url = String.valueOf(httpServletRequest.getRequestURL());
        String urls[] = url.split("/");
        System.out.println(urls[5]);
        String id =urls[5];
        String path=url.substring(url.indexOf(urls[5]),url.length());
        System.out.println(path);
        String temp_path = "D:\\codeshare_res\\code\\" +"\\" +path;
        return ResponseEntity.ok()
                .header("Content-Type", FileUtils.getContentType(url))
                .body(new FileSystemResource(temp_path));
    }
    @RequestMapping("/api/updateSnippetProjectFile")
    @ResponseBody
    public ResultBuild updateSnippet(@RequestHeader("token") String token,String content,String path) throws Exception {
        Pattern pattern = Pattern.compile("(\\\\|/)\\.\\.(\\\\|/)|(\\\\|/)\\.\\.$|^\\.\\.(\\\\|/)");
        String id = path.split("/")[0];
        if(pattern.matcher(path).find()){
            throw new Exception("非法的路径，不得包含/../或使用/..结尾");
        }
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,DirOperation2.update(path,content));
    }
}
