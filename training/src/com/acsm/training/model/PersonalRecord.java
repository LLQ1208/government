package com.acsm.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_PERSONAL_RECORD")
public class PersonalRecord {

    private int id;
    private int userId;//用户编号
    private int actionId;//动作编号
    private String actionName;//动作名称
    private String prUnit;//单位
    private int pr;//最好成绩
    private Date lastUpdateTime;

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "USER_ID", length = 11)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "ACTION_ID", length = 11)
    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }


    @Column(name = "PR_UNIT", length = 20)
    public String getPrUnit() {
        return prUnit;
    }

    public void setPrUnit(String prUnit) {
        this.prUnit = prUnit;
    }


    @Column(name = "PR", length = 11)
    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    @Column(name="ACTION_NAME", length = 50)
    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @Column(name = "LAST_UPDATE_TIME")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
