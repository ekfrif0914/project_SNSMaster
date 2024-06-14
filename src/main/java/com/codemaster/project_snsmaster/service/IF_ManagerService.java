package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;

import java.util.List;

public interface IF_ManagerService {
    public void insert(String memo) throws Exception;
    public List<GroupPostVO> groupreport() throws Exception;
    public List<PostVO> postreport() throws Exception;
    public GroupPostVO selectpost(int g_no) throws Exception;
    public void stopinsert(StopMemberVO stop) throws Exception;
    public void delete(int g_no) throws Exception;
}