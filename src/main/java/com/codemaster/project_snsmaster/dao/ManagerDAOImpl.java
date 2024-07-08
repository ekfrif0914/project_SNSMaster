package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ManagerDAOImpl implements IF_ManagerDAO {
    private static String mapperQuery="com.codemaster.project_snsmaster.dao.IF_ManagerDAO";
    @Autowired
    SqlSession sqlSession;

    @Override
    public void insert(String memo) throws Exception {
        sqlSession.insert(mapperQuery + ".insert",memo);
    }

    @Override
    public List<GroupPostVO> groupreport() throws Exception {
        return sqlSession.selectList(mapperQuery+".groupPost");
    }

    @Override
    public List<PostVO> postreport() throws Exception {
        return sqlSession.selectList(mapperQuery+".postReport");
    }

    @Override
    public GroupPostVO selectgroupPost(int g_no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectgroupPost",g_no);
    }

    @Override
    public void stopinsert(StopMemberVO stop) throws Exception {
     sqlSession.insert(mapperQuery+".stopinsert",stop);
    }

    @Override
    public void delete(int g_no) throws Exception {
      sqlSession.delete(mapperQuery+".delete",g_no);
    }

    @Override
    public void delete2(int gNo) throws Exception {
        sqlSession.delete(mapperQuery+".delete2",gNo);
    }

    @Override
    public PostVO selectpost(int no) throws Exception {
        return sqlSession.selectOne(mapperQuery+".selectpost",no);
    }

    @Override
    public List<PostVO> selectrandom(HashMap<String, String> params)throws Exception {
        return  sqlSession.selectList(mapperQuery+".selectrandom",params);
    }

    @Override
    public List<GroupPostVO> selectrandom2(HashMap<String, String> params)throws Exception {
        return sqlSession.selectList(mapperQuery+".selectrandom2",params);
    }

    @Override
    public void alter(int no) throws Exception {
        sqlSession.update(mapperQuery+".alter",no);
    }

    @Override
    public List<StopMemberVO> stopMember() throws Exception {
        return sqlSession.selectList(mapperQuery+".stopMember");
    }

    @Override
    public void stopdelete(String id) throws Exception {
        sqlSession.delete(mapperQuery + ".deletestop", id);
    }

    @Override
    public void fordelete(List<String> list) throws Exception {
        sqlSession.delete(mapperQuery + ".fordelete",list);
    }

    @Override
    public void fordelete2(List<String> list) throws Exception {
        sqlSession.delete(mapperQuery+".fordelete2",list);
    }

    @Override
    public void alter2(int g_no) throws Exception  {
        sqlSession.update(mapperQuery+".alter2",g_no);
    }

    @Override
    public void Notification(HashMap<String, Object> data) {
        sqlSession.insert(mapperQuery+".Notification",data);
    }

    @Override
    public int stateNotification(String id) {
      return  sqlSession.selectOne(mapperQuery+".stateNotification",id);
    }

    @Override
    public List<NotificationVO> notificationlook(String id) {
        return sqlSession.selectList(mapperQuery+".notificationlook",id);
    }

    @Override
    public void notifi(String id) {
        sqlSession.update(mapperQuery+".notifi",id);
    }

    @Override
    public List<NoticeVO> notice() {
        return sqlSession.selectList(mapperQuery+".notice");
    }

    @Override
    public void noticedell(int NO) {
        sqlSession.delete(mapperQuery+".noticedell",NO);
    }

    @Override
    public List<StopMemberVO> selectstopmember(HashMap<String, String> params) {
        return sqlSession.selectList(mapperQuery+".searchstopmember",params);
    }
}
