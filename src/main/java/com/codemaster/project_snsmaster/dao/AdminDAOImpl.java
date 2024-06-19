package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


}
