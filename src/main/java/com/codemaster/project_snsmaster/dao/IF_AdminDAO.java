package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.MemberVO;

public interface IF_AdminDAO {
    public void insert(MemberVO memberVO)throws  Exception;

    public int overlappedID(String id) throws Exception;

    public int overlappedEmail(String email) throws Exception;
}
