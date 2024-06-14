package com.codemaster.project_snsmaster.controller;


import com.codemaster.project_snsmaster.service.IF_LoginService;
import com.codemaster.project_snsmaster.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    IF_LoginService loginService;

    @GetMapping("loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("findMember")
    public String findMember(Model model) {

        return "findMember";

    }


    @PostMapping("idSearch")
    public String idSearch(@ModelAttribute MemberVO memberVO, Model model) throws Exception {
        System.out.println(memberVO.toString());
        List<MemberVO> idmvo = loginService.idSearch(memberVO);
        if (idmvo.size() != 0) {
            int cnt=0;
            for (MemberVO idmvo1 : idmvo) {
                if (idmvo1.getEmail().equals(memberVO.getEmail())) {
                    System.out.println(idmvo1.toString());
                    System.out.println("일치함");
                    System.out.println(idmvo1.getId());

                    cnt=1;

                }
            }

            if(cnt==0){
                System.out.println("이메일이 일치하지 않음");

            }
        } else {
            System.out.println("해당하는 이름이 없음");

        }

        return "redirect:/findMember";
    }

    @PostMapping("login")
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pass, HttpSession session)
            throws Exception {
        System.out.println(id);
        System.out.println(pass);
        MemberVO mvo = loginService.login(id);

        if (mvo != null) {
            if (mvo.getPw().equals(pass)) {
                // 로그인 성공
                // 세션처리
                // 이때 서버는 쿠키를 만들고 세션영역을 쿠키로 구분이 가능하다. 클라이언트는 접속시 쿠키값을 서버에게
                // 전송하고 서버에서는 쿠키값을 참고하여 세션에서 등록된 변수 값을 가져온다.
                if (session.getAttribute("userid") != null) {// 만약 쓰레기 값이 있다면
                    session.removeAttribute("userid");// 지워라
                    session.removeAttribute("username");
                    session.removeAttribute("userregion");
                    System.out.println("null이 아님");
                }
                session.setAttribute("userid", mvo.getId());
                session.setAttribute("username", mvo.getName());
                session.setAttribute("userregion", mvo.getRegion());// 디비에서 저장된 값으로 셋팅할 수도 있다.
            } else {
                // 비밀번호 틀림
                System.out.println("비밀번호 틀림");
                return "loginForm";
            }

        } else {
            // 아이디 없음
            System.out.println("아이디가 존재하지 않습니다");
            System.out.println("활동정지 입니다");
            return "loginForm";
        }
        return "redirect:snsMaster";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:snsMaster";

    }
}
