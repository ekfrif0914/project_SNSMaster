package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_AdminDAO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IF_AdminService{

@Autowired
private IF_AdminDAO adminDao;

    @Override
    public void insert(MemberVO memberVO) throws Exception{
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
}
