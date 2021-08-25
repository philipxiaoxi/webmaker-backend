package com.lazyfish.codeshare.entity;

import lombok.Data;

import java.util.Date;
@Data
public class ForumReply {
    Integer id;
    Integer forumId;
    String content;
    Integer replyId;
    Integer userId;
    Date time;
}
