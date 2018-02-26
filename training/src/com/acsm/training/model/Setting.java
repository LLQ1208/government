package com.acsm.training.model;/**
 * Created by lq on 2017/12/8.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lianglinqiang
 * @create 2017-12-08
 */
@Entity
@Table(name="t_setting")
public class Setting implements Serializable{
    private static final long serialVersionUID = 1835465902945573707L;

    private Integer id;
    private Integer userId;//老板的user_id
    private Integer appShowDay;//APP公布时间-天
    private Integer appShowHour;//APP公布时间-时
    private Integer appShowMinus;//APP公布时间-分
    private Integer wodShowWeek;//wod日历页展示周
    private Integer classReservationMinus;//课程表预约/取消时间
    private Integer dropInMinus;//drop in
    private Integer reservationDay;//可预约天数

    @Id
    @GeneratedValue
    @Column(name="ID",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name="app_show_day")
    public Integer getAppShowDay() {
        return appShowDay;
    }

    public void setAppShowDay(Integer appShowDay) {
        this.appShowDay = appShowDay;
    }
    @Column(name="app_show_hour")
    public Integer getAppShowHour() {
        return appShowHour;
    }

    public void setAppShowHour(Integer appShowHour) {
        this.appShowHour = appShowHour;
    }
    @Column(name="app_show_minus")
    public Integer getAppShowMinus() {
        return appShowMinus;
    }

    public void setAppShowMinus(Integer appShowMinus) {
        this.appShowMinus = appShowMinus;
    }



    @Column(name="wod_show_week")
    public Integer getWodShowWeek() {
        return wodShowWeek;
    }

    public void setWodShowWeek(Integer wodShowWeek) {
        this.wodShowWeek = wodShowWeek;
    }
    @Column(name="class_reservation_minus")
    public Integer getClassReservationMinus() {
        return classReservationMinus;
    }

    public void setClassReservationMinus(Integer classReservationMinus) {
        this.classReservationMinus = classReservationMinus;
    }
    @Column(name="drop_in_minus")
    public Integer getDropInMinus() {
        return dropInMinus;
    }

    public void setDropInMinus(Integer dropInMinus) {
        this.dropInMinus = dropInMinus;
    }
    @Column(name="reservation_day")
    public Integer getReservationDay() {
        return reservationDay;
    }

    public void setReservationDay(Integer reservationDay) {
        this.reservationDay = reservationDay;
    }
}
