package com.acsm.training.model;/**
 * Created by lq on 2017/12/2.
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
 * @create 2017-12-02
 */
@Entity
@Table(name="t_section")
public class Section implements Serializable{
    private static final long serialVersionUID = 3314824252921673856L;

    private Integer sectionId;
    private String title;//阶段标题
    private Integer userId;
    private Date insertTime;
    private Integer isDeleted;//-1删除  0正常



    @Id
    @GeneratedValue
    @Column(name="section_id",length=11)
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
