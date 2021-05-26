package com.lazyfish.codeshare.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.SnippetList;
import com.lazyfish.codeshare.service.SnippetService;
import com.lazyfish.codeshare.utils.ResultBuild;
import com.lazyfish.codeshare.validator.SnippetValidator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@CrossOrigin(origins = "*") // 支持跨域
@Controller
public class SnippetController {
    @Resource
    SnippetService snippetService;
    @Resource
    SnippetValidator snippetValidator;
    @RequestMapping("/api/getSnippetByUserid")
    @ResponseBody
    public ResultBuild getSnippetByUserid(@RequestHeader("token") String token,int pageNum) {
        String id = (String) StpUtil.getLoginIdByToken(token);
        if (id == null) {
            return new ResultBuild(401, "无有效id。");
        }
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 10);
        PageInfo<SnippetList> pageInfo = new PageInfo<>(snippetService.getSnippetByUserid(Integer.parseInt(id)));
        return new ResultBuild(200,pageInfo);

    }
    @RequestMapping("/common/getAllSnippet")
    @ResponseBody
    public ResultBuild getAllSnippet(int pageNum) {
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 10);
        PageInfo<SnippetList> pageInfo = new PageInfo<>(snippetService.getAllSnippet());
        return new ResultBuild(200,pageInfo);

    }
    @RequestMapping("/common/getSearchSnippet")
    @ResponseBody
    public ResultBuild getSearchSnippet(int pageNum,String title) {
        //[pageNum, pageSize]  页码  每页显示数量
        PageHelper.startPage(pageNum, 10);
        PageInfo<SnippetList> pageInfo = new PageInfo<>(snippetService.getSearchSnippet(title));
        return new ResultBuild(200,pageInfo);
    }
    @RequestMapping("/common/getSnippetImg/{id}")
    @ResponseBody
    public ResultBuild getSnippetImg(@PathVariable int id) {
        return new ResultBuild(200,snippetService.getSnippetImg(id)) ;
    }
    @RequestMapping("/common/getSnippet")
    @ResponseBody
    public ResultBuild getSnippet(Integer id) {
        Snippet snippet = snippetService.getSnippet(id);
        return new ResultBuild(200,snippet);
    }

    // TODO: 2021/5/23 修复片段ID是前端传过来的
    @RequestMapping("/api/updateSnippet")
    @ResponseBody
    public ResultBuild updateSnippet(@RequestHeader("token") String token,Snippet snippet) {
        String userid = (String) StpUtil.getLoginIdByToken(token);
        snippetValidator.validate(Integer.parseInt(userid),snippet.getId());
        return new ResultBuild(200,snippetService.updateSnippet(snippet));
    }

    @RequestMapping("/api/insertSnippet")
    @ResponseBody
    public ResultBuild insertSnippet(@RequestHeader("token") String token,Snippet snippet) {
        Integer userid = Integer.parseInt((String) StpUtil.getLoginIdByToken(token));
        snippet.setUserid(userid);
        snippetService.insertSnippet(snippet);
        return new ResultBuild(200,snippet.getId());
    }

    /**
     * 测试图片跨域
     * 假设客户端传入一个url,添加
     */
        @RequestMapping(value = "/common/get",produces = MediaType.IMAGE_JPEG_VALUE)
        @ResponseBody
        public byte[] getImage() throws IOException {
            File file = new File("C:\\userData_new\\xiaoxi\\code\\codeshare\\public\\img\\code.jpg");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }
    /**
     * 测试图片跨域
     * 假设客户端传入一个url,添加
     */

    @RequestMapping(value = "/common/getImageByHeader",produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
    public void getImageByHeader(String src, HttpServletResponse response) throws IOException {
        URL url = new URL(src);
        HttpURLConnection conn =(HttpURLConnection)url.openConnection();
        String v = conn.getHeaderField("Content-Length");
        response.setHeader("Content-Length", v);
        InputStream inputStream=conn.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[10240];

        int cnt = 0;
        while ( (cnt = inputStream.read(bytes) ) > 0) {
            outputStream.write(bytes, 0, cnt);
        }
    }
}
