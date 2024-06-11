package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_LoginDAO;
import com.codemaster.project_snsmaster.dao.IF_ManagerDAO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements IF_LoginService {

    @Autowired
    IF_LoginDAO logindao;


    @Override
    public MemberVO login(String id) throws Exception {

  //      MemberVO mvo = logindao.selectOne(id);
//        if(mvo!=null) {
//            if (mvo.isState()) {
        return logindao.selectOne(id);
//            }else{
//                return null;
//            }
//        }else{
//         return null;
//        }
//    }
    }

    @Override
    public List<MemberVO> idSearch(MemberVO memberVO) throws Exception {
       return logindao.idSearch(memberVO);
    }
}
