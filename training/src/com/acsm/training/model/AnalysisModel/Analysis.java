package com.acsm.training.model.AnalysisModel;

import com.acsm.training.model.reservationModel.Series;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 14:57 2017/12/26
 */
public class Analysis {
    private ArrayList<AnalysisSeries> serieses;

    private String timeTitle;

    private Integer weekType;
    private String beginDate;
    private String endDate;
    private Integer lastOrNextWeek;

    public String getTimeTitle() {
        return timeTitle;
    }

    public void setTimeTitle(String timeTitle) {
        this.timeTitle = timeTitle;
    }

    public List<AnalysisSeries> getSerieses() {
        return serieses;
    }

    public void setSerieses(ArrayList<AnalysisSeries> serieses) {
        this.serieses = serieses;
    }

    public Integer getWeekType() {
        return weekType;
    }

    public void setWeekType(Integer weekType) {
        this.weekType = weekType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getLastOrNextWeek() {
        return lastOrNextWeek;
    }

    public void setLastOrNextWeek(Integer lastOrNextWeek) {
        this.lastOrNextWeek = lastOrNextWeek;
    }
}
