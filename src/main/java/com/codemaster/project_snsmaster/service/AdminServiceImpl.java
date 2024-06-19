package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_AdminDAO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements IF_AdminService {

    @Autowired
    private IF_AdminDAO adminDao;

    @Override
    public void insert(MemberVO memberVO) throws Exception {
        adminDao.insert(memberVO);
    }

    @Override
    public int overlappedID(String id) throws Exception {
        int result = adminDao.overlappedID(id);
        return result;
    }

    @Override
    public int overlappedEmail(String email) throws Exception {
        int result = adminDao.overlappedEmail(email);
        return result;
    }

    @Override
    public void updateProfile(String id, String[] file_name) throws Exception {
        String filename = file_name[0];
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("filename", filename);


        adminDao.updateProfile(param);


    }

    @Override
    public MemberVO getMember(String id) throws Exception {
        return adminDao.getMember(id);
    }

    @Override
    public void updateSave(MemberVO memberVO) throws Exception {
        adminDao.updateSave(memberVO);
    }

    @Override
    public void stop(String id) throws Exception {
        adminDao.stop(id);
    }

    @Override
    public void deletePostArray(String postArray) throws Exception {
        adminDao.deletePostArray(postArray);
    }

    @Override
    public void deletegPostArray(String gpostArray) throws Exception {
        adminDao.deletegPostArray(gpostArray);
    }

    @Override
    public void deletegJoinArray(String gjoinArray) throws Exception {
        adminDao.deletegJoinArray(gjoinArray);
    }

    @Override
    public void changeDefaultimg(String id) throws Exception {
        adminDao.changeDefaultimg(id);
    }
}
