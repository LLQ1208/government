package com.acsm.training.model;/**
 * Created by lq on 2017/12/12.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lianglinqiang
 * @create 2017-12-12
 */
@Entity
@Table(name="t_coach_course")
public class CoachCourse implements Serializable{
    private static final long serialVersionUID = 8020655834630032686L;
    private Integer id;
    private Integer coachId;//教练
    private Integer week;//周几
    private String startTime;//开始时间
    private String endTime;//结束时间
    private Integer peopleLimit;//预约人数
    private Integer isDelete;//是否被删除 0未删除  1删除
    private Integer bossId;
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
    @Column(name="coach_id")
    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }
    @Column(name="week")
    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
    @Column(name="start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    @Column(name="end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    @Column(name="people_limit")
    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }
    @Column(name="is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    @Column(name="boss_id")
    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }

    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
}
