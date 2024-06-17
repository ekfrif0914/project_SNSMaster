package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_AdminService;
import com.codemaster.project_snsmaster.service.IF_EmailService;
import com.codemaster.project_snsmaster.util.FileDataUtil;
import com.codemaster.project_snsmaster.vo.MemberVO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
        System.out.println(memberVO.toString());
        ifAdminService.insert(memberVO);
        String idEncoding =  URLEncoder.encode(memberVO.getId(),"UTF-8");
        return "redirect:/profilePage?id=" +  idEncoding ;
    }

    @GetMapping("profilePage")
    public String profileUpdate(@RequestParam String id, Model model) {
        model.addAttribute("nowid", id);
        return "profileUpdate";

    }

    @ResponseBody // 값 변환을 위해 꼭 필요함
    @GetMapping("idCheck") // 아이디 중복확인을 위한 값으로 따로 매핑
    public int overlappedID(String id) throws Exception {
        System.out.println("확인");
        int result = ifAdminService.overlappedID(id);
        System.out.println(result);// 중복확인한 값을 int로 받음
        return result;
    }

    @ResponseBody
    @GetMapping("emailConfirm")
    public String emailConfirm(String email) throws MessagingException, UnsupportedEncodingException,
            Exception {

        String confirm = ifEmailService.sendSimpleMessage(email);
        System.out.println(confirm);
        return confirm;
    }

    @ResponseBody
    @GetMapping("emailCheck")
    public int emailCheck(String email) throws Exception {
        System.out.println("확인");
        int result = ifAdminService.overlappedEmail(email);
        System.out.println(result);// 중복확인한 값을 int로 받음
        return result;
    }

    @PostMapping("updateProfile")//프로필 사진 올리기
    public String updateProfile(@RequestParam("id") String id, MultipartFile[] file) throws Exception {
        System.out.println(id);
        System.out.println(file[0].getOriginalFilename());

        if (file != null) {
            String[] filename = fileDataUtil.fileUpload(file);
            ifAdminService.updateProfile(id, filename);
            System.out.println("신갓다");
        }

        return "redirect:/snsMaster";
    }

    @GetMapping("myinfoPage")
    public String myinfoPage(@RequestParam("id") String id, Model model) throws Exception {
        //System.out.println(id);

        MemberVO member = ifAdminService.getMember(id);
        model.addAttribute("member", member);
        return "myinfoPage";
    }

    @PostMapping("updateSave")
    public String updateSave(@ModelAttribute MemberVO memberVO) throws Exception {
        System.out.println(memberVO.toString());
        ifAdminService.updateSave(memberVO);
        return "redirect:myPage";

    }

    @PostMapping("memberCancelPage")
    public String memberCancelPage(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception {
        System.out.println(id);
        System.out.println(pw);
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "memberCancelPage";
    }

    @PostMapping("memberCancelPage2")
    public String memberCancelPage2(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception {
        System.out.println(id);
        System.out.println(pw);
        model.addAttribute("id", id);
        model.addAttribute("pw", pw);
        return "memberCancelPage2";
    }

    @GetMapping("stopMember")
    public String deleteMember(@RequestParam String id, HttpSession session) throws Exception {
        ifAdminService.stop(id);
        session.invalidate();
        return "redirect:snsMaster";

    }
}
