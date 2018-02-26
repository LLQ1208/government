package com.acsm.training.service.impl;/**
 * Created by lq on 2018/1/18.
 */

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.MemberModel;
import com.acsm.training.service.CoachCourseService;
import com.acsm.training.service.MemberAnalysisService;
import com.acsm.training.service.MemberNewService;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.UserRegisterValidateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.enums.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-18
 */
@Service
public class MemberNewServiceImpl implements MemberNewService {
    @Autowired
    MemberCardTemplateDao memberCardTemplateDao;

    @Autowired
    MemberDao memberDao;

    @Autowired
    MemberBoxDao memberBoxDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CourseOrderDao courseOrderDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    CoachCourseOrderDao coachCourseOrderDao;

    @Autowired
    CoachCourseDao coachCourseDao;

    @Autowired
    MemberCardDao memberCardDao;

    @Autowired
    MemberAnalysisDao memberAnalysisDao;

    @Autowired
    CoachDao coachDao;
    @Autowired
    EmployeeNewDao employeeNewDao;


    @Autowired
    MemberRenewHistoryDao memberRenewHistoryDao;

    @Autowired
    MemberAnalysisService memberAnalysisService;

    @Autowired
    CoachCourseService coachCourseService;

    @Override
    public MemberCardTemplate queryMemberCardTemplateById(int id) {
        return memberCardTemplateDao.queryById(id);
    }

    @Override
    public void addMemberCardTemplate(MemberCardTemplate template,User user) {
        template.setInsertUser(user.getId());
        template.setIsDeleted(0);
        template.setInsertTime(new Date());
        memberCardTemplateDao.add(template);
    }

    @Override
    public void updateMemberCardTemplate(MemberCardTemplate template) {
        memberCardTemplateDao.update(template);
    }

    @Override
    public PageHelper<MemberCardTemplate> queryByPage(int userId, int pageIndex, int pageSize) {
        PageHelper pageHelper = memberCardTemplateDao.queryByPage(userId, pageSize, pageIndex);
        List<Object[]> tempLateList = pageHelper.getList();
        List<MemberCardTemplate> list = new ArrayList<>();
        if(null != tempLateList && tempLateList.size() > 0){
            for(Object[] obj : tempLateList){
                MemberCardTemplate template = new MemberCardTemplate();
                template.setId(Integer.valueOf(obj[0].toString()));
                template.setTemplateName(obj[1].toString());
                template.setTemplateType(Integer.valueOf(obj[2].toString()));
                template.setMoney(Integer.valueOf(obj[3].toString()));
                template.setCourseTypeIds(obj[4] == null ? "" : obj[4].toString());
                template.setBoutCardType(obj[5]==null || "".equals(obj[5].toString()) ? null:Integer.valueOf(obj[5].toString()));
                list.add(template);
            }
        }
        PageHelper<MemberCardTemplate> returnTemplate = new PageHelper<>();
        returnTemplate.setList(list);
        returnTemplate.setTotalRow(pageHelper.getTotalRow());
        returnTemplate.setPageSize(pageSize);
        returnTemplate.setNowPageIndex(pageIndex);
        return returnTemplate;
    }

    @Override
    public List<MemberCardTemplate> queryTemplateList(int userId) {
        List<MemberCardTemplate> templateList = new ArrayList<>();
        List<Object[]> objectsList = memberCardTemplateDao.queryList(userId);
        if(null != objectsList && objectsList.size() > 0){
            for(Object[] obj : objectsList){
                MemberCardTemplate template = new MemberCardTemplate();
                template.setId(Integer.valueOf(obj[0].toString()));
                template.setTemplateType(Integer.valueOf(obj[2].toString()));
                template.setTemplateName(obj[1].toString());
                template.setMoney(Integer.valueOf(obj[3].toString()));
                template.setCourseTypeNames(obj[4] == null ? "私教" : obj[4].toString());
                templateList.add(template);
            }
        }
        return templateList;
    }

    @Override
    public JSONObject memberAddCheck(String phone, String memberName,Integer boxId, Integer templateId, Integer customType, String beginTime, String endTime) {
        JSONObject resultJson = new JSONObject();
        List<Object[]> memberList = memberDao.queryMemberByPhont(phone);
        if(null == memberList || memberList.size() < 1){
            resultJson.put("status","0");
        }else{
            resultJson.put("status","1");
        }
        return resultJson;
    }

