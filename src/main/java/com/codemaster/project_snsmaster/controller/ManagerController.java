package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_AdminService;
import com.codemaster.project_snsmaster.service.IF_GroupService;
import com.codemaster.project_snsmaster.service.IF_ManagerService;
import com.codemaster.project_snsmaster.service.IF_PostService;
import com.codemaster.project_snsmaster.util.FileDataUtil;
import com.codemaster.project_snsmaster.vo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
public class ManagerController {
    @Autowired
    IF_ManagerService manager;
    @Autowired
    FileDataUtil fileDataUtil;
    @Autowired
    IF_PostService postService;
    @Autowired
    IF_AdminService adminService;
    @Autowired
    IF_GroupService groupService;

    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String home(Model model) {
       List<NoticeVO>allList=manager.notice();
       model.addAttribute("allList", allList);
        return "review";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(@RequestParam("s_text") String memo) throws Exception {
        System.out.println(memo.toString());
        manager.insert(memo);
        return "redirect:review";
    }

   @GetMapping(value="dell")
   public String dell(@RequestParam int NO)throws Exception{
    manager.noticedell(NO);
       return "redirect:review";
   }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@RequestParam("g_no") int g_no) throws Exception {
        System.out.println(g_no);
        manager.delete(g_no);
        return "redirect:managerMode2";
    }

    @RequestMapping(value = "delete2", method = RequestMethod.GET)
    public String delete2(@RequestParam("no") int no) throws Exception {
        System.out.println(no);
        manager.delete2(no);
        return "redirect:managerMode";
    }

    @RequestMapping(value = "/managerMode2", method = RequestMethod.GET)
    public String reportsave(Model model) throws Exception {
        List<GroupPostVO> allList = manager.groupreport();
        model.addAttribute("all", allList);
        return "Manager.Main2";
    }

