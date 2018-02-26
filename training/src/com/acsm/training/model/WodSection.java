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
import javax.persistence.Transient;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
@Entity
@Table(name="t_wod_section")
public class WodSection implements Serializable {
    private static final long serialVersionUID = 7291289112743038002L;
    private Integer wodSectionId;
    private Integer wodId;//计划
    private Integer sectionId;//计划
    private Integer tIndex;//顺序
    private String remark;//备注
    private Integer isDeleted;//-1删除 0正常
    private Date insertTime;
    private Integer type;//0 wod下没有对应阶段的content的wod_section  1 正常的
    private Section section;
    @Id
    @GeneratedValue
    @Column(name="wod_section_id",length=11)
    public Integer getWodSectionId() {
        return wodSectionId;
    }

    public void setWodSectionId(Integer wodSectionId) {
        this.wodSectionId = wodSectionId;
    }

    @Column(name="wod_id",length=11)
    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }
    @Column(name="t_index",length=11)
    public Integer gettIndex() {
        return tIndex;
    }

    public void settIndex(Integer tIndex) {
        this.tIndex = tIndex;
    }




    @Column(name="remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name="is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

   @Transient
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    @Column(name="section_id")
    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }
}
