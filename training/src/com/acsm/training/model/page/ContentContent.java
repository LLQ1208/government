package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/4.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2017-12-04
 */
public class ContentContent implements Serializable{
    private static final long serialVersionUID = 9120751478042814080L;
    private Integer sets;
    private Integer resps;
    private Integer isRx;
    private Integer metconType;
    private Integer unit;
    private Integer measures;
    private Integer in;
    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getResps() {
        return resps;
    }

    public void setResps(Integer resps) {
        this.resps = resps;
    }

    public Integer getIsRx() {
        return isRx;
    }

    public void setIsRx(Integer isRx) {
        this.isRx = isRx;
    }

    public Integer getMetconType() {
        return metconType;
    }

    public void setMetconType(Integer metconType) {
        this.metconType = metconType;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getMeasures() {
        return measures;
    }

    public void setMeasures(Integer measures) {
        this.measures = measures;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }
}
