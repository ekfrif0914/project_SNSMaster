package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.PostCommentVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PostDAOImpl implements IF_PostDAO{
    private static String mapperQuery = "com.codemaster.project_snsmaster.dao.IF_PostDAO";
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertPost(PostVO postVO) throws Exception{
//        System.out.println(postVO.toString());
        sqlSession.insert(mapperQuery+".insertPost", postVO);
    }

    @Override
    public List<PostVO> selectAll() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectAll");
    }

    @Override
    public List<PostVO> select(HashMap<String, String> params) throws Exception {
        return sqlSession.selectList(mapperQuery+".select", params);
    }

    @Override
    public void saveAttach(String fileName) throws Exception {
        sqlSession.insert(mapperQuery+".insertAttach", fileName);
    }

    @Override
    public PostVO selectOne(String no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectOne", no);
    }

    @Override
    public List<String> selectFileNames(String no) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectFileNames", no);
    }

    @Override
    public void insertComment(PostCommentVO postCommentVO) throws Exception {
        sqlSession.insert(mapperQuery+".insertComment", postCommentVO);
    }

    @Override
    public List<PostCommentVO> selectComment(String no) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectComment", no);
    }

    @Override
    public void deleteComment(String c_no) throws Exception {
        sqlSession.delete(mapperQuery+".deleteComment", c_no);
    }

    @Override
    public void deletePost(String no) throws Exception {
        sqlSession.delete(mapperQuery+".deletePost", no);
    }

}
