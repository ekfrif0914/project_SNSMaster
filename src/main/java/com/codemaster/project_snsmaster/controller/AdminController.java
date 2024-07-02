package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_AdminService;
import com.codemaster.project_snsmaster.service.IF_EmailService;
import com.codemaster.project_snsmaster.util.FileDataUtil;
import com.codemaster.project_snsmaster.vo.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    IF_AdminService ifAdminService;

    @Autowired
    IF_EmailService ifEmailService;
    @Autowired
    private FileDataUtil fileDataUtil;


    @GetMapping("signUp")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("saveSignUp")
    public String saveSignUp(@ModelAttribute MemberVO memberVO) throws Exception {
        ifAdminService.insert(memberVO);
        String idEncoding = URLEncoder.encode(memberVO.getId(), "UTF-8");
        return "redirect:/profilePage?id=" + idEncoding;
    }

    @GetMapping("profilePage")//회원가입 후 프로필 사진 넣기
    public String profileUpdate(@RequestParam String id, Model model) {
        model.addAttribute("nowid", id);
        return "profileUpdate";
    }

    @ResponseBody // 값 변환을 위해 꼭 필요함
    @GetMapping("idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int overlappedID(String id) throws Exception {
        int result = ifAdminService.overlappedID(id);
        return result;
    }

    @ResponseBody //비동기통신에서 특정 값을 리턴해줄때 씀
    @GetMapping("memberCheck") // 아이디 중복확인을 위한 값으로 따로 매핑,회원인지 아닌지 확인
    public String memberCheck(String id) throws Exception {
        MemberVO mvo = ifAdminService.getMember(id);
        if (mvo != null) {
            return mvo.getPw();
        } else {
            String nomember = "회원아님";
            return nomember;
        }
    }

    @ResponseBody
    @GetMapping("emailConfirm")//이메일 인증
    public String emailConfirm(String email) throws MessagingException, UnsupportedEncodingException,
            Exception {
        String confirm = ifEmailService.sendSimpleMessage(email);
        return confirm;
    }

    @ResponseBody
    @GetMapping("emailCheck")//이메일 중복확인
    public int emailCheck(String email) throws Exception {
        int result = ifAdminService.overlappedEmail(email);
        return result;
    }

    @PostMapping("updateProfile")//프로필 사진 올리기
    public String updateProfile(@RequestParam("id") String id, MultipartFile[] file) throws Exception {
        if (file != null) {
            String[] filename = fileDataUtil.fileUpload(file);
            ifAdminService.updateProfile(id, filename);
        }

        return "redirect:/snsMaster";
    }

    @GetMapping("myinfoPage")//내 정보 관리
    public String myinfoPage(@RequestParam("id") String id, Model model) throws Exception {
        MemberVO member = ifAdminService.getMember(id);
        model.addAttribute("member", member);
        return "myinfoPage";
    }

    @PostMapping("updateSave")//회원정보 수정
    public String updateSave(@ModelAttribute MemberVO memberVO, HttpSession session) throws Exception {
        ifAdminService.updateSave(memberVO);
        session.setAttribute("username", memberVO.getName());
        session.setAttribute("userregion", memberVO.getRegion());
        return "redirect:myPage";

    }

    @PostMapping("memberCancelPage")//회원 탈퇴 페이지
    public String memberCancelPage(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception {
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "memberCancelPage";
    }

    @PostMapping("memberCancelPage2")//비밀번호 재확인 페이지
    public String memberCancelPage2(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception {
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "memberCancelPage2";
    }

    @GetMapping("stopMember")//회원 탈퇴 후 처리
    public String deleteMember(@RequestParam String id, HttpSession session) throws Exception {
        ifAdminService.stop(id);
        session.removeAttribute("userid");
        session.removeAttribute("username");
        session.removeAttribute("userregion");
        session.removeAttribute("prev_url");
        return "redirect:snsMaster";
    }

    @PostMapping("changeDefaultimg")//기본이미지로 변경
    public String changeDefaultimg(@RequestParam("id") String id, @RequestParam String[] delfname, HttpServletRequest request) throws Exception {
        fileDataUtil.fileDelete(delfname);
        ifAdminService.changeDefaultimg(id);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("updatemyProfileImg")//프로필 사진 변경
    public String updateMyProfileImg(@RequestParam("id") String id, MultipartFile[] file, HttpServletRequest request) throws Exception {
        if (file != null) {
            String[] filename = fileDataUtil.fileUpload(file);
            ifAdminService.updateProfile(id, filename);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("FAQPage")//FAQ페이지의 메인
    public String faqPage(Model model, @ModelAttribute PageVO pagevo) throws Exception {
        if (pagevo.getPage() == null) {
            pagevo.setPage(1);
        }
        if (pagevo.getSearchKeyword() == null || pagevo.getSearchType() == null
                || pagevo.getSearchKeyword().equals("") || pagevo.getSearchType().equals("")) {
            pagevo.setTotalCount(ifAdminService.getTotalCount());
            List<FAQVO> faqvoList = ifAdminService.faqselect(pagevo);
            model.addAttribute("pagevo", pagevo);
            model.addAttribute("list", faqvoList);
        } else {
            HashMap<String, String> param = new HashMap<>();
            param.put("searchKeyword", pagevo.getSearchKeyword());
            param.put("searchType", pagevo.getSearchType());
            pagevo.setTotalCount(ifAdminService.getSearchTotalCount(param));
            List<FAQVO> faqvoSearchList = ifAdminService.faqSearchselect(pagevo);
            model.addAttribute("pagevo", pagevo);
            model.addAttribute("list", faqvoSearchList);
        }
        return "memberFAQ";
    }

    @GetMapping("FAQPageWrite")//FAQ글 작성
    public String faqPageWrite(HttpServletRequest request, Model model) throws Exception {
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        return "memberFAQWrite";
    }

    @PostMapping("FAQInputSave")//글 작성 저장
    public String faqinputSave(@ModelAttribute FAQVO faqvo) throws Exception {
        ifAdminService.faqinsert(faqvo);
        return "redirect:/FAQPage";
    }

    @GetMapping(value = "/FAQDetail")//글 상세보기
    public String postDetail(HttpServletRequest request, @RequestParam String f_no, Model model) throws Exception {
        ifAdminService.viewUp(f_no);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        model.addAttribute("FAQDetail", ifAdminService.selectOne(f_no));
        return "FAQDetail";
    }

    @ResponseBody
    @PostMapping("following")//팔로우
    public boolean following(@ModelAttribute FollowVO fvo) throws Exception {
        boolean isFollowing = ifAdminService.following(fvo);//true면 팔로잉 됨,false면 팔로우 취소
        return isFollowing;
    }

}
