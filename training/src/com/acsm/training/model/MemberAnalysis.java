package com.acsm.training.model;/**
 * Created by lq on 2018/1/29.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2018-01-29
 */
@Entity
@Table(name="t_member_analysis")
public class MemberAnalysis implements Serializable{
    private static final long serialVersionUID = 5100286667058408123L;

    private Integer id;
    private Integer memberId;
    private Integer boxId;
    private Date groupCourseLastTime;
    private Integer groupCourseAllCount;
    private Double groupCourseAllRate;
    private Integer groupCourseThisCardId;
    private Integer groupCourseThisCount;
    private Double groupCourseThisRate;
    private Double groupCourseChangeRate;
    private Date privateCourseLastTime;
    private Integer privateCourseAllCount;
    private Double privateCourseAllRate;
    private Integer privateCourseThisCardId;
    private Integer privateCourseThisCount;
    private Double privateCourseThisRate;
    private Double privateCourseChangeRate;
    private Date updateTime;
    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="member_id")
    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    @Column(name="group_course_last_time")
    public Date getGroupCourseLastTime() {
        return groupCourseLastTime;
    }

    public void setGroupCourseLastTime(Date groupCourseLastTime) {
        this.groupCourseLastTime = groupCourseLastTime;
    }
    @Column(name="group_course_all_count")
    public Integer getGroupCourseAllCount() {
        return groupCourseAllCount;
    }

    public void setGroupCourseAllCount(Integer groupCourseAllCount) {
        this.groupCourseAllCount = groupCourseAllCount;
    }
    @Column(name="group_course_all_rate")
    public Double getGroupCourseAllRate() {
        return groupCourseAllRate;
    }

    public void setGroupCourseAllRate(Double groupCourseAllRate) {
        this.groupCourseAllRate = groupCourseAllRate;
    }
    @Column(name="group_course_this_count")
    public Integer getGroupCourseThisCount() {
        return groupCourseThisCount;
    }

    public void setGroupCourseThisCount(Integer groupCourseThisCount) {
        this.groupCourseThisCount = groupCourseThisCount;
    }
    @Column(name="group_course_this_rate")
    public Double getGroupCourseThisRate() {
        return groupCourseThisRate;
    }

    public void setGroupCourseThisRate(Double groupCourseThisRate) {
        this.groupCourseThisRate = groupCourseThisRate;
    }
    @Column(name="private_course_last_time")
    public Date getPrivateCourseLastTime() {
        return privateCourseLastTime;
    }

    public void setPrivateCourseLastTime(Date privateCourseLastTime) {
        this.privateCourseLastTime = privateCourseLastTime;
    }
    @Column(name="group_course_change_rate")
    public Double getGroupCourseChangeRate() {
        return groupCourseChangeRate;
    }

    public void setGroupCourseChangeRate(Double groupCourseChangeRate) {
        this.groupCourseChangeRate = groupCourseChangeRate;
    }
    @Column(name="private_course_all_count")
    public Integer getPrivateCourseAllCount() {
        return privateCourseAllCount;
    }

    public void setPrivateCourseAllCount(Integer privateCourseAllCount) {
        this.privateCourseAllCount = privateCourseAllCount;
    }
    @Column(name="private_course_all_rate")
    public Double getPrivateCourseAllRate() {
        return privateCourseAllRate;
    }

    public void setPrivateCourseAllRate(Double privateCourseAllRate) {
        this.privateCourseAllRate = privateCourseAllRate;
    }
    @Column(name="private_course_this_count")
    public Integer getPrivateCourseThisCount() {
        return privateCourseThisCount;
    }

    public void setPrivateCourseThisCount(Integer privateCourseThisCount) {
        this.privateCourseThisCount = privateCourseThisCount;
    }
    @Column(name="private_course_this_rate")
    public Double getPrivateCourseThisRate() {
        return privateCourseThisRate;
    }

    public void setPrivateCourseThisRate(Double privateCourseThisRate) {
        this.privateCourseThisRate = privateCourseThisRate;
    }
    @Column(name="private_course_change_rate")
    public Double getPrivateCourseChangeRate() {
        return privateCourseChangeRate;
    }

    public void setPrivateCourseChangeRate(Double privateCourseChangeRate) {
        this.privateCourseChangeRate = privateCourseChangeRate;
    }
    @Column(name="update_time")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Column(name="group_course_this_card_id")
    public Integer getGroupCourseThisCardId() {
        return groupCourseThisCardId;
    }

    public void setGroupCourseThisCardId(Integer groupCourseThisCardId) {
        this.groupCourseThisCardId = groupCourseThisCardId;
    }
    @Column(name="private_course_this_card_id")
    public Integer getPrivateCourseThisCardId() {
        return privateCourseThisCardId;
    }

    public void setPrivateCourseThisCardId(Integer privateCourseThisCardId) {
        this.privateCourseThisCardId = privateCourseThisCardId;
    }
    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
