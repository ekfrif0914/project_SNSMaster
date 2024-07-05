package com.codemaster.project_snsmaster.controller;

import com.codemaster.project_snsmaster.service.IF_GroupService;
import com.codemaster.project_snsmaster.service.IF_ManagerService;
import com.codemaster.project_snsmaster.vo.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
public class GroupController {
    @Autowired
    IF_ManagerService manager;
    @Autowired
    IF_GroupService gservice;

    @RequestMapping(value = "/groupinput", method = RequestMethod.GET)//그룹에서 게시글 작성
    public String groupinput(@RequestParam int gno, Model model) {
        model.addAttribute("gno", gno);
        return "groupinput";
    }

    @RequestMapping(value = "/groupinputSave", method = RequestMethod.POST)//그룹에서 게시글 작성
    public String groupinputSave(@ModelAttribute GroupPostVO gpvo, HttpSession session) throws Exception {
        gpvo.setId((String) session.getAttribute("userid"));
        gpvo.setG_content(gpvo.getG_content().replace("\r\n", "<br>"));
        gservice.insert(gpvo);
        return "redirect:gpList?gno=" + gpvo.getGno();
    }

    @RequestMapping(value = "/grupinput", method = RequestMethod.GET)//그룹에서 동행 모집글 작성
    public String grupinput(@RequestParam int gno, Model model) {
        model.addAttribute("gno", gno);
        return "groupinput";
    }

    @RequestMapping(value = "/joininputSave", method = RequestMethod.POST)//그룹에서 모임동행 모집글 작성
    public String joininputSave(@ModelAttribute G_joinVO gjvo, HttpSession session) throws Exception {
        gjvo.setId((String) session.getAttribute("userid"));
        gjvo.setM_contents(gjvo.getM_contents().replace("\r\n", "<br>"));
        gservice.gjinsert(gjvo);
        return "redirect:gpList?gno=" + gjvo.getGno();
    }

    @RequestMapping(value = "/membergroupinput", method = RequestMethod.GET)//그룹만들기
    public String membergroupinput(HttpServletRequest request, Model model) {
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "membergroup";
    }

    @RequestMapping(value = "/membergroupinputSave", method = RequestMethod.POST)//그룹만들기
    public String membergroupinputSave(@RequestParam("referer") String referer, @ModelAttribute MemberGroupVO mgvo, HttpSession session, HttpServletRequest request) throws Exception {
        mgvo.setG_id((String) session.getAttribute("userid"));
        gservice.mginsert(mgvo);
        return "redirect:gList";
    }

    @ResponseBody
    @RequestMapping(value = "/groupjoin", method = RequestMethod.GET) //그룹에 가입
    public int groupjoin(@ModelAttribute GroupJoinVO gjvo, HttpSession session, Model model, @RequestParam("gno") String gno) throws Exception {
        String id = (String) session.getAttribute("userid");
        HashMap<Object, Object> gjoinmap = new HashMap<>();
        gjoinmap.put("gno", (gno));
        gjoinmap.put("id", (id));
        int gjoin = gservice.gjoinselect(gjoinmap);
        model.addAttribute("gjoin", gjoin);
        if (gjoin == 0) {
            HashMap<Object, Object> gjoinwait = new HashMap<>();
            gjoinwait.put("gno", (gno));
            gjoinwait.put("id", (id));
            int joinwait = gservice.gjoinwaitselect(gjoinwait);
            if (joinwait == 0) {
                gjvo.setId((String) session.getAttribute("userid"));
                gservice.joinsert(gjvo);
            } else if (joinwait == 1) {
            }
        } else if (gjoin == 1) {
        }
        return gjoin;
    }


