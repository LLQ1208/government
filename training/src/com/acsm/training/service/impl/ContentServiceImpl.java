package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/2.
 */

import java.util.List;

import com.acsm.training.dao.ContentDao;
import com.acsm.training.model.Content;
import com.acsm.training.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author lianglinqiang
 * @create 2017-12-02
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    ContentDao contentDao;
    @Override
    public List<Content> queryAllContent() {
        return contentDao.queryContentList();
    }

    @Override
    public void addContent(Content content) {
        contentDao.add(content);
    }

    @Override
    public void updateContent(Integer contentId, String name, String description,Integer recordType,Integer isRx,Integer recordTypeFamousWod,Integer eachRoundNum ,Integer eachRoundRecordType) {
        Content content = contentDao.load(contentId);
        content.setName(name);
        content.setDescription(description);
        if(null != recordType){
            content.setRecordType(recordType);
            if(recordType == 5 ){
                content.setEachMeasure(eachRoundNum);
                content.setEachRecordType(eachRoundRecordType);
            }
        }
        if(null != isRx){
           content.setIsRx(isRx);
        }
        if(null != recordTypeFamousWod ){
            content.setFamousWodRecordType(recordTypeFamousWod);
        }
        contentDao.update(content);
    }

    @Override
    public void delContent(Integer contentId) {
        Content content = contentDao.load(contentId);
        content.setIsDeleted(-1);
        contentDao.update(content);
    }

    @Override
    public List<Content> queryListOfBoss(int bossId) {
        return contentDao.queryListOfBoss(bossId);
    }

    @Override
    public Content queryContentById(int contentId) {
        return contentDao.queryContentById(contentId);
    }


}
