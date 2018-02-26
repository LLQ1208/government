package com.acsm.training.service.impl;/**
 * Created by lq on 2018/1/27.
 */

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.util.DateUtil;
import com.acsm.training.service.MemberAnalysisService;
import com.acsm.training.util.WodUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-27
 */
@Service("memberAnalysisService")
public class MemberAnalysisServiceImpl implements MemberAnalysisService{
    @Autowired
    MemberDao memberDao;
    @Autowired
    CourseOrderDao courseOrderDao;
    @Autowired
    MemberAnalysisDao memberAnalysisDao;
    @Autowired
    MemberCardDao memberCardDao;
    @Autowired
    CoachCourseOrderDao coachCourseOrderDao;

    @Override
    public List<Member> queryAllMember() {
        return memberDao.queryAllMember();
    }

    @Override
    public List<Object[]> queryGroupCourseDesc(int memberId,Integer boxId) {
        List<Object[]> courseOrderList = courseOrderDao.queryLastOrderOfMember(memberId,boxId);
        if(null == courseOrderList || courseOrderList.size() < 1){
            return null;
        }else{
            return courseOrderList;
        }
    }

    @Override
    public Integer queryGroupCardDays(int memberId,Integer boxId) {
        return memberAnalysisDao.queryGroupCardDays(memberId,boxId);
    }

    @Override
    public Integer queryNowGroupCard(int memberId,Integer boxId) {
        return memberAnalysisDao.queryNowGroupCard(memberId,boxId);
    }

    @Override
    public MemberCard queryCardById(int cardId) {
        return memberCardDao.queryById(cardId);
    }

    @Override
    public List<CourseOrder> queryNowGroupCourseCount(int memberId, Date beginTime,Integer boxId) {
        return courseOrderDao.queryNowOrderCount(memberId, DateUtil.format(beginTime, "yyyy-MM-dd"),boxId);
    }

    @Override
    public List<CoachCourseOrder> queryPrivateCourseOrder(int memberId,Integer boxId) {
        return coachCourseOrderDao.queryLastOfMember(memberId,boxId);
    }

    @Override
    public Integer queryPrivateCardAllDays(int memberId,Integer boxId) {
        return memberAnalysisDao.queryPrivateCardDays(memberId,boxId);
    }

    @Override
    public Integer queryNowPrivateCard(int memberId,Integer boxId) {
        return memberAnalysisDao.queryNowPrivateCard(memberId,boxId);
    }

    @Override
    public List<CoachCourseOrder> queryNowPrivateCourseOrder(int memberId, Date beginTime,Integer boxId) {

        return coachCourseOrderDao.queryNowCardCount(memberId,DateUtil.format(beginTime,"yyyy-MM-dd"),boxId);
    }

    @Override
    public Double queryGroupLastMonthRate(Date beginTime, Integer memberId,Integer boxId) {
        String lastMonth = DateUtil.format(DateUtil.getOneMonthBeforeCurrentTime(1), "yyyy-MM");
        List<CourseOrder> lastList = courseOrderDao.queryLastMonthOrder(memberId,DateUtil.format(beginTime,"yyyy-MM-dd"),lastMonth,boxId);
        int lastMonthCount = lastList == null ? 0 : lastList.size();
        Date lastMonthDate = DateUtil.StringToDate(lastMonth+"-01","yyyy-MM-dd");
        int days = 0;
        //开卡时间小于上个月一号
        if(beginTime.getTime() < lastMonthDate.getTime()){
            //获取上月多少天
            days = DateUtil.getMonthLastDay(Integer.valueOf(lastMonth.substring(0,4)),Integer.valueOf(lastMonth.substring(5,7)));
        }else{
            int totalDays = DateUtil.getMonthLastDay(Integer.valueOf(lastMonth.substring(0,4)),Integer.valueOf(lastMonth.substring(5,7)));
            String cardBeginTime = DateUtil.format(beginTime,"yyyy-MM-dd");
            days = totalDays - Integer.valueOf(cardBeginTime.substring(8,10)) + 1;
        }
        if(lastMonthCount == 0){
            return 0d;
        }
        return days / new Double(lastMonthCount);
    }

    @Override
    public Double queryGroupThisMonthRate(int memberId,Integer boxId) {
        String thisMonth = DateUtil.format(new Date(), "yyyy-MM");
        List<CourseOrder> thisList = courseOrderDao.queryThisMonthOrder(memberId, thisMonth,boxId);
        int thisMonthCount = thisList == null ? 0 : thisList.size();
        String now = DateUtil.format(new Date(),"yyyy-MM-dd");
        int days = Integer.valueOf(now.substring(8,10));
        if(thisMonthCount == 0){
            return 0d;
        }
        return days / new Double(thisMonthCount);
    }

    @Override
    public Double queryPrivateLastWeekRate(int memberId,Date beginTime,Integer boxId) {
        Date lastMonday = WodUtil.getLastWeekBeginDate(WodUtil.getDateWeekMonday(new Date()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(lastMonday);
        cal.add(Calendar.DATE, 6);
        Date lastSunday = cal.getTime();
        List<CoachCourseOrder> orderList =  coachCourseOrderDao.querCardCountByTime(memberId,DateUtil.format(lastMonday,"yyyy-MM-dd"),
                DateUtil.format(lastSunday,"yyyy-MM-dd"),boxId);
       int lastWeekCount = orderList == null ? 0 : orderList.size();
        int days = 0;
        if(DateUtil.isThisWeek(beginTime,lastMonday)){
            days = DateUtil.daysBetween(beginTime,lastSunday);
        }else{
            days = 7;
        }
        if(lastWeekCount == 0){
            return 0d;
        }
        return days/new Double(lastWeekCount);
    }

    @Override
    public Double queryPrivateThisWeekRate(int memberId,Integer boxId) {
        Date monday = WodUtil.getDateWeekMonday(new Date());
        List<CoachCourseOrder> orderList =  coachCourseOrderDao.querCardCountByTime(memberId,DateUtil.format(monday,"yyyy-MM-dd"),
                DateUtil.format(new Date(),"yyyy-MM-dd"),boxId);
        int lastWeekCount = orderList == null ? 0 : orderList.size();
        int  days = DateUtil.daysBetween(monday,new Date());
        if(lastWeekCount == 0){
            return 0d;
        }
        return days/new Double(lastWeekCount);
    }

    @Override
    public MemberAnalysis queryByMemberId(int memberId,Integer boxId) {
        return memberAnalysisDao.queryByMemberId(memberId,boxId);
    }

    @Override
    public void save(MemberAnalysis memberAnalysis) {
       if(null != memberAnalysis.getId()){
           memberAnalysisDao.update(memberAnalysis);
       }else{
           memberAnalysisDao.add(memberAnalysis);
       }
    }


}
