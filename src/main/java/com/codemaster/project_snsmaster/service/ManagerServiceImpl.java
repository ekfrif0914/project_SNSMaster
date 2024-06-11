package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_ManagerDAO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements IF_ManagerService{
@Autowired
IF_ManagerDAO mdao;

    @Override
    public void insert(String memo) throws Exception {
        mdao.insert(memo);
    }

    @Override
    public List<GroupPostVO> groupreport() throws Exception {
        return mdao.groupreport();
    }

    @Override
    public List<PostVO> postreport() throws Exception {
        return mdao.postreport();
    }

    @Override
    public GroupPostVO selectpost(int g_no) throws Exception {
        return mdao.selectpost(g_no);
    }

    @Override
    public void stopinsert(StopMemberVO stop) throws Exception {
         mdao.stopinsert(stop);
    }

    @Override
    public void delete(int g_no) throws Exception {
        mdao.delete(g_no);
    }


}
