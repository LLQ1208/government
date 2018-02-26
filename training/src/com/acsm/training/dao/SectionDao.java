package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Section;

/**
 * Created by lq on 2017/12/2.
 */
public interface SectionDao extends BaseDao<Section>{
    public List<Section> querySectionList();

    public Section querySectionById(int id);

    public boolean updateSection(Integer sectionId,String title);

    public List<Section> queryListOfBoss(int bossId);

    public Section queryById(int id);
}
