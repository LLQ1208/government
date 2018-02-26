package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/7.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-07
 */
public class CourseModel implements Serializable{

    private static final long serialVersionUID = -8358797632101128934L;

    private String beginTime;
    private String capacity;
    private String crouseTypeName;
    private String endTime;
    private String masterName;
    private String id;
    private String date;
    private String color;
    private Integer week;
    private Integer courseTypeId;
    private Integer coachId;
    private Integer num;
    private Integer operation;//状态

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCrouseTypeName() {
        return crouseTypeName;
    }

    public void setCrouseTypeName(String crouseTypeName) {
        this.crouseTypeName = crouseTypeName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }
}
