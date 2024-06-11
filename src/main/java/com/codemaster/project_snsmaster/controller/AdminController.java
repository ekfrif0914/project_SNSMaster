package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_AdminService;
import com.codemaster.project_snsmaster.service.IF_EmailService;
import com.codemaster.project_snsmaster.vo.MemberVO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller
public class AdminController {
    @Autowired
    IF_AdminService ifAdminService;

    @Autowired
    IF_EmailService ifEmailService;

    @GetMapping("signUp")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("saveSignUp")
    public String saveSignUp(@ModelAttribute MemberVO memberVO) throws Exception {
        System.out.println(memberVO.toString());
        ifAdminService.insert(memberVO);
        return "redirect:signUp";
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


}
