package com.codemaster.project_snsmaster.service;

import com.codemaster.project_snsmaster.dao.IF_AdminDAO;
import com.codemaster.project_snsmaster.vo.FAQVO;
import com.codemaster.project_snsmaster.vo.FollowVO;
import com.codemaster.project_snsmaster.vo.MemberVO;
import com.codemaster.project_snsmaster.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements IF_AdminService {

    @Autowired
    private IF_AdminDAO adminDao;

    @Override
    public void insert(MemberVO memberVO) throws Exception {
        adminDao.insert(memberVO);
    }

    @Override
    public int overlappedID(String id) throws Exception {
        int result = adminDao.overlappedID(id);
        return result;
    }

    @Override
    public int overlappedEmail(String email) throws Exception {
        int result = adminDao.overlappedEmail(email);
        return result;
    }

    @Override
    public void updateProfile(String id, String[] file_name) throws Exception {
        String filename = file_name[0];
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("filename", filename);


        adminDao.updateProfile(param);


    }

    @Override
    public MemberVO getMember(String id) throws Exception {
        return adminDao.getMember(id);
    }

    @Override
    public void updateSave(MemberVO memberVO) throws Exception {
        adminDao.updateSave(memberVO);
    }

    @Override
    public void stop(String id) throws Exception {
        adminDao.stop(id);
    }

    @Override
    public void deletePostArray(String postArray) throws Exception {
        adminDao.deletePostArray(postArray);
    }

    @Override
    public void deletegPostArray(String gpostArray) throws Exception {
        adminDao.deletegPostArray(gpostArray);
    }

    @Override
    public void deletegJoinArray(String gjoinArray) throws Exception {
        adminDao.deletegJoinArray(gjoinArray);
    }

    @Override
    public void changeDefaultimg(String id) throws Exception {
        adminDao.changeDefaultimg(id);
    }

    @Override
    public void faqinsert(FAQVO faqvo) throws Exception {
        adminDao.faqinsert(faqvo);
    }

    @Override
    public List<FAQVO> faqselect(PageVO pvo) throws Exception {

        return adminDao.faqselect(pvo);
    }

    @Override
    public FAQVO selectOne(String f_no) throws Exception {
        return adminDao.selectOne(f_no);
    }

    @Override
    public void viewUp(String f_no) throws Exception {
        adminDao.viewUp(f_no);
    }

    @Override
    public int getTotalCount() throws Exception {
        return adminDao.getTotalcount();
    }

    @Override
    public int getSearchTotalCount(HashMap<String, String> param) throws Exception {
        return adminDao.getSearchTotalCount(param);
    }

    @Override
    public List<FAQVO> faqSearchselect(PageVO pvo) throws Exception {
        return adminDao.faqSearchselect(pvo);
    }

    @Override
    public boolean following(FollowVO fvo) throws Exception {
        int isFollowing = adminDao.cntFollowing(fvo);//1이면 이미 팔로우 0이면 팔로우 안함
        if (isFollowing == 1) {
            adminDao.deleteFollowing(fvo);
            return false;
        } else {//(==0) =>팔로우 함
            adminDao.insertFollowing(fvo);
            return true;
        }
    }

    @Override
    public List<String> selectMyFollowinglist(String userid) throws Exception {
        return adminDao.selectMyFollowinglist(userid);
    }

    @Override
    public int myfollowCount(String id) throws Exception {
        return adminDao.myfollowCount(id);
    }

    @Override
    public int myfollowingCount(String id) throws Exception {
        return adminDao.myfollowingCount(id);
    }

    @Override
    public List<String> myfollowList(String id) throws Exception {
        return adminDao.myfollowList(id);
    }

}
