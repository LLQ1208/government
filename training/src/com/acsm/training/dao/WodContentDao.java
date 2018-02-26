package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodContent;

/**
 * Created by lq on 2017/12/1.
 */
public interface WodContentDao extends BaseDao<WodContent>{
    List<WodContent> queryWodContentListByWodSectionId(int wodSectionId);

    List<WodContent> queryWodContentListDesc(int wodSectionId, int wodId);

    WodContent queryWodContentById(int wodContentId);

    List<WodContent> queryWodContentListAsc(int wodSectionId, int wodId);

    List<WodContent> queryListByWodId(int wodId);

    List<WodContent> queryListByWodIdASC(int wodId);
    
    List<WodContent> queryWodContentOfCourse(Integer courseId,String date);
}