    @RequestMapping(value = "/groupjoinmember", method = RequestMethod.GET)//그룹 가입 그룹장이 수락
    public String groupjoinmember(@ModelAttribute GroupJoinVO gjvo, HttpSession session) throws Exception {
        String id = gjvo.getId();
        int gno = gjvo.getGno();
        String userid = (String) session.getAttribute("userid");
        String content = userid + "님이 가입을 승인하였습니다";
        String url = "http://localhost:8080/gpList?gno=" + gno;
        HashMap<String, Object> data = new HashMap<>();
        data.put("userid", id);
        data.put("content", content);
        data.put("urll", url);
        manager.Notification(data);
        gservice.gjminsert(gjvo);
        gservice.gjmdelete(gjvo);
        return "redirect:/groupmy?gno=" + gjvo.getGno();
    }


    @GetMapping("/gList")//그룹 전체보기
    public String gList(Model model, HttpSession session) throws Exception {
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        List<MemberGroupVO> gList = gservice.gList();
        model.addAttribute("gList", gList);
        return "groupall";
    }

    @GetMapping("/gsearch")// 그룹 전체보기 검색
    public String search(@RequestParam("search") String search, @RequestParam String g_region, Model model, HttpSession session
            , HttpServletRequest request) throws Exception {
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        HashMap<String, String> param = new HashMap<>();
        param.put("search", search);
        param.put("g_region", g_region);
        model.addAttribute("gList", gservice.search(param));
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "groupall";
    }

    @GetMapping("/gjsearch")//그룹 동행 모집글 검색
    public String gjsearch
            (HttpSession session, RedirectAttributes redirect,
             @RequestParam("gno") String gno, @RequestParam String search,
             @RequestParam String g_region, Model model) throws Exception {
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        HashMap<String, String> param = new HashMap<>();
        List<GroupPostVO> gpList = gservice.gpList(gno);
        param.put("gno", gno);
        param.put("search", search);
        param.put("g_region", g_region);
        model.addAttribute("gpList", gpList);
        model.addAttribute("gjList", gservice.gjsearch(param));
        model.addAttribute("gno", gno);
        //redirect.addAttribute("gList",gservice.gpsearch(param));
        //return "redirect:/gpList?gno="+ gno;
        return "gpall";
    }

    @GetMapping("/gpsearch")//그룹 게시글 검색
    public String gpsearch
            (HttpSession session,
             @RequestParam("gno") String gno, @RequestParam String search,
             @RequestParam String idsearch, Model model, HttpServletRequest request) throws Exception {
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        HashMap<String, String> param = new HashMap<>();
        List<G_joinVO> gjList = gservice.gjList(gno);
        param.put("gno", gno);
        param.put("search", search);
        param.put("idsearch", idsearch);
        model.addAttribute("gjList", gjList);
        model.addAttribute("gpList", gservice.gpsearch(param));
        model.addAttribute("gno", gno);
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "gpall";
    }


    @GetMapping("/gpList")//그룹에 들어가면 그 그룹에 관한 게시글 보기
    public String gpList(@RequestParam("gno") String gno, Model model, HttpSession session, HttpServletRequest request) throws Exception {
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        model.addAttribute("gno", gno);
        HashMap<String, String> param = new HashMap<>();
        param.put("gno", gno);
        param.put("id", id);
        model.addAttribute("gjoinList", gservice.gjoinList(param));
        model.addAttribute("gjsize", gservice.gjoinList(param).size());
        model.addAttribute("mgList", gservice.mgList(param));
        model.addAttribute("mgsize", gservice.mgList(param).size());
        List<GroupPostVO> gpList = gservice.gpList(gno);
        List<G_joinVO> gjList = gservice.gjList(gno);
        model.addAttribute("gpList", gpList);
        model.addAttribute("gjList", gjList);
        String referer = request.getHeader("referer");
        model.addAttribute("referer", referer);
        return "gpall";
    }

    @GetMapping("/groupmy")//그룹 가입 신청 한 사람 보기
    public String groupmy(@RequestParam("gno") String gno, Model model) throws Exception {
        model.addAttribute("gno", gno);
        List<GroupJoinVO> gjoList = gservice.gjoList(gno);
        model.addAttribute("gjoList", gjoList);
        return "groupmy";
    }

