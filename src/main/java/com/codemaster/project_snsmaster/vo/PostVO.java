package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class PostVO {

    private int no;
    private String id;
    private String region;
    private String title;
    private String content;
    private String good;
    private String report;
    private String category;
    private String[] file_name;
    private boolean isLike=false;
    private boolean followstate=false;
}
