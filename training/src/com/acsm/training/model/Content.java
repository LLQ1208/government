package com.acsm.training.model;/**
 * Created by lq on 2017/12/2.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 训练计划内容表
 * @Author lianglinqiang
 * @create 2017-12-02
 */
@Entity
@Table(name="t_content")
public class Content implements Serializable{
    private static final long serialVersionUID = 8314240882281536388L;

    private Integer contentId;
    private String name;//content名称
    private String description;//content描述
    private Integer userId;//用户ID，admin用户添加的content为系统类型，其他用户添加的为自定义
    private Integer contentType;//动作类型,标准类型：1,Warm-Ups 2,Gymnastics 3,Weightlifting 4,Metcons,5,Popular Wod 6,自定义Warm-Ups 7,自定义Gymnastics 8,自定义Weightlifting 9,自定义Metcons
    private Integer recordType;//记录方式  1,Time 2,AMRAP-Rounds 3,AMRAP-Reps 4 AMRAP-Rounds and Reps 5,Each Round 6,Distance 7,Calories 8,No Measure 9,reps
    private Integer isRx;//
    private Integer isDeleted;//-1删除  0正常
    private Integer famous_wod_record_type;//-1删除  0正常
    private Integer famousWodRecordType;//-1删除  0正常
    private Integer eachMeasure;//each round组数
    private Integer eachRecordType; //记录方式  1，for reps 2,for time 3,for weight 4,for distance 5,for calories

    @Id
    @GeneratedValue
    @Column(name="content_id",length=11)
    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public void setFamousWodRecordType(Integer famousWodRecordType) {
        this.famousWodRecordType = famousWodRecordType;
    }
    @Column(name="famous_wod_record_type")
    public Integer getFamousWodRecordType() {

        return famousWodRecordType;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name="content_type")
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
//    @Column(name="no_use_day")
//    public String getNoUseDay() {
//        return noUseDay;
//    }
//
//    public void setNoUseDay(String noUseDay) {
//        this.noUseDay = noUseDay;
//    }
//    @Column(name="last__use_day")
//    public String getLastUseDay() {
//        return lastUseDay;
//    }
//
//    public void setLastUseDay(String lastUseDay) {
//        this.lastUseDay = lastUseDay;
//    }
    @Column(name="record_type")
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
//    @Column(name="record_name")
//    public String getRecordName() {
//        return recordName;
//    }
//
//    public void setRecordName(String recordName) {
//        this.recordName = recordName;
//    }
    @Column(name="is_rx")
    public Integer getIsRx() {
        return isRx;
    }

    public void setIsRx(Integer isRx) {
        this.isRx = isRx;
    }
    @Column(name="is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Column(name="each_measure")
    public Integer getEachMeasure() {
        return eachMeasure;
    }

    public void setEachMeasure(Integer eachMeasure) {
        this.eachMeasure = eachMeasure;
    }
    @Column(name="each_record_type")
    public Integer getEachRecordType() {
        return eachRecordType;
    }

    public void setEachRecordType(Integer eachRecordType) {
        this.eachRecordType = eachRecordType;
    }
}
