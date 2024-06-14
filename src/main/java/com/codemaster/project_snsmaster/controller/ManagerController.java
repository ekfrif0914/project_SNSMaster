package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_ManagerService;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {
    @Autowired
    IF_ManagerService manager;


    @RequestMapping(value = "/review", method = RequestMethod.GET)
    public String home() {
        return "review";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(@RequestParam("id") String id) throws Exception {
        System.out.println(id.toString());
        manager.insert(id);
        return "review";
    }

    @RequestMapping(value="delete",method=RequestMethod.GET)
    public String delete(@RequestParam("g_no") int g_no) throws Exception {
        System.out.println(g_no);
        manager.delete(g_no);
        return "redirect:managerMode2";
    }

    @RequestMapping(value="delete2",method=RequestMethod.GET)
    public String delete2(@RequestParam("no") int no) throws Exception {
        System.out.println(no);
        manager.delete2(no);
        return "redirect:managerMode";
    }

    @RequestMapping(value= "look", method=RequestMethod.GET)
    public String look(@RequestParam("g_no") int g_no,Model model) throws Exception{
        System.out.println(g_no);
        GroupPostVO look=manager.selectgroupPost(g_no);
        model.addAttribute("g_no",look);
        return "look";
    }

    @RequestMapping(value= "look2", method=RequestMethod.GET)
    public String look2(@RequestParam("no") int no,Model model) throws Exception{
        PostVO look=manager.selectpost(no);
        model.addAttribute("no",look);
        return "look2";
    }

    @RequestMapping(value = "/managerMode2", method = RequestMethod.GET)
    public String reportsave(Model model) throws Exception {
        List<GroupPostVO> allList = manager.groupreport();
        model.addAttribute("all", allList);
        return "Manager.Main2";
    }

    @RequestMapping(value = "/managerMode", method = RequestMethod.GET)
    public String reportsave2(Model model) throws Exception {
        List<PostVO> allList=manager.postreport();
        model.addAttribute("all",allList);
        return "Manager.Main";
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public String stop(Model model,String id) throws Exception {
        System.out.println(id);
        model.addAttribute("id",id);
        return "stop";
    }
    @RequestMapping(value="stopinput",method=RequestMethod.GET)
    public String stopinput(@ModelAttribute StopMemberVO stop)throws Exception {
        System.out.println(stop.toString());
        manager.stopinsert(stop);
        return "redirect:managerMode";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("searchcell") String searchcell,
                         @RequestParam("search") String search, Model model) throws Exception {
        HashMap<String, String> params = new HashMap<>();
        params.put("searchcell", searchcell);
        params.put("search", search);
        List<PostVO>allList=manager.selectrandom(params);
        model.addAttribute("all",allList);
        return "Manager.Main";
    }
}
