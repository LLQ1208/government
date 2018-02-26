package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/12.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-12
 */
public class CoachCourseModel implements Serializable{
    private static final long serialVersionUID = -7898986418879732294L;
    private Integer id;
    private Integer coachId;//教练
    private Integer week;//周几
    private String startTime;//开始时间
    private String endTime;//结束时间
    private Integer peopleLimit;//预约人数
    private String date;
    private String capacity;
    private Integer reservNum;
    private String reservDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(Integer peopleLimit) {
        this.peopleLimit = peopleLimit;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Integer getReservNum() {
        return reservNum;
    }

    public void setReservNum(Integer reservNum) {
        this.reservNum = reservNum;
    }

    public String getReservDesc() {
        return reservDesc;
    }

    public void setReservDesc(String reservDesc) {
        this.reservDesc = reservDesc;
    }
}
