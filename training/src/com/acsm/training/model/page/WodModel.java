package com.acsm.training.model.page;/**
 * Created by lq on 2017/11/30.
 */

import java.io.Serializable;
import java.util.List;

import com.acsm.training.model.Wod;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
public class WodModel implements Serializable{
    private static final long serialVersionUID = -7285953346034799092L;
    private String day;
    private Integer index;
    private String date;
    private Wod wod;
    private List<WodSectionModel> sectionModelList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Wod getWod() {
        return wod;
    }

    public void setWod(Wod wod) {
        this.wod = wod;
    }

    public List<WodSectionModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<WodSectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }


}
