package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/18.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
public class Weightlifting implements Serializable{
    private static final long serialVersionUID = 8063266005588310720L;
    private String maxSource;
    private String name;
    private List<WeightData> personalData;
    private Integer[] percentage;
    private List<Series> serieses;
    private Integer historySourceCount;
    private  List<HistorySource> historySource;

    public String getMaxSource() {
        return maxSource;
    }

    public void setMaxSource(String maxSource) {
        this.maxSource = maxSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WeightData> getPersonalData() {
        return personalData;
    }

    public void setPersonalData(List<WeightData> personalData) {
        this.personalData = personalData;
    }

    public Integer[] getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer[] percentage) {
        this.percentage = percentage;
    }

    public List<Series> getSerieses() {
        return serieses;
    }

    public void setSerieses(List<Series> serieses) {
        this.serieses = serieses;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getHistorySourceCount() {
        return historySourceCount;
    }

    public void setHistorySourceCount(Integer historySourceCount) {
        this.historySourceCount = historySourceCount;
    }

    public List<HistorySource> getHistorySource() {
        return historySource;
    }

    public void setHistorySource(List<HistorySource> historySource) {
        this.historySource = historySource;
    }
}
