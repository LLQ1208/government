package com.acsm.training.model.page.wodList;/**
 * Created by lq on 2017/12/10.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-10
 */
public class WodOnePageModel implements Serializable{
    private static final long serialVersionUID = 3909740914317903638L;
    private String day;
    private Integer index;
    private String date;
    private Integer wodId;
    private String wodName;
    private List<WodSectionPageModel> sectionModelList;
    private List<WodContentPageModel> wodContentList;//sectionId为0的

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

    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }

    public String getWodName() {
        return wodName;
    }

    public void setWodName(String wodName) {
        this.wodName = wodName;
    }

    public List<WodSectionPageModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<WodSectionPageModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }

    public List<WodContentPageModel> getWodContentList() {
        return wodContentList;
    }

    public void setWodContentList(List<WodContentPageModel> wodContentList) {
        this.wodContentList = wodContentList;
    }
}
