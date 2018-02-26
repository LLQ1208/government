package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.Content;

/**
 * Created by lq on 2017/12/2.
 */
public interface ContentService {
    public List<Content> queryAllContent();

    public void addContent(Content content);

    public void updateContent(Integer contentId,String name,String description,Integer recordType,Integer isRx,Integer recordTypeFamousWod, Integer eachRoundNum ,Integer eachRoundRecordType);

    public void delContent(Integer contentId);

    public List<Content> queryListOfBoss(int bossId);

    public Content queryContentById(int contentId);


}
