package com.acsm.training.model.CoachBack;/**
 * Created by lq on 2017/12/21.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-21
 */
public class BoardModel implements Serializable{
    private static final long serialVersionUID = -3617741250623744689L;

    private String memberName;
    private String courseTitle;
    private Integer isRx;
    private String ico;
    private String record;
    private Integer tempNum;
    private String remark;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Integer getIsRx() {
        return isRx;
    }

    public void setIsRx(Integer isRx) {
        this.isRx = isRx;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Integer getTempNum() {
        return tempNum;
    }

    public void setTempNum(Integer tempNum) {
        this.tempNum = tempNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
