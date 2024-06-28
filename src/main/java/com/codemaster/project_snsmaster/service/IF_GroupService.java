package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.*;

import java.util.HashMap;
import java.util.List;

public interface IF_GroupService {
    public void insert(GroupPostVO gpvo) throws Exception;

    public void gjinsert(G_joinVO gjvo) throws Exception;

    public void mginsert(MemberGroupVO mgvo) throws Exception;

    public List<MemberGroupVO> gList() throws Exception;

    public void joinsert(GroupJoinVO gjvo) throws Exception;

    public void gjminsert(GroupJoinVO gjvo) throws Exception;


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

    public List<MemberGroupVO> search(HashMap<String, String> param) throws Exception;

    public List<G_joinVO> gjsearch(HashMap<String, String> param) throws Exception;

   public List<GroupPostVO> gpsearch(HashMap<String, String> param) throws Exception;

    public List<GroupJoinVO> gjoinList(HashMap<String, String> param) throws Exception;

    public List<MemberGroupVO> mgList(HashMap<String, String> param) throws Exception;

   public List<GroupJoinVO> gjoinpopList(String gno) throws Exception;

    public Object greport(HashMap<String, String> param) throws Exception;

   public Object likegroup(HashMap<String, String> param) throws Exception;

    public Object likegroupdel(HashMap<String, String> likedel) throws Exception;

    public void greportselect(int g_no) throws Exception;

   public void likepostselect(int g_no)throws Exception;

    public void likepostdel(int g_no)throws Exception;

  public int reportsaveselect(HashMap<Object, Object> report) throws Exception;

   public int likeselect(HashMap<Object, Object> likemap) throws Exception;

   public int gjoinselect(HashMap<Object, Object> gjoinmap) throws Exception;

   public int gjoinwaitselect(HashMap<Object, Object> gjoinwait)throws Exception;

    public void monoup(int mo_no) throws Exception;

    public int joinmb(HashMap<Object, Object> gmjoin) throws Exception;

   public void joininsert(HashMap<Object, Object> joininsert)throws Exception;




    //int reportsaveselect(int gNo, String id);


    /* public int reportsaveselect(int g_no)throws Exception;*/
}
