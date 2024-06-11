package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.GroupJoinVO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.MemberGroupVO;

import java.util.List;

public interface IF_GroupService {
    public void insert(GroupPostVO gpvo)throws Exception;

   public void mginsert(MemberGroupVO mgvo) throws Exception;

   public List<MemberGroupVO> gList() throws Exception;
   public  void joinsert(GroupJoinVO gjvo) throws Exception;

    public List<GroupPostVO> gpList(String gno)throws Exception;

    public void delete(int g_no)throws Exception;

   public void mdelete(int gno) throws Exception;


    GroupPostVO modno(int g_no) throws Exception;

   public void modnoSaver(GroupPostVO gpvo) throws Exception;
}
