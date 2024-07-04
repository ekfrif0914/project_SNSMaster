package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class GroupDAOImpl implements IF_GroupDAO {

    private static final String mapperQuery = "com.codemaster.project_snsmaster.dao.IF_GroupDAO";
    @Autowired
    SqlSession sqlSession;

    @Override
    public void insert(GroupPostVO gpvo) throws Exception {
        sqlSession.insert(mapperQuery + ".insert", gpvo);
    }

    @Override
    public void gjinsert(G_joinVO gjvo) throws Exception {
        sqlSession.insert(mapperQuery + ".gjinsert", gjvo);
    }

    @Override
    public void mginsert(MemberGroupVO mgvo) throws Exception {
        sqlSession.insert(mapperQuery + ".mginsert", mgvo);
    }

    @Override
    public List<MemberGroupVO> selectAll() throws Exception {
        return sqlSession.selectList(mapperQuery + ".select");
    }

    @Override
    public int joinsert(GroupJoinVO gjvo) throws Exception {
        return sqlSession.insert(mapperQuery + ".joinsert", gjvo);
    }

    @Override
    public int gjminsert(GroupJoinVO gjvo) throws Exception {
        return sqlSession.insert(mapperQuery+".gjminsert",gjvo);
    }


    @Override
    public List<GroupPostVO> gpselectAll(String gno) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gpselect", gno);
    }

    @Override
    public List<G_joinVO> gjselectAll(String gno) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gjselect", gno);
    }

    @Override
    public List<GroupJoinVO> gjoselectAll(String gno) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gjoselect", gno);
    }

    @Override
    public void delete(int g_no) throws Exception {
        sqlSession.delete(mapperQuery + ".delete", g_no);
    }

    @Override
    public void gjdelete(int mo_no) throws Exception {
        sqlSession.delete(mapperQuery + ".gjdelete", mo_no);
    }

    @Override
    public void mdelete(int gno) throws Exception {
        sqlSession.delete(mapperQuery + ".mdelete", gno);
    }

    @Override
    public GroupPostVO modno(int g_no) throws Exception {

        return sqlSession.selectOne(mapperQuery + ".selectone", g_no);
    }

    @Override
    public G_joinVO gmjoinmod(int mo_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".gmjoinmod", mo_no);
    }

    @Override
    public void modupdate(GroupPostVO gpvo) throws Exception {
        sqlSession.update(mapperQuery + ".modupdate", gpvo);
    }

    @Override
    public void gmjoinupdate(G_joinVO gjvo) throws Exception {
        sqlSession.update(mapperQuery+".gmjoinupdate", gjvo);
    }

    @Override
    public GroupPostVO gposelect(String g_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".gposelect", g_no);
    }

    @Override
    public void gcinsert(GroupPostCommentVO gcvo) throws Exception {
        sqlSession.insert(mapperQuery + ".gcinsert", gcvo);
    }

    @Override
    public List<GroupPostCommentVO> cmselectAll(String g_no) throws Exception {
        return sqlSession.selectList(mapperQuery + ".cmselectall", g_no);
    }

    @Override
    public void cmdelete(int c_no) throws Exception {
        sqlSession.delete(mapperQuery + ".cmdelete", c_no);
    }

    @Override
    public void gjmdelete(GroupJoinVO gjvo) throws Exception {
        sqlSession.delete(mapperQuery+".gjmdelete", gjvo);
    }

    @Override
    public void dgjmdelete(int wait_no) throws Exception {
        sqlSession.delete(mapperQuery+".dgjmdelete", wait_no);
    }

    @Override
    public List<MemberGroupVO> searchselectAll(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery + ".searchselectall", param);
    }

    @Override
    public List<G_joinVO> gjsearchselectAll(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gjsearchselectall", param);
    }

    @Override
    public List<GroupPostVO> gpsearchselectAll(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gpsearchselectall", param);
    }

    @Override
    public List<GroupJoinVO> gjoinselect(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gjoinselectall", param);
    }

    @Override
    public List<MemberGroupVO> mgselect(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery + ".mjoinselectall", param);
    }

    @Override
    public Object greportinsert(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery+".greportinsert", param);
    }

    @Override
    public Object likeinsert(HashMap<String, String> param) throws Exception {
        return sqlSession.selectList(mapperQuery+".likeinsert", param);
    }

    @Override
    public Object likedel(HashMap<String, String> likedel) throws Exception {
        return sqlSession.delete(mapperQuery+".likedel", likedel);
    }

    @Override
    public void greportselect(int g_no) throws Exception {
        sqlSession.update(mapperQuery + ".greportupdate", g_no);
    }

    @Override
    public void likepostselect(int g_no) throws Exception {
        sqlSession.update(mapperQuery+".likeupdate", g_no);
    }

    @Override
    public void likepostdel(int g_no) throws Exception {
        sqlSession.update(mapperQuery+".likepostupdate", g_no);
    }

    @Override
    public int reportsaveselect(HashMap<Object, Object> report) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".reportsaveselect", report);
    }

    @Override
    public int likeselect(HashMap<Object, Object> likemap) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".likeselect", likemap);
    }

    @Override
    public int gjinselect(HashMap<Object, Object> gjoinmap) throws Exception {
        return sqlSession.selectOne(mapperQuery+".gjinselect", gjoinmap);
    }

    @Override
    public int gjoinwaitselect(HashMap<Object, Object> gjoinwait) throws Exception {
        return sqlSession.selectOne(mapperQuery+".gjoinwaitselect", gjoinwait);
    }

    @Override
    public void monoup(int mo_no) throws Exception {
        sqlSession.update(mapperQuery+".monoup", mo_no);
    }

    @Override
    public int joinmb(HashMap<Object, Object> gmjoin) throws Exception {
        return sqlSession.selectOne(mapperQuery+".joinmb",gmjoin);
    }

    @Override
    public void joininsert(HashMap<Object, Object> joininsert) throws Exception {
        sqlSession.insert(mapperQuery+".joininsert",joininsert);
    }

    @Override
    public List<GroupJoinVO> gjoinpopselect(String gno) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gjoinpopselect", gno);
    }

    @Override
    public List<G_memberVO> gmemberpopList(int mo_no) throws Exception {
        return sqlSession.selectList(mapperQuery + ".gmemberpoplist", mo_no);
    }

  /*  @Override
    public int reportsaveselect(int g_no) throws Exception {
        return sqlSession.selectOne(mapperQuery + ".reportsaveselect", g_no);
    }*/


}
