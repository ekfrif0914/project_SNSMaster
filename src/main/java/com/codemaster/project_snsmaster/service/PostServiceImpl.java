package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_PostDAO;
import com.codemaster.project_snsmaster.vo.PostCommentVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements IF_PostService{

    @Autowired
    IF_PostDAO postDAO;

    @Override
    public void insertPost(PostVO postVO) throws Exception {
        postDAO.insertPost(postVO);
        String[] filename = postVO.getFile_name();
        for (String s : filename) {
            if(s != null){
                postDAO.saveAttach(s);
            }
        }
    }

    @Override
    public List<PostVO> selectAll() throws Exception {
        return postDAO.selectAll();
    }

    @Override
    public List<PostVO> select(HashMap<String, String> params) throws Exception {
        return postDAO.select(params);
    }

    @Override
    public PostVO selectOne(String no) throws Exception {
        return postDAO.selectOne(no);
    }

    @Override
    public List<String> selectFileNames(String no) throws Exception {
        return postDAO.selectFileNames(no);
    }

    @Override
    public void insertComment(PostCommentVO postCommentVO) throws Exception {
        postDAO.insertComment(postCommentVO);
    }

    @Override
    public List<PostCommentVO> selectComment(String no) throws Exception {
        return postDAO.selectComment(no);
    }

    @Override
    public void deleteComment(String c_no) throws Exception {
        postDAO.deleteComment(c_no);
    }

    @Override
    public void deletePost(String no) throws Exception {
        postDAO.deletePost(no);
    }
}
