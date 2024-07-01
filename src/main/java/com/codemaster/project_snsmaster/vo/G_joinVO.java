package com.codemaster.project_snsmaster.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class G_joinVO {
    private int mo_no;
    private int gno;
    private String id;
    private String m_name;
    private int cnt;//인원
    private String m_contents;
    private int cont;//몇명 찼는지
}