    @GetMapping("/grouppopup")//그룹 가입한사람 리스트
    public String groupppopup(String gno, Model model) throws Exception {
        model.addAttribute("gno", gno);
        List<GroupJoinVO> gjoinpopList = gservice.gjoinpopList(gno);
        model.addAttribute("gjoinpopList", gjoinpopList);
        return "groupPopup";
    }


    @GetMapping("/joinpopup")//동행글 신청한 사람 리스트
    public String joinpopup(String mo_no, Model model) throws Exception {
        model.addAttribute("mo_no", mo_no);
        List<G_memberVO> gmemberpopList = gservice.gmemberpopList(Integer.parseInt(mo_no));
        model.addAttribute("gmemberpopList", gmemberpopList);
        return "joinpopup";
    }


    @RequestMapping(value = "/delgroup", method = RequestMethod.GET)//게시글 삭제
    public String delgroup(@RequestParam("gno") String gno, @RequestParam("g_no") int g_no, HttpServletRequest request) throws Exception {
        gservice.delete(g_no);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
        //return redirect 할때 그룹 게시글이 있는 화면으로 넘어 갈려고 할때 ?gno="+gno 쓰면
        //삭제한 게시글이 있던 그룹으로 화면을 보여준다
    }

    @RequestMapping(value = "/gjdel", method = RequestMethod.GET)//그룹에서 모임동행글 삭제
    public String gjdel(@RequestParam("mo_no") int mo_no, @RequestParam("gno") String gno, HttpServletRequest request) throws Exception {
        gservice.gjdelete(mo_no);
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }


    @RequestMapping(value = "/delmembergroup", method = RequestMethod.GET)//그룹 삭제
    public String delmembergroup(@RequestParam("gno") int gno) throws Exception {
        gservice.mdelete(gno);
        return "redirect:/gList";
    }

    @RequestMapping(value = "/delgroupjoinmember", method = RequestMethod.GET)//그룹 가입 신청 그룹장이 거부
    public String delgroupjoinmember(@RequestParam("wait_no") int wait_no, @RequestParam("gno") int gno) throws Exception {
        gservice.dgjmdelete(wait_no);
        return "redirect:/groupmy?gno=" + gno;
    }

    @GetMapping(value = "/modno")//수정할글 화면에 출력
    public String modno(@RequestParam("g_no") int g_no, Model model, HttpServletRequest request) throws Exception {
        GroupPostVO gpvo = gservice.modno(g_no);
        model.addAttribute("gpvo", gpvo);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        return "grouppostmodall";
    }

    @PostMapping(value = "/modnosave")//수정글 저장
    public String modnoSave(@ModelAttribute GroupPostVO gpvo, HttpSession session, String referer) throws Exception {
        gpvo.setId((String) session.getAttribute("userid"));
        gpvo.setG_content(gpvo.getG_content().replace("\r\n", "<br>"));
        gservice.modnoSaver(gpvo);
        //return "redirect:/gpList?gno=" + gpvo.getGno();
        return "redirect:" + referer;
    }

    @ResponseBody
    @GetMapping("/greport")//게시글 신고
    public int greport(@RequestParam("g_no") int g_no,
                       HttpSession session, Model model) throws Exception {
        String id = (String) session.getAttribute("userid");
        HashMap<Object, Object> report = new HashMap<>();
        report.put("g_no", (g_no));
        report.put("id", (id));
        int a = gservice.reportsaveselect(report);
        if (a == 0) {
            HashMap<String, String> param = new HashMap<>();
            param.put("g_no", String.valueOf(g_no));
            param.put("id", id);
            model.addAttribute("greport", gservice.greport(param));
            gservice.greportselect(g_no);
        } else if (a == 1) {
        }
        return a;

    }

