package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_ManagerDAO;
import com.codemaster.project_snsmaster.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class ManagerServiceImpl implements IF_ManagerService{
@Autowired
IF_ManagerDAO mdao;
    Random rand = new Random();
    @Override
    public void insert(String memo) throws Exception {
        mdao.insert(memo);
    }

    @Override
    public List<GroupPostVO> groupreport() throws Exception {
        return mdao.groupreport();
    }

    @Override
    public List<PostVO> postreport() throws Exception {
        return mdao.postreport();
    }

    @Override
    public GroupPostVO selectgroupPost(int g_no) throws Exception {
        return mdao.selectgroupPost(g_no);
    }

    @Override
    public void stopinsert(StopMemberVO stop) throws Exception {
         mdao.stopinsert(stop);
    }

    @Override
    public void delete(int g_no) throws Exception {
        mdao.delete(g_no);
    }

    @Override
    public void delete2(int No) throws Exception {
        mdao.delete2(No);
    }

    @Override
    public PostVO selectpost(int no) throws Exception {
       return mdao.selectpost(no);
    }



    @Override
    public List<PostVO> selectrandom(HashMap<String, String> params) throws Exception {
        return mdao.selectrandom(params);
    }
    @Override
    public List<GroupPostVO> selectrandom2(HashMap<String, String> params) throws Exception {
        return mdao.selectrandom2(params);
    }

    @Override
    public void alter(int no) throws Exception {
        mdao.alter(no);
    }

    @Override
    public List<StopMemberVO> stopMember() throws Exception {
       return mdao.stopMember();
    }

    @Override
    public void stopdelete(String id) throws Exception {
        mdao.stopdelete(id);
    }

    @Override
    public void fordelete(List<String> list) throws Exception {
        System.out.println(list);
        mdao.fordelete(list);
    }

    @Override
    public void fordelete2(List<String> list) throws Exception {
        mdao.fordelete2(list);
    }

    @Override
    public void alter2(int g_no) throws Exception {
        mdao.alter2(g_no);
    }

    @Override
    public void Notification(HashMap<String, Object> data) {
      mdao.Notification(data);
    }

    @Override
    public int statecount(String id) {
     return mdao.stateNotification(id);
    }

    @Override
    public List<NotificationVO> notificationlook(String id) {
      return mdao.notificationlook(id);
    }

    @Override
    public void notifi(String id) {
         mdao.notifi(id);
    }

    @Override
    public List<NoticeVO> notice() {
        return mdao.notice();
    }

    @Override
    public void noticedell(int NO) {
        mdao.noticedell(NO);
    }

    @Override
    public List<StopMemberVO> selectStopmember(HashMap<String, String> params) {
        return mdao.selectstopmember(params);
    }
}