    @RequestMapping(value = "/managerMode", method = RequestMethod.GET)
    public String reportsave2(Model model,HttpSession session) throws Exception {
        List<PostVO> allList = manager.postreport();
        model.addAttribute("all", allList);
        String managername=(String)session.getAttribute("managername");
        model.addAttribute("managername", managername);
        return "Manager.Main";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stop(Model model, String id) throws Exception {
        model.addAttribute("id", id);
        return "stop";
    }

    @RequestMapping(value = "stopinput", method = RequestMethod.GET)
    public String stopinput2(@ModelAttribute StopMemberVO stop) throws Exception {
        manager.stopinsert(stop);
        return  "redirect:managerMode";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("searchcell") String searchcell,
                         @RequestParam("search") String search, Model model,HttpSession session) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("searchcell", searchcell);
        params.put("search", search);
        List<PostVO> allList = manager.selectrandom(params);
        model.addAttribute("all", allList);
        String managername=(String)session.getAttribute("managername");
        model.addAttribute("managername", managername);
        return "Manager.Main";
    }

    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    public String search2(@RequestParam("searchcell") String searchcell,
                          @RequestParam("search") String search, Model model) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("searchcell", searchcell);
        params.put("search", search);
        List<GroupPostVO> allList = manager.selectrandom2(params);
        model.addAttribute("all", allList);
        return "Manager.Main2";
    }
    @RequestMapping(value = "/search3", method = RequestMethod.GET)
    public String search3(@RequestParam("searchcell") String searchcell,
                          @RequestParam("search") String search, Model model) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("searchcell", searchcell);
        params.put("search", search);
        List<StopMemberVO> allList = manager.selectStopmember(params);
        model.addAttribute("all", allList);
        return "Manager.stopmember";
    }

    @RequestMapping(value = "alter", method = RequestMethod.GET)
    public String alter(@RequestParam("no") int no) throws Exception {
        System.out.println(no);
        manager.alter(no);
        return "redirect:managerMode";
    }

    @RequestMapping(value = "alter2", method = RequestMethod.GET)
    public String alter2(@RequestParam("g_no") int g_no) throws Exception {
        System.out.println(g_no);
        manager.alter2(g_no);
        return "redirect:managerMode2";
    }

    @RequestMapping(value = "stopmember", method = RequestMethod.GET)
    public String stopmember(Model model) throws Exception {
        List<StopMemberVO> allList = manager.stopMember();
        model.addAttribute("all", allList);
        return "Manager.stopmember";
    }

    @RequestMapping(value = "stopdelete", method = RequestMethod.GET)
    public String stopdelete(Model model, @RequestParam("id") String id) throws Exception {
        manager.stopdelete(id);
        List<StopMemberVO> allList = manager.stopMember();
        model.addAttribute("all", allList);
        return "Manager.stopmember";
    }


    @RequestMapping(value = "fordelete", method = RequestMethod.GET)
    public String fordelete(Model model, @RequestParam("no") String[] no) throws Exception {
        List<String> list = Arrays.asList(no);
        manager.fordelete(list);
        List<PostVO> allList = manager.postreport();
        model.addAttribute("all", allList);
        return "Manager.Main";
    }

    @RequestMapping(value = "fordelete2", method = RequestMethod.GET)
    public String fordelete2(Model model, @RequestParam("no") String[] no) throws Exception {
        List<String> list = Arrays.asList(no);
        manager.fordelete2(list);
        List<GroupPostVO> allList = manager.groupreport();
        model.addAttribute("all", allList);
        return "Manager.Main2";
    }

    @RequestMapping(value = "StopAND", method = RequestMethod.GET)
    public String StopAND(Model model, @RequestParam("no") String no, @RequestParam("id") String id) throws Exception {
        model.addAttribute("no", no);
        model.addAttribute("id", id);
        return "StopANDdelete";
    }

    @RequestMapping(value = "stopdeleteinput", method = RequestMethod.GET)
    public String stopinput2(@ModelAttribute StopMemberVO stop, @RequestParam int no) throws Exception {
        manager.stopinsert(stop);
        manager.delete2(no);
        return "redirect:managerMode";
    }

    @ResponseBody
    @RequestMapping(value = "look", method = RequestMethod.GET)
    public HashMap<String, Object> look(@RequestParam("no") String no, @RequestParam("id") String id) throws Exception {
        PostVO look = manager.selectpost(Integer.parseInt(no));
        HashMap<String, Object> a = new HashMap<>();
        a.put("id", id);
        a.put("no", look);
        a.put("post", postService.selectOne(no));
        return a;
    }

    @ResponseBody
    @RequestMapping(value = "look2", method = RequestMethod.GET)
    public HashMap<String, Object> look2(@RequestParam("no") String no, @RequestParam("id") String id) throws Exception {
        GroupPostVO look = manager.selectgroupPost(Integer.parseInt(no));
        HashMap<String, Object> a = new HashMap<>();
        a.put("id", id);
        a.put("no", look);
        a.put("post", groupService.gpselect(no));
        return a;
    }

    @ResponseBody
    @GetMapping(value = "/like Notification")
    public void postInput(@RequestParam int no,@RequestParam String content, @RequestParam String userid, @RequestParam String urll) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userid", userid);
        data.put("content", content);
        data.put("urll", urll);
        data.put("no", no);
        manager.likeNotification(data);
    }
    @ResponseBody
    @GetMapping(value = "/follow Notification")
    public void postInput(@RequestParam String id,@RequestParam String content, @RequestParam String userid, @RequestParam String urll) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id",id);
        data.put("followid", userid);
        data.put("content", content);
        data.put("urll", urll);
        manager.Notification(data);
    }

    @ResponseBody
    @GetMapping(value = "/comment Notification")
    public void commentInput(@RequestParam String no,@RequestParam String content,@RequestParam String userid,@RequestParam String urll) {
        System.out.println(no);
        System.out.println(urll);
        HashMap<String, Object> data = new HashMap<>();
        data.put("userid", userid);
        data.put("content", content);
        data.put("urll", urll);
        data.put("no", no);
        manager.commentNotification(data);
    }
    @ResponseBody
    @GetMapping(value = "/groupinput Notification")
    public void groupInput(@RequestParam String gno,@RequestParam String content,@RequestParam String userid,@RequestParam String urll) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userid", userid);
        data.put("content", content);
        data.put("urll", urll);
        data.put("gno", gno);
        manager.groupNotification(data);
    }

    @ResponseBody
    @GetMapping(value = "/notification state")
    public int commentState(@RequestParam String id) {
        int state = manager.statecount(id);
        return state;
    }

    @GetMapping(value = "notification")
    public String notification(@RequestParam String id, Model model) {
        List<NotificationVO> allList = manager.notificationlook(id);
        manager.notifi(id);
        model.addAttribute("all", allList);
        return "Notification";
    }
}


