package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_GroupDAO;
import com.codemaster.project_snsmaster.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements IF_GroupService {
    @Autowired
    IF_GroupDAO gdao;

    @Override
    public void insert(GroupPostVO gpvo) throws Exception {
        gdao.insert(gpvo);
    }

    @Override
    public void gjinsert(G_joinVO gjvo) throws Exception {
        gdao.gjinsert(gjvo);
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
    public void gjminsert(GroupJoinVO gjvo) throws Exception {
        gdao.gjminsert(gjvo);
    }

    @Override
    public void gmjinsert(G_memberVO gmvo) throws Exception {
        gdao.gmjinsert(gmvo);
    }

    @Override
    public List<GroupPostVO> gpList(String gno) throws Exception {
        return gdao.gpselectAll(gno);
    }

    @Override
    public List<G_joinVO> gjList(String gno) throws Exception {
        return gdao.gjselectAll(gno);
    }

    @Override
    public List<GroupJoinVO> gjoList(String gno) throws Exception {
        return gdao.gjoselectAll(gno);
    }

    @Override
    public void delete(int g_no) throws Exception {
        gdao.delete(g_no);
    }

    @Override
    public void gjdelete(int mo_no) throws Exception {
        gdao.gjdelete(mo_no);
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
    public G_joinVO gmjoinmod(int mo_no) throws Exception {
        return gdao.gmjoinmod(mo_no);
    }

    @Override
    public void modnoSaver(GroupPostVO gpvo) throws Exception {
        gdao.modupdate(gpvo);
    }

    @Override
    public void gmjoinmodSave(G_joinVO gjvo) throws Exception {
        gdao.gmjoinupdate(gjvo);
    }

    @Override
    public GroupPostVO gpselect(String g_no) throws Exception {
        return gdao.gposelect(g_no);
    }

    @Override
    public void gcinsert(GroupPostCommentVO gcvo) throws Exception {
        gdao.gcinsert(gcvo);
    }

    @Override
    public List<GroupPostCommentVO> cmList(String g_no) throws Exception {
        return gdao.cmselectAll(g_no);
    }

    @Override
    public void commentdel(int c_no) throws Exception {
        gdao.cmdelete(c_no);
    }

    @Override
    public void gjmdelete(GroupJoinVO gjvo) throws Exception {
        gdao.gjmdelete(gjvo);
    }

    @Override
    public void dgjmdelete(int wait_no) throws Exception {
        gdao.dgjmdelete(wait_no);
    }

   /* @Override
    public int countselect(int mo_no) throws Exception {
        return gdao.countselect(mo_no);
    }*/


}

