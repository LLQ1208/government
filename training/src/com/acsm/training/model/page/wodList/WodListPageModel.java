package com.acsm.training.model.page.wodList;/**
 * Created by lq on 2017/12/10.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-10
 */
public class WodListPageModel implements Serializable{

    private static final long serialVersionUID = -1559950402464727964L;

    private List<WodOnePageModel> wodPageModelList;
    private Integer weekType;
    private String beginDate;
    private String endDate;
    private Integer boxId;
    private Integer courseTypeId;
    private Integer lastOrNextWeek;
    private String today;
    private String title;

    public List<WodOnePageModel> getWodPageModelList() {
        return wodPageModelList;
    }

    public void setWodPageModelList(List<WodOnePageModel> wodPageModelList) {
        this.wodPageModelList = wodPageModelList;
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

    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    public Integer getLastOrNextWeek() {
        return lastOrNextWeek;
    }

    public void setLastOrNextWeek(Integer lastOrNextWeek) {
        this.lastOrNextWeek = lastOrNextWeek;
    }

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
