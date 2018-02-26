package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/18.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
public class WeightData implements Serializable{
    private static final long serialVersionUID = 809566713270474228L;

    private String rpsSource;
    private String rpsTitle;

    public String getRpsSource() {
        return rpsSource;
    }

    public void setRpsSource(String rpsSource) {
        this.rpsSource = rpsSource;
    }

    public String getRpsTitle() {
        return rpsTitle;
    }

    public void setRpsTitle(String rpsTitle) {
        this.rpsTitle = rpsTitle;
    }
}