    @ResponseBody
    @GetMapping("/grouplike")//좋아요
    public int grouplike(@RequestParam("g_no") int g_no,
                         HttpSession session, Model model) throws Exception {
        String id = (String) session.getAttribute("userid");
        HashMap<Object, Object> likemap = new HashMap<>();
        likemap.put("g_no", (g_no));
        likemap.put("id", (id));
        int like = gservice.likeselect(likemap);
        if (like == 0) {
            HashMap<String, String> param = new HashMap<>();
            param.put("g_no", String.valueOf(g_no));
            param.put("id", id);
            model.addAttribute("likegroup", gservice.likegroup(param));
            gservice.likepostselect(g_no);
        } else if (like == 1) {
            HashMap<String, String> likedel = new HashMap<>();
            likedel.put("g_no", String.valueOf(g_no));
            likedel.put("id", id);
            model.addAttribute("likegroupdel", gservice.likegroupdel(likedel));
            gservice.likepostdel(g_no);
        }
        return like;
    }

    @ResponseBody
    @GetMapping(value = "/g_memberjoin")//그룹에서 모임동행글 신청
    public int memberjoin(@RequestParam("cont") int cont, @RequestParam("cnt") int cnt, @RequestParam("gno") int gno, @RequestParam("mo_no") int mo_no, HttpSession session, Model model) throws Exception {
        String id = (String) session.getAttribute("userid");
        HashMap<Object, Object> gmjoin = new HashMap<>();
        gmjoin.put("mo_no", mo_no);
        gmjoin.put("id", (id));
        int joinmember = gservice.joinmb(gmjoin);
        if (cont == cnt) {
        } else {
            if (joinmember == 0) {
                HashMap<Object, Object> joininsert = new HashMap<>();
                joininsert.put("gno", gno);
                joininsert.put("mo_no", mo_no);
                joininsert.put("id", id);
                gservice.joininsert(joininsert);
                gservice.monoup(mo_no);
            } else if (joinmember == 1) {
            }
        }
        return joinmember;
    }


    @GetMapping(value = "/gmjoinmod")//그룹 모임동행글 수정
    public String gmjoinmod(@RequestParam("mo_no") int mo_no, Model model, HttpServletRequest request) throws Exception {
        G_joinVO gjvo = gservice.gmjoinmod(mo_no);
        model.addAttribute("gjvo", gjvo);
        String referer = request.getHeader("Referer");
        model.addAttribute("referer", referer);
        return "gmjoinmod";
    }

    @PostMapping(value = "/gmjoinmodsave")//그룹 모임동행글 수정
    public String gmjoinmodsave(@ModelAttribute G_joinVO gjvo, HttpSession session, String referer) throws Exception {
        gjvo.setId((String) session.getAttribute("userid"));
        gjvo.setM_contents(gjvo.getM_contents().replace("\r\n", "<br>"));
        gservice.gmjoinmodSave(gjvo);
        return "redirect:" + referer;
    }

    @GetMapping(value = "/grouppost")//그룹에서 게시글 자세히 보기
    public String grouppost(@RequestParam("g_no") String g_no, Model model, HttpSession session) throws Exception {
        GroupPostVO gpvo = gservice.gpselect(g_no);
        gpvo.setG_content(gpvo.getG_content().replace("\r\n","<br>"));
        String id = (String) session.getAttribute("userid");
        model.addAttribute("id", id);
        model.addAttribute("gpvo", gpvo);
        List<GroupPostCommentVO> cmList = gservice.cmList(g_no);
        model.addAttribute("cmList", cmList);
        return "grouppost";
    }

    @RequestMapping(value = "/groupcommentSave", method = RequestMethod.POST)//그룹에서 게시글 작성
    public String groupcommentSave(@ModelAttribute GroupPostCommentVO gcvo, HttpSession session) throws Exception {
        gcvo.setId((String) session.getAttribute("userid"));
        gservice.gcinsert(gcvo);
        return "redirect:grouppost?g_no=" + gcvo.getG_no();
    }

    @GetMapping(value = "/commentdel")// 그룹 뎃글 삭제
    public String commentdel(@RequestParam("c_no") int c_no, @RequestParam("g_no") String g_no) throws Exception {
        gservice.commentdel(c_no);
        return "redirect:grouppost?g_no=" + g_no;
    }
}

