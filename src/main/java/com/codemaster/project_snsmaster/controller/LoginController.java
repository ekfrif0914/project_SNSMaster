package com.codemaster.project_snsmaster.controller;


import com.codemaster.project_snsmaster.service.IF_LoginService;
import com.codemaster.project_snsmaster.vo.MemberVO;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    IF_LoginService loginService;
    private HikariDataSource dataSource;

    @GetMapping("loginForm")//로그인 창
    public String loginForm(HttpServletRequest request, Model model) {
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "loginForm";
    }

    @GetMapping("findMember")//아이디,비밀번호 찾기
    public String findMember(Model model) {
        return "findMember";
    }

    @PostMapping("pwSearch")//비밀번호 찾기
    public String pwSearch(@ModelAttribute MemberVO member, Model model) throws Exception {
        int result=loginService.pwSearch(member);
        if(result==1){
            model.addAttribute("pwMsg", "해당 메일로 비밀번호가 전송되었습니다");
        }else if(result==2){
            model.addAttribute("pwMsg", "이메일이 일치하지 않습니다");
        }else{
            model.addAttribute("pwMsg", "아이디가 존재하지 않습니다");
        }
        return "findMember";
    }

    @GetMapping("pwSearch")
    public String pwSearch2(Model model) throws Exception {
        return "findMember";
    }

    @GetMapping("idSearch")
    public String idSearch2() {
        return "findMember";
    }

    @PostMapping("idSearch")//아이디 찾기
    public String idSearch(@ModelAttribute MemberVO memberVO, Model model) throws Exception {
        List<MemberVO> idmvo = loginService.idSearch(memberVO);
        if (idmvo.size() != 0) {
            int cnt=0;
            for (MemberVO idmvo1 : idmvo) {
                if (idmvo1.getEmail().equals(memberVO.getEmail())) {
                    model.addAttribute("id",idmvo1.getId());
                    cnt=1;
                }
            }
            if(cnt==0){
                model.addAttribute("msg","이메일이 일치하지 않습니다");
            }
        } else {
            model.addAttribute("msg","해당하는 이름이 없습니다");
        }
        return "findMember";
    }

    @PostMapping("login")//로그인 하기
    public String login(@RequestParam("id") String id, @RequestParam("pw") String pass, Model model, HttpSession session,
                        @RequestParam String referer) throws Exception {
        String prev_url = (String)session.getAttribute("prev_url");
        if(prev_url!=null){//인터셉터로 가로채서 값이 있는 경우
            session.removeAttribute("prev_url");
        }else{//값이 없는 경우
            prev_url=referer;
        }
        MemberVO mvo = loginService.login(id);

        if (mvo != null) {
            if(mvo.getS_text()==null) {
                if (mvo.getPw().equals(pass)) {
                    // 로그인 성공
                    // 세션처리
                    // 이때 서버는 쿠키를 만들고 세션영역을 쿠키로 구분이 가능하다. 클라이언트는 접속시 쿠키값을 서버에게
                    // 전송하고 서버에서는 쿠키값을 참고하여 세션에서 등록된 변수 값을 가져온다.
                    if (session.getAttribute("userid") != null) {// 만약 쓰레기 값이 있다면
                        session.removeAttribute("userid");// 지워라
                        session.removeAttribute("username");
                        session.removeAttribute("userregion");
                    }
                    session.setAttribute("userid", mvo.getId());
                    session.setAttribute("username", mvo.getName());
                    session.setAttribute("userregion", mvo.getRegion());
                } else {
                    // 비밀번호 틀림
                    model.addAttribute("wrong", "비밀번호가 일치하지 않습니다");
                    model.addAttribute("referer", referer);
                    return "loginForm";
                }
            } else{
                if(mvo.getPw().equals(pass)) {//계정정지인데 비밀번호가 일치할 경우
                    model.addAttribute("stopMember", mvo);//계정 정지됨
                    model.addAttribute("referer", referer);
                    return "loginForm";
                }else{//비밀번호 틀림
                    model.addAttribute("wrong", "비밀번호가 일치하지 않습니다");
                    model.addAttribute("referer", referer);
                    return "loginForm";
                }
            }

        } else {
            model.addAttribute("wrong","아이디가 존재하지 않습니다");
            model.addAttribute("referer", referer);
            return "loginForm";
        }
        return "redirect:" + prev_url;
    }

    @GetMapping("logout")//로그 아웃
    public String logout(HttpSession session, HttpServletRequest request, Model model) {
        session.removeAttribute("userid");
        session.removeAttribute("username");
        session.removeAttribute("userregion");
        session.removeAttribute("prev_url");
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "redirect:"+referer;
    }

    @GetMapping("logoutManager")//매니저 로그아웃
    public String logoutManager(HttpSession session) {
        session.removeAttribute("managerid");
        session.removeAttribute("managername");
        session.removeAttribute("managergrade");
        return "redirect:snsMaster";
    }

    @GetMapping("managerPage")//로그인 되있으면 매니저 페이지로 이동
    public String managerMode(Model model,HttpSession session) {
        if(session.getAttribute("managerid")!=null){
            return "redirect:managerMode";
        }else{
            return "managerPage";
        }
    }

    @PostMapping("loginManager")//로그인 매니저
    public String loginManager(@RequestParam("id") String id, @RequestParam("pw") String pass, Model model, HttpSession session) throws Exception {
        MemberVO mvo = loginService.login(id);
        if (mvo != null) {
            if (mvo.getGrade() != null) {
                if (mvo.getPw().equals(pass)) {
                    if (session.getAttribute("managerid") != null) {
                        session.removeAttribute("managerid");
                        session.removeAttribute("managername");
                        session.removeAttribute("managergrade");
                    }
                    session.setAttribute("managerid", mvo.getId());
                    session.setAttribute("managername", mvo.getName());
                    session.setAttribute("managergrade", mvo.getGrade());
                } else {
                    //비밀번호 틀림
                    model.addAttribute("wrong", "잘못된 접근입니다");
                    return "managerPage";
                }
            }else{
                model.addAttribute("wrong","잘못된 접근입니다");
                return "managerPage";
                //일반사용자가 접근 시도
            }
        } else {
            //아이디 없음
            model.addAttribute("wrong", "잘못된 접근입니다");
            return "managerPage";
        }
        return "redirect:managerMode";
    }
}
