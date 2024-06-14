package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.*;

import java.util.List;

public interface IF_GroupService {
    public void insert(GroupPostVO gpvo) throws Exception;

    public void gjinsert(G_joinVO gjvo) throws Exception;

    public void mginsert(MemberGroupVO mgvo) throws Exception;

    public List<MemberGroupVO> gList() throws Exception;

    public void joinsert(GroupJoinVO gjvo) throws Exception;

    public void gjminsert(GroupJoinVO gjvo) throws Exception;

    public void gmjinsert(G_memberVO gmvo) throws Exception;

    public List<GroupPostVO> gpList(String gno) throws Exception;

    public List<G_joinVO> gjList(String gno) throws Exception;

    public List<GroupJoinVO> gjoList(String gno) throws Exception;

    public void delete(int g_no) throws Exception;

    public void gjdelete(int moNo) throws Exception;

    public void mdelete(int gno) throws Exception;


    GroupPostVO modno(int g_no) throws Exception;

    G_joinVO gmjoinmod(int mo_no) throws Exception;

    public void modnoSaver(GroupPostVO gpvo) throws Exception;

    public void gmjoinmodSave(G_joinVO gjvo) throws Exception;

    public GroupPostVO gpselect(String g_no) throws Exception;

    public void gcinsert(GroupPostCommentVO gcvo) throws Exception;

    public List<GroupPostCommentVO> cmList(String g_no) throws Exception;


    public void commentdel(int c_no) throws Exception;

   public void gjmdelete(GroupJoinVO gjvo) throws Exception;

   public void dgjmdelete(int wait_no) throws Exception;


    // public int countselect(int mo_no) throws Exception;
}
