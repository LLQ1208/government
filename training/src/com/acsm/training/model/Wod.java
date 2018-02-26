package com.acsm.training.model;/**
 * Created by lq on 2017/11/30.
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
 * @create 2017-11-30
 */
@Entity
@Table(name="T_WOD")
public class Wod implements Serializable{

    private static final long serialVersionUID = 23264852175785450L;
    private Integer id;
    private String name;
    private String duration;
    private Integer quantity;
    private String action;
    private Integer heat;
    private Date wDate;
    private Integer isDeleted;
    private String remark;
    private Integer userId;
    private Integer appShowHour;
    private Integer appShowMinuts;

    @Id
    @GeneratedValue
    @Column(name="ID",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="DURATION")
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Column(name="QUANTITY")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Column(name="ACTIONS")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    @Column(name="HEAT")
    public Integer getHeat() {
        return heat;
    }

    public void setHeat(Integer heat) {
        this.heat = heat;
    }
    @Column(name="IS_DELETED")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Column(name="REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }



    @Column(name="DATE")
    public Date getwDate() {
        return wDate;
    }

    public void setwDate(Date wDate) {
        this.wDate = wDate;
    }

    @Column(name="USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name="app_show_hour")
    public Integer getAppShowHour() {
        return appShowHour;
    }

    public void setAppShowHour(Integer appShowHour) {
        this.appShowHour = appShowHour;
    }
    @Column(name="app_show_minuts")
    public Integer getAppShowMinuts() {
        return appShowMinuts;
    }

    public void setAppShowMinuts(Integer appShowMinuts) {
        this.appShowMinuts = appShowMinuts;
    }
}
