package com.acsm.training.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MemberContentPRDao;
import com.acsm.training.model.MemberContentPR;
import com.acsm.training.util.DateUtil;

@Repository
public class MemberContentPRDaoImpl extends BaseDaoImpl<MemberContentPR> implements MemberContentPRDao {

    @Override
    public List<MemberContentPR> queryListByBoxAndContentOrderByPr(int boxId,int contentId) {
        String hql = "from MemberBox where boxId="+boxId+" and contentId="+contentId;
        return this.list(hql);
    }

    @Override
    public List<MemberContentPR> queryListByBoxAndSexAndContent(int boxId,int sex,int contentId) {
        String hql = "from MemberContentPR where boxId="+boxId+" and contentId="+contentId+" and sex="+sex+" order by pr desc";
        return this.list(hql);
    }
    
    @Override
    public MemberContentPR queryListByBoxAndMemberAndContent(int boxId,int memberId,int contentId) {
        String hql = "from MemberContentPR where boxId="+boxId+" and memberId="+memberId+" and contentId="+contentId;
        return (MemberContentPR)this.Queryobject(hql);
    }
    
    @Override
    public void updateBysql(float pr,int id) {
    		Object[] args= {pr,new Date(),id};
    		this.UpdateByHql("update MemberContentPR set pr =? , updateTime=? where id=?",args);
    	}
    
}
