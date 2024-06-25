package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.MemberVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDAOImpl implements IF_LoginDAO{

    public static String mapperquery="com.codemaster.project_snsmaster.dao.IF_LoginDAO";

    @Autowired
    SqlSession sqlSession;

    @Override
    public MemberVO selectOne(String id) throws Exception {
        return sqlSession.selectOne(mapperquery+".selectOne",id);
    }

    @Override
    public List<MemberVO> idSearch(MemberVO vo) throws Exception {
        return sqlSession.selectList(mapperquery+".idSearch",vo);
    }

    @Override
    public MemberVO pwSearch(MemberVO vo) throws Exception {
        return sqlSession.selectOne(mapperquery+".selectOne",vo);
    }

    @Override
    public MemberVO ifStop(String id) throws Exception {
        return sqlSession.selectOne(mapperquery+".ifStop",id);
    }
}
