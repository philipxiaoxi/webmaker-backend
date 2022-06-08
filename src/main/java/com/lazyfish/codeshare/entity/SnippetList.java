package com.lazyfish.codeshare.entity;
import org.apache.commons.codec.digest.DigestUtils;

public class SnippetList {
    private int id;
    private String title;
    private String name;
    private String email;
    private int identity;
    private int type;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return DigestUtils.md5Hex(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
