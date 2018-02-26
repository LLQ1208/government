package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/16.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-16
 */
public class ReservationManModel implements Serializable{
    private static final long serialVersionUID = 7640671446739024695L;

    private Integer courseOrderId;
    private Integer memberId;
    private String ico;//图片
    private String memberName;//会员名字
    private Integer courseId;//课程编号
    private Integer courseTypeId;//课程类型
    private Integer signStatus;//签到状态
    private boolean courseEnd;
    private boolean boxMember = true;//是否是该训练馆会员
    private Integer wodId;//对应的训练计划id
    private List<ReservatoinActionModel> actionModelList;

    public Integer getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(Integer courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Integer getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(Integer signStatus) {
        this.signStatus = signStatus;
    }

    public boolean isCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(boolean courseEnd) {
        this.courseEnd = courseEnd;
    }

    public boolean isBoxMember() {
        return boxMember;
    }

    public void setBoxMember(boolean boxMember) {
        this.boxMember = boxMember;
    }

    public List<ReservatoinActionModel> getActionModelList() {
        return actionModelList;
    }

    public void setActionModelList(List<ReservatoinActionModel> actionModelList) {
        this.actionModelList = actionModelList;
    }

    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }
}
