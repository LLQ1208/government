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

import com.acsm.training.model.page.ContentContent;
import com.google.gson.Gson;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
@Entity
@Table(name="T_WOD_CONTENT")
public class WodContent implements Serializable {
    private static final long serialVersionUID = 5945124444069637760L;
    private Integer wodContentId;
    private Integer wodId;
    private Integer wodSectionId;
    private Integer contentId;
    private Content contentEntity;
    private String contentTitle;
    private Integer tIndex;//顺序
    private Integer recordType;//类型
    private String content;//内容
    private String remark;
    private Integer isDeleted;//-1删除 0正常
    private String descript;//描述
    private Integer contentType;//动作类型,标准类型：1,Warm-Ups 2,Gymnastics 3,Weightlifting 4,Metcons,5,Popular Wod 6,自定义Warm-Ups 7,自定义Gymnastics 8,自定义Weightlifting 9,自定义Metcons
    private Date insertTime;
    private String seriaNum;//序号
    private String repsScheme;
    private ContentContent conentContent;
    private String contentRecordTypeName;//常规的metcon记录方式
    private Integer canEdit;
    @Id
    @GeneratedValue
    @Column(name="wod_content_id",length=11)
    public Integer getWodContentId() {
        return wodContentId;
    }

    public void setWodContentId(Integer wodContentId) {
        this.wodContentId = wodContentId;
    }
    @Column(name="wod_id",length=11)
    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }
    @Column(name="wod_section_id",length=11)
    public Integer getWodSectionId() {
        return wodSectionId;
    }

    public void setWodSectionId(Integer wodSectionId) {
        this.wodSectionId = wodSectionId;
    }

   @Transient
    public Content getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(Content contentEntity) {
        this.contentEntity = contentEntity;
    }

    @Column(name="t_index",length=11)
    public Integer gettIndex() {
        return tIndex;
    }

    public void settIndex(Integer tIndex) {
        this.tIndex = tIndex;
    }
    @Column(name="record_type",length=11)
    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
    @Column(name="content_type",length=11)
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }


    @Column(name="content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name="descript")
    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
    @Column(name="content_title")
    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }
    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="seria_num")
    public String getSeriaNum() {
        return seriaNum;
    }

    public void setSeriaNum(String seriaNum) {
        this.seriaNum = seriaNum;
    }




    @Transient
    public ContentContent getConentContent() {
        if(this.content == null || this.content.equals("")){
            return null;
        }else{
            return new Gson().fromJson(this.content,ContentContent.class);
        }
    }

    public void setConentContent(ContentContent conentContent) {
        this.conentContent = conentContent;
    }

    @Transient
    public String getContentRecordTypeName() {
        return contentRecordTypeName;
    }

    public void setContentRecordTypeName(String contentRecordTypeName) {
        this.contentRecordTypeName = contentRecordTypeName;
    }
    @Column(name="content_id")

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
    @Column(name="reps_scheme")
    public String getRepsScheme() {
        return repsScheme;
    }

    public void setRepsScheme(String repsScheme) {
        this.repsScheme = repsScheme;
    }
    @Column(name="can_edit")
    public Integer getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Integer canEdit) {
        this.canEdit = canEdit;
    }
}
