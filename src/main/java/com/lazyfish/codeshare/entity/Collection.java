package com.lazyfish.codeshare.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Data
public class ForumPage {
    Integer id;
    Integer userId;
    String preface;
    String title;
    String topic;
    String content;
    Date time;
    Date updateTime;
}
