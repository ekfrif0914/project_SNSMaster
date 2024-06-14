package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_LoginDAO;
import com.codemaster.project_snsmaster.dao.IF_ManagerDAO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements IF_LoginService {

    @Autowired
    IF_LoginDAO logindao;

    @Autowired
    IF_EmailService emailservice;

    @Override
    public MemberVO login(String id) throws Exception {

        MemberVO mvo = logindao.selectOne(id);
        if (mvo != null) {
            MemberVO result = logindao.ifStop(id);//활동정지 인지
            if (result !=null) {
                return logindao.ifStop(id);//활동정지
            } else {
                return logindao.selectOne(id);//사용가능
            }
        } else {
            return null;//아이디 없음
        }

    }

    @Override
    public List<MemberVO> idSearch(MemberVO memberVO) throws Exception {
        return logindao.idSearch(memberVO);
    }

    @Override
    public int pwSearch(MemberVO memberVO) throws Exception {

        MemberVO result = logindao.pwSearch(memberVO);
        if (result != null) {
            if (result.getEmail().equals(memberVO.getEmail())) {
                emailservice.sendPwMessage(result.getEmail(), result.getPw());
                return 1;
            } else {
                //이메일 틀림
                return 2;
            }
        } else {
            //찾으려는 아이디가 없음
            return 3;
        }

    }
}
