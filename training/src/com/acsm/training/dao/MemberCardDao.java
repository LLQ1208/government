package com.acsm.training.dao;

import com.acsm.training.model.MemberCard;

import java.util.List;

/**
 * Created by lq on 2018/1/26.
 */
public interface MemberCardDao extends BaseDao<MemberCard>{
    public MemberCard queryById(int id);

    public List<Object[]> queryGroupList(int memberId,Integer boxId);

    public List<Object[]> queryPrivateList(int memberId,Integer boxId);
    
    List<MemberCard> queryByMemberId(int memberId, int customCardCourseType);
    
    public List<MemberCard> queryByMemberIdByBetweenDate(int memberId);
}
