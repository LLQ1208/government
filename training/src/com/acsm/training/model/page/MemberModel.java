package com.acsm.training.model.page;/**
 * Created by lq on 2018/1/22.
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-22
 */
public class MemberModel implements Serializable{
    private static final long serialVersionUID = 781197996879522881L;

    private Integer memberId;
    private MultipartFile img;
    private String birthday;
    private Integer sex;
    private String memberName;
    private String phone;
    private String email;
    private Integer box;
    private Integer templateId;
    private String customCardType;
    private String countStartTime;
    private String countEndTime;
    private String countExpireDay;
    private String  countNum;
    private String timeStartTime;
    private String timeEndTime;
    private String timeExpire;
    private String customCourseTypeIds;
    private String generalMoney;
    private String countMoney;
    private String timeMoney;
    private String generalBeginTime;
    private String customCountType;

    //会员列表需要用的
    private Integer memberCardId;
    private String sexStr;
    private String firstOpenCardTime;//初次开卡时间
    private String continueCardTime;//续卡时间
    private String cardEndTime;//卡到期时间
    private String cardTemplateName;//卡名称
    private String memberCardType;//卡类型
    private String totalCount;//总次数
    private String cardStatus;//状态
    private String lastCourseTime;//最近上课时间
    private String orderCourseCount;//累计约课次数
    private String orderCourseRate;//累计约课频率
    private String thisCardOrderCourseCount;//当前约课次数
    private String thisCardOrderCourseRate;//当前约课频率
    private String attendanceRate;//会员出勤率变化
    private Integer status;


    private String dealName;//会员卡处理人
    private String canOrderCourseType;
    private List<String> eventList;


    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBox() {
        return box;
    }

    public void setBox(Integer box) {
        this.box = box;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getCustomCardType() {
        return customCardType;
    }

    public void setCustomCardType(String customCardType) {
        this.customCardType = customCardType;
    }

    public String getCountStartTime() {
        return countStartTime;
    }

    public void setCountStartTime(String countStartTime) {
        this.countStartTime = countStartTime;
    }

    public String getCountEndTime() {
        return countEndTime;
    }

    public void setCountEndTime(String countEndTime) {
        this.countEndTime = countEndTime;
    }

    public String getCountExpireDay() {
        return countExpireDay;
    }

    public void setCountExpireDay(String countExpireDay) {
        this.countExpireDay = countExpireDay;
    }

    public String getTimeStartTime() {
        return timeStartTime;
    }

    public void setTimeStartTime(String timeStartTime) {
        this.timeStartTime = timeStartTime;
    }

    public String getCountNum() {
        return countNum;
    }

    public void setCountNum(String countNum) {
        this.countNum = countNum;
    }

    public String getTimeEndTime() {
        return timeEndTime;
    }

    public void setTimeEndTime(String timeEndTime) {
        this.timeEndTime = timeEndTime;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getCustomCourseTypeIds() {
        return customCourseTypeIds;
    }

    public void setCustomCourseTypeIds(String customCourseTypeIds) {
        this.customCourseTypeIds = customCourseTypeIds;
    }

    public String getGeneralMoney() {
        return generalMoney;
    }

    public void setGeneralMoney(String generalMoney) {
        this.generalMoney = generalMoney;
    }

    public String getGeneralBeginTime() {
        return generalBeginTime;
    }

    public void setGeneralBeginTime(String generalBeginTime) {
        this.generalBeginTime = generalBeginTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }



    public String getCardTemplateName() {
        return cardTemplateName;
    }

    public void setCardTemplateName(String cardTemplateName) {
        this.cardTemplateName = cardTemplateName;
    }

    public String getMemberCardType() {
        return memberCardType;
    }

    public void setMemberCardType(String memberCardType) {
        this.memberCardType = memberCardType;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getFirstOpenCardTime() {
        return firstOpenCardTime;
    }

    public void setFirstOpenCardTime(String firstOpenCardTime) {
        this.firstOpenCardTime = firstOpenCardTime;
    }

    public String getContinueCardTime() {
        return continueCardTime;
    }

    public void setContinueCardTime(String continueCardTime) {
        this.continueCardTime = continueCardTime;
    }

    public String getCardEndTime() {
        return cardEndTime;
    }

    public void setCardEndTime(String cardEndTime) {
        this.cardEndTime = cardEndTime;
    }

    public String getLastCourseTime() {
        return lastCourseTime;
    }

    public void setLastCourseTime(String lastCourseTime) {
        this.lastCourseTime = lastCourseTime;
    }

    public String getOrderCourseCount() {
        return orderCourseCount;
    }

    public void setOrderCourseCount(String orderCourseCount) {
        this.orderCourseCount = orderCourseCount;
    }

    public String getOrderCourseRate() {
        return orderCourseRate;
    }

    public void setOrderCourseRate(String orderCourseRate) {
        this.orderCourseRate = orderCourseRate;
    }

    public String getThisCardOrderCourseCount() {
        return thisCardOrderCourseCount;
    }

    public void setThisCardOrderCourseCount(String thisCardOrderCourseCount) {
        this.thisCardOrderCourseCount = thisCardOrderCourseCount;
    }

    public String getThisCardOrderCourseRate() {
        return thisCardOrderCourseRate;
    }

    public void setThisCardOrderCourseRate(String thisCardOrderCourseRate) {
        this.thisCardOrderCourseRate = thisCardOrderCourseRate;
    }

    public String getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(String attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public String getSexStr() {
        return sexStr;
    }

    public void setSexStr(String sexStr) {
        this.sexStr = sexStr;
    }

    public String getCustomCountType() {
        return customCountType;
    }

    public void setCustomCountType(String customCountType) {
        this.customCountType = customCountType;
    }

    public String getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(String countMoney) {
        this.countMoney = countMoney;
    }

    public String getTimeMoney() {
        return timeMoney;
    }

    public void setTimeMoney(String timeMoney) {
        this.timeMoney = timeMoney;
    }

    public Integer getMemberCardId() {
        return memberCardId;
    }

    public void setMemberCardId(Integer memberCardId) {
        this.memberCardId = memberCardId;
    }


    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getCanOrderCourseType() {
        return canOrderCourseType;
    }

    public void setCanOrderCourseType(String canOrderCourseType) {
        this.canOrderCourseType = canOrderCourseType;
    }

    public List<String> getEventList() {
        return eventList;
    }

    public void setEventList(List<String> eventList) {
        this.eventList = eventList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
