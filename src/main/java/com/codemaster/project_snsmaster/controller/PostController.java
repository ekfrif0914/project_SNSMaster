package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_AdminService;

import com.codemaster.project_snsmaster.service.IF_GroupService;

import com.codemaster.project_snsmaster.service.IF_PostService;
import com.codemaster.project_snsmaster.util.FileDataUtil;
import com.codemaster.project_snsmaster.vo.PostCommentVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Controller
public class PostController {
    @Autowired
    IF_PostService postService;

    @Autowired
    IF_AdminService adminService;
    @Autowired

    IF_GroupService groupService;

    @Autowired
    FileDataUtil fileDataUtil;

    @GetMapping(value = "/snsMaster")
    public String postMain(Model model) throws Exception {
        model.addAttribute("posts", postService.selectAll());
        return "post_main";
    }
    @GetMapping(value = "/postSearch")
    public String postSearch(@RequestParam String sword, @RequestParam String region, Model model) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("sword", sword);
        params.put("region", region);
        model.addAttribute("posts", postService.select(params));
        return "post_main";
    }
    @GetMapping(value = "/myPost")
    public String postInput() {

        return "post_inputForm";
    }
    @PostMapping(value = "/postInputSave")
    public String postInputSave(@ModelAttribute PostVO postvo, HttpSession session, MultipartFile[] file) throws Exception {

        postvo.setId((String)session.getAttribute("userid"));

        if(file != null) {
            String[] fileName = fileDataUtil.fileUpload(file);
            postvo.setFile_name(fileName);
        }
        postService.insertPost(postvo);
        return "redirect:/snsMaster";
    }
    @GetMapping(value = "/myPage")
    public String postMyPage(Model model, HttpSession session, String category) throws Exception {
        String userid = (String) session.getAttribute("userid");

        if(userid == null){
            return "loginForm";
        }
        if(category == null){
            model.addAttribute("posts", postService.selectMyPost(userid));
            model.addAttribute("gposts", postService.selectMyGroupPost(userid));
            model.addAttribute("gjoins", postService.selectMyGroupJoin(userid));
        } else {
            switch (category){
                case "자유게시글":
                case "여행계획서":
                    PostVO postVO = new PostVO();
                    postVO.setCategory(category);
                    postVO.setId(userid);
                    model.addAttribute("posts", postService.selectMyPostbyCategory(postVO));
                    break;
                case "그룹게시글":
                    model.addAttribute("gposts", postService.selectMyGroupPost(userid));
                    break;
                case "모임모집글":
                    model.addAttribute("gjoins", postService.selectMyGroupJoin(userid));
                    break;
                default:
                    model.addAttribute("posts", postService.selectMyPost(userid));
                    model.addAttribute("gposts", postService.selectMyGroupPost(userid));
                    model.addAttribute("gjoins", postService.selectMyGroupJoin(userid));
                    break;
            }
        }

        model.addAttribute("memberinfo",adminService.getMember(userid));

        System.out.println(adminService.getMember(userid).getFile_name());
        model.addAttribute("memberinfo",  adminService.getMember(userid));

        model.addAttribute("userid", userid);
        model.addAttribute("category", category);

        return "post_myPage";
    }
    @GetMapping(value = "/postDetail")
    public String postDetail(@RequestParam String no, Model model) throws Exception {
        model.addAttribute("post", postService.selectOne(no));
        model.addAttribute("fileNames", postService.selectFileNames(no));
        model.addAttribute("comments", postService.selectComment(no));
        return "post_detail";
    }
    @PostMapping(value = "/postSaveComment")
    public String postSaveComment(@ModelAttribute PostCommentVO postCommentVO, HttpServletRequest request, HttpSession session) throws Exception {
        postCommentVO.setId((String) session.getAttribute("userid"));
        postService.insertComment(postCommentVO);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    @GetMapping(value = "/postDeleteComment")
    public String postDeleteComment(@ModelAttribute PostCommentVO postCommentVO, HttpServletRequest request) throws Exception {
        postService.deleteComment(postCommentVO.getC_no()+"");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    @GetMapping(value = "/postDelete")
    public String postDelete(@RequestParam String no, HttpServletRequest request) throws Exception {
        String[] filenames = postService.selectOne(no).getFile_name();
        postService.deletePost(no);
        fileDataUtil.fileDelete(filenames);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
    @GetMapping(value = "/postMod")
    public String postMod(@RequestParam String no, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("post", postService.selectOne(no));
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        return "post_modForm";
    }
    @PostMapping(value = "/postModSave")
    public String postModSave(@ModelAttribute PostVO postVO, MultipartFile[] file, String[] delfname, String referer) throws Exception {
        fileDataUtil.fileDelete(delfname);
        if(file != null) {
            String[] fileName = fileDataUtil.fileUpload(file);
            postVO.setFile_name(fileName);
        }
        postService.modPost(postVO, delfname);
        return "redirect:" + referer;
    }

}
