package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/8.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-08
 */
public class CourseEditModel implements Serializable{
    private static final long serialVersionUID = -1711302036969550163L;
    private Integer courseId;
    private Integer beginHour;
    private Integer beginMinus;
    private Integer endHour;
    private Integer endMinus;
    private Integer courseTypeId;
    private Integer coachId;
    private Integer peopleNum;
    private Integer week;

    public Integer getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(Integer beginHour) {
        this.beginHour = beginHour;
    }

    public Integer getBeginMinus() {
        return beginMinus;
    }

    public void setBeginMinus(Integer beginMinus) {
        this.beginMinus = beginMinus;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public Integer getEndMinus() {
        return endMinus;
    }

    public void setEndMinus(Integer endMinus) {
        this.endMinus = endMinus;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
}
