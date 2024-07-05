package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_PostDAO;
import com.codemaster.project_snsmaster.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostServiceImpl implements IF_PostService {

    @Autowired
    IF_PostDAO postDAO;

    @Override
    public void insertPost(PostVO postVO) throws Exception {
        postDAO.insertPost(postVO);
        String[] filename = postVO.getFile_name();
        for (String s : filename) {
            if (s != null) {
                postDAO.saveAttach(s);
            }
        }
    }

    @Override
    public List<HashMap<String, Object>> selectLimit(String userid) throws Exception {
        List<HashMap<String, Object>> postMaps = new ArrayList<HashMap<String, Object>>();
        List<PostVO> postList = postDAO.selectLimit();
        List<PostAttachVO> postAttachVOList = postDAO.selectLimitFileNames();
        List<Integer> likeNos = postDAO.selectMyLikeNo(userid);
        for (PostVO postVO : postList) {
            HashMap<String, Object> postMap = new HashMap<>();
            String[] filenames = new String[8];
            int i = 0;
            for (PostAttachVO postAttachVO : postAttachVOList) {
                if (postVO.getNo() == postAttachVO.getNo()) {
                    filenames[i++] = postAttachVO.getFile_name();
                }
            }
            postVO.setFile_name(filenames);
            for (Integer likeNo : likeNos) {
                if (likeNo != null) {
                    if (postVO.getNo() == likeNo) {
                        postVO.setLike(true);
                    }
                }
            }
            String profileImgName = postDAO.selectProfileImg(postVO.getId());
            postMap.put("profileImgName", profileImgName);
            postMap.put("post", postVO);
            postMaps.add(postMap);
        }
        return postMaps;
    }

    @Override
    public List<HashMap<String, Object>> selectLimit(String userid, HashMap<String, String> params) throws Exception {
        List<HashMap<String, Object>> postMaps = new ArrayList<>();
        List<PostVO> postList = postDAO.selectLimit(params);
        List<PostAttachVO> postAttachVOList = postDAO.selectAllFileNames();
        List<Integer> likeNos = postDAO.selectMyLikeNo(userid);
        for (PostVO postVO : postList) {
            HashMap<String, Object> postMap = new HashMap<>();
            String[] filenames = new String[8];
            int i = 0;
            for (PostAttachVO postAttachVO : postAttachVOList) {
                if (postVO.getNo() == postAttachVO.getNo()) {
                    filenames[i++] = postAttachVO.getFile_name();
                }
            }
            postVO.setFile_name(filenames);
            for (Integer likeNo : likeNos) {
                if (likeNo != null) {
                    if (postVO.getNo() == likeNo) {
                        postVO.setLike(true);
                    }
                }
            }
            String profileImgName = postDAO.selectProfileImg(postVO.getId());
            postMap.put("profileImgName", profileImgName);
            postMap.put("post", postVO);
            postMaps.add(postMap);
        }
        return postMaps;
    }

    @Override
    public List<HashMap<String, Object>> select(HashMap<String, String> params, String userid) throws Exception {
        List<HashMap<String, Object>> postMaps = new ArrayList<HashMap<String, Object>>();
        List<PostVO> postList = postDAO.select(params);
        List<PostAttachVO> postAttachVOList = postDAO.selectAllFileNames();
        List<Integer> likeNos = postDAO.selectMyLikeNo(userid);
        for (PostVO postVO : postList) {
            HashMap<String, Object> postMap = new HashMap<>();
            String[] filenames = new String[8];
            int i = 0;
            for (PostAttachVO postAttachVO : postAttachVOList) {
                if (postVO.getNo() == postAttachVO.getNo()) {
                    filenames[i++] = postAttachVO.getFile_name();
                }
            }
            postVO.setFile_name(filenames);
            for (Integer likeNo : likeNos) {
                if (likeNo != null) {
                    if (postVO.getNo() == likeNo) {
                        postVO.setLike(true);
                    }
                }
            }
            String profileImgName = postDAO.selectProfileImg(postVO.getId());
            postMap.put("profileImgName", profileImgName);
            postMap.put("post", postVO);
            postMaps.add(postMap);
        }
        return postMaps;
    }

    @Override
    public PostVO selectOne(String no) throws Exception {
        PostVO postVO = postDAO.selectOne(no);
        int size = selectFileNames(no).size() + 1;
        if (size > 8) {
            size = 8;
        }
        postVO.setFile_name(postDAO.selectFileNames(no).toArray(new String[size]));
        postVO.setContent(postVO.getContent().replace("<br>", "\r\n"));
        return postVO;
    }

    @Override
    public HashMap<String, Object> selectOneMap(String no, String userid) throws Exception {
        HashMap<String, Object> postMap = new HashMap<>();
        PostVO postVO = postDAO.selectOne(no);
        List<Integer> likeNos = postDAO.selectMyLikeNo(userid);
        int size = selectFileNames(no).size() + 1;
        if (size > 8) {
            size = 8;
        }
        postVO.setFile_name(postDAO.selectFileNames(no).toArray(new String[size]));
        for (Integer likeNo : likeNos) {
            if (likeNo != null) {
                if (postVO.getNo() == likeNo) {
                    postVO.setLike(true);
                }
            }
        }
        String profileImgName = postDAO.selectProfileImg(postVO.getId());
        postMap.put("profileImgName", profileImgName);
        postMap.put("post", postVO);
        return postMap;
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
    public List<HashMap<String, Object>> selectComment(String no) throws Exception {
        List<HashMap<String, Object>> commentMaps = new ArrayList<>();
        List<PostCommentVO> commentList = postDAO.selectComment(no);
        for (PostCommentVO comment : commentList) {
            HashMap<String, Object> commentMap = new HashMap<>();
            String profileImgName = postDAO.selectProfileImg(comment.getId());
            commentMap.put("profileImgName", profileImgName);
            commentMap.put("comment", comment);
            commentMaps.add(commentMap);
        }
        return commentMaps;
    }

    @Override
    public List<HashMap<String, String>> selectAllComment() throws Exception {
        return postDAO.selectAllComment();
    }

    @Override
    public void deleteComment(String c_no) throws Exception {
        postDAO.deleteComment(c_no);
    }

    @Override
    public void deletePost(String no) throws Exception {
        postDAO.deletePost(no);
    }

    @Override
    public List<PostVO> selectMyPost(String userid) throws Exception {
        return postDAO.selectMyPost(userid);
    }

    @Override
    public List<PostVO> selectMyPostbyCategory(PostVO postVO) throws Exception {
        return postDAO.selectMyPostbyCategory(postVO);
    }

    @Override
    public void modPost(PostVO postVO, String[] delfname) throws Exception {
        postDAO.modPost(postVO);
        String[] filename = postVO.getFile_name();
        String no = postVO.getNo() + "";
        if (filename != null) {
            for (String s : filename) {
                if (s != null) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("no", no);
                    param.put("fileName", s);
                    postDAO.saveAttachbyNo(param);
                }
            }
        }
        if (delfname != null) {
            for (String s : delfname) {
                postDAO.deleteAttach(s);
            }
        }
    }

    @Override
    public List<GroupPostVO> selectMyGroupPost(String userid) throws Exception {
        return postDAO.selectMyGroupPost(userid);
    }

    @Override
    public List<G_joinVO> selectMyGroupJoin(String userid) throws Exception {
        return postDAO.selectMyGroupJoin(userid);
    }

    @Override
    public List<Integer> selectMyLikeNo(String userid) throws Exception {
        return postDAO.selectMyLikeNo(userid);
    }

    @Override
    public boolean changeLike(String no, String id) throws Exception {
        HashMap<String, String> param = new HashMap<>();
        param.put("no", no);
        param.put("id", id);
        int isLike = postDAO.isLike(param);
        if (isLike > 0) {
            postDAO.deleteLike(param);
            postDAO.minusLike(no);
            return false;
        } else {
            postDAO.insertLike(param);
            postDAO.plusLike(no);
            return true;
        }
    }

    @Override
    public int likeCnt(String no) throws Exception {
        return postDAO.likeCnt(no);
    }

    @Override
    public boolean report(String no, String id) throws Exception {
        HashMap<String, String> param = new HashMap<>();
        param.put("no", no);
        param.put("id", id);
        int isReport = postDAO.cntReport(param);
        if (isReport > 0) {
            return true;
        } else {
            postDAO.insertReport(param);
            postDAO.plusReport(no);
            return false;
        }
    }

    @Override
    public List<String> selectAllNotice() throws Exception {
        return postDAO.selectAllNotice();
    }

}
