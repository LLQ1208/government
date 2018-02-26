package com.acsm.training.dao;

import com.acsm.training.model.MemberAnalysis;
import com.acsm.training.model.MemberAnalysis;
import com.acsm.training.model.MemberCard;

import java.util.List;

/**
 * Created by lq on 2018/1/29.
 */
public interface MemberAnalysisDao extends BaseDao<MemberAnalysis>{

    Integer queryGroupCardDays(Integer memberId,Integer boxId);

    Integer queryNowGroupCard(Integer memberId,Integer boxId);

    List<Object> queryFutureGroupCard(Integer memberId);

    Integer queryPrivateCardDays(Integer memberId,Integer boxId);

    Integer queryNowPrivateCard(Integer memberId,Integer boxId);

    List<Object> queryFuturePrivateCard(Integer memberId);

    MemberAnalysis queryByMemberId(int memberId,Integer boxId);


}
