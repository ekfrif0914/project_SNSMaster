package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ManagerDAOImpl implements IF_ManagerDAO {
    private static String mapperQuery="com.codemaster.project_snsmaster.dao.IF_ManagerDAO";
    @Autowired
    SqlSession sqlSession;

    @Override
    public void insert(String memo) throws Exception {
        sqlSession.insert(mapperQuery + ".insert",memo);
    }

    @Override
    public List<GroupPostVO> groupreport() throws Exception {
        return sqlSession.selectList(mapperQuery+".groupPost");
    }

    @Override
    public List<PostVO> postreport() throws Exception {
        return sqlSession.selectList(mapperQuery+".postReport");
    }

    @Override
    public GroupPostVO selectgroupPost(int g_no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectgroupPost",g_no);
    }

    @Override
    public void stopinsert(StopMemberVO stop) throws Exception {
     sqlSession.insert(mapperQuery+".stopinsert",stop);
    }

    @Override
    public void delete(int g_no) throws Exception {
      sqlSession.delete(mapperQuery+".delete",g_no);
    }

    @Override
    public void delete2(int gNo) throws Exception {
        sqlSession.delete(mapperQuery+".delete2",gNo);
    }

    @Override
    public PostVO selectpost(int no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectpost",no);
    }

    @Override
    public List<PostVO> selectrandom(HashMap<String, String> params)throws Exception {
        return  sqlSession.selectList(mapperQuery+".selectrandom",params);
    }
}
