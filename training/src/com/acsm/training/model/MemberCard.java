package com.acsm.training.model;/**
 * Created by lq on 2018/1/26.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2018-01-26
 */
@Entity
@Table(name="t_member_card")
public class MemberCard implements Serializable{
    private static final long serialVersionUID = 9169663460851367489L;

    private Integer id;
    private Integer memerId;//会员id
    private Integer memberCardTemplateId;//会员卡模板ID
    private Integer money;//金额
    private Date cardStartTime;//会员卡开始时间
    private Date cardEndTime;//自定义的 会员卡结束时间
    private Integer customCardType;//自定义卡类型 1 时限卡  2次卡
    private Integer customCardCourseType;//  1团课卡 2私教卡
    private Integer cardExpireDay; //自定义过期提醒时间
    private Integer cardTotalCount;//自定义的会员卡次卡次数
    private Integer remainNum;//次卡剩余次数
    private String customCourseTypeIds;//自定义时限卡的课程类型
    private Integer status;//状态 0默认
    private Date createTime;
    private Integer insertUser;//
    private MemberCardTemplate template;
    private Integer boxId;

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="memer_id")
    public Integer getMemerId() {
        return memerId;
    }

    public void setMemerId(Integer memerId) {
        this.memerId = memerId;
    }
    @Column(name="member_card_template_id")
    public Integer getMemberCardTemplateId() {
        return memberCardTemplateId;
    }

    public void setMemberCardTemplateId(Integer memberCardTemplateId) {
        this.memberCardTemplateId = memberCardTemplateId;
    }
    @Column(name="money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
    @Column(name="card_start_time")
    public Date getCardStartTime() {
        return cardStartTime;
    }

    public void setCardStartTime(Date cardStartTime) {
        this.cardStartTime = cardStartTime;
    }
    @Column(name="card_end_time")
    public Date getCardEndTime() {
        return cardEndTime;
    }

    public void setCardEndTime(Date cardEndTime) {
        this.cardEndTime = cardEndTime;
    }
    @Column(name="custom_card_type")
    public Integer getCustomCardType() {
        return customCardType;
    }

    public void setCustomCardType(Integer customCardType) {
        this.customCardType = customCardType;
    }
    @Column(name="card_expire_day")
    public Integer getCardExpireDay() {
        return cardExpireDay;
    }

    public void setCardExpireDay(Integer cardExpireDay) {
        this.cardExpireDay = cardExpireDay;
    }
    @Column(name="card_total_count")
    public Integer getCardTotalCount() {
        return cardTotalCount;
    }


    public void setCardTotalCount(Integer cardTotalCount) {
        this.cardTotalCount = cardTotalCount;
    }
    @Column(name="remain_num")
    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }
    @Column(name="custom_course_type_ids")
    public String getCustomCourseTypeIds() {
        return customCourseTypeIds;
    }

    public void setCustomCourseTypeIds(String customCourseTypeIds) {
        this.customCourseTypeIds = customCourseTypeIds;
    }

    @Column(name="status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name="custom_card_course_type")

    public Integer getCustomCardCourseType() {
        return customCardCourseType;
    }

    public void setCustomCardCourseType(Integer customCardCourseType) {
        this.customCardCourseType = customCardCourseType;
    }
    @Column(name="insert_user")
    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    @Transient
	public MemberCardTemplate getTemplate() {
		return template;
	}

	public void setTemplate(MemberCardTemplate template) {
		this.template = template;
	}
    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return " id "+this.id+" tmpid "+this.memberCardTemplateId;
    }
}
