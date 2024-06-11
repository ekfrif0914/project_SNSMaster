package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_GroupDAO;
import com.codemaster.project_snsmaster.vo.GroupJoinVO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.MemberGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IF_GroupService{
    @Autowired
    IF_GroupDAO gdao;

    @Override
    public void insert(GroupPostVO gpvo) throws Exception {
        gdao.insert(gpvo);
    }

    @Override
    public void mginsert(MemberGroupVO mgvo) throws Exception {
        gdao.mginsert(mgvo);
    }

    @Override
    public List<MemberGroupVO> gList() throws Exception {

        return gdao.selectAll();
    }

    @Override
    public void joinsert(GroupJoinVO gjvo) throws Exception {
        gdao.joinsert(gjvo);
    }

    @Override
    public List<GroupPostVO> gpList(String gno) throws Exception {
        return gdao.gpselectAll(gno);
    }

    @Override
    public void delete(int g_no) throws Exception {
        gdao.delete(g_no);
    }

    @Override
    public void mdelete(int gno) throws Exception {
        gdao.mdelete(gno);
    }

    @Override
    public GroupPostVO modno(int g_no) throws Exception {

        return gdao.modno(g_no);
    }

    @Override
    public void modnoSaver(GroupPostVO gpvo) throws Exception {
        gdao.modupdate(gpvo);
    }


}

