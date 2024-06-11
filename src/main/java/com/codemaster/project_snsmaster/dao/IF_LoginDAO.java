package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.MemberVO;

import java.util.List;

public interface IF_LoginDAO {

    public MemberVO selectOne(String id) throws Exception;

    public List<MemberVO> idSearch(MemberVO vo) throws Exception;
}
