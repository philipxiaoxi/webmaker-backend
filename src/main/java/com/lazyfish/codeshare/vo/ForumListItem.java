package com.lazyfish.codeshare.vo;

import org.apache.commons.codec.digest.DigestUtils;

public class ForumListItem {
    String preface;
    String author;
    String identity;
    String topic;
    String id;
    String time;
    String title;
    String email;

    public String getPreface() {
        return preface;
    }

    public void setPreface(String preface) {
        this.preface = preface;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return DigestUtils.md5Hex(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
