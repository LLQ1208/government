package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/2.
 */

import java.util.ArrayList;
import java.util.List;

import com.acsm.training.dao.SectionDao;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.WodActionModelDao;
import com.acsm.training.model.Section;
import com.acsm.training.model.page.WodActionModel;

/**
 * @Author lianglinqiang
 * @create 2017-12-02
 */
@Service
public class SectionServiceImpl implements SectionService {
    @Autowired
    SectionDao sectionDao;
    @Autowired
    WodActionModelDao wodActionModelDao;

    @Override
    public List<Section> querySectionList() {
        return sectionDao.querySectionList();
    }

    /**
     *@Author : RedCometJ
     *@Description :
     *@params  [userId, searchType, pageSize, pageIndex]
     *@return PageHelper<WodActionModel>
     *@Date : 2017/12/4
     */
    @Override
    public PageHelper<WodActionModel> querySectionListChange(Integer userId, Integer searchType,Integer pageSize,Integer pageIndex,String keyword) {
        List<WodActionModel> wodActionModelsList = new ArrayList<WodActionModel>();
        PageHelper pageHelperList = wodActionModelDao.querySectionListChange(userId, searchType,pageSize,pageIndex,keyword);
        List<Object[]> objList = pageHelperList.getList();
        PageHelper<WodActionModel> pageHelper = new PageHelper<WodActionModel>();
        for (int i = 0; i < objList.size(); i++) {
            Object[] o = objList.get(i);
            WodActionModel wodActionModel = new WodActionModel();
            wodActionModel.setSectionId(Integer.valueOf(o[0].toString()));
            wodActionModel.setTitle(o[1].toString());
            wodActionModel.setUserId(Integer.valueOf(o[2].toString()));
            wodActionModelsList.add(wodActionModel);
        }
        pageHelper.setList(wodActionModelsList);
        pageHelper.setCurrentPage(pageHelperList.getCurrentPage());
        pageHelper.setTotalRow(pageHelperList.getTotalRow());
        pageHelper.setPageSize(pageSize);
        pageHelper.setNowPageIndex(pageIndex);
        System.out.println("开始    "+pageHelper.getStart());
        System.out.println("结束 "+pageHelper.getEnd());
        return pageHelper;
    }

    /**
    *@Author : RedCometJ
    *@Description :
    *@params  [userId, searchType, pageSize, pageIndex]
    *@return PageHelper<WodActionModel>
    *@Date : 2017/12/4
    */
    @Override
    public PageHelper<WodActionModel> queryContentListChange(Integer userId, Integer searchType, Integer pageSize, Integer pageIndex,Integer contentType,String keyword) {
        List<WodActionModel> wodActionModelsList = new ArrayList<WodActionModel>();
        PageHelper<WodActionModel> pageHelper = new PageHelper<WodActionModel>();
        PageHelper pageHelperList =  wodActionModelDao.queryContentListChange(userId, searchType,pageSize,pageIndex,contentType,keyword);
        List<Object[]> objList = pageHelperList.getList();

        for (int j = 0; j  < objList.size(); j ++) {
            Object[] o = objList.get(j );
            WodActionModel wodActionModel = new WodActionModel();
            wodActionModel.setContentId(Integer.valueOf(o[0].toString()));
            wodActionModel.setTitle(o[1].toString());
            wodActionModel.setUserId(Integer.valueOf(o[2].toString()) == 174 ? 1 : 0); //自定义0 系统1

            wodActionModel.setNoUseDay(null != o[3] ? Integer.valueOf(o[3].toString()) < 0 ? String.valueOf(Math.abs(Integer.valueOf(o[3].toString()))) : o[3].toString() : "--");
            wodActionModel.setLastUseDay(null != o[4] ? o[4].toString() : "--");
            if(null != o[7]){
               wodActionModel.setRecord(queryRecordName(Integer.valueOf(o[7].toString())));
                if(o[7].toString().equals("9")){
                    wodActionModel.setRecord("Max Reps");
                }
            }else{
                wodActionModel.setRecord("--");
            }
            wodActionModel.setDescriptions(null != o[6] ? o[6].toString() : "");
            wodActionModel.setRecordType(null != o[7] ? Integer.valueOf(o[7].toString()) : 1);
            wodActionModel.setIsRx(null != o[8] ? Integer.valueOf(o[8].toString()) : 0);
            wodActionModel.setRecordTypeFamousWod(null != o[9] ? Integer.valueOf(o[9].toString()) : 1);
            wodActionModel.setEachRoundNum(null != o[10] ? Integer.valueOf(o[10].toString()) : 0);
            wodActionModel.setEachRoundRecordType(null != o[11] ? Integer.valueOf(o[11].toString()) : 1);
            wodActionModelsList.add(wodActionModel);
        }
        pageHelper.setCurrentPage(pageHelperList.getCurrentPage());
        pageHelper.setTotalRow(pageHelperList.getTotalRow());
        pageHelper.setPageSize(pageSize);
        pageHelper.setNowPageIndex(pageIndex);
        pageHelper.setList(wodActionModelsList);

        return pageHelper;
    }

    @Override
    public void updateSection(Integer sectionId, String title) {
        Section section = sectionDao.load(sectionId);
        section.setTitle(title);
        sectionDao.update(section);
//        sectionDao.updateSection(sectionId,title);
    }

    @Override
    public void addSection(String title,Integer userId) {
        Section section = new Section();
        section.setTitle(title);
        section.setUserId(userId);
        section.setIsDeleted(0);
        sectionDao.add(section);
    }

    @Override
    public void delSection(Integer sectionId) {
        Section section = sectionDao.load(sectionId);
        section.setIsDeleted(-1);
        sectionDao.update(section);
    }

    @Override
    public List<Section> querySectionOfBoss(int bossId) {
        return sectionDao.queryListOfBoss(bossId);
    }

    public String queryRecordName(Integer recordTypeId){
        String recordName = "";
        switch (recordTypeId){
            case 1 : recordName = "time"; break;
            case 2 : recordName = "AMRAP-Rounds"; break;
            case 3 : recordName = "AMRAP-Reps"; break;
            case 4 : recordName = "AMRAP-Rounds and Reps"; break;
            case 5 : recordName = "each round"; break;
            case 6 : recordName = "distance"; break;
            case 7 : recordName = "Calories"; break;
            case 8 : recordName = "No Measure"; break;
            case 9 : recordName = "reps"; break;
            case 10 : recordName = "weight"; break;
        }
        return  recordName;
    }
}
