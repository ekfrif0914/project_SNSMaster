package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_PostDAO;
import com.codemaster.project_snsmaster.vo.*;
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
        List<PostVO> postList = postDAO.selectAll();
        List<PostAttachVO> postAttachVOList = postDAO.selectAllFileNames();
        for(PostVO postVO : postList){
            String[] filenames = new String[8];
            int i = 0;
            for(PostAttachVO postAttachVO : postAttachVOList){
                if(postVO.getNo() == postAttachVO.getNo()){
                    filenames[i++] = postAttachVO.getFile_name();
                }
            }
            postVO.setFile_name(filenames);
        }
        return postList;
    }

    @Override
    public List<PostVO> select(HashMap<String, String> params) throws Exception {
        List<PostVO> postList = postDAO.select(params);
        List<PostAttachVO> postAttachVOList = postDAO.selectAllFileNames();
        for(PostVO postVO : postList){
            String[] filenames = new String[8];
            int i = 0;
            for(PostAttachVO postAttachVO : postAttachVOList){
                if(postVO.getNo() == postAttachVO.getNo()){
                    filenames[i++] = postAttachVO.getFile_name();
                }
            }
            postVO.setFile_name(filenames);
        }
        return postList;
    }

    @Override
    public PostVO selectOne(String no) throws Exception {
        PostVO postVO = postDAO.selectOne(no);
        int size = selectFileNames(no).size() + 1;
        if(size > 8){
            size = 8;
        }
        postVO.setFile_name(postDAO.selectFileNames(no).toArray(new String[size]));
        return postVO;
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
        String no = postVO.getNo()+"";
        if(filename != null){
            for (String s : filename) {
                if(s != null){
                    HashMap<String, String> param = new HashMap<>();
                    param.put("no", no);
                    param.put("fileName", s);
                    postDAO.saveAttachbyNo(param);
                }
            }
        }
        if(delfname != null){
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


}
