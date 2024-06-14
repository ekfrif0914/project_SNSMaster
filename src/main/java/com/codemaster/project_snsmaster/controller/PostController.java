package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_PostService;
import com.codemaster.project_snsmaster.util.FileDataUtil;
import com.codemaster.project_snsmaster.vo.PostCommentVO;
import com.codemaster.project_snsmaster.vo.PostVO;
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
    FileDataUtil fileDataUtil;

    @GetMapping(value = "/snsMaster")
    public String postMain(Model model) throws Exception {
        model.addAttribute("posts", postService.selectAll());
        return "post_main_test"; // test 끝난 후 변경
    }
    @GetMapping(value = "/postSearch")
    public String postSearch(@RequestParam String sword, @RequestParam String region, Model model) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("sword", sword);
        params.put("region", region);
        model.addAttribute("posts", postService.select(params));
        return "post_main_test"; // test 끝난 후 변경
    }
    @GetMapping(value = "/myPost")
    public String postInput() {

        return "post_inputForm_test"; // test 끝난 후 변경
    }
    @PostMapping(value = "/postInputSave")
    public String postInputSave(@ModelAttribute PostVO postvo, HttpSession session, MultipartFile[] file) throws Exception {
//        postvo.setId(session.getAttribute("userId"));
        if(file != null) {
            String[] fileName = fileDataUtil.fileUpload(file);
            postvo.setFile_name(fileName);
        }
        postService.insertPost(postvo);
        return "redirect:/postMain";
    }
    @GetMapping(value = "/myPage")
    public String postMyPage() {

        return "post_myPage_test"; // test 끝난 후 변경
    }
    @GetMapping(value = "/postDetail")
    public String postDetail(@RequestParam String no, Model model) throws Exception {
        model.addAttribute("post", postService.selectOne(no));
//        System.out.println(no);
//        System.out.println(postService.selectFileNames(no));
        model.addAttribute("fileNames", postService.selectFileNames(no));
        model.addAttribute("comments", postService.selectComment(no));
        return "post_detail_test";
    }
    @PostMapping(value = "/postSaveComment")
    public String postSaveComment(@ModelAttribute PostCommentVO postCommentVO, HttpSession session) throws Exception {
//        postCommentVO.setId(session.getAttribute("userid")); // 세션으로부터 아이디 받아옴
        postService.insertComment(postCommentVO);
        return "redirect:/postDetail?no=" + postCommentVO.getNo();
    }
    @GetMapping(value = "/postDeleteComment")
    public String postDeleteComment(@ModelAttribute PostCommentVO postCommentVO, HttpSession session) throws Exception {
        postService.deleteComment(postCommentVO.getC_no()+"");
        return "redirect:/postDetail?no=" + postCommentVO.getNo();
    }
    @GetMapping(value = "/postDelete")
    public String postDelete(@RequestParam String no) throws Exception {
        postService.deletePost(no);
        return "redirect:/postMain";
    }
    @GetMapping(value = "/postMod")
    public String postMod(@RequestParam String no) throws Exception {
        postService.selectOne(no);
        return "redirect:/postDetail?no=" + no;
    }

}
