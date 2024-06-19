package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.MemberVO;

public interface IF_AdminService {

    public void insert(MemberVO memberVO)throws Exception;

    public int overlappedID(String id) throws Exception;

    public int overlappedEmail(String email) throws Exception;

    public void updateProfile(String id, String[] file_name)throws Exception;

    public MemberVO getMember(String id) throws Exception;

    public void updateSave(MemberVO memberVO)throws Exception;

    public void stop(String id) throws Exception;

    public void deletePostArray(String postArray) throws Exception;

    public void deletegPostArray(String gpostArray) throws Exception;

    public void deletegJoinArray(String gjoinArray) throws Exception;

    public void changeDefaultimg(String id)throws Exception;
}
