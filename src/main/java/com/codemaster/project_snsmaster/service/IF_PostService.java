package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.vo.*;

import java.util.HashMap;
import java.util.List;

public interface IF_PostService {
    public void insertPost(PostVO postVO) throws Exception;

    public List<HashMap<String, Object>> selectLimit(String userid) throws Exception;

    public List<HashMap<String, Object>> selectLimit(String userid, HashMap<String, String> params) throws Exception;

    public List<HashMap<String, Object>> select(HashMap<String, String> params, String userid) throws Exception;

    public PostVO selectOne(String no) throws Exception;

    public HashMap<String, Object> selectOneMap(String no, String userid) throws Exception;

    public List<String> selectFileNames(String no) throws Exception;

    public void insertComment(PostCommentVO postCommentVO) throws Exception;

    public List<HashMap<String, Object>> selectComment(String no) throws Exception;

    public List<HashMap<String, String>> selectAllComment() throws Exception;

    public void deleteComment(String c_no) throws Exception;

    public void deletePost(String no) throws Exception;

    public List<PostVO> selectMyPost(String userid) throws Exception;

    public List<PostVO> selectMyPostbyCategory(PostVO postVO) throws Exception;

    public void modPost(PostVO postVO, String[] delfname) throws Exception;

    public List<GroupPostVO> selectMyGroupPost(String userid) throws Exception;

    public List<G_joinVO> selectMyGroupJoin(String userid) throws Exception;

    public List<Integer> selectMyLikeNo(String userid) throws Exception;

    public boolean changeLike(String no, String id) throws Exception;

    public int likeCnt(String no) throws Exception;

    public boolean report(String no, String id) throws Exception;

    public List<String> selectAllNotice() throws Exception;
}
