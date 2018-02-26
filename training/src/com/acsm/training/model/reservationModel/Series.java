package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/19.
 */

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-19
 */
public class Series {
    private List<SerieY> data;
    private String name;

    public List<SerieY> getData() {
        return data;
    }

    public void setData(List<SerieY> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
