package com.acsm.training.model.page;/**
 * Created by lq on 2017/11/30.
 */

import java.io.Serializable;
import java.util.List;

import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodSection;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodSection;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
public class WodSectionModel implements Serializable{
    private static final long serialVersionUID = 3711869803841850859L;
    private WodSection wodSection;
    private List<WodContent> wodContentList;

    public WodSection getWodSection() {
        return wodSection;
    }

    public void setWodSection(WodSection wodSection) {
        this.wodSection = wodSection;
    }

    public List<WodContent> getWodContentList() {
        return wodContentList;
    }

    public void setWodContentList(List<WodContent> wodContentList) {
        this.wodContentList = wodContentList;
    }
}
