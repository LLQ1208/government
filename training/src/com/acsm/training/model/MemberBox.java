package com.acsm.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.acsm.training.util.DateUtil;

/**
 * 会员所属训练馆
 */
@Entity
@Table(name = "T_MEMBER_BOX")
public class MemberBox {

    private int id;
    private int memberId;
    private int boxId;
    private Integer memberType;//会员卡类型
    private Date memberStartTime;//会员卡开始时间
    private Date memberEndTime;//会员卡结束时间
    private Integer memberDays;//会员卡天数，
    private Integer memberRemainNum;//会员卡剩余次数（次卡）
    private String courseTypeIds;//可预约课程
    private Date createTime;//创建时间
    private int isMain;//是否主训练馆
    private int isActive;//等待激活，次卡会员第一次约课后激活，月卡直接激活

    private String memberStartTimeFormat;
    private String memberEndTimeFormat;
    private Integer timeStatus;//1.正常 2.即将过期，3.已经过期，

    private Box box;
    private Member member;

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "MEMBER_ID", length = 11)
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Column(name = "BOX_ID", length = 11)
    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    @Column(name = "MEMBER_TYPE", length = 2)
    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    @Column(name = "MEMBER_START_TIME")
    public Date getMemberStartTime() {
        return memberStartTime;
    }

    public void setMemberStartTime(Date memberStartTime) {
        this.memberStartTime = memberStartTime;
    }

    @Column(name = "MEMBER_END_TIME")
    public Date getMemberEndTime() {
        return memberEndTime;
    }

    public void setMemberEndTime(Date memberEndTime) {
        this.memberEndTime = memberEndTime;
    }

    @Column(name = "MEMBER_DAYS", length = 11)
    public Integer getMemberDays() {
        return memberDays;
    }

    public void setMemberDays(Integer memberDays) {
        this.memberDays = memberDays;
    }


    @Column(name = "MEMBER_REMAIN_NUM")
    public Integer getMemberRemainNum() {
        return memberRemainNum;
    }

    public void setMemberRemainNum(Integer memberRemainNum) {
        this.memberRemainNum = memberRemainNum;
    }

    @Column(name = "COURSE_TYPE_IDS", length = 100)
    public String getCourseTypeIds() {
        return courseTypeIds;
    }

    public void setCourseTypeIds(String courseTypeIds) {
        this.courseTypeIds = courseTypeIds;
    }


    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "IS_ACTIVE", length = 2)
    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Transient
    public String getMemberStartTimeFormat() {
        return DateUtil.format(memberStartTime, "yyyy/MM/dd");
    }
    public void setMemberStartTimeFormat(String memberStartTimeFormat) {
        this.memberStartTimeFormat = memberStartTimeFormat;
    }

    @Transient
    public String getMemberEndTimeFormat() {
        return DateUtil.format(memberEndTime, "yyyy/MM/dd");
    }
    public void setMemberEndTimeFormat(String memberEndTimeFormat) {
        this.memberEndTimeFormat = memberEndTimeFormat;
    }

    @Transient
    public Integer getTimeStatus() {
        long datePoorDay = DateUtil.getDatePoorDay(new Date(), memberEndTime);
        if(memberType==1){
            if(datePoorDay<=10&&datePoorDay>=0){
                return 2;//即将过期
            }else if(datePoorDay<0){
                return 3;//已经过期
            }else{
                return 1;//大于10天正常
            }
        }else{
            //次卡会员，次数小于等于5次，或者到期日期小于等于10天则为即将过期
            if(isActive == 0){
                return 1;//次卡还未第一次使用激活
            }
            if((memberRemainNum<=5&&memberRemainNum>0)||(datePoorDay<=10&&datePoorDay>=0)){
                return 2;//即将过期
            }else if(memberRemainNum<=0||datePoorDay<0){
                return 3;//已经过期
            }else{
                return 1;//正常
            }
        }
    }

    public void setTimeStatus(Integer timeStatus) {
        this.timeStatus = timeStatus;
    }

    @Transient
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return "MemberBox{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", boxId=" + boxId +
                ", memberType=" + memberType +
                ", memberStartTime=" + memberStartTime +
                ", memberEndTime=" + memberEndTime +
                ", memberDays=" + memberDays +
                ", memberRemainNum=" + memberRemainNum +
                ", memberStartTimeFormat='" + memberStartTimeFormat + '\'' +
                ", memberEndTimeFormat='" + memberEndTimeFormat + '\'' +
                ", timeStatus=" + timeStatus +
                ", courseTypeIds='" + courseTypeIds + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    @Column(name = "IS_MAIN", length = 2)
    public int getIsMain() {
        return isMain;
    }

    public void setIsMain(int isMain) {
        this.isMain = isMain;
    }

    @Transient
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
