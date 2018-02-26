package com.acsm.training.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "T_RECORD")
public class Record {
    private int id;
    private int userId;//用户编号
    private int duration;//时长，分钟
    private int quantity;//组数
    private Date trainingDate;
    private Date createTime;//创建时间
    private Date lastUpdateTime;//最后更新时间

    private List<RecordAction> recordActionList;

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DURATION", length = 11)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "QUANTITY", length = 11)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Transient
    public List<RecordAction> getRecordActionList() {
        return recordActionList;
    }

    public void setRecordActionList(List<RecordAction> recordActionList) {
        this.recordActionList = recordActionList;
    }

    @Column(name = "USER_ID", length = 11)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "LAST_UPDATE_TIME")
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Column(name = "TRAINING_DATE")
    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", duration=" + duration +
                ", quantity=" + quantity +
                ", createTime=" + createTime +
                ", recordActionList=" + recordActionList +
                ", trainingDate=" + trainingDate +
                '}';
    }
}
