package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/16.
 */

import com.acsm.training.model.page.CourseModel;
import com.acsm.training.model.page.CourseModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-16
 */
public class ReservationListModel implements Serializable{
    private static final long serialVersionUID = -3652452345999127860L;

    private Integer maleNum;//男性人数
    private Integer fmaleNum;//女性人数
    private List<ReservationManModel> maleList;//男性会员列表
    private List<ReservationManModel> fmaleList;//女性列表
    private List<CourseModel> courseModelList;
    private Integer coachSignStatus;//教练是否签到  0未签到 1签到

    public Integer getMaleNum() {
        return maleNum;
    }

    public void setMaleNum(Integer maleNum) {
        this.maleNum = maleNum;
    }

    public Integer getFmaleNum() {
        return fmaleNum;
    }

    public void setFmaleNum(Integer fmaleNum) {
        this.fmaleNum = fmaleNum;
    }

    public List<ReservationManModel> getMaleList() {
        return maleList;
    }

    public void setMaleList(List<ReservationManModel> maleList) {
        this.maleList = maleList;
    }

    public List<ReservationManModel> getFmaleList() {
        return fmaleList;
    }

    public void setFmaleList(List<ReservationManModel> fmaleList) {
        this.fmaleList = fmaleList;
    }

    public List<CourseModel> getCourseModelList() {
        return courseModelList;
    }

    public void setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
    }

    public Integer getCoachSignStatus() {
        return coachSignStatus;
    }

    public void setCoachSignStatus(Integer coachSignStatus) {
        this.coachSignStatus = coachSignStatus;
    }
}
