package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_GroupService;
import com.codemaster.project_snsmaster.vo.GroupJoinVO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.MemberGroupVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    IF_GroupService gservice;

    @RequestMapping(value = "/groupinput",method = RequestMethod.GET)//그룹에서 게시글 작성
    public String groupinput(@RequestParam int gno, Model model){
        System.out.println(gno);
        model.addAttribute("gno",gno);
        return "groupinput_test";
    }

    @RequestMapping(value = "/groupinputSave",method = RequestMethod.POST)//그룹에서 게시글 작성
    public String groupinputSave(@ModelAttribute GroupPostVO gpvo)throws Exception{
        System.out.println(gpvo.toString());
        gservice.insert(gpvo);
        return "redirect:groupinput?gno="+gpvo.getGno();
    }

    @RequestMapping(value = "/membergroupinput",method = RequestMethod.GET)//그룹만들기
    public String membergroupinput(){
        return "membergroup_test";
    }

    @RequestMapping(value = "/membergroupinputSave",method = RequestMethod.POST)//그룹만들기
    public String membergroupinputSave(@ModelAttribute MemberGroupVO mgvo)throws Exception{
        System.out.println(mgvo.toString());
        gservice.mginsert(mgvo);
        return "membergroup_test";
    }
    @GetMapping("/gList")//그룹 전체보기
    public String gList(Model model)throws Exception{

        List<MemberGroupVO> gList = gservice.gList();
        model.addAttribute("gList",gList);
        return "groupall_test";
    }
    @GetMapping("/gpList")//그룹에 들어가면 그 그룹에 관한 게시글 보기
    public String gpList(@RequestParam("gno")String gno,Model model)throws Exception{
        System.out.println(gno);
        model.addAttribute("gno",gno);
        List<GroupPostVO> gpList = gservice.gpList(gno);

        System.out.println(gpList);
        model.addAttribute("gpList",gpList);
        return "gpall_test";
    }

    @RequestMapping (value = "/",method = RequestMethod.GET)
    public String home(){
        return "grouphome_test";
    }

    @RequestMapping(value = "/delgroup",method = RequestMethod.GET)//게시글 삭제
    public String delgroup(@RequestParam("gno")String gno,@RequestParam("g_no") int g_no)throws Exception{
        System.out.println(g_no);
        System.out.println(gno);

        gservice.delete(g_no);

        return "redirect:/gpList?gno="+gno;
        //return redirect 할때 그룹 게시글이 있는 화면으로 넘어 갈려고 할때 ?gno="+gno 쓰면
        //삭제한 게시글이 있던 그룹으로 화면을 보여준다
    }

    @RequestMapping(value = "/delmembergroup",method = RequestMethod.GET)//그룹 삭제
    public String delmembergroup(@RequestParam("gno") int gno)throws Exception{
        System.out.println(gno);
        gservice.mdelete(gno);
        return "redirect:/gList";
    }

    @GetMapping(value = "/modno")//수정할글 화면에 출력
    public String modno(@RequestParam("g_no") int g_no,Model model)throws Exception{
        GroupPostVO gpvo = gservice.modno(g_no);
        System.out.println("수정된 글 정보 확인");
        System.out.println(gpvo.toString());
        model.addAttribute("gpvo",gpvo);
        return "modall_test";
    }
    @PostMapping(value = "/modnosave")//수정글 저장
    public String modnoSave(@ModelAttribute GroupPostVO gpvo)throws Exception{
        System.out.println(gpvo.toString());
        System.out.println(gpvo.getG_no());
        gservice.modnoSaver(gpvo);
        return  "redirect:/gpList?gno="+gpvo.getGno();
    }


    @RequestMapping(value = "/groupjoin",method = RequestMethod.GET) //그룹에 가입
    public String groupjoin(@ModelAttribute GroupJoinVO gjvo, HttpSession session)throws Exception{
        //gjvo.setId((String) session.getAttribute("userid"));
        System.out.println(gjvo.getGno());
        System.out.println(gjvo.getId());
        gservice.joinsert(gjvo);
        return "groupjoin_test";
    }

}
