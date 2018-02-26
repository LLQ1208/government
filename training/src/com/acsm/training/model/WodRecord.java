package com.acsm.training.model;/**
 * Created by lq on 2017/12/13.
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author lianglinqiang
 * @create 2017-12-13
 */
@Entity
@Table(name="t_wod_record")
public class WodRecord implements Serializable{
    private static final long serialVersionUID = 2294641623720088498L;

    private Integer id;
    private Integer wodId;
    private Integer type;//插入 1  更新2
    private Date insertTime;
    private Integer userId;
    private User operatorUser;
    @Id
    @GeneratedValue
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="wod_id")
    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }
    @Column(name="type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Transient
    public User getOperatorUser() {
        return operatorUser;
    }

    public void setOperatorUser(User operatorUser) {
        this.operatorUser = operatorUser;
    }
}
