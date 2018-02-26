package com.acsm.training.service;

import com.acsm.training.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by lq on 2018/1/27.
 */
public interface MemberAnalysisService {
    /**
     * 查询所有会员
     * @return
     */
    public List<Member> queryAllMember();

    /**
     * 上次团课时间
     * @param memberId
     * @return
     */
    public List<Object[]> queryGroupCourseDesc(int memberId,Integer boxId);

    /**
     * 查询团课卡所有的天数之和
     * @param memberId
     * @return
     */
    public Integer queryGroupCardDays(int memberId,Integer boxId);

    /**
     * 查询当前会员卡
     * @param memberId
     * @return
     */
    public Integer queryNowGroupCard(int memberId,Integer boxId);

    public MemberCard queryCardById(int cardId);

    public List<CourseOrder> queryNowGroupCourseCount(int memberId,Date beginTime,Integer boxId);

    public List<CoachCourseOrder> queryPrivateCourseOrder(int memberId,Integer boxId);

    public Integer queryPrivateCardAllDays(int memberId,Integer boxId);

    public Integer queryNowPrivateCard(int memberId,Integer boxId);

    public List<CoachCourseOrder> queryNowPrivateCourseOrder(int memberId,Date beginTime,Integer boxId);

    /**
     * 查询上月团课打卡频率
     * @return
     */
    public Double queryGroupLastMonthRate(Date beginTime,Integer memberId,Integer boxId);

    public Double queryGroupThisMonthRate(int memberId,Integer boxId);


    public Double queryPrivateLastWeekRate(int memberId,Date beginTime,Integer boxId);

    public Double queryPrivateThisWeekRate(int memberId,Integer boxId);

    public MemberAnalysis queryByMemberId(int memberId,Integer boxId);

    public void save(MemberAnalysis memberAnalysis);
}
