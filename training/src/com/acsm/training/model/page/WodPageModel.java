package com.acsm.training.model.page;/**
 * Created by lq on 2017/12/6.
 */

import java.io.Serializable;
import java.util.List;

import com.acsm.training.model.Wod;
import com.acsm.training.model.Wod;
import com.acsm.training.model.WodTopRelation;

/**
 * @Author lianglinqiang
 * @create 2017-12-06
 */
public class WodPageModel implements Serializable{
    private static final long serialVersionUID = 5034408234033362350L;
    private Wod wod;
    private List<WodTopRelation> wodTopRelationList;
    private Integer time;
    private Integer minitus;
    private Integer courseType;
    private Boolean isCanEdit;

    public Wod getWod() {
        return wod;
    }

    public void setWod(Wod wod) {
        this.wod = wod;
    }

    public List<WodTopRelation> getWodTopRelationList() {
        return wodTopRelationList;
    }

    public void setWodTopRelationList(List<WodTopRelation> wodTopRelationList) {
        this.wodTopRelationList = wodTopRelationList;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMinitus() {
        return minitus;
    }

    public void setMinitus(Integer minitus) {
        this.minitus = minitus;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public Boolean getIsCanEdit() {
        return isCanEdit;
    }

    public void setIsCanEdit(Boolean isCanEdit) {
        this.isCanEdit = isCanEdit;
    }
}
