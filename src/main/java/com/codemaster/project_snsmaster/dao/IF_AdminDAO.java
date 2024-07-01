package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.FAQVO;
import com.codemaster.project_snsmaster.vo.FollowVO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import com.codemaster.project_snsmaster.vo.PageVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IF_AdminDAO {
    public void insert(MemberVO memberVO) throws Exception;

    public int overlappedID(String id) throws Exception;

    public int overlappedEmail(String email) throws Exception;

    public void updateProfile(Map<String, Object> param) throws Exception;

    public MemberVO getMember(String id) throws Exception;

    public void updateSave(MemberVO memberVO) throws Exception;

    public void stop(String id) throws Exception;

    public void deletePostArray(String postArray) throws Exception;

    public void deletegPostArray(String gpostArray) throws Exception;

    public void deletegJoinArray(String gjoinArray) throws Exception;

    public void changeDefaultimg(String id) throws Exception;

    public void faqinsert(FAQVO faqvo) throws Exception;

    public List<FAQVO> faqselect(PageVO pvo) throws Exception;

    public FAQVO selectOne(String f_no) throws Exception;

    public void viewUp(String f_no) throws Exception;

    public int getTotalcount() throws Exception;

    public int getSearchTotalCount(HashMap<String, String> param) throws Exception;

    public List<FAQVO> faqSearchselect(PageVO pvo) throws Exception;

    public int cntFollowing(FollowVO fvo) throws Exception;

    public void insertFollowing(FollowVO fvo) throws Exception;

    public List<String> selectMyFollowinglist(String userid) throws Exception;

    public void deleteFollowing(FollowVO fvo) throws Exception;

    public int myfollowCount(String id)throws Exception;

    public int myfollowingCount(String id)throws Exception;

    public List<String> myfollowList (String id) throws Exception;

}
