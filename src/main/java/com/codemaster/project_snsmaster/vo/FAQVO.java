package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class FAQVO {
    private int rownum;
    private int f_no;
    private String id;
    private String title;
    private String content;
    private String today;
    private String view_c;
    private boolean secret;

}
