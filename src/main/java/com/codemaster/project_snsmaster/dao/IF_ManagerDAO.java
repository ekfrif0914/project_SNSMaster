package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.*;

import java.util.HashMap;
import java.util.List;

public interface IF_ManagerDAO {

    public void insert(String memo) throws Exception;

    public List<GroupPostVO> groupreport() throws Exception;

    public List<PostVO> postreport() throws Exception;

    public GroupPostVO selectgroupPost(int g_no) throws Exception;

    public void stopinsert(StopMemberVO stop) throws Exception;

    public void delete(int g_no) throws Exception;

   public void delete2(int gNo)throws Exception;

  public PostVO selectpost(int no)throws Exception;

   public List<PostVO> selectrandom(HashMap<String, String> params)throws Exception;

   public List<GroupPostVO> selectrandom2(HashMap<String, String> params)throws Exception;

   public void alter(int no)throws Exception;

   public List<StopMemberVO> stopMember()throws Exception;

    public void stopdelete(String id)throws Exception;

  public  void fordelete(List<String> list)throws Exception;

   public void fordelete2(List<String> list)throws Exception;;

   public void alter2(int g_no)throws Exception;;

   public void Notification(HashMap<String, Object> data);

    public int stateNotification(String id);

   public List<NotificationVO> notificationlook(String id);

   public void notifi(String id);

   public List<NoticeVO> notice();

  public void noticedell(int NO);
}
