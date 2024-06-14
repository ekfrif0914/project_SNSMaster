package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;

import java.util.HashMap;
import java.util.List;

public interface IF_ManagerService {
    public void insert(String memo) throws Exception;
    public List<GroupPostVO> groupreport() throws Exception;
    public List<PostVO> postreport() throws Exception;
    public GroupPostVO selectgroupPost(int g_no) throws Exception;
    public void stopinsert(StopMemberVO stop) throws Exception;
    public void delete(int g_no) throws Exception;

   public void delete2(int gNo)throws Exception;

   public PostVO selectpost(int no)throws Exception;



    public List<PostVO>selectrandom(HashMap<String, String> params)throws Exception;
}