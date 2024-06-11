package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_ManagerService;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.PostVO;
import com.codemaster.project_snsmaster.vo.StopMemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "redirect:manager";
    }

    @RequestMapping(value= "look", method=RequestMethod.GET)
    public String look(@RequestParam("g_no") int g_no,Model model) throws Exception{
       System.out.println(g_no);
      GroupPostVO look=manager.selectpost(g_no);
      model.addAttribute("g_no",look);
        return "look";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String reportsave(Model model) throws Exception {
        List<GroupPostVO> allList = manager.groupreport();
//        List<PostVO> allList2=manager.postreport();
        System.out.println(allList.toString());
        model.addAttribute("all", allList);
//        model.addAttribute("all2", allList2);
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
        return "redirect:manager";
    }
}
