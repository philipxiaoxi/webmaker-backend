package com.lazyfish.codeshare.vo;

import org.apache.commons.codec.digest.DigestUtils;

public class SnippetVo {
    private int id;
    private int userid;
    private String content;
    private String title;
    private String note;
    private String img;
    private int type =0;
    private String email;
    private String name;
    private int identity;
    private int openSource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        if(this.email.contains("@")) {
            return DigestUtils.md5Hex(this.email);
        }
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenSource() {
        return openSource;
    }

    public void setOpenSource(int openSource) {
        this.openSource = openSource;
    }
}
