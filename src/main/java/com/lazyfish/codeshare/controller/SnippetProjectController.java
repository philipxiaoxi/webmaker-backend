package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.service.SnippetService;
import com.lazyfish.codeshare.utils.DirOperation;
import com.lazyfish.codeshare.utils.FileUtils;
import com.lazyfish.codeshare.utils.ResultBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.lazyfish.codeshare.validator.SnippetValidator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class SnippetProjectController {
    @Resource
    SnippetService snippetService;
    @Resource
    SnippetValidator snippetValidator;
    @Autowired
    DirOperation dirOperation;
    @Value("${userProjects.path}")
    String rootPath;
    @RequestMapping("/common/getSnippetProject")
    @ResponseBody
    public ResultBuild getSnippetProject(Integer id) {
        return new ResultBuild(200,dirOperation.getTree(id));
    }
    @RequestMapping("/common/getSnippetProjectFile/**")
    @ResponseBody
    public ResponseEntity<FileSystemResource> getSnippetProjectFile(HttpServletRequest httpServletRequest) throws Exception {
        String url = String.valueOf(httpServletRequest.getRequestURL());
        String urls[] = url.split("/");
        String path=url.substring(url.indexOf(urls[5]));
        FileUtils.pathTest(path);
        String temp_path = rootPath+"/code/" +"/" +path;
        return ResponseEntity.ok()
                .header("Content-Type", FileUtils.getContentType(url))
                .body(new FileSystemResource(temp_path));
    }
    @RequestMapping("/api/updateSnippetProjectFile")
    @ResponseBody
    public ResultBuild updateSnippetProjectFile(@RequestHeader("token") String token,String content,String path) throws Exception {
        FileUtils.pathTest(path);
        String id = path.split("/")[0];
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,dirOperation.update(path,content));
    }
    @RequestMapping("/api/SnippetProjectReName")
    @ResponseBody
    public ResultBuild SnippetProjectReName(@RequestHeader("token") String token,String path,String new_path) throws Exception {
        FileUtils.pathTest(path);
        FileUtils.pathTest(new_path);
        String id = path.split("/")[0];
        String id2 = new_path.split("/")[0];
        if(!id.equals(id2)){
            throw new Exception("非法跨片段存储。");
        }
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,dirOperation.reName(path, new_path));
    }
    @RequestMapping("/api/SnippetProjectNewDirectory")
    @ResponseBody
    public ResultBuild SnippetProjectNewDirectory(@RequestHeader("token") String token,String path) throws Exception {
        FileUtils.pathTest(path);
        String id = path.split("/")[0];
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,dirOperation.newDirectory(path));
    }
    @RequestMapping("/api/SnippetProjectNewFile")
    @ResponseBody
    public ResultBuild SnippetProjectNewFile(@RequestHeader("token") String token,String path,String name) throws Exception {
        FileUtils.pathTest(path);
        FileUtils.pathTest(name);
        String id = path.split("/")[0];
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,dirOperation.newFile(path,name));
    }
    @RequestMapping("/api/SnippetProjectDelFile")
    @ResponseBody
    public ResultBuild SnippetProjectDelFile(@RequestHeader("token") String token,String path) throws Exception {
        FileUtils.pathTest(path);
        String id = path.split("/")[0];
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        return new ResultBuild(200,dirOperation.delFile(path));
    }
    @RequestMapping("/api/insertSnippetProject")
    @ResponseBody
    public ResultBuild insertSnippet(@RequestHeader("token") String token, Snippet snippet) throws IOException {
        Integer userid = Integer.parseInt((String) StpUtil.getLoginIdByToken(token));
        snippet.setUserid(userid);
        snippet.setType(1);
        snippetService.insertSnippet(snippet);
        dirOperation.newDirectory(String.valueOf(snippet.getId()));
        return new ResultBuild(200,snippet.getId());
    }
    @RequestMapping("/api/SnippetProjectMoveFile")
    @ResponseBody
    public ResultBuild SnippetProjectMoveFile(@RequestHeader("token") String token,String path,String new_path) throws Exception {
        FileUtils.pathTest(path);
        FileUtils.pathTest(new_path);
        String id = path.split("/")[0];
        String id2 = new_path.split("/")[0];
        if(!id.equals(id2)){
            throw new Exception("非法跨片段存储。");
        }
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),Integer.valueOf(id));
        dirOperation.moveFile(path, new_path);
        return new ResultBuild(200,true);
    }
}