    @Override
    public JSONObject memberExtendCheck(Integer memberId,String phone, String memberName, Integer boxId,
                                        Integer templateId, Integer customType, String beginTime, String endTime,Integer boutType) {
        JSONObject resultJson = new JSONObject();
        Member member = memberDao.queryById(memberId);
        Date cardStartDate = DateUtil.StringToDate(beginTime, "yyyy-MM-dd");
        Date cardEndDate = null;
        int cardType = 0;
        //如果是添加自定义
        if(templateId == 0){
            if(customType == 2){
                if(boutType == 1){
                    cardType = 1;
                }else if(boutType == 2){
                    cardType = 2;
                }
            }else if(customType == 1){
                cardType = 1;
            }
            cardEndDate = DateUtil.StringToDate(endTime, "yyyy-MM-dd");;
        }else{
            MemberCardTemplate template = memberCardTemplateDao.queryById(templateId);
            if(template.getTemplateType() == 1){
                cardType = 1;
            }else{
                if(template.getBoutCardType() == 1){
                    cardType = 1;
                }else{
                    cardType = 2;
                }
            }
            cardEndDate = DateUtil.getEndTime(cardStartDate,template.getValidity());
        }

        //查询是否当前有有效的卡
        if(cardType == 1){
            Integer nowGroupCard = memberAnalysisDao.queryNowGroupCard(member.getId(),boxId);
            if(null == nowGroupCard){
                resultJson.put("status","success");
                resultJson.put("mesg","成功");
            }else{
               MemberCard memberCard = memberCardDao.queryById(nowGroupCard);
               if(cardStartDate.getTime() < memberCard.getCardEndTime().getTime()){
                   resultJson.put("status","fail");
                   resultJson.put("mesg","请调整时间至" + DateUtil.DateToString(memberCard.getCardEndTime(),"yyyy-MM-dd") + "之后");
               }
            }
           //查询未来的
            if("success".equals(resultJson.get("status"))){
                List<Object> futureList = memberAnalysisDao.queryFutureGroupCard(memberId);
                if(null != futureList && futureList.size() > 0){
                    for(Object obj : futureList){
                        MemberCard future = memberCardDao.queryById(Integer.valueOf(obj.toString()));
                        if((future.getCardStartTime().getTime() < cardStartDate.getTime() && cardStartDate.getTime() < future.getCardEndTime().getTime())
                                || (future.getCardStartTime().getTime() < cardEndDate.getTime() && cardEndDate.getTime() < future.getCardEndTime().getTime())){
                            resultJson.put("status","fail");
                            resultJson.put("mesg","和某一张未来的卡时间冲突");
                        }
                    }
                }
            }
        }else if(cardType == 2){
            Integer nowPrivateCard = memberAnalysisDao.queryNowPrivateCard(member.getId(),boxId);
            if(null == nowPrivateCard){
                resultJson.put("status","success");
                resultJson.put("mesg","成功");
            }else{
                MemberCard memberCard = memberCardDao.queryById(nowPrivateCard);
                if(cardStartDate.getTime() < memberCard.getCardEndTime().getTime()){
                    resultJson.put("status","fail");
                    resultJson.put("mesg","请调整时间至" + DateUtil.DateToString(memberCard.getCardEndTime(),"yyyy-MM-dd") + "之后");
                }
            }
            //查询未来的
            if("success".equals(resultJson.get("status"))){
                List<Object> futureList = memberAnalysisDao.queryFuturePrivateCard(memberId);
                if(null != futureList && futureList.size() > 0){
                    for(Object obj : futureList){
                        MemberCard future = memberCardDao.queryById(Integer.valueOf(obj.toString()));
                        if((future.getCardStartTime().getTime() < cardStartDate.getTime() && cardStartDate.getTime() < future.getCardEndTime().getTime())
                                || (future.getCardStartTime().getTime() < cardEndDate.getTime() && cardEndDate.getTime() < future.getCardEndTime().getTime())){
                            resultJson.put("status","fail");
                            resultJson.put("mesg","和某一张未来的卡时间冲突");
                        }
                    }
                }
            }
        }
        return resultJson;
    }

    @Override
    public void addMemberCustomTime(Member member,MemberCard memberCard, String beginTime, String endTime, Integer expireDay, String customCourseTypeIds,Integer money) {
        boolean isAddFlag = true;
        member.setCreateTime(new Date());
        member.setMemberType(1);
        if(null == member.getId()){
            memberDao.add(member);
        }else{
            memberDao.update(member);
            isAddFlag = false;
        }
        //会员卡
        memberCard.setMemerId(member.getId());
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        memberCard.setCardEndTime(DateUtil.StringToDate(endTime, "yyyy-MM-dd"));
        memberCard.setCustomCardType(1);
        memberCard.setCardExpireDay(expireDay);
        memberCard.setCustomCourseTypeIds(customCourseTypeIds);
        memberCard.setMoney(money);
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);

        //member_box
        MemberBox memberBox = new MemberBox();
        memberBox.setBoxId(member.getBoxId());
        memberBox.setMemberId(member.getId());
        memberBox.setCourseTypeIds(customCourseTypeIds);
        memberBox.setCreateTime(new Date());
        memberBox.setMemberType(1);
        memberBox.setIsMain(1);
        memberBox.setIsActive(1);
        memberBox.setMemberStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        Date memberEndTime = DateUtil.StringToDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        memberBox.setMemberEndTime(memberEndTime);
        memberBoxDao.add(memberBox);

        if(isAddFlag){
            //添加用户
            //添加用户信息
            User user = new User();
            user.setUserName(member.getPhone());
            user.setPassword(UserRegisterValidateUtil.encodePassword("88888888", "SHA"));
            user.setRelatedId(member.getId());
            user.setUserType(UserType.MEMBER.CODE);
            user.setIsDeleted(0);
            userDao.add(user);
        }


