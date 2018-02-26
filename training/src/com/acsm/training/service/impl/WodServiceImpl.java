package com.acsm.training.service.impl;/**
 * Created by lq on 2017/11/30.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.model.page.ContentContent;
import com.acsm.training.model.page.WodModel;
import com.acsm.training.service.WodService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.model.page.WodPageModel;
import com.acsm.training.model.page.WodSectionModel;
import com.acsm.training.util.WodUtil;
import com.google.gson.Gson;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
@Service
public class WodServiceImpl implements WodService {
    private static Logger log = Logger.getLogger(WodServiceImpl.class);

    @Autowired
    WodDao wodDao;
    @Autowired
    WodContentDao wodContentDao;
    @Autowired
    WodTopRelationDao wodTopRelationDao;
    @Autowired
    ContentDao contentDao;
    @Autowired
    WodSectionDao wodSectionDao;
    @Autowired
    SectionDao sectionDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    SettingDao settingDao;
    @Override
    public List<WodModel> queryWodList(Map map){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String beginDate = map.get("beginDate").toString();
            String weekType = map.get("weekType").toString();
            List<WodModel> wodModelList = new ArrayList<>();
            int days = Integer.valueOf(weekType) * 7;
            for(int i=0; i < days; i++){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(beginDate));
                WodModel wodModel = new WodModel();
                wodModel.setIndex(i+1);
                calendar.add(Calendar.DAY_OF_MONTH, i);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                wodModel.setDay(day + "");
                Date thisDate = calendar.getTime();
                wodModel.setDate(sdf.format(thisDate));
                wodModelList.add(wodModel);
            }
            if(null != map.get("boxId") && !"".equals(map.get("boxId").toString())
                    && null != map.get("courseId") && !"".equals(map.get("courseId").toString())){
                List<Wod> wodList = wodDao.queryWodList(map);
                //通过日期匹配
                for(WodModel wodModel : wodModelList){
                    for(Wod wod : wodList){
                        String wodDate = sdf.format(wod.getwDate());
                        if(wodModel.getDate().equals(wodDate)){
                            wodModel.setWod(wod);
                            wodModel.setSectionModelList(getSectionModelOfWod(wod.getId()));
                        }
                    }
                }
            }
            return wodModelList;
        }catch(Exception e){
            e.printStackTrace();
//            log.error("WodService.queryWodList() error "+ e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteWod(int wodId) {
        Wod wod = wodDao.queryWodById(wodId);
        wod.setIsDeleted(1);
        wodDao.update(wod);
        List<WodTopRelation> wtrList = wodTopRelationDao.queryListByWodId(wodId);
        for(WodTopRelation wtr : wtrList){
            wtr.setIsDeleted(-1);
            wodTopRelationDao.update(wtr);
        }
    }

    @Override
    public WodModel queryWodModelByWodId(int wodId) {
        WodModel wodModel = new WodModel();
        wodModel.setWod(wodDao.queryWodById(wodId));
        wodModel.setSectionModelList(getSectionModelOfWod(wodId));
        return wodModel;
    }


    @Override
    public Wod queryWodById(int id) {
        return wodDao.queryWodById(id);
    }


    @Override
    public void addWodSection(WodSection wodSection) {
        wodSectionDao.add(wodSection);
    }

    @Override
    public List<WodSection> queryWodScetionOfWod(int wodId) {
        List<WodSection> wodSectionList =wodSectionDao.queryWodSectionByWodIdDesc(wodId);
        for(WodSection ws : wodSectionList){
            ws.setSection(sectionDao.querySectionById(ws.getSectionId()));
        }
        return wodSectionList;
    }

    /**
     * 查找wod下的section ->content
     * @param wodId
     * @return
     */
    private List<WodSectionModel> getSectionModelOfWod(int wodId){
        List<WodSectionModel> sectionModelList = new ArrayList<>();
        List<WodSection> sectionList = wodSectionDao.queryWodSectionByWodIdAsc(wodId);
        for(WodSection ws : sectionList){
            ws.setSection(sectionDao.queryById(ws.getSectionId()));
        }
        if(null != sectionList){
            for(WodSection wodSection : sectionList){
                WodSectionModel wodSectionModel = new WodSectionModel();
                wodSectionModel.setWodSection(wodSection);
                List<WodContent> wodContentList = wodContentDao.queryWodContentListByWodSectionId(wodSection.getWodSectionId());
                for(WodContent wodContent : wodContentList){
                    if(null != wodContent.getContentId() && 0 != wodContent.getContentId()){
                        wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                    }else{
                        wodContent.setContentEntity(new Content());
                    }
                    if(wodContent.getContentType() == 4 || wodContent.getContentType() == 5){
                        wodContent.setContentRecordTypeName(WodUtil.queryRecordName(wodContent.getContentEntity().getRecordType()));
                    }
                    if(wodContent.getContentType() == 9){
                        if(wodContent.getConentContent() == null ){
                            wodContent.setContentRecordTypeName(WodUtil.queryRecordName(1));
                        }else{
                            ContentContent content = new Gson().fromJson(wodContent.getContent(),ContentContent.class);
                            wodContent.setContentRecordTypeName(WodUtil.queryRecordName(content.getMetconType()));
                        }
                    }
                }
                wodSectionModel.setWodContentList(wodContentList);
                sectionModelList.add(wodSectionModel);
            }
        }
        return sectionModelList;
    }

    @Override
    public Integer addWod(Wod wod, Integer crouseTypeId, List<Integer> boxIds) {
        Wod wodNew = wodDao.add(wod);
        for (int i = 0; i < boxIds.size(); i++) {
            WodTopRelation wodTopRelation = new WodTopRelation();
            wodTopRelation.setBoxId(boxIds.get(i));
            wodTopRelation.setCourseId(crouseTypeId);
            wodTopRelation.setWodId(wodNew.getId());
            wodTopRelation.setIsDeleted(0);
            wodTopRelationDao.add(wodTopRelation);
        }
        return wodNew.getId();
    }

    @Override
    public WodSection queryWodSectionById(int wodSectionId) {
        WodSection wodSection = wodSectionDao.queryWodSectoinById(wodSectionId);
        wodSection.setSection(sectionDao.querySectionById(wodSection.getSectionId()));
        return wodSection;
    }

    @Override
    public void updateWodSection(WodSection wodSection) {
        wodSectionDao.update(wodSection);
    }

    @Override
    public void deleteWodSection(int wodSectionId) {
        WodSection wodSection = wodSectionDao.queryWodSectoinById(wodSectionId);
        wodSection.setIsDeleted(-1);
        wodSectionDao.update(wodSection);
    }

    @Override
    public WodContent addgeneralWodContent(WodContent wodContent, int contentId) {
        List<WodContent> wodContentList = wodContentDao.queryWodContentListDesc(wodContent.getWodSectionId(),wodContent.getWodId());

        //如果对应的wodsectionId为0，则创建一个没有意义的wodsection
        int wodSectionId = wodContent.getWodSectionId();
        if(wodContent.getWodSectionId() == 0){
            WodSection queryWodSection = wodSectionDao.queryWodSectionByType(wodContent.getWodId(),0);
            if(null != queryWodSection && queryWodSection.getWodSectionId() != null){
                wodSectionId = queryWodSection.getWodSectionId();
            }else{
                WodSection nullSection = new WodSection();
                nullSection.setWodId(wodContent.getWodId());
                nullSection.setSectionId(0);
                nullSection.settIndex(1);
                nullSection.setIsDeleted(0);
                nullSection.setInsertTime(new Date());
                nullSection.setType(0);
                wodSectionDao.add(nullSection);
                wodSectionId = nullSection.getWodSectionId();
            }
        }
        Content content = contentDao.queryContentById(contentId);
        wodContent.setWodSectionId(wodSectionId);
        wodContent.setContentEntity(content);
        wodContent.setContentId(contentId);
        wodContent.settIndex(1);
        wodContent.setIsDeleted(0);
        wodContent.setContentType(content.getContentType());
        wodContent.setRecordType(content.getRecordType());
        if(null != wodContentList && wodContentList.size() > 0){
            wodContent.settIndex(wodContentList.get(0).gettIndex() + 1);
        }
        wodContent.setInsertTime(new Date());
        wodContent.setCanEdit(0);
//        if(wodContent.getContentType() == 4 || wodContent.getContentType() == 5){
//            ContentContent contentStr = new ContentContent();
//            if(null == content.getIsRx() || 0 == content.getIsRx()){
//                contentStr.setIsRx(0);
//            }else{
//                contentStr.setIsRx(1);
//            }
//            wodContent.setContent(new Gson().toJson(contentStr));
//        }
        wodContentDao.add(wodContent);
        WodContent newWodContent = wodContentDao.queryWodContentById(wodContent.getWodContentId());
        if(null != newWodContent.getContentId() && 0 != newWodContent.getContentId()){
            newWodContent.setContentEntity(contentDao.queryContentById(newWodContent.getContentId()));
        }else{
            newWodContent.setContentEntity(new Content());
        }
        newWodContent.setDescript(newWodContent.getContentEntity().getDescription());
        if(newWodContent.getContentType() == 4 || newWodContent.getContentType() == 5){
            newWodContent.setContentRecordTypeName(WodUtil.queryRecordName(newWodContent.getContentEntity().getRecordType()));
        }
        return newWodContent;
    }

    @Override
    public List<WodContent> queryWodContentOfSectionDesc(int wodSectoinId, int wodId) {
        return null;
    }

    @Override
    public WodContent queryWodContentById(int wodContentId) {
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        if(null != wodContent.getContentId() && 0 != wodContent.getContentId()){
            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
        }else{
            wodContent.setContentEntity(new Content());
        }
        if(wodContent.getContentType() == 4 || wodContent.getContentType() == 5){
            wodContent.setContentRecordTypeName(WodUtil.queryRecordName(wodContent.getContentEntity().getRecordType()));
        }
        if(wodContent.getContentType() == 9){
            if(wodContent.getConentContent() == null ){
                wodContent.setContentRecordTypeName(WodUtil.queryRecordName(1));
            }else{
                ContentContent content = new Gson().fromJson(wodContent.getContent(),ContentContent.class);
                wodContent.setContentRecordTypeName(WodUtil.queryRecordName(content.getMetconType()));
            }
        }
        return wodContent;
    }

    @Override
    public WodContent addCustonmWodContent(WodContent wodContent, User user, int contentType) {
        wodContent.setContentId(0);
        wodContent.settIndex(1);
        wodContent.setContentType(contentType);
        List<WodContent> wodContentList =
                wodContentDao.queryWodContentListDesc(wodContent.getWodSectionId(),wodContent.getWodId());
        //如果对应的wodsectionId为0，则创建一个没有意义的wodsection
        int wodSectionId = wodContent.getWodSectionId();
        if(wodContent.getWodSectionId() == 0){
            WodSection queryWodSection = wodSectionDao.queryWodSectionByType(wodContent.getWodId(),0);
            if(null != queryWodSection && queryWodSection.getWodSectionId() != null){
                wodSectionId = queryWodSection.getWodSectionId();
            }else{
                WodSection nullSection = new WodSection();
                nullSection.setWodId(wodContent.getWodId());
                nullSection.setSectionId(0);
                nullSection.settIndex(1);
                nullSection.setIsDeleted(0);
                nullSection.setInsertTime(new Date());
                nullSection.setType(0);
                wodSectionDao.add(nullSection);
                wodSectionId = nullSection.getWodSectionId();
            }
        }
        if(null != wodContentList && wodContentList.size() > 0){
            wodContent.settIndex(wodContentList.get(0).gettIndex() + 1);
        }
        wodContent.setWodSectionId(wodSectionId);
        wodContent.setIsDeleted(0);
        wodContent.setInsertTime(new Date());
        wodContent.setCanEdit(0);
        wodContentDao.add(wodContent);
        return wodContent;
    }

    @Override
    public void updateWodContent(WodContent wodContent) {
        wodContentDao.update(wodContent);
    }

    @Override
    public void deleteWodContent(int wodContentId) {
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        wodContent.setIsDeleted(-1);
        wodContentDao.update(wodContent);
    }

    @Override
    public void updateSortWodContent(int wodContentId, int oldWodSectionId, int newWodSectionId,Integer preWodContentId) {
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        if(null != wodContent.getContentId() && 0 != wodContent.getContentId()){
            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
        }else{
            wodContent.setContentEntity(new Content());
        }

        //顺序计算
        wodContent.setWodSectionId(newWodSectionId);
        //查找插入进来的，将顺序计算
        List<WodContent> newWodContentList = wodContentDao.queryWodContentListByWodSectionId(newWodSectionId);
        for(WodContent wc : newWodContentList){
            if(null != wc.getContentId() && 0 != wc.getContentId()){
                wc.setContentEntity(contentDao.queryContentById(wc.getContentId()));
            }else{
                wc.setContentEntity(new Content());
            }
        }
       //如果没有前一个，则为第一个
        if(preWodContentId == null){
            wodContent.settIndex(1);
            for(WodContent wc : newWodContentList){
                if(wc.getWodContentId().intValue() != wodContentId){
                    wc.settIndex(wc.gettIndex() +1);
                    wodContentDao.update(wc);
                }
            }
        }else{//如果有前一个，则顺序比前一个大1
            WodContent preWodContent = wodContentDao.queryWodContentById(preWodContentId);
            if(null != preWodContent.getContentId() && 0 != preWodContent.getContentId()){
                preWodContent.setContentEntity(contentDao.queryContentById(preWodContent.getContentId()));
            }else{
                preWodContent.setContentEntity(new Content());
            }
            wodContent.settIndex(preWodContent.gettIndex()+1);
            for(WodContent wc : newWodContentList){
                if(wc.gettIndex() > preWodContent.gettIndex() && wc.getWodContentId().intValue() != wodContentId){
                    wc.settIndex(wc.gettIndex() +1);
                    wodContentDao.update(wc);
                }
            }
        }
        wodContentDao.update(wodContent);
        if(oldWodSectionId != newWodSectionId){
            updateReSortWodContent(oldWodSectionId);
        }
    }

    @Override
    public List<WodContent> queryWodContentListByWod(int wodId) {
        return wodContentDao.queryListByWodId(wodId);
    }

    @Override
    public void updateSortWodSection(int wodSectionId, int newPreSectionId, String newContentIds) {
        WodSection wodSectoin = wodSectionDao.queryWodSectoinById(wodSectionId);
        List<WodSection> wodSectionList = wodSectionDao.queryWodSectionByWodIdAsc(wodSectoin.getWodId());
        //旧的前一个的顺序
        WodSection oldPreWodSection = null;
        for(WodSection ws : wodSectionList){
            if(ws.getWodSectionId().intValue() == wodSectoin.getWodSectionId()){
                break;
            }
            oldPreWodSection = ws;
        }
        WodSection newPreSection = wodSectionDao.queryWodSectoinById(newPreSectionId);
        //重新排序
        wodSectoin.settIndex(newPreSection.gettIndex()+1);
        wodSectionDao.update(wodSectoin);
        for(WodSection ws : wodSectionList){
            if(ws.gettIndex() > newPreSection.gettIndex() && ws.getWodSectionId().intValue() != wodSectionId){
                ws.settIndex(ws.gettIndex() +1);
                wodSectionDao.update(ws);
            }
        }
        //重新搞从属关系
        List<WodContent> oldWodContentList = wodContentDao.queryWodContentListByWodSectionId(wodSectionId);
        //这些拖动后属于上一个section了
        for(WodContent wc : oldWodContentList){
            wc.setWodSectionId(oldPreWodSection.getWodSectionId());
            wodContentDao.update(wc);
        }
        //wodSectoin新的wod_content
        if(!"".equals(newContentIds)){
            String[] contentIdArr = newContentIds.split(",");
            for(String contentId : contentIdArr){
                WodContent newWodContent = wodContentDao.queryWodContentById(Integer.valueOf(contentId));
                newWodContent.setWodSectionId(wodSectionId);
                wodContentDao.update(newWodContent);
            }
        }
    }

    @Override
    public  List<WodTopRelation>  queryWodTopRelations(int wodId) {
       return  wodTopRelationDao.queryListByWodId(wodId);
    }

    @Override
    public WodTopRelation queryWodTopRelation(int boxId, int courseTypeId, String date) {
        return wodTopRelationDao.queryByCondition(boxId,courseTypeId,date);
    }

    @Override
    public WodPageModel queryWodPageModel(int wodId) {
        WodPageModel wodPageModel = new WodPageModel();
        Wod wod = wodDao.queryWodById(wodId);
        wodPageModel.setWod(wod);
        List<WodTopRelation> wodTopRelationList = wodTopRelationDao.queryListByWodId(wodId);
        wodPageModel.setWodTopRelationList(wodTopRelationList);
        int courseTypeId = wodTopRelationList.get(0).getCourseId();
        wodPageModel.setCourseType(courseTypeId);
        if(null != wod.getAppShowHour()){
            wodPageModel.setTime(wod.getAppShowHour());
        }
        if(null != wod.getAppShowMinuts()){
            wodPageModel.setMinitus(wod.getAppShowMinuts());
        }
        return wodPageModel;
    }

    @Override
    public void deletedWodTopRelation(WodTopRelation wodTopRelation) {
        wodTopRelationDao.update(wodTopRelation);
    }

    @Override
    public void updateWodTopRelation(Wod wod, Integer crouseTypeId, List<Integer> boxIds) {
        List<WodTopRelation> wodTopRelationList = wodTopRelationDao.queryListByWodId(wod.getId());
        for(WodTopRelation wodTopRelation:wodTopRelationList){
            wodTopRelation.setIsDeleted(-1);
            wodTopRelationDao.update(wodTopRelation);
        }
        wodDao.update(wod);
        for (int i = 0; i < boxIds.size(); i++) {
            WodTopRelation wodTopRelation = new WodTopRelation();
            wodTopRelation.setBoxId(boxIds.get(i));
            wodTopRelation.setCourseId(crouseTypeId);
            wodTopRelation.setWodId(wod.getId());
            wodTopRelation.setIsDeleted(0);
            wodTopRelationDao.add(wodTopRelation);
        }
    }


    /**
     * 重新计算顺序
     * @param wodSectionId
     */
    private void updateReSortWodContent(int wodSectionId){
        List<WodContent> newWodContentList = wodContentDao.queryWodContentListByWodSectionId(wodSectionId);
        for(int i=0;i<newWodContentList.size(); i++){
            WodContent wodContent = newWodContentList.get(i);
            wodContent.settIndex(i+1);
            wodContentDao.update(wodContent);
        }
    }




}
