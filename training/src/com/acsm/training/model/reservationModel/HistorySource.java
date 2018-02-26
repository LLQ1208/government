package com.acsm.training.model.reservationModel;/**
 * Created by lq on 2017/12/18.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
public class HistorySource implements Serializable{

    private static final long serialVersionUID = 5290195954466851838L;

    private String recordSource;
    private String remark;
    private String time;

    public String getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