        //分析
        MemberAnalysis analysis = new MemberAnalysis();
        analysis.setBoxId(member.getBoxId());
        analysis.setMemberId(member.getId());
        analysis.setGroupCourseThisCardId(memberCard.getId());
        analysis.setGroupCourseAllCount(0);
        analysis.setGroupCourseAllRate(0d);
        analysis.setGroupCourseThisCount(0);
        analysis.setGroupCourseThisRate(0d);
        memberAnalysisDao.add(analysis);
    }

    @Override
    public void addMemberCustomCount(Member member, MemberCard memberCard, String beginTime, String endTime, Integer expireDay, Integer countNum,
                                     Integer countType,String courseTypeIds,Integer money) {
        boolean isAddFlag = true;
        member.setCreateTime(new Date());
        member.setMemberType(2);
        if(null == member.getId()){
            memberDao.add(member);
        }else{
            memberDao.update(member);
            isAddFlag = false;
        }



        //会员卡
        memberCard.setMemerId(member.getId());
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        memberCard.setCardEndTime(DateUtil.StringToDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        memberCard.setCustomCardType(2);
        memberCard.setCustomCardCourseType(countType);
        if(countType == 1){
            memberCard.setCustomCourseTypeIds(courseTypeIds);
        }
        memberCard.setMoney(money);
        memberCard.setCardExpireDay(expireDay);
        memberCard.setCardTotalCount(countNum);
        memberCard.setRemainNum(countNum);
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);

        //member_box
        MemberBox memberBox = new MemberBox();
        memberBox.setBoxId(member.getBoxId());
        memberBox.setMemberId(member.getId());
        memberBox.setCreateTime(new Date());
        memberBox.setMemberType(2);
        memberBox.setIsMain(1);
        memberBox.setIsActive(1);
        memberBox.setMemberRemainNum(countNum);
        memberBox.setMemberStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        Date memberEndTime = DateUtil.StringToDate(endTime, "yyyy-MM-dd");
        memberBox.setMemberEndTime(memberEndTime);
        memberBoxDao.add(memberBox);

        if(isAddFlag){
            //添加用户
            //添加用户信息
            User user = new User();
            user.setUserName(member.getPhone());
            user.setPassword(UserRegisterValidateUtil.encodePassword("88888888", "SHA"));
            user.setRelatedId(member.getId());
            user.setUserType(UserType.MEMBER.CODE);
            user.setIsDeleted(0);
            userDao.add(user);
        }


        //分析
        MemberAnalysis analysis = new MemberAnalysis();
        analysis.setMemberId(member.getId());
        analysis.setBoxId(member.getBoxId());
        if(countType == 1){
            analysis.setGroupCourseThisCardId(memberCard.getId());
            analysis.setGroupCourseAllCount(0);
            analysis.setGroupCourseAllRate(0d);
            analysis.setGroupCourseThisCount(0);
            analysis.setGroupCourseThisRate(0d);
        }else{
            analysis.setPrivateCourseThisCardId(memberCard.getId());
            analysis.setPrivateCourseAllCount(0);
            analysis.setPrivateCourseAllRate(0d);
            analysis.setPrivateCourseThisCount(0);
            analysis.setPrivateCourseThisRate(0d);
        }
        memberAnalysisDao.add(analysis);
    }

    @Override
    public void addMemberGeneral(Member member,MemberCard memberCard, String beginTime, Integer money) {
        boolean isAddFlag = true;
        MemberCardTemplate template = memberCardTemplateDao.queryById(memberCard.getMemberCardTemplateId());
        member.setCreateTime(new Date());
        if(null == member.getId()){
            memberDao.add(member);
        }else{
            memberDao.update(member);
            isAddFlag = false;
        }

        memberCard.setMemerId(member.getId());
        memberCard.setMoney(money);
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        Date tempDate = DateUtil.getDate(memberCard.getCardStartTime(),template.getValidity());
        memberCard.setCardEndTime(DateUtil.StringToDate(DateUtil.format(tempDate,"yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        if(template.getTemplateType() == 2){
            memberCard.setCardTotalCount(template.getBoutCount());
            memberCard.setRemainNum(template.getBoutCount());
        }
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);


        //member_box
        MemberBox memberBox = new MemberBox();
        memberBox.setBoxId(member.getBoxId());
        memberBox.setMemberId(member.getId());
        memberBox.setCreateTime(new Date());
        memberBox.setMemberType(template.getTemplateType());
        memberBox.setIsMain(1);
        memberBox.setIsActive(1);
        memberBox.setMemberStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        memberBoxDao.add(memberBox);

        if(isAddFlag){
            //添加用户
            User user = new User();
            user.setUserName(member.getPhone());
            user.setPassword(UserRegisterValidateUtil.encodePassword("88888888", "SHA"));
            user.setRelatedId(member.getId());
            user.setUserType(UserType.MEMBER.CODE);
            user.setIsDeleted(0);
            userDao.add(user);
        }


        //分析
        MemberAnalysis analysis = new MemberAnalysis();
        analysis.setBoxId(member.getBoxId());
        analysis.setMemberId(member.getId());
        if(template.getTemplateType() == 1 ){
            analysis.setGroupCourseThisCardId(memberCard.getId());
            analysis.setGroupCourseAllCount(0);
            analysis.setGroupCourseAllRate(0d);
            analysis.setGroupCourseThisCount(0);
            analysis.setGroupCourseThisRate(0d);
        }else {
            if(template.getBoutCardType() == 1){
                analysis.setGroupCourseThisCardId(memberCard.getId());
                analysis.setGroupCourseAllCount(0);
                analysis.setGroupCourseAllRate(0d);
                analysis.setGroupCourseThisCount(0);
                analysis.setGroupCourseThisRate(0d);
            }else{
                analysis.setPrivateCourseThisCardId(memberCard.getId());
                analysis.setPrivateCourseAllCount(0);
                analysis.setPrivateCourseAllRate(0d);
                analysis.setPrivateCourseThisCount(0);
                analysis.setPrivateCourseThisRate(0d);
            }
        }
        memberAnalysisDao.add(analysis);
    }

    @Override
    public PageHelper<MemberModel> queryMemberList(String boxIds,Integer boxId,String search,Integer cardType,Integer timeType,
                                                   Integer sex, Integer templateId,Integer orderType,Integer orderDesc,Integer pageIndex,
                                                   Integer pageSize,String memberCardIds) {
        PageHelper<MemberModel> result = new PageHelper<>();
        PageHelper pageHelper = null;
        if(cardType == 1){//团课卡
             pageHelper = memberDao.queryGroupMemberList(boxIds, boxId, search, timeType,
                    sex, templateId, orderType,orderDesc, pageIndex, pageSize,memberCardIds);
        }else{//私教卡
            pageHelper = memberDao.queryPrivateMemberList(boxIds, boxId, search, timeType,
                    sex, templateId, orderType, orderDesc, pageIndex, pageSize,memberCardIds);
        }
        List<Object[]> objectList = pageHelper.getList();
        List<MemberModel> memberModelList = new ArrayList<>();
        if(null != objectList && objectList.size() > 0){
            for(Object[] obj : objectList){
                MemberModel member = new MemberModel();
                member.setMemberId(Integer.valueOf(obj[0].toString()));
                member.setMemberName(obj[1].toString());
                member.setMemberCardId(Integer.valueOf(obj[23].toString()));
                member.setBox(Integer.valueOf(obj[24].toString()));
                if(null == obj[2]){
                    member.setSexStr("...");
                }else if("0".equals(obj[2].toString())){
                    member.setSexStr("男");
                }else if("1".equals(obj[2].toString())){
                    member.setSexStr("女");
                }
                if(null == obj[3]){
                    member.setBirthday("...");
                }else{
                    member.setBirthday(DateUtil.DateToString((Date)obj[3],"yyyy/MM/dd"));
                }
                if(null == obj[4]){
                    member.setPhone("...");
                }else{
                    member.setPhone(obj[4].toString());
                }
                if(null == obj[5]){
                    member.setEmail("...");
                }else{
                    member.setEmail(obj[5].toString());
                }
                //初次办卡时间
                if(null == obj[6]){
                    member.setFirstOpenCardTime("...");
                }else{
                    member.setFirstOpenCardTime(DateUtil.DateToString((Date)obj[6],"yyyy/MM/dd"));
                }
                //续卡时间
                if(null == obj[7]){
                    member.setContinueCardTime("...");
                }else{
                    member.setContinueCardTime(DateUtil.DateToString((Date) obj[7], "yyyy/MM/dd"));
                }
                //到期时间
                if(null == obj[8]){
                    member.setCardEndTime("...");
                }else{
                    member.setCardEndTime(DateUtil.DateToString((Date) obj[8],"yyyy/MM/dd"));
                }
                //卡模板名字
                if(obj[9] == null ){
                    member.setCardTemplateName("...");
                    member.setMemberCardType("...");
                    member.setTotalCount("...");
                }else{
                    int cardTemplateId = Integer.valueOf(obj[9].toString());
                    if(0 == cardTemplateId){
                        member.setCardTemplateName("自定义");
                        int customType = Integer.valueOf(obj[11].toString());
                        if(customType == 1){
                            member.setMemberCardType("时限卡");
                            member.setTotalCount("无限制");
                        }else{
                            member.setMemberCardType("次卡");
                            member.setTotalCount(obj[22].toString());
                        }
                    }else{
                        member.setCardTemplateName(obj[10].toString());
                        int templateType = Integer.valueOf(obj[12].toString());
                        if(templateType == 1){
                            member.setMemberCardType("时限卡");
                            member.setTotalCount("无限制");
                        }else{
                            member.setMemberCardType("次卡");
                            member.setTotalCount(obj[22].toString());
                        }
                    }
                }
                //判断是否过期
                member.setCardStatus(getEffectShow(obj[7], obj[8], obj[9], obj[20],obj[21],obj[11],obj[22],obj[12]));
                member.setLastCourseTime(obj[14] == null ? "..." : DateUtil.DateToString((Date) obj[14], "yyyy/MM/dd"));
                member.setOrderCourseCount(obj[15] == null ? "0" : obj[15].toString());
                member.setOrderCourseRate(obj[16] == null ? "0" : obj[16].toString());
                member.setThisCardOrderCourseCount(obj[17] == null ? "NA" : obj[17].toString());
                member.setThisCardOrderCourseRate(obj[18] == null ? "NA" : obj[18].toString());
                member.setAttendanceRate(obj[19] == null ? "NA" : obj[19].toString());
                memberModelList.add(member);
            }
        }
        result.setList(memberModelList);
        result.setTotalRow(pageHelper.getTotalRow());
        if(pageSize != null && pageIndex != null){
            result.setPageSize(pageSize);
            result.setNowPageIndex(pageIndex);
        }
        return result;
    }

    @Override
    public void updateMemberEndDate(String cardIds, Integer day) {
        String[] cardIdArr = cardIds.split(",");
        for(String cardIdStr : cardIdArr){
            int cardId = Integer.valueOf(cardIdStr);
            MemberCard card = memberCardDao.queryById(cardId);
            //记录
            MemberRenewHistory memberRenewHistory = new MemberRenewHistory();
            memberRenewHistory.setAddDays(day);
            memberRenewHistory.setMemberId(card.getMemerId());
            memberRenewHistory.setNewStartTime(card.getCardStartTime());
            memberRenewHistory.setOldEndTime(card.getCardEndTime());
            memberRenewHistory.setOldStartTime(card.getCardStartTime());
            memberRenewHistory.setAddDaysTime(new Date());

            //更新
            Date oldEndTime = card.getCardEndTime();
            Date endTime = DateUtil.getDate(oldEndTime,day);
            card.setCardEndTime(endTime);
            memberCardDao.update(card);


            memberRenewHistory.setNewEndTime(endTime);
            memberRenewHistoryDao.add(memberRenewHistory);
        }
    }

    @Override
    public MemberAnalysis queryAnalysisByMemberId(int memberId,Integer boxId) {
        return memberAnalysisDao.queryByMemberId(memberId,boxId);
    }

    @Override
    public MemberCard queryCardById(int cardId) {
        return memberCardDao.queryById(cardId);
    }

    @Override
    public void addCardExtendTime(Member member, MemberCard memberCard, String beginTime, String endTime, Integer expireDay, String customCourseTypeIds, Integer money) {
       memberDao.update(member);
        //会员卡
        memberCard.setMemerId(member.getId());
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        memberCard.setCardEndTime(DateUtil.StringToDate(endTime, "yyyy-MM-dd"));
        memberCard.setCustomCardType(1);
        memberCard.setCardExpireDay(expireDay);
        memberCard.setCustomCourseTypeIds(customCourseTypeIds);
        memberCard.setMoney(money);
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);

    }

    @Override
    public void addCardExtendCount(Member member, MemberCard memberCard, String beginTime, String endTime, Integer expireDay, Integer countNum, Integer countType, String courseTypeIds, Integer money) {
        memberDao.update(member);
        //会员卡
        memberCard.setMemerId(member.getId());
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        memberCard.setCardEndTime(DateUtil.StringToDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        memberCard.setCustomCardType(2);
        memberCard.setCustomCardCourseType(countType);
        if(countType == 1){
            memberCard.setCustomCourseTypeIds(courseTypeIds);
        }
        memberCard.setMoney(money);
        memberCard.setCardExpireDay(expireDay);
        memberCard.setCardTotalCount(countNum);
        memberCard.setRemainNum(countNum);
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);

    }

    @Override
    public void addCardExtendGeneral(Member member, MemberCard memberCard, String beginTime, Integer money) {
        MemberCardTemplate template = memberCardTemplateDao.queryById(memberCard.getMemberCardTemplateId());
        memberDao.update(member);

        memberCard.setMemerId(member.getId());
        memberCard.setMoney(money);
        memberCard.setCardStartTime(DateUtil.StringToDate(beginTime, "yyyy-MM-dd"));
        Date tempDate = DateUtil.getDate(memberCard.getCardStartTime(),template.getValidity());
        memberCard.setCardEndTime(DateUtil.StringToDate(DateUtil.format(tempDate,"yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
        if(template.getTemplateType() == 2){
            memberCard.setCardTotalCount(template.getBoutCount());
            memberCard.setRemainNum(template.getBoutCount());
        }
        memberCard.setStatus(0);
        memberCard.setCreateTime(new Date());
        memberCardDao.add(memberCard);

    }


    @Override
    public List<MemberModel> queryCardList(int memberId,int type,Integer boxId) {
        List<MemberModel> resultList = new ArrayList<>();

        List<Object[]> cardList = null;
        if(type == 1){//团课
            cardList = memberCardDao.queryGroupList(memberId,boxId);
        }else{
            cardList = memberCardDao.queryPrivateList(memberId,boxId);
        }
       if(null != cardList && cardList.size() > 0){
           for(Object[] obj : cardList){
               MemberModel card = new MemberModel();
               Integer templateId = Integer.valueOf(obj[1].toString());
               //卡名字
               if(templateId == 0){
                   card.setCardTemplateName("自定义");
                   int customType = Integer.valueOf(obj[5].toString());
                   if(customType == 1 || (customType == 2 && Integer.valueOf(obj[6].toString()) == 1)){
                       card.setCanOrderCourseType(obj[10].toString());
                   }else{
                       card.setCanOrderCourseType("私教");
                   }
               }else{
                    card.setCardTemplateName(obj[12].toString());
                   int templateType = Integer.valueOf(obj[7].toString());
                   if(templateType == 1 || (templateType == 2 && Integer.valueOf(obj[8].toString()) == 1)){
                       card.setCanOrderCourseType(obj[11].toString());
                   }else{
                       card.setCanOrderCourseType("私教");
                   }
               }
               card.setGeneralMoney(obj[2] == null ? "0" : obj[2].toString());
               card.setGeneralBeginTime(DateUtil.DateToString((Date) obj[3], "yyyy/MM/dd"));
               card.setCardEndTime(DateUtil.DateToString((Date) obj[4], "yyyy/MM/dd"));
               card.setCardStatus(getEffectShow(obj[3],obj[4],obj[1],obj[13],obj[14],obj[5],obj[9],obj[7]));
               Integer insertUserId = Integer.valueOf(obj[15].toString());
               User insertUser = userDao.queryById(insertUserId);
               if(insertUser.getUserType() == UserType.BOSS.getCODE()){
                   card.setDealName(insertUser.getUserName());
               }
               if(insertUser.getUserType() == UserType.COACH.getCODE()){
                   Coach coach = coachDao.queryById(insertUser.getRelatedId());
                   card.setDealName(coach.getName());
               }
               if(insertUser.getUserType() == UserType.EMPLOYEE.getCODE()){
                   EmployeeNew emp = employeeNewDao.queryById(insertUser.getRelatedId());
                   card.setDealName(emp.getName());
               }
                //todo---事件
               List<MemberRenewHistory> historyList = memberRenewHistoryDao.queryListByCardId(Integer.valueOf(obj[0].toString()));
               List<String> eventList = new ArrayList<>();
               if(null != historyList && historyList.size() > 0){
                   int i = 0;
                   for(MemberRenewHistory history : historyList){
                       if(i < 2){
                           if(history.getType() == null ){
                               eventList.add(DateUtil.DateToString(history.getAddDaysTime(),"yyyy/MM/dd")+"延长" + history.getAddDays() + "天");
                           }else if(history.getType() == 2){
                               eventList.add(DateUtil.DateToString(history.getStopCardTime(),"yyyy/MM/dd")+"停卡");
                           }else if(history.getType() == 3){
                               eventList.add(DateUtil.DateToString(history.getStopCardTime(),"yyyy/MM/dd")+"-" +
                                       DateUtil.DateToString(history.getOpenCardTime(),"yyyy/MM/dd") + "停卡");
                           }
                       }
                       i ++ ;
                   }
               }
               card.setEventList(eventList);
               card.setStatus(obj[16] == null ? null : Integer.valueOf(obj[16].toString()));
               resultList.add(card);
           }
       }
        return resultList;
    }

    @Override
    public void updateMemberCardStatus(MemberCard card) {

        if(card.getStatus() == 1){
            MemberRenewHistory memberRenewHistory = new MemberRenewHistory();
            memberRenewHistory.setMemberId(card.getMemerId());
            memberRenewHistory.setNewStartTime(new Date());
            memberRenewHistory.setOldEndTime(card.getCardEndTime());
            memberRenewHistory.setOldStartTime(card.getCardStartTime());
            memberRenewHistory.setStopCardTime(new Date());
            memberRenewHistory.setType(2);
            memberRenewHistory.setMemberCardId(card.getId());
            memberRenewHistoryDao.add(memberRenewHistory);
        }else{
            List<MemberRenewHistory> historyList = memberRenewHistoryDao.queryStopCardList(card.getId());
            MemberRenewHistory memberRenewHistory = historyList.get(0);
            memberRenewHistory.setOpenCardTime(new Date());
            memberRenewHistory.setType(3);
            //查询停卡时间
            /*Date lastTopTime = historyList.get(0).getStopCardTime();
            String now = DateUtil.format(new Date(),"yyyy-MM-dd");
            String last = DateUtil.format(lastTopTime,"yyyy-MM-dd");
            if(!last.equals(now)){
                int day = daysBetween(lastTopTime,new Date());
               card.setCardEndTime(DateUtil.getDate(card.getCardEndTime(),day));
            }*/
            memberRenewHistoryDao.update(memberRenewHistory);
        }

        memberCardDao.update(card);
    }

    @Override
    public boolean queryTemplateByName(String name, Integer userId,Integer templateId) {
        boolean exist = false;
        List<MemberCardTemplate> templateList = memberCardTemplateDao.queryListByName(name,userId,templateId);
        if(templateList!= null && templateList.size() > 0){
            exist = true;
        }
        return exist;
    }

    @Override
    public List<Member> queryListByPhoneAndNullBox(int boxId, String phone) {
        return memberDao.queryListByPhoneAndNullBox(boxId,phone);
    }



    private int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        double day = new Double(1000 * 3600 * 24);
        double between_days = (time2 - time1) / day;
        return new Double(Math.ceil(between_days)).intValue();
    }
    /**
     * 判断过期的展示
     * @param startTime
     * @param endTime
     * @param templateId
     * @param customExpiredDay
     * @param templateDay
     * @return
     */
    private String getEffectShow(Object startTime,Object endTime,Object templateId,Object customExpiredDay,
                                 Object templateDay,Object customCardType,Object remainNum,Object templateType){
       String result = "";
        if(null == startTime || null == endTime || null == templateId){
           return "...";
       }
       Date beginDate = (Date)startTime;
       Date endDate = (Date)endTime;
        Date now = new Date();
        if(beginDate.getTime() <= now.getTime() && now.getTime() <= endDate.getTime()){
            if(0 == Integer.valueOf(templateId.toString())){
                if(1== Integer.valueOf(customCardType.toString())){
                    if(DateUtil.daysBetween(now,endDate) < Integer.valueOf(customExpiredDay.toString())){
                        result = "即将过期";
                    }else{
                        result = "有效";
                    }
                }else{
                    if(DateUtil.daysBetween(now,endDate) < Integer.valueOf(customExpiredDay.toString()) || Integer.valueOf(remainNum.toString()) < 5){
                        result = "即将过期";
                    }else{
                        result = "有效";
                    }
                }
            }else{
                if(1 == Integer.valueOf(templateType.toString())){
                    if(DateUtil.daysBetween(now,endDate) < Integer.valueOf(templateDay.toString())){
                        result = "即将过期";
                    }else{
                        result = "有效";
                    }
                }else{
                    if(DateUtil.daysBetween(now,endDate) < Integer.valueOf(templateDay.toString()) || Integer.valueOf(remainNum.toString()) < 5){
                        result = "即将过期";
                    }else{
                        result = "有效";
                    }
                }
            }
        }else if(beginDate.getTime() > now.getTime() && now.getTime() < endDate.getTime()){
            result = "未开始";
        }else{
                result = "已过期";
        }
       return result;
    }


    public void updateAnalysisCard(Member member,Integer boxId) {
        MemberAnalysis memberAnalysis = memberAnalysisService.queryByMemberId(member.getId(),boxId);
        if (null == memberAnalysis) {
            memberAnalysis = new MemberAnalysis();
        }
        memberAnalysis.setMemberId(member.getId());
        memberAnalysis.setBoxId(boxId);
        /**
         * 团课计算-------begin
         */
        List<Object[]> courseOrderList = memberAnalysisService.queryGroupCourseDesc(member.getId(),boxId);
        //查询会员上次上团课课时间
        Date lastGroupCourseTime = courseOrderList == null || courseOrderList.size() < 1 ? null : (Date) courseOrderList.get(0)[0];
        memberAnalysis.setGroupCourseLastTime(lastGroupCourseTime);
        //数据库存的要展示的东西
        //String groupCourseTimeString = getLastTimeShow(lastGroupCourseTime,courseOrderList == null ? null : courseOrderList.get(0)[1].toString());
        //查询累计约团课次数
        int groupCourseCount = courseOrderList == null ? 0 : courseOrderList.size();
        memberAnalysis.setGroupCourseAllCount(groupCourseCount);
        //团课所有天数
        int groupCardDays = memberAnalysisService.queryGroupCardDays(member.getId(),boxId);
        //团课预约频率
        double groupCrouseRate = groupCardDays == 0 ? 0 : groupCourseCount / new Double(groupCardDays);
        memberAnalysis.setGroupCourseAllRate(groupCrouseRate);

        //查询当前团课会员卡
        Integer nowGroupCard = memberAnalysisService.queryNowGroupCard(member.getId(),boxId);
        if (null != nowGroupCard) {
            memberAnalysis.setGroupCourseThisCardId(nowGroupCard);
            MemberCard memberCard = memberAnalysisService.queryCardById(nowGroupCard);
            Date beginDate = memberCard.getCardStartTime();
            int days = DateUtil.daysBetween(beginDate, new Date());

            //当前约课次数
            List<CourseOrder> nowCourseOrderList = memberAnalysisService.queryNowGroupCourseCount(member.getId(), beginDate,boxId);
            int nowCount = 0;
            if (null != nowCourseOrderList) {
                nowCount = nowCourseOrderList.size();
            }
            memberAnalysis.setGroupCourseThisCount(nowCount);
            //当前约课频率
            double nowRate = days == 0 ? 0d : nowCount / new Double(days);
            memberAnalysis.setGroupCourseThisRate(nowRate);
            //查询出勤率变化
            Double changeRate = null;
            //是否这个月开的卡
            boolean isThisMonth = DateUtil.isThisMonth(beginDate.getTime());
            if (!isThisMonth) {
                //查询上月的出勤次数
                double lastMonthRate = memberAnalysisService.queryGroupLastMonthRate(beginDate, member.getId(),boxId);
                double thisMonthRate = memberAnalysisService.queryGroupThisMonthRate(member.getId(),boxId);
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
        List<CoachCourseOrder> allPrivateOrderList = memberAnalysisService.queryPrivateCourseOrder(member.getId(),boxId);
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
        int privateCardDays = memberAnalysisService.queryPrivateCardAllDays(member.getId(),boxId);
        //私教课预约频率
        double privateCrouseRate = privateCardDays == 0 ? 0 :allPrivateCourseCount / new Double(privateCardDays);
        memberAnalysis.setPrivateCourseAllRate(privateCrouseRate);
        //查询当前死角卡
        Integer nowPrivateCard = memberAnalysisService.queryNowPrivateCard(member.getId(),boxId);
        if(null != nowPrivateCard){
            MemberCard privateCard = memberAnalysisService.queryCardById(nowPrivateCard);
            memberAnalysis.setPrivateCourseThisCardId(nowPrivateCard);
            Date beginDate = privateCard.getCardStartTime();
            int days = DateUtil.daysBetween(beginDate,new Date());
            //当前私教课约课次数
            List<CoachCourseOrder> nowOrderList = memberAnalysisService.queryNowPrivateCourseOrder(member.getId(),beginDate,boxId);

            int nowCount = 0;
            if(null != nowOrderList){
                nowCount = nowOrderList.size();
            }
            memberAnalysis.setPrivateCourseThisCount(nowCount);
            //当前约课频率
            double nowRate = days == 0 ? 0d : nowCount/new Double(days);
            memberAnalysis.setPrivateCourseThisRate(nowRate);

            //查询出勤率变化
            Double changeRate = null;
            //是否这周开的卡
            boolean isThisWeek = DateUtil.isThisWeek(beginDate,new Date());
            if(!isThisWeek){
                //查询上月的出勤次数
                double lastMonthRate = memberAnalysisService.queryPrivateLastWeekRate(member.getId(), beginDate,boxId);
                double thisMonthRate = memberAnalysisService.queryPrivateThisWeekRate(member.getId(),boxId);
                //出勤率变化
                changeRate = lastMonthRate == 0 ? 0d : (lastMonthRate - thisMonthRate) / lastMonthRate * 100 ;
                memberAnalysis.setPrivateCourseChangeRate(changeRate);
            }
        }
        /**
         *私教课---end
         */
        memberAnalysis.setUpdateTime(new Date());
        memberAnalysisService.save(memberAnalysis);
    }

    @Override
    public JSONArray queryOrderList(int memberId,int cardType) {
        JSONArray jsonArray = new JSONArray();
        if(cardType == 1){
            List<Object[]> list = courseOrderDao.queryOrderList(memberId);
            if(null != list && list.size() > 0){
                for(Object[] obj : list){
                    JSONObject json = new JSONObject();
                    json.put("time",obj[0]);
                    json.put("boxName",obj[1]);
                    json.put("courseTypeName",obj[2]);
                    json.put("detail",obj[3].toString() + " " + obj[2].toString());
                    Integer signStatus = Integer.valueOf(obj[4].toString());
                    String courseStartTime = obj[0].toString() + " " + obj[3].toString();
                    String courseEndTime = obj[0].toString() + " " + obj[5].toString();
                    String status = "未签到";
                    if("1".equals(obj[6].toString())){
                        status = "取消";
                    }else{
                        if(signStatus == 1){
                            status = "完成";
                        }else{
                            if(DateUtil.StringToDate(courseEndTime,"yyyy/MM/dd HH:mm").getTime() < new Date().getTime()){
                                status = "旷课(未签到)";
                            }
                            if(DateUtil.StringToDate(courseStartTime,"yyyy/MM/dd HH:mm").getTime() > new Date().getTime()){
                                status = "已预约";
                            }
                        }
                    }
                    json.put("status",status);
                    jsonArray.add(json);
                }
            }
        }else{
            List<Object[]> list = coachCourseOrderDao.queryOrderList(memberId);
            if(null != list && list.size() > 0){
                for(Object[] obj : list){
                    JSONObject json = new JSONObject();
                    json.put("time",obj[0]);
                    json.put("boxName",obj[1]);
                    json.put("courseTypeName",obj[2]);
                    json.put("detail",obj[3].toString() + " " + obj[2].toString());
                    Integer signStatus = Integer.valueOf(obj[4].toString());
                    String courseStartTime = obj[0].toString() + " " + obj[3].toString();
                    String courseEndTime = obj[0].toString() + " " + obj[5].toString();
                    String status = "未签到";
                    if("1".equals(obj[6].toString())){
                        status = "取消";
                    }else{
                        if(signStatus == 1){
                            status = "完成";
                        }else{
                            if(DateUtil.StringToDate(courseEndTime,"yyyy/MM/dd HH:mm").getTime() < new Date().getTime()){
                                status = "旷课(未签到)";
                            }
                            if(DateUtil.StringToDate(courseStartTime,"yyyy/MM/dd HH:mm").getTime() > new Date().getTime()){
                                status = "已预约";
                            }
                        }
                    }
                    json.put("status",status);
                    jsonArray.add(json);
                }
            }
        }
        return jsonArray;
    }

    @Override
    public void upateMemberCard(MemberCard memberCard) {
        memberCardDao.update(memberCard);
    }
}
