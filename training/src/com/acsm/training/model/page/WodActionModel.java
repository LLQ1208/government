package com.acsm.training.model.page;

import java.io.Serializable;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:58 2017/12/3
 */
public class WodActionModel implements Serializable {
    private String title;
    private Integer userId;
    private String record;
    private String noUseDay;
    private String lastUseDay;
    private Integer sectionId;
    private Integer contentId;
    private String descriptions;
    private Integer eachRoundNum;
    private Integer eachRoundRecordType;

    public Integer getEachRoundNum() {
        return eachRoundNum;
    }

    public void setEachRoundNum(Integer eachRoundNum) {
        this.eachRoundNum = eachRoundNum;
    }

    public Integer getEachRoundRecordType() {
        return eachRoundRecordType;
    }

    public void setEachRoundRecordType(Integer eachRoundRecordType) {
        this.eachRoundRecordType = eachRoundRecordType;
    }

    public void setRecordTypeFamousWod(Integer recordTypeFamousWod) {
        this.recordTypeFamousWod = recordTypeFamousWod;
    }

    public Integer getRecordTypeFamousWod() {
        return recordTypeFamousWod;
    }

    private Integer recordTypeFamousWod;

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getDescriptions() {
        return descriptions;
    }

    private Integer recordType;
    private Integer isRx;


    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public void setIsRx(Integer isRx) {
        this.isRx = isRx;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public Integer getIsRx() {
        return isRx;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSectionId() {

        return sectionId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setNoUseDay(String noUseDay) {
        this.noUseDay = noUseDay;
    }

    public void setLastUseDay(String lastUseDay) {
        this.lastUseDay = lastUseDay;
    }

    public String getTitle() {

        return title;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getRecord() {
        return record;
    }

    public String getNoUseDay() {
        return noUseDay;
    }

    public String getLastUseDay() {
        return lastUseDay;
    }
}
