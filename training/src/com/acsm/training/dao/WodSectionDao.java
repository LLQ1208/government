package com.acsm.training.dao;


import java.util.List;

import com.acsm.training.model.WodSection;
import com.acsm.training.model.WodSection;

/**
 * Created by lq on 2017/12/1.
 */
public interface WodSectionDao extends BaseDao<WodSection>{
    List<WodSection> queryWodSectionByWodIdAsc(int wodId);

    List<WodSection> queryWodSectionByWodIdDesc(int wodId);

    WodSection queryWodSectoinById(int wodSectionId);

    WodSection queryWodSectionByType(int wodId,int type);

}
