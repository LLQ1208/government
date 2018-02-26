package com.acsm.training.model;/**
 * Created by lq on 2017/12/18.
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
@Entity
@Table(name="t_sport_result")
public class SportResult implements Serializable{

    private static final long serialVersionUID = 6033392842654073553L;

    private Integer sportResultId;
    private Integer boxId;//训练馆
    private Integer courseOrderId;//预约的id
    private Integer courseId; //课程
    private Integer memberId;//会员id
    private Integer recordType;//记录成绩类型
    private Integer isRx;
    private Integer isRxPlus;//RX+
    private String remark;
    private Integer sets;
    private Integer reps;
    private Float weight;
    private Integer minutes;
    private Integer seconds;
    private Integer calories;
    private Integer meters;
    private String eachRoundJson;
    private Integer unitType;//单位类型  1,kg 2,lb 3,mile 4,meters 5,km 6,inches 7,yards 8,feet 9,cm
    private Date insertTime;
    private Integer wodContentId;
    private Integer contentId;
    private String pics;
    private Integer recordTotal;//each_round记录方式的总量
    @Id
    @GeneratedValue
    @Column(name="sport_result_id",length=11)
    public Integer getSportResultId() {
        return sportResultId;
    }

    public void setSportResultId(Integer sportResultId) {
        this.sportResultId = sportResultId;
    }

    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
    @Column(name="course_order_id")
    public Integer getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(Integer courseOrderId) {
        this.courseOrderId = courseOrderId;
    }
    @Column(name="course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    @Column(name="member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    @Column(name="record_type")
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
    @Column(name="isRx")
    public Integer getIsRx() {
        return isRx;
    }

    public void setIsRx(Integer isRx) {
        this.isRx = isRx;
    }
    @Column(name="isRxPlus")
    public Integer getIsRxPlus() {
        return isRxPlus;
    }

    public void setIsRxPlus(Integer isRxPlus) {
        this.isRxPlus = isRxPlus;
    }
    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name="sets")
    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }
    @Column(name="reps")
    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }
    @Column(name="weight")
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
    @Column(name="minutes")
    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
    @Column(name="calories")
    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }
    @Column(name="seconds")
    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }
    @Column(name="meters")
    public Integer getMeters() {
        return meters;
    }

    public void setMeters(Integer meters) {
        this.meters = meters;
    }
    @Column(name="each_round_json")
    public String getEachRoundJson() {
        return eachRoundJson;
    }

    public void setEachRoundJson(String eachRoundJson) {
        this.eachRoundJson = eachRoundJson;
    }
    @Column(name="unit_type")
    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }
 
    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="wod_content_id")
    public Integer getWodContentId() {
        return wodContentId;
    }

    public void setWodContentId(Integer wodContentId) {
        this.wodContentId = wodContentId;
    }
    @Column(name="content_id")
    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
    @Column(name="pics")
	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}
    @Column(name="record_total")
    public Integer getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(Integer recordTotal) {
        this.recordTotal = recordTotal;
    }
}
