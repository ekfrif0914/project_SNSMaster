package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.FAQVO;
import com.codemaster.project_snsmaster.vo.FollowVO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import com.codemaster.project_snsmaster.vo.PageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminDAOImpl implements IF_AdminDAO {
    private static String mapperquery = "com.codemaster.project_snsmaster.dao.IF_AdminDAO";

    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insert(MemberVO memberVO) throws Exception {
        sqlSession.insert(mapperquery + ".insert", memberVO);
    }

    @Override
    public int overlappedID(String id) throws Exception {
        return sqlSession.selectOne(mapperquery + ".overlappedID", id);
    }

    @Override
    public int overlappedEmail(String email) throws Exception {
        return sqlSession.selectOne(mapperquery + ".overlappedEmail", email);
    }

    @Override
    public void updateProfile(Map<String, Object> param) throws Exception {
        sqlSession.update(mapperquery + ".updateProfile", param);
    }

    @Override
    public MemberVO getMember(String id) throws Exception {
        return sqlSession.selectOne(mapperquery + ".memberInfo", id);
    }

    @Override
    public void updateSave(MemberVO memberVO) throws Exception {
        sqlSession.update(mapperquery + ".updateSave", memberVO);
    }

    @Override
    public void stop(String id) throws Exception {
        sqlSession.insert(mapperquery + ".stop", id);
    }

    @Override
    public void deletePostArray(String postArray) throws Exception {
        sqlSession.delete(mapperquery + ".deletePostArray", postArray);
    }

    @Override
    public void deletegPostArray(String gpostArray) throws Exception {
        sqlSession.delete(mapperquery + ".deletegPostArray", gpostArray);
    }

    @Override
    public void deletegJoinArray(String gjoinArray) throws Exception {
        sqlSession.delete(mapperquery + ".deletegJoinArray", gjoinArray);
    }

    @Override
    public void changeDefaultimg(String id) throws Exception {
        sqlSession.update(mapperquery + ".changeDefaultimg", id);
    }

    @Override
    public void faqinsert(FAQVO faqvo) throws Exception {
        sqlSession.insert(mapperquery+".faqinsert",faqvo);
    }

    @Override
    public List<FAQVO> faqselect(PageVO pvo) throws Exception {
        return sqlSession.selectList(mapperquery+".faqselect",pvo);
    }

    @Override
    public FAQVO selectOne(String f_no) throws Exception {
        return sqlSession.selectOne(mapperquery+".selectOne",f_no);
    }

    @Override
    public void viewUp(String f_no) throws Exception {
        sqlSession.update(mapperquery+".viewUp",f_no);
    }

    @Override
    public int getTotalcount() throws Exception {
        return sqlSession.selectOne(mapperquery + ".getTotalCount");
    }

    @Override
    public int getSearchTotalCount(HashMap<String, String> param) throws Exception {
        return sqlSession.selectOne(mapperquery + ".getSearchTotalCount",param);
    }

    @Override
    public List<FAQVO> faqSearchselect(PageVO pvo) throws Exception {
        return sqlSession.selectList(mapperquery+".faqsearchselect",pvo);
    }

    @Override
    public int cntFollowing(FollowVO fvo) throws Exception {
        return sqlSession.selectOne(mapperquery+".cntFollowing",fvo);
    }

    @Override
    public void insertFollowing(FollowVO fvo) throws Exception {
       sqlSession.insert(mapperquery+".insertFollowing",fvo);
    }

    @Override
    public List<String> selectMyFollowinglist(String userid) throws Exception {
        return sqlSession.selectList(mapperquery+".selectMyFollowinglist",userid);
    }

    @Override
    public void deleteFollowing(FollowVO fvo) throws Exception {
        sqlSession.delete(mapperquery+".deleteFollowing",fvo);
    }

    @Override
    public int myfollowCount(String id) throws Exception {
        return sqlSession.selectOne(mapperquery+".myfollowCount",id);
    }

    @Override
    public int myfollowingCount(String id) throws Exception {
        return sqlSession.selectOne(mapperquery+".myfollowingCount",id);
    }

    @Override
    public List<String> myfollowList(String id) throws Exception {
        return sqlSession.selectList(mapperquery+".myfollowList",id);
    }

}
