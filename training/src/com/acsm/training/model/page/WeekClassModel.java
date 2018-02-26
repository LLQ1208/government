package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/7.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-07
 */
public class WeekClassModel implements Serializable{
    private static final long serialVersionUID = -5054061799403581567L;
    private List<CourseModel> monday;
    private List<CourseModel> tuesday;
    private List<CourseModel> wednesday;
    private List<CourseModel> thursday;
    private List<CourseModel> friday;
    private List<CourseModel> saturday;
    private List<CourseModel> sunday;

    public List<CourseModel> getMonday() {
        return monday;
    }

    public void setMonday(List<CourseModel> monday) {
        this.monday = monday;
    }

    public List<CourseModel> getTuesday() {
        return tuesday;
    }

    public void setTuesday(List<CourseModel> tuesday) {
        this.tuesday = tuesday;
    }

    public List<CourseModel> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<CourseModel> wednesday) {
        this.wednesday = wednesday;
    }

    public List<CourseModel> getThursday() {
        return thursday;
    }

    public void setThursday(List<CourseModel> thursday) {
        this.thursday = thursday;
    }

    public List<CourseModel> getFriday() {
        return friday;
    }

    public void setFriday(List<CourseModel> friday) {
        this.friday = friday;
    }

    public List<CourseModel> getSaturday() {
        return saturday;
    }

    public void setSaturday(List<CourseModel> saturday) {
        this.saturday = saturday;
    }

    public List<CourseModel> getSunday() {
        return sunday;
    }

    public void setSunday(List<CourseModel> sunday) {
        this.sunday = sunday;
    }
}
