package com.acsm.training.model.AnalysisModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:54 2017/12/26
 */
public class AnalysisSeries {
    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<Object> data;
    private String name;
}
