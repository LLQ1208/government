package com.acsm.training.scheduleds;/**
 * Created by lq on 2018/1/27.
 */

import com.acsm.training.model.*;
import com.acsm.training.service.CoachCourseService;
import com.acsm.training.service.MemberBoxService;
import com.acsm.training.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.acsm.training.service.MemberAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-27
 */
@Component
public class MemberCardScheduled {
    @Autowired
    MemberAnalysisService memberAnalysisService;
    @Autowired
    CoachCourseService coachCourseService;
    @Autowired
    MemberBoxService memberBoxService;


    public void init() {
    }

    public void analysis() throws ClientException, InterruptedException {
        System.out.println("统计会员约课和频率-----------------------------go");
        List<MemberBox> memberList = memberBoxService.queryAll();
        //所有的会员
        for (MemberBox memberBox : memberList) {
            MemberAnalysis memberAnalysis =memberAnalysisService.queryByMemberId(memberBox.getMemberId(),memberBox.getBoxId());
            if(null == memberAnalysis){
                memberAnalysis = new MemberAnalysis();
            }
            memberAnalysis.setMemberId(memberBox.getMemberId());
            memberAnalysis.setBoxId(memberBox.getBoxId());
            /**
             * 团课计算-------begin
             */
            List<Object[]> courseOrderList = memberAnalysisService.queryGroupCourseDesc(memberBox.getMemberId(),memberBox.getBoxId());
           //查询会员上次上团课课时间
            Date lastGroupCourseTime = courseOrderList == null ||  courseOrderList.size() < 1 ? null : (Date)courseOrderList.get(0)[0];
            memberAnalysis.setGroupCourseLastTime(lastGroupCourseTime);
            //数据库存的要展示的东西
            //String groupCourseTimeString = getLastTimeShow(lastGroupCourseTime,courseOrderList == null ? null : courseOrderList.get(0)[1].toString());
            //查询累计约团课次数
            int groupCourseCount = courseOrderList == null ? 0 : courseOrderList.size();
            memberAnalysis.setGroupCourseAllCount(groupCourseCount);
            //团课所有天数
            int groupCardDays = memberAnalysisService.queryGroupCardDays(memberBox.getMemberId(),memberBox.getBoxId());
            //团课预约频率
            double groupCrouseRate = groupCardDays == 0 ? 0 : groupCourseCount / new Double(groupCardDays);
            memberAnalysis.setGroupCourseAllRate(groupCrouseRate);

            //查询当前团课会员卡
            Integer nowGroupCard = memberAnalysisService.queryNowGroupCard(memberBox.getMemberId(),memberBox.getBoxId());
            if(null != nowGroupCard){
                memberAnalysis.setGroupCourseThisCardId(nowGroupCard);
                MemberCard memberCard = memberAnalysisService.queryCardById(nowGroupCard);
                Date beginDate = memberCard.getCardStartTime();
                int days = DateUtil.daysBetween(beginDate, new Date());

                //当前约课次数
                List<CourseOrder> nowCourseOrderList = memberAnalysisService.queryNowGroupCourseCount(memberBox.getMemberId(),beginDate,memberBox.getBoxId());
                int nowCount = 0;
                if(null != nowCourseOrderList){
                    nowCount = nowCourseOrderList.size();
                }
                memberAnalysis.setGroupCourseThisCount(nowCount);
                //当前约课频率
                double nowRate = days == 0 ? 0d : nowCount/new Double(days);
                memberAnalysis.setGroupCourseThisRate(nowRate);
                //查询出勤率变化
                Double changeRate = null;
                //是否这个月开的卡
                boolean isThisMonth = DateUtil.isThisMonth(beginDate.getTime());
                if(!isThisMonth){
                    //查询上月的出勤次数
                    double lastMonthRate = memberAnalysisService.queryGroupLastMonthRate(beginDate,memberBox.getId(),memberBox.getBoxId());
                    double thisMonthRate = memberAnalysisService.queryGroupThisMonthRate(memberBox.getMemberId(),memberBox.getBoxId());
                    //出勤率变化
                    changeRate = lastMonthRate == 0 ? 0:(lastMonthRate - thisMonthRate) / lastMonthRate * 100;
                    memberAnalysis.setGroupCourseChangeRate(changeRate);
                }

            }

            /**
             * 团课计算-------end
             */

            /**
             *私教课---开始
             */
            //查询所有的私教课上课记录
            List<CoachCourseOrder> allPrivateOrderList = memberAnalysisService.queryPrivateCourseOrder(memberBox.getMemberId(),memberBox.getBoxId());
            //查询会员上次上私教课时间
            Date lastPrivateDate = allPrivateOrderList == null || allPrivateOrderList.size() < 1? null : allPrivateOrderList.get(0).getDate();
            memberAnalysis.setPrivateCourseLastTime(lastGroupCourseTime);
            //数据库存的要展示的东西
            String privateCourseTime = "";
            if(null != lastPrivateDate){
                CoachCourse coachCourse =coachCourseService.queryById(allPrivateOrderList.get(0).getCoachCourseId());
                privateCourseTime = coachCourse.getStartTime();
            }
            //String privateCourseTimeString = getLastTimeShow(lastGroupCourseTime,privateCourseTime);
            //累计私教课次数
            int allPrivateCourseCount = allPrivateOrderList == null ? 0: allPrivateOrderList.size();
            memberAnalysis.setPrivateCourseAllCount(allPrivateCourseCount);
            //私教卡所有天数
            int privateCardDays = memberAnalysisService.queryPrivateCardAllDays(memberBox.getMemberId(),memberBox.getBoxId());
            //私教课预约频率
            double privateCrouseRate = privateCardDays == 0 ? 0 :allPrivateCourseCount / new Double(privateCardDays);
            memberAnalysis.setPrivateCourseAllRate(privateCrouseRate);
            //查询当前死角卡
            Integer nowPrivateCard = memberAnalysisService.queryNowPrivateCard(memberBox.getMemberId(),memberBox.getBoxId());
            if(null != nowPrivateCard){
                MemberCard privateCard = memberAnalysisService.queryCardById(nowPrivateCard);
                memberAnalysis.setPrivateCourseThisCardId(nowPrivateCard);
                Date beginDate = privateCard.getCardStartTime();
                int days = DateUtil.daysBetween(beginDate,new Date());
                //当前私教课约课次数
                List<CoachCourseOrder> nowOrderList = memberAnalysisService.queryNowPrivateCourseOrder(memberBox.getId(),beginDate,memberBox.getBoxId());

                int nowCount = 0;
                if(null != nowOrderList){
                    nowCount = nowOrderList.size();
                }
                memberAnalysis.setPrivateCourseThisCount(nowCount);
                //当前约课频率
                double nowRate = days == 0 ? 0 : nowCount/new Double(days);
                memberAnalysis.setPrivateCourseThisRate(nowRate);

                //查询出勤率变化
                Double changeRate = null;
                //是否这周开的卡
                boolean isThisWeek = DateUtil.isThisWeek(beginDate,new Date());
                if(!isThisWeek){
                    //查询上月的出勤次数
                    double lastMonthRate = memberAnalysisService.queryPrivateLastWeekRate(memberBox.getMemberId(), beginDate,memberBox.getBoxId());
                    double thisMonthRate = memberAnalysisService.queryPrivateThisWeekRate(memberBox.getMemberId(),memberBox.getBoxId());
                    //出勤率变化
                    changeRate = lastMonthRate == 0 ? 0:(lastMonthRate - thisMonthRate) / lastMonthRate * 100 ;
                    memberAnalysis.setPrivateCourseChangeRate(changeRate);
                }
            }
            /**
             *私教课---end
             */
            memberAnalysis.setUpdateTime(new Date());
            memberAnalysisService.save(memberAnalysis);
        }

        System.out.println("统计会员约课和频率-----------------------------end");
    }


    private String getLastTimeShow(Date lastTime,String courseTime){
        if(lastTime == null){
            return "never";
        }
        String today = DateUtil.format(new Date(),"yyyy/MM/dd");
        String last = DateUtil.format(lastTime,"yyyy/MM/dd");
        if(today.equals(last)){
            long hour = DateUtil.getDatePoorHour(DateUtil.StringToDate(last + " " + courseTime,"yyyy/MM/dd HH:mm"),new Date());
            return hour+"小时前";
        }else{
            return last;
        }
    }


    public void test(){
        System.out.println("--------------test");
    }
}
