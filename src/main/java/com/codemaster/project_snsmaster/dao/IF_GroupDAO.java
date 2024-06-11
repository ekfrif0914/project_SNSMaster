package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.GroupJoinVO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.MemberGroupVO;

import java.util.List;

public interface IF_GroupDAO {
    public void insert(GroupPostVO gpvo)throws Exception;
    public void mginsert(MemberGroupVO mgvo)throws Exception;
    public List<MemberGroupVO> selectAll() throws Exception;
    public int joinsert(GroupJoinVO gjvo)throws Exception;

    public List<GroupPostVO> gpselectAll(String gno)throws  Exception;


    public void delete(int g_no)throws Exception;

   public void mdelete(int gno) throws Exception;



   public GroupPostVO modno(int g_no) throws Exception;

   public void modupdate(GroupPostVO gpvo) throws Exception;
}
