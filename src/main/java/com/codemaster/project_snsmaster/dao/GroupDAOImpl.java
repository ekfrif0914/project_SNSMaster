package com.codemaster.project_snsmaster.dao;

import com.codemaster.project_snsmaster.vo.GroupJoinVO;
import com.codemaster.project_snsmaster.vo.GroupPostVO;
import com.codemaster.project_snsmaster.vo.MemberGroupVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDAOImpl implements IF_GroupDAO{

private static final String mapperQuery="com.codemaster.project_snsmaster.dao.IF_GroupDAO";
@Autowired
 SqlSession sqlSession;

    @Override
    public void insert(GroupPostVO gpvo) throws Exception {
        System.out.println("DAO layer Insert method");
        System.out.println(gpvo.toString());
        sqlSession.insert(mapperQuery+".insert",gpvo);
    }

    @Override
    public void mginsert(MemberGroupVO mgvo) throws Exception {
        System.out.println("DAO layer Insert method");
        System.out.println(mgvo.toString());
        sqlSession.insert(mapperQuery+".mginsert",mgvo);
    }

    @Override
    public List<MemberGroupVO> selectAll() throws Exception {
        return sqlSession.selectList(mapperQuery+".select");
    }

    @Override
    public int joinsert(GroupJoinVO gjvo) throws Exception {
        return sqlSession.insert(mapperQuery+".joinsert",gjvo);
    }

    @Override
    public List<GroupPostVO> gpselectAll(String gno) throws Exception {
        return sqlSession.selectList(mapperQuery+".gpselect",gno);
    }

    @Override
    public void delete(int g_no) throws Exception {
        sqlSession.delete(mapperQuery+".delete",g_no);
    }

    @Override
    public void mdelete(int gno) throws Exception {
        sqlSession.delete(mapperQuery+".mdelete",gno);
    }

    @Override
    public GroupPostVO modno(int g_no) throws Exception {

        return sqlSession.selectOne(mapperQuery+".selectone",g_no);
    }

    @Override
    public void modupdate(GroupPostVO gpvo) throws Exception {
        sqlSession.update(mapperQuery+".modupdate",gpvo);
    }


}
