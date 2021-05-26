package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.dao.SnippetDao;
import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.SnippetList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SnippetServiceImpl implements SnippetService{
    @Autowired
    SnippetDao snippetDao;
    @Override
    public int getTotal() {
        return snippetDao.getTotal();
    }

    @Override
    public Snippet getSnippet(int id) {
        return snippetDao.getSnippet(id);
    }

    @Override
    public List<SnippetList> getSnippetByUserid(int userid) {
        return snippetDao.getSnippetByUserid(userid);
    }

    @Override
    public List<SnippetList> getAllSnippet() {
        return snippetDao.getAllSnippet();
    }

    @Override
    public void insertSnippet(Snippet snippet) {
        snippetDao.insertSnippet(snippet);
    }

    @Override
    public int updateSnippet(Snippet snippet) {
        return snippetDao.updateSnippet(snippet);
    }

    @Override
    public String getSnippetImg(int id) {
        Snippet snippet = snippetDao.getSnippetImg(id);
        if(snippet!=null){
            return snippetDao.getSnippetImg(id).getImg();
        }else{
            return "/img/code.jpg";
        }

    }

    @Override
    public List<SnippetList> getSearchSnippet(String title) {
        return snippetDao.getSearchSnippet(title);
    }
}
