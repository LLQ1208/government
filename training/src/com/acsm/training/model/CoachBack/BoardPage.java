package com.acsm.training.model.CoachBack;/**
 * Created by lq on 2017/12/21.
 */

import com.acsm.training.model.WodContent;
import com.acsm.training.model.page.WodModel;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.page.WodModel;

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-21
 */
public class BoardPage implements Serializable{
    private static final long serialVersionUID = -1792052697903514207L;

    private List<BoardModel> maleList;
    private List<BoardModel> fmaleList;
    private WodModel wodModel;
    private List<WodContent> wodContentList;
    private String dateWeek;
    public List<BoardModel> getFmaleList() {
        return fmaleList;
    }

    public void setFmaleList(List<BoardModel> fmaleList) {
        this.fmaleList = fmaleList;
    }

    public List<BoardModel> getMaleList() {
        return maleList;
    }

    public void setMaleList(List<BoardModel> maleList) {
        this.maleList = maleList;
    }

    public WodModel getWodModel() {
        return wodModel;
    }

    public void setWodModel(WodModel wodModel) {
        this.wodModel = wodModel;
    }

    public List<WodContent> getWodContentList() {
        return wodContentList;
    }

    public void setWodContentList(List<WodContent> wodContentList) {
        this.wodContentList = wodContentList;
    }

    public String getDateWeek() {
        return dateWeek;
    }

    public void setDateWeek(String dateWeek) {
        this.dateWeek = dateWeek;
    }
}
