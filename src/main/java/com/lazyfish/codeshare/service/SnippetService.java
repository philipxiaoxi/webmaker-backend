package com.lazyfish.codeshare.service;

import com.lazyfish.codeshare.entity.Snippet;
import com.lazyfish.codeshare.entity.SnippetList;

import java.util.List;

public interface SnippetService {
    int getTotal();
    Snippet getSnippet(int id);

    List<SnippetList> getSnippetByUserid(int userid);

    List<SnippetList>  getAllSnippet();

    void insertSnippet(Snippet snippet);

    int updateSnippet(Snippet snippet);

    String getSnippetImg(int id);

    List<SnippetList>  getSearchSnippet(String title);
}
