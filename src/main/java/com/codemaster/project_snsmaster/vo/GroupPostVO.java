package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class GroupPostVO {//그룹 게시물

    private int g_no;
    private int gno;
    private String id;
    private String g_title;
    private String g_content;
    private String g_like;
    private String g_report;
    private String[] file_name;
}
