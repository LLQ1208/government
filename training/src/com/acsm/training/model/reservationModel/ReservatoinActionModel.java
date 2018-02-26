package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/17.
 */

import java.io.Serializable;

import com.acsm.training.model.page.ContentContent;
import com.acsm.training.model.page.ContentContent;

/**
 * @Author lianglinqiang
 * @create 2017-12-17
 */
public class ReservatoinActionModel implements Serializable{
    private static final long serialVersionUID = 3045775367392096106L;
    private Integer wodContentId;
    private String actionTitle;
    private String actionName;
    private boolean rx;//记录成绩了，并且选择了rx
    private boolean hasRxPlus;//是否选择了rx+
    private boolean rxPlus;//训练计划是否选了rx+
    private String content;
    private ContentContent contentEntity;
    private Integer recordType;//记录方式
    private boolean hasPop;
    private Integer contentId;
    private Integer sportResultId;

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public boolean getRx() {
        return rx;
    }

    public void setRx(boolean rx) {
        this.rx = rx;
    }

    public boolean getRxPlus() {
        return rxPlus;
    }

    public void setRxPlus(boolean rxPlus) {
        this.rxPlus = rxPlus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentContent getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(ContentContent contentEntity) {
        this.contentEntity = contentEntity;
    }

    public Integer getWodContentId() {
        return wodContentId;
    }

    public void setWodContentId(Integer wodContentId) {
        this.wodContentId = wodContentId;
    }

    public Integer getRecordType() {
        return recordType;
    }

    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }

    public boolean getHasPop() {
        return hasPop;
    }

    public void setHasPop(boolean hasPop) {
        this.hasPop = hasPop;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getSportResultId() {
        return sportResultId;
    }

    public void setSportResultId(Integer sportResultId) {
        this.sportResultId = sportResultId;
    }


    public boolean getHasRxPlus() {
        return hasRxPlus;
    }

    public void setHasRxPlus(boolean hasRxPlus) {
        this.hasRxPlus = hasRxPlus;
    }
}
