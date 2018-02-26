package com.acsm.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 动作
 */
@Entity
@Table(name = "T_ACTION")
public class Action implements Serializable{

    private int id;
    private String cName;//中文名
    private String eName;//英文名
    private int actionTypeId;//类型
    private int status;//1.使用中，0.禁止
    private String units;

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "C_NAME", length = 50)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Column(name = "E_NAME", length = 50)
    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    @Column(name = "STATUS", length = 2)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "ACTION_TYPE_ID", length = 11)
    public int getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(int actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    @Column(name = "UNITS", length = 500)
    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                ", eName='" + eName + '\'' +
                ", actionTypeId=" + actionTypeId +
                ", status=" + status +
                '}';
    }
}
