package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.*;
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
        sqlSession.insert(mapperQuery+".insertPost", postVO);
    }

    @Override
    public List<PostVO> selectAll() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectAll");
    }

    @Override
    public List<PostVO> selectLimit() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectLimit");
    }

    @Override
    public List<PostVO> selectLimit( HashMap<String, String> params) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectLimitUnder", params);
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
    public void saveAttachbyNo(HashMap<String, String> param) throws Exception {
        sqlSession.insert(mapperQuery+".insertAttachbyNo", param);
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
    public List<HashMap<String, String>> selectAllComment() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectAllComment");
    }

    @Override
    public void deleteComment(String c_no) throws Exception {
        sqlSession.delete(mapperQuery+".deleteComment", c_no);
    }

    @Override
    public void deletePost(String no) throws Exception {
        sqlSession.delete(mapperQuery+".deletePost", no);
    }

    @Override
    public List<PostAttachVO> selectAllFileNames() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectAllFileNames");
    }

    @Override
    public List<PostAttachVO> selectLimitFileNames() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectLimitFileNames");
    }

    @Override
    public List<PostAttachVO> selectLimitFileNames(String lastNo) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectLimitFileNamesUnder", lastNo);
    }

    @Override
    public List<PostVO> selectMyPost(String userid) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectMyPost", userid);
    }

    @Override
    public List<PostVO> selectMyPostbyCategory(PostVO postVO) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectMyPostbyCategory", postVO);
    }

    @Override
    public void modPost(PostVO postVO) throws Exception {
        sqlSession.update(mapperQuery+".modPost", postVO);
    }

    @Override
    public void deleteAttach(String fname) throws Exception {
        sqlSession.delete(mapperQuery+".deleteAttach", fname);
    }

    @Override
    public List<GroupPostVO> selectMyGroupPost(String userid) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectMyGroupPost", userid);
    }

    @Override
    public List<G_joinVO> selectMyGroupJoin(String userid) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectMyGroupJoin", userid);
    }

    @Override
    public List<Integer> selectMyLikeNo(String userid) throws Exception {
        return sqlSession.selectList(mapperQuery+".selectMyLikeNo", userid);
    }

    @Override
    public void insertLike(HashMap<String, String> param) throws Exception {
        sqlSession.insert(mapperQuery+".insertLike", param);
    }

    @Override
    public void deleteLike(HashMap<String, String> param) throws Exception {
        sqlSession.delete(mapperQuery+".deleteLike", param);
    }

    @Override
    public int isLike(HashMap<String, String> param) throws Exception {
        return sqlSession.selectOne(mapperQuery+".isLike", param);
    }

    @Override
    public int likeCnt(String no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".likeCnt", no);
    }

    @Override
    public void plusLike(String no) throws Exception {
        sqlSession.update(mapperQuery+".plusLike", no);
    }

    @Override
    public void minusLike(String no) throws Exception {
        sqlSession.update(mapperQuery+".minusLike", no);
    }

    @Override
    public int cntReport(HashMap<String, String> param) throws Exception {
        return sqlSession.selectOne(mapperQuery+".cntReport", param);
    }

    @Override
    public void insertReport(HashMap<String, String> param) throws Exception {
        sqlSession.insert(mapperQuery+".insertReport", param);
    }

    @Override
    public void plusReport(String no) throws Exception {
        sqlSession.update(mapperQuery+".plusReport", no);
    }

    @Override
    public List<String> selectAllNotice() throws Exception {
        return sqlSession.selectList(mapperQuery+".selectAllNotice");
    }

    @Override
    public String selectProfileImg(String id) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectProfileImg", id);
    }

}
