package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NotificationVO {
    public String id;
    public String content;
    public int state;
    public String url;
    public String nowtime;
}
