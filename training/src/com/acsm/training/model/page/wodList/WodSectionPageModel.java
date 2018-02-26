package com.acsm.training.model.page.wodList;/**
 * Created by lq on 2017/12/10.
 */

import java.io.Serializable;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-10
 */
public class WodSectionPageModel implements Serializable{
    private static final long serialVersionUID = -4628634678539239719L;
    private Integer wodSectionId;
    private String wodSectionName;
    private String comment;
    private Integer type;
    private List<WodContentPageModel> wodContentPageModelList;

    public Integer getWodSectionId() {
        return wodSectionId;
    }

    public void setWodSectionId(Integer wodSectionId) {
        this.wodSectionId = wodSectionId;
    }

    public String getWodSectionName() {
        return wodSectionName;
    }

    public void setWodSectionName(String wodSectionName) {
        this.wodSectionName = wodSectionName;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<WodContentPageModel> getWodContentPageModelList() {
        return wodContentPageModelList;
    }

    public void setWodContentPageModelList(List<WodContentPageModel> wodContentPageModelList) {
        this.wodContentPageModelList = wodContentPageModelList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
