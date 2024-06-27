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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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
    public String postMain(Model model, HttpSession session) throws Exception {
        String userid = (String) session.getAttribute("userid");
        List<PostVO> posts = postService.selectAll();
        List<Integer> likeNos = postService.selectMyLikeNo(userid);
        for (PostVO post : posts) {
            for (Integer likeNo : likeNos) {
                if (likeNo != null) {
                    if (post.getNo() == likeNo) {
                        post.setLike(true);
                    }
                }
            }
        }
        model.addAttribute("posts", posts);
        model.addAttribute("comments", postService.selectAllComment());

        // 공공데이터 xml을 document로 파싱
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("https://www.cng.go.kr/country/api/cngevent.do");
        Element root = document.getDocumentElement();
        int dataLength = root.getChildNodes().getLength();
        Random random = new Random();
        int randomNo = random.nextInt(dataLength);
        while (!root.getChildNodes().item(randomNo).getNodeName().equals("list")){
            randomNo = random.nextInt(dataLength);
        }
        NodeList childNodes = root.getChildNodes().item(randomNo).getChildNodes();
        HashMap<String, String> festivalMap = new HashMap<>();
        festivalMap.put("name", childNodes.item(3).getTextContent());
        festivalMap.put("period", childNodes.item(5).getTextContent());
        festivalMap.put("place", childNodes.item(7).getTextContent());
        festivalMap.put("sponsor", childNodes.item(9).getTextContent());
        festivalMap.put("content", childNodes.item(11).getTextContent());
        model.addAttribute("festivalMap", festivalMap);

        return "post_main";
    }

    @GetMapping(value = "/postSearch")
    public String postSearch(@RequestParam String category,
                             @RequestParam String sword,
                             @RequestParam String region,
                             Model model, HttpSession session) throws Exception {
        String userid = (String) session.getAttribute("userid");
        HashMap<String, String> params = new HashMap<>();
        params.put("sword", sword);
        params.put("region", region);
        params.put("category", category);
        model.addAttribute("posts", postService.select(params));
        model.addAttribute("comments", postService.selectAllComment());
        return "post_main";
    }

    @GetMapping(value = "/myPost")
    public String postInput() {
        return "post_inputForm";
    }

    @PostMapping(value = "/postInputSave")
    public String postInputSave(@ModelAttribute PostVO postvo, HttpSession session, MultipartFile[] file) throws Exception {

        postvo.setId((String) session.getAttribute("userid"));

        if (file != null) {
            String[] fileName = fileDataUtil.fileUpload(file);
            postvo.setFile_name(fileName);
        }
        postService.insertPost(postvo);
        return "redirect:/snsMaster";
    }

    @GetMapping(value = "/myPage")
    public String postMyPage(Model model, HttpSession session, String category) throws Exception {
        String userid = (String) session.getAttribute("userid");
        if (userid == null) {
            return "loginForm";
        }
        if (category == null) {
            model.addAttribute("posts", postService.selectMyPost(userid));
            model.addAttribute("gposts", postService.selectMyGroupPost(userid));
            model.addAttribute("gjoins", postService.selectMyGroupJoin(userid));
        } else {
            switch (category) {
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
      
        model.addAttribute("memberinfo", adminService.getMember(userid));
        model.addAttribute("userid", userid);
        model.addAttribute("category", category);

        return "post_myPage";
    }

    @GetMapping(value = "/postDetail")
    public String postDetail(@RequestParam String no, Model model, HttpSession session) throws Exception {
        String userid = (String) session.getAttribute("userid");
        PostVO post = postService.selectOne(no);
        List<Integer> likeNos = postService.selectMyLikeNo(userid);
        for (Integer likeNo : likeNos) {
            if (likeNo != null) {
                if (post.getNo() == likeNo) {
                    post.setLike(true);
                }
            }
        }
        model.addAttribute("post", post);
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
        postService.deleteComment(postCommentVO.getC_no() + "");
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
        if (file != null) {
            String[] fileName = fileDataUtil.fileUpload(file);
            postVO.setFile_name(fileName);
        }
        postService.modPost(postVO, delfname);
        return "redirect:" + referer;
    }


    @ResponseBody
    @PostMapping("postLike")
    public HashMap<String, Object> postLike(String no, String id) throws Exception {
        boolean isLike = postService.changeLike(no, id);
        int likeCnt = postService.likeCnt(no);
        HashMap<String, Object> data = new HashMap<>();
        data.put("isLike", isLike);
        data.put("likeCnt", likeCnt);
        return data;
    }

    @ResponseBody
    @PostMapping("postReport")
    public boolean postReport(String no, String id) throws Exception {
        boolean isReport = postService.report(no, id);
        return isReport;
    }

    @PostMapping("deletePostArray")
    public String deletePost(@RequestParam String postArray, @RequestParam String gpostArray, @RequestParam String gjoinArray, HttpServletRequest request) throws Exception {
        System.out.println(postArray);
        System.out.println(gpostArray);
        System.out.println(gjoinArray);
        if (postArray != "") {
            System.out.println(postArray + "postarray");
            adminService.deletePostArray(postArray);
        }
        if (gpostArray != "") {
            System.out.println(gpostArray + "gpostarray");
            adminService.deletegPostArray(gpostArray);
        }
        if (gjoinArray != "") {
            System.out.println(gjoinArray + "gjoinarray");
            adminService.deletegJoinArray(gjoinArray);
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

    }


}
