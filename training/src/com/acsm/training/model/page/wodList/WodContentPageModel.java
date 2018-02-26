package com.acsm.training.model.page.wodList;/**
 * Created by lq on 2017/12/10.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-10
 */
public class WodContentPageModel implements Serializable{
    private static final long serialVersionUID = -4437672771061563110L;

    private Integer wodContentId;
    private String wodContentName;
    private String seriaNum;
    private String comment;
    private Integer wodContentType;
    private String descript;//描述
    private String contentRecordTypeName;//常规的metcon记录方式
    private String repsScheme;

    public Integer getWodContentId() {
        return wodContentId;
    }

    public void setWodContentId(Integer wodContentId) {
        this.wodContentId = wodContentId;
    }

    public String getWodContentName() {
        return wodContentName;
    }

    public void setWodContentName(String wodContentName) {
        this.wodContentName = wodContentName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSeriaNum() {
        return seriaNum;
    }

    public void setSeriaNum(String seriaNum) {
        this.seriaNum = seriaNum;
    }

    public Integer getWodContentType() {
        return wodContentType;
    }

    public void setWodContentType(Integer wodContentType) {
        this.wodContentType = wodContentType;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getContentRecordTypeName() {
        return contentRecordTypeName;
    }

    public void setContentRecordTypeName(String contentRecordTypeName) {
        this.contentRecordTypeName = contentRecordTypeName;
    }

    public String getRepsScheme() {
        return repsScheme;
    }

    public void setRepsScheme(String repsScheme) {
        this.repsScheme = repsScheme;
    }
}
