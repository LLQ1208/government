package com.acsm.training.service;

import com.acsm.training.model.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.MemberModel;

import java.util.List;

/**
 * Created by lq on 2018/1/18.
 */
public interface MemberNewService {

    public MemberCardTemplate queryMemberCardTemplateById(int id);

    public void addMemberCardTemplate(MemberCardTemplate template,User user);

    public void updateMemberCardTemplate(MemberCardTemplate template);

    /**
     * 根据老板分页查询
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageHelper<MemberCardTemplate> queryByPage(int userId,int pageIndex,int pageSize);

    /**
     * 根据老板查询所有的
     * @param userId
     * @return
     */
    public List<MemberCardTemplate> queryTemplateList(int userId);

    /**
     * 校验会员卡
     * @param phone
     * @param memberName
     * @param templateId
     * @param customType
     * @param beginTime
     * @param endTime
     * @return
     */
    public JSONObject memberAddCheck(String phone,String memberName,Integer boxId,Integer templateId,
                                     Integer customType,String beginTime, String endTime);


    public JSONObject memberExtendCheck(Integer memberId,String phone,String memberName,Integer boxId,Integer templateId,
                                     Integer customType,String beginTime, String endTime,Integer boutType);

    /**
     * 添加自定义时限卡
     * @param member
     * @param beginTime
     * @param endTime
     * @param expireDay
     * @param customCourseTypeIds
     */
    public void addMemberCustomTime(Member member,MemberCard memberCard,String beginTime,String endTime,Integer expireDay,String customCourseTypeIds,Integer money);

    /**
     * 添加自定义次卡
     * @param member
     * @param beginTime
     * @param endTime
     * @param expireDay
     * @param countNum
     */
    public void addMemberCustomCount(Member member,MemberCard memberCard,String beginTime,String endTime,Integer expireDay,
                                     Integer countNum,Integer countType,String courseTypeIds,Integer money);

    /**
     * 添加模板卡会员
     * @param member
     * @param beginTime
     * @param money
     */
    public void addMemberGeneral(Member member,MemberCard memberCard,String beginTime,Integer money);

    /**
     *
     * @param boxIds  //所有的馆
     * @param boxId //
     * @param cardType //卡类型 团课卡 死角卡
     * @param search //搜索的调价
     * @param timeType //全部 过期
     * @param sex //性别
     * @param orderType // 排序
     * @param pageIndex //页码
     * @param pageSize//每页多少条
     * @param cardType//卡类型 1团课 2私教
     * @return
     */
    public PageHelper<MemberModel> queryMemberList(String boxIds,Integer boxId,String search,Integer cardType,Integer timeType,Integer sex,
                                                   Integer templateId,Integer orderType,Integer orderDesc,Integer pageIndex,Integer pageSize,
                                                   String memberCardIds);


    public void updateMemberEndDate(String cardIds,Integer day);

    public MemberAnalysis queryAnalysisByMemberId(int memberId,Integer boxId);

    public MemberCard queryCardById(int cardId);


    public void addCardExtendTime(Member member,MemberCard memberCard,String beginTime,String endTime,Integer expireDay,String customCourseTypeIds,Integer money);

    public void addCardExtendCount(Member member,MemberCard memberCard,String beginTime,String endTime,Integer expireDay,
                                     Integer countNum,Integer countType,String courseTypeIds,Integer money);
    public void addCardExtendGeneral(Member member,MemberCard memberCard,String beginTime,Integer money);

    public List<MemberModel> queryCardList(int memberId,int type,Integer boxId);


    public void updateMemberCardStatus(MemberCard card);

    public boolean queryTemplateByName(String name,Integer userId,Integer templateId);


    public List<Member> queryListByPhoneAndNullBox(int boxId,String phone);



    public void updateAnalysisCard(Member member,Integer boxId);


    public JSONArray queryOrderList(int memberId,int cardType);

    public void upateMemberCard(MemberCard memberCard);

}
