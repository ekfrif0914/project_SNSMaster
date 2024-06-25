package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.*;

import java.util.HashMap;
import java.util.List;

public interface IF_PostDAO {
    public void insertPost(PostVO postVO) throws Exception;
    public List<PostVO> selectAll() throws Exception;
    public List<PostVO> select(HashMap<String, String> params) throws Exception;
    public void saveAttach(String fileName) throws Exception;
    public void saveAttachbyNo(HashMap<String, String> param) throws Exception;
    public List<PostAttachVO> selectAllFileNames() throws Exception;
    public List<String> selectFileNames(String no) throws Exception;
    public void deleteAttach(String fname) throws Exception;
    public PostVO selectOne(String no) throws Exception;
    public void insertComment(PostCommentVO postCommentVO) throws Exception;
    public List<PostCommentVO> selectComment(String no) throws Exception;
    public List<HashMap<String, String>> selectAllComment() throws Exception;
    public void deleteComment(String c_no) throws Exception;
    public void deletePost(String no) throws Exception;
    public List<PostVO> selectMyPost(String userid) throws Exception;
    public List<PostVO> selectMyPostbyCategory(PostVO postVO) throws Exception;
    public void modPost(PostVO postVO) throws Exception;
    public List<GroupPostVO> selectMyGroupPost(String userid) throws Exception;
    public List<G_joinVO> selectMyGroupJoin(String userid) throws Exception;
    public List<Integer> selectMyLikeNo(String userid) throws Exception;
    public void insertLike(HashMap<String, String> param) throws Exception;
    public void deleteLike(HashMap<String, String> param) throws Exception;
    public int isLike(HashMap<String, String> param) throws Exception;
    public int likeCnt(String no) throws Exception;
    public void plusLike(String no) throws Exception;
    public void minusLike(String no) throws Exception;
    public int cntReport (HashMap<String, String> param) throws Exception;
    public void insertReport(HashMap<String, String> param) throws Exception;
    public void plusReport(String no) throws Exception;
}
