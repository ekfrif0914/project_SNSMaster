package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.PostAttachVO;
import com.codemaster.project_snsmaster.vo.PostCommentVO;
import com.codemaster.project_snsmaster.vo.PostVO;

import java.util.HashMap;
import java.util.List;

public interface IF_PostService {
    public void insertPost(PostVO postVO) throws Exception;
    public List<PostVO> selectAll() throws Exception;
    public List<PostVO> select(HashMap<String, String> params) throws Exception;
    public PostVO selectOne(String no) throws Exception;
    public List<String> selectFileNames(String no) throws Exception;
    public void insertComment(PostCommentVO postCommentVO) throws Exception;
    public List<PostCommentVO> selectComment(String no) throws Exception;
    public void deleteComment(String c_no) throws Exception;
    public void deletePost(String no) throws Exception;
    public List<PostVO> selectMyPost(String userid) throws Exception;
    public List<PostVO> selectMyPostbyCategory(PostVO postVO) throws Exception;
}
