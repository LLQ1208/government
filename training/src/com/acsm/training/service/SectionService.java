package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Section;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.WodActionModel;

/**
 * Created by lq on 2017/12/2.
 */
public interface SectionService {
    public List<Section> querySectionList();

    public PageHelper<WodActionModel> querySectionListChange(Integer userId, Integer searchType,Integer pageSize,Integer pageIndex,String keyword);

    public PageHelper<WodActionModel> queryContentListChange(Integer userId, Integer searchType,Integer pageSize,Integer pageIndex,Integer contentType,String keyword);

    public void updateSection(Integer sectionId,String title);

    public void addSection(String title,Integer userId);

    public void delSection(Integer sectionId);

    public List<Section> querySectionOfBoss(int bossId);
}
