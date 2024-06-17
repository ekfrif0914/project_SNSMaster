package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.MemberVO;

import java.util.HashMap;
import java.util.Map;

public interface IF_AdminDAO {
    public void insert(MemberVO memberVO) throws Exception;

    public int overlappedID(String id) throws Exception;

    public int overlappedEmail(String email) throws Exception;

    public void updateProfile(Map<String, Object> param) throws Exception;

    public MemberVO getMember(String id) throws Exception;

    public void updateSave(MemberVO memberVO) throws Exception;

    public void stop(String id) throws Exception;
}
