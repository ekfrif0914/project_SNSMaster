package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.MemberVO;

import java.util.List;

public interface IF_LoginService {

    public MemberVO login(String id) throws Exception;
    public List<MemberVO> idSearch(MemberVO memberVO) throws Exception;
}
