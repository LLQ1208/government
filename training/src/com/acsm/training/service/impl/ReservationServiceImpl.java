package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/18.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.model.page.ContentContent;
import com.acsm.training.model.reservationModel.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.util.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.service.ReservationService;
import com.acsm.training.util.WodUtil;
import com.google.gson.Gson;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
@Service
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    CourseOrderDao courseOrderDao;
    @Autowired
    WodDao wodDao;
    @Autowired
    WodContentDao wodContentDao;
    @Autowired
    ContentDao contentDao;
    @Autowired
    SportResultDao sportResultDao;
    @Autowired
    CourseSignDao courseSignDao;
    @Autowired
    CourseDao courseDao;

    @Override
    public ReservationListModel queryReservationList(Integer boxId, Integer courseId, String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ReservationListModel resultModel = new ReservationListModel();
            List<CourseOrder> courseOrderList = courseOrderDao.queryReservationList(boxId, courseId, date);
            List<ReservationManModel> maleReservationList = new ArrayList<>();
            List<ReservationManModel> fmaleReservationList = new ArrayList<>();
            for(CourseOrder co : courseOrderList){
                ReservationManModel one = new ReservationManModel();
                one.setCourseOrderId(co.getId());
                one.setMemberId(co.getMemberId());
                one.setIco(co.getMember().getAvatar());
                one.setMemberName(co.getMember().getName());
                one.setCourseId(co.getCourseId());
                one.setCourseTypeId(co.getCourse().getCourseTypeId());
                one.setSignStatus(co.getSignStatus());
                String courseEnd = co.getCourse().getEndTime();
                Date courseEndDate = sdf.parse(date + " " + courseEnd);
                String tempNowStr = sdf.format(new Date());
                if(courseEndDate.getTime() < sdf.parse(tempNowStr).getTime()){
                    one.setCourseEnd(true);
                }
                if(co.getMember().getBoxId().intValue() != boxId){
                    one.setBoxMember(false);
                }
                List<ReservatoinActionModel>  reservatoinActionModelList= new ArrayList<>();
                //查询动作
                Wod wod= wodDao.queryWodByDateAndType(boxId,co.getCourse().getCourseTypeId(),date);
                //如果该课程在当天有对应的wod计划，则把所有的动作查出来
                if(null != wod && null != wod.getId()){
                    one.setWodId(wod.getId());
                    List<WodContent> wodContentList = wodContentDao.queryListByWodId(wod.getId());
                    for(WodContent wodContent : wodContentList){
                        ReservatoinActionModel reservatoinAction = new ReservatoinActionModel();
                        int wodContentType = wodContent.getContentType();
                        reservatoinAction.setWodContentId(wodContent.getWodContentId());
                        reservatoinAction.setContent(wodContent.getContent());
                        if(wodContentType == 1){
                            reservatoinAction.setRecordType(0);
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规warm-up不能记录成绩
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()+" (no measure)");
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            continue;
                        }else if(wodContentType == 2){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            reservatoinAction.setRecordType(11);
                            //常规gymnastics
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                            }
                            //成绩记录
                            //reservatoinAction.set
                        }else if(wodContentType == 3){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规weight
                            reservatoinAction.setRecordType(10);
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                            }
                            //成绩记录
                            //reservatoinAction.set
                        }else if(wodContentType == 4 || wodContentType == 5){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规metcon、popular wod
                            reservatoinAction.setRecordType(wodContent.getContentEntity().getRecordType());
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + " (" + WodUtil.queryRecordName(wodContent.getContentEntity().getRecordType() == null ? 1 : wodContent.getContentEntity().getRecordType()) + ")");
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                if(wodContent.getContentEntity().getRecordType() == 5){
                                    contentEntity.setUnit(wodContent.getContentEntity().getEachMeasure());
                                    contentEntity.setMeasures(wodContent.getContentEntity().getEachRecordType());
                                }else if(wodContent.getContentEntity().getRecordType() == 6){
                                    contentEntity.setUnit(3);
                                }else if(wodContent.getContentEntity().getRecordType() == 10){
                                    contentEntity.setUnit(1);
                                }
                                reservatoinAction.setContentEntity(contentEntity);
                                if(contentEntity.getIsRx() != null && contentEntity.getIsRx() == 1){
                                    reservatoinAction.setHasRxPlus(true);
                                }
                            }else{
                                ContentContent contentEntity = new ContentContent();
                                if(wodContent.getContentEntity().getRecordType() == 5){
                                    contentEntity.setUnit(wodContent.getContentEntity().getEachMeasure());
                                    contentEntity.setMeasures(wodContent.getContentEntity().getEachRecordType());
                                }else if(wodContent.getContentEntity().getRecordType() == 6){
                                    contentEntity.setUnit(3);
                                }else if(wodContent.getContentEntity().getRecordType() == 10){
                                    contentEntity.setUnit(1);
                                }
                                contentEntity.setIsRx(1);
                                reservatoinAction.setContentEntity(contentEntity);
                                reservatoinAction.setHasRxPlus(wodContent.getContentEntity().getIsRx() == null ? true :
                                        (wodContent.getContentEntity().getIsRx() == 1 ? true : false));
                            }
                            reservatoinAction.setContent(new Gson().toJson(reservatoinAction.getContentEntity()));
                            //成绩记录
                            //reservatoinAction.set
                            if(wodContent.getContentEntity().getRecordType() == 8){
                                continue;
                            }
                        }else if(wodContentType == 6){
                            reservatoinAction.setRecordType(0);
                            //自定义warm-up
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            reservatoinAction.setActionTitle(actionTitle + " (no measure)");
                            reservatoinAction.setActionName(actionTitle);
                            continue;
                        }else if(wodContentType == 7){
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            //常规gymnastics
                            reservatoinAction.setActionTitle(actionTitle + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(actionTitle);
                            reservatoinAction.setRecordType(11);
                        }else if(wodContentType == 8){
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            //常规weight
                            reservatoinAction.setRecordType(10);
                            reservatoinAction.setActionTitle(actionTitle + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(actionTitle);
                        }else if(wodContentType == 9){
                            //常规weight
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            reservatoinAction.setActionName(actionTitle);
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                                if(contentEntity.getIsRx() != null && contentEntity.getIsRx() == 1){
                                    reservatoinAction.setHasRxPlus(true);
                                }
                                actionTitle += " " +WodUtil.queryRecordName(contentEntity.getMetconType());
                                reservatoinAction.setRecordType(contentEntity.getMetconType());
                                if(contentEntity.getMetconType() == 10){
                                    reservatoinAction.setRecordType(12);
                                }
                                if(contentEntity.getMetconType() == 8){
                                    continue;
                                }
                            }else {
                                actionTitle += " " +  WodUtil.queryRecordName(1);
                                reservatoinAction.setRecordType(1);
                                reservatoinAction.setHasRxPlus(false);
                            }
                            reservatoinAction.setActionTitle(actionTitle);

                        }

                        //查询该动作又没有记录成绩,如果记录成绩
                        SportResult sportResult = sportResultDao.queryListByWodContent(wodContent.getWodContentId(), co.getMemberId(),co.getId());
                        boolean isRxFlag = false;
                        boolean rxPlusFlag = false;
                        if(null != sportResult){
                            if(sportResult != null && sportResult.getIsRx() == 1){
                                isRxFlag = true;
                            }
                            if(sportResult.getIsRxPlus() != null && sportResult.getIsRxPlus() == 1){
                                rxPlusFlag = true;
                            }
                            reservatoinAction.setSportResultId(sportResult.getSportResultId());
                        }
                        //查询该动作有没有分析弹框
                        if(wodContent.getContentType() == 2 || wodContent.getContentType() == 3){
                            List<SportResult> detailData = sportResultDao.queryListByMemberAndContent(boxId, co.getMemberId(), wodContent.getContentId());
                            if(null != detailData && detailData.size() > 0){
                                reservatoinAction.setContentId(wodContent.getContentId());
                                reservatoinAction.setHasPop(true);
                            }
                        }
                        reservatoinAction.setRx(isRxFlag);
                        reservatoinAction.setRxPlus(rxPlusFlag);
                        if(wodContentType != 1 && wodContentType != 6){
                            reservatoinActionModelList.add(reservatoinAction);
                        }
                    }
                }
                one.setActionModelList(reservatoinActionModelList);
                //男or女
                if(co.getMember().getSex() == 0){
                    maleReservationList.add(one);
                }else if(co.getMember().getSex() == 1){
                    fmaleReservationList.add(one);
                }
            }
            resultModel.setFmaleNum(fmaleReservationList.size());
            resultModel.setMaleNum(maleReservationList.size());
            resultModel.setMaleList(maleReservationList);
            resultModel.setFmaleList(fmaleReservationList);
            return resultModel;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ReservationListModel queryCoachReservationList(Integer boxId, Integer courseId, Integer coachId, String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ReservationListModel resultModel = new ReservationListModel();
            List<CourseOrder> courseOrderList = courseOrderDao.queryCoachReservationList(boxId, courseId,coachId,date);
            List<ReservationManModel> maleReservationList = new ArrayList<>();
            List<ReservationManModel> fmaleReservationList = new ArrayList<>();
            Course course = courseDao.queryById(courseId);
            CourseSign courseSign = courseSignDao.queryCourseSign(courseId,date);
            if(null == courseSign){
                if(new Date().getTime() > sdf.parse(date + " " + course.getEndTime()).getTime()){
                    resultModel.setCoachSignStatus(-1);
                }else{
                    resultModel.setCoachSignStatus(0);
                }
            }else{
                resultModel.setCoachSignStatus(1);
            }
            for(CourseOrder co : courseOrderList){
                ReservationManModel one = new ReservationManModel();
                one.setCourseOrderId(co.getId());
                one.setMemberId(co.getMemberId());
                one.setIco(co.getMember().getAvatar());
                one.setMemberName(co.getMember().getName());
                one.setCourseId(co.getCourseId());
                one.setCourseTypeId(co.getCourse().getCourseTypeId());
                one.setSignStatus(co.getSignStatus());
                String courseEnd = co.getCourse().getEndTime();
                Date courseEndDate = sdf.parse(date + " " + courseEnd);
                if(courseEndDate.getTime() < new Date().getTime()){
                    one.setCourseEnd(true);
                }
                if(co.getMember().getBoxId().intValue() != boxId){
                    one.setBoxMember(false);
                }
                List<ReservatoinActionModel>  reservatoinActionModelList= new ArrayList<>();
                //查询动作
                Wod wod= wodDao.queryWodByDateAndType(boxId,co.getCourse().getCourseTypeId(),date);
                //如果该课程在当天有对应的wod计划，则把所有的动作查出来
                if(null != wod && null != wod.getId()){
                    one.setWodId(wod.getId());
                    List<WodContent> wodContentList = wodContentDao.queryListByWodId(wod.getId());
                    for(WodContent wodContent : wodContentList){
                        ReservatoinActionModel reservatoinAction = new ReservatoinActionModel();
                        int wodContentType = wodContent.getContentType();
                        reservatoinAction.setWodContentId(wodContent.getWodContentId());
                        reservatoinAction.setContent(wodContent.getContent());
                        if(wodContentType == 1){
                            reservatoinAction.setRecordType(0);
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规warm-up不能记录成绩
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()+"(no measure)");
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            continue;
                        }else if(wodContentType == 2){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            reservatoinAction.setRecordType(11);
                            //常规gymnastics
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? "("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                            }
                            //成绩记录
                            //reservatoinAction.set
                        }else if(wodContentType == 3){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规weight
                            reservatoinAction.setRecordType(10);
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                            }
                            //成绩记录
                            //reservatoinAction.set
                        }else if(wodContentType == 4 || wodContentType == 5){
                            wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                            //常规metcon、popular wod
                            reservatoinAction.setRecordType(wodContent.getContentEntity().getRecordType());
                            reservatoinAction.setActionTitle(wodContent.getContentEntity().getName()
                                    + " (" + WodUtil.queryRecordName(wodContent.getContentEntity().getRecordType() == null ? 1 : wodContent.getContentEntity().getRecordType()) + ")");
                            reservatoinAction.setActionName(wodContent.getContentEntity().getName());
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                if(wodContent.getContentEntity().getRecordType() == 5){
                                    contentEntity.setUnit(wodContent.getContentEntity().getEachMeasure());
                                    contentEntity.setMeasures(wodContent.getContentEntity().getEachRecordType());
                                }else if(wodContent.getContentEntity().getRecordType() == 6){
                                    contentEntity.setUnit(3);
                                }else if(wodContent.getContentEntity().getRecordType() == 10){
                                    contentEntity.setUnit(1);
                                }
                                reservatoinAction.setContentEntity(contentEntity);
                                //设置是否有训练计划有rxPlus
                                if(contentEntity.getIsRx() != null && contentEntity.getIsRx() == 1){
                                    reservatoinAction.setHasRxPlus(true);
                                }
                            }else{
                                ContentContent contentEntity = new ContentContent();
                                if(wodContent.getContentEntity().getRecordType() == 5){
                                    contentEntity.setUnit(wodContent.getContentEntity().getEachMeasure());
                                    contentEntity.setMeasures(wodContent.getContentEntity().getEachRecordType());
                                }else if(wodContent.getContentEntity().getRecordType() == 6){
                                    contentEntity.setUnit(3);
                                }else if(wodContent.getContentEntity().getRecordType() == 10){
                                    contentEntity.setUnit(1);
                                }
                                contentEntity.setIsRx(1);
                                reservatoinAction.setContentEntity(contentEntity);
                                reservatoinAction.setHasRxPlus(wodContent.getContentEntity().getIsRx() == null ? true :
                                        (wodContent.getContentEntity().getIsRx() == 1 ? true : false));
                            }
                            reservatoinAction.setContent(new Gson().toJson(reservatoinAction.getContentEntity()));
                            if(wodContent.getContentEntity().getRecordType() == 8){
                                continue;
                            }
                        }else if(wodContentType == 6){
                            reservatoinAction.setRecordType(0);
                            //自定义warm-up
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            reservatoinAction.setActionTitle(actionTitle + " (no measure)");
                            reservatoinAction.setActionName(actionTitle);
                            continue;
                        }else if(wodContentType == 7){
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            //常规gymnastics
                            reservatoinAction.setActionTitle(actionTitle + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(actionTitle);
                            reservatoinAction.setRecordType(11);
                        }else if(wodContentType == 8){
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            //常规weight
                            reservatoinAction.setRecordType(10);
                            reservatoinAction.setActionTitle(actionTitle + (wodContent.getRepsScheme() != null && !"".equals(wodContent.getRepsScheme()) ? " ("+wodContent.getRepsScheme()+")" : "" ));
                            reservatoinAction.setActionName(actionTitle);
                        }else if(wodContentType == 9){
                            //常规weight
                            String actionTitle = (null != wodContent.getContentTitle() && !"".equals(wodContent.getContentTitle()) ? wodContent.getContentTitle() : "Metcon");
                            reservatoinAction.setActionName(actionTitle);
                            if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                                ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                                reservatoinAction.setContentEntity(contentEntity);
                                if(contentEntity.getIsRx() != null && contentEntity.getIsRx() == 1){
                                    reservatoinAction.setHasRxPlus(true);
                                }
                                actionTitle += " " +WodUtil.queryRecordName(contentEntity.getMetconType());
                                reservatoinAction.setRecordType(contentEntity.getMetconType());
                                if(contentEntity.getMetconType() == 10){
                                    reservatoinAction.setRecordType(12);
                                }
                                if(contentEntity.getMetconType() == 8){
                                    continue;
                                }
                            }else {
                                actionTitle += " " +  WodUtil.queryRecordName(1);
                                reservatoinAction.setRecordType(1);
                                reservatoinAction.setHasRxPlus(false);
                            }
                            reservatoinAction.setActionTitle(actionTitle);
                        }

                        //查询该动作又没有记录成绩,如果记录成绩
                        SportResult sportResult = sportResultDao.queryListByWodContent(wodContent.getWodContentId(), co.getMemberId(),co.getId());
                        boolean isRxFlag = false;
                        boolean rxPlusFlag = false;
                        if(null != sportResult){
                            if(sportResult.getIsRx() != null && sportResult.getIsRx() == 1){
                                isRxFlag = true;
                            }
                            if(null != sportResult.getIsRxPlus() && sportResult.getIsRxPlus() == 1){
                                rxPlusFlag = true;
                            }
                            reservatoinAction.setSportResultId(sportResult.getSportResultId());
                        }
                        //查询该动作有没有分析弹框
                        if(wodContent.getContentType() == 2 || wodContent.getContentType() == 3){
                            List<SportResult> detailData = sportResultDao.queryListByMemberAndContent(boxId, co.getMemberId(), wodContent.getContentId());
                            if(null != detailData && detailData.size() > 0){
                                reservatoinAction.setContentId(wodContent.getContentId());
                                reservatoinAction.setHasPop(true);
                            }
                        }
                        reservatoinAction.setRx(isRxFlag);
                        reservatoinAction.setRxPlus(rxPlusFlag);
                        reservatoinActionModelList.add(reservatoinAction);
                    }
                }
                one.setActionModelList(reservatoinActionModelList);
                //男or女
                if(co.getMember().getSex() == 0){
                    maleReservationList.add(one);
                }else if(co.getMember().getSex() == 1){
                    fmaleReservationList.add(one);
                }
            }
            resultModel.setFmaleNum(fmaleReservationList.size());
            resultModel.setMaleNum(maleReservationList.size());
            resultModel.setMaleList(maleReservationList);
            resultModel.setFmaleList(fmaleReservationList);
            return resultModel;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveSportResult(SportResult sportResult) {
        if(null == sportResult.getSportResultId()){
            sportResultDao.add(sportResult);
        }else{
            sportResultDao.update(sportResult);
        }

    }

    @Override
    public Weightlifting queryWeightHistoryData(int boxId,int memberId, int contentId, int wodContentId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Weightlifting weightlifting = new Weightlifting();
        List<SportResult> sportResultList = sportResultDao.queryListByMemberAndContent(boxId,memberId, contentId);
        String title = "";
        int realMaxData = 0;
        int esMaxData = 0;//预估

        Map<String, Integer> personDataMap = new HashMap<>();
        Map<String, SportResult> personObjMap = new HashMap<>();
        List<SerieY> realWeightList = new ArrayList<>();
        List<SerieY> esWeightList = new ArrayList<>();
        for(SportResult sr : sportResultList){
            Integer sets = sr.getSets();
            Integer reps = sr.getReps();
            Integer weight = sr.getWeight().intValue();
            Integer unit = sr.getUnitType();
            //计算最大的
            if(sets == 1 && reps == 1){
                realMaxData = (realMaxData < (weight * Constans.WEIGHT_UNIT_M.get(unit)) ? weight : realMaxData);

                realWeightList.add(new SerieY(sdf.format(sr.getInsertTime()),weight));
            }
            if(sets != 1 || reps != 1){
                Float tempFloat =  weight * (1+reps/30f);
                weight = tempFloat.intValue();
                esMaxData = (esMaxData < (weight * Constans.WEIGHT_UNIT_M.get(unit)) ? weight : esMaxData);
                esWeightList.add(new SerieY(sdf.format(sr.getInsertTime()),weight));
            }
            //个人数据记录
            String key = sets + "x" + reps;
            if(personDataMap.get(key) == null){
                personDataMap.put(key,weight);
                personObjMap.put(key,sr);
            }else{
                if(personDataMap.get(key) < weight ){
                    personDataMap.put(key,weight);
                    personObjMap.put(key,sr);
                }
            }

        }
        //highchart
        Series realS = new Series();
        realS.setData(realWeightList);
        realS.setName("实际");
        Series esS = new Series();
        esS.setData(esWeightList);
        esS.setName("估算");
        List<Series> serieses = new ArrayList<Series>();
        serieses.add(realS);
        serieses.add(esS);
        //个人数据记录
        List<WeightData> personDataList = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : personDataMap.entrySet()){
            SportResult valueResult = personObjMap.get(entry.getKey());
            WeightData weightData = new WeightData();
            weightData.setRpsTitle("重量("+ entry.getKey() + ")");
            weightData.setRpsSource(entry.getValue() + (valueResult.getUnitType() == 1 ? "kg" : "lb"));
            personDataList.add(weightData);
        }
        weightlifting.setPersonalData(personDataList);
        int resultMaxWeight = 0;
        if(realMaxData != 0){
            title = "1次最大：" + realMaxData;
            resultMaxWeight = realMaxData;
        }else{
            title = "1次最大：" + esMaxData + "(估算)";
            resultMaxWeight = esMaxData;
        }
        //百分比重量
        Integer[] percentage = new Integer[9];
        percentage[0] = new Float(resultMaxWeight*0.95).intValue();
        percentage[1] = new Float(resultMaxWeight*0.8).intValue();
        percentage[2] = new Float(resultMaxWeight*0.65).intValue();
        percentage[3] = new Float(resultMaxWeight*0.9).intValue();
        percentage[4] = new Float(resultMaxWeight*0.75).intValue();
        percentage[5] = new Float(resultMaxWeight*0.6).intValue();
        percentage[6] = new Float(resultMaxWeight*0.85).intValue();
        percentage[7] = new Float(resultMaxWeight*0.7).intValue();
        percentage[8] = new Float(resultMaxWeight*0.55).intValue();
        weightlifting.setPercentage(percentage);
        //查询动作名称
        String contentTitle = "";
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        if(wodContent.getContentType() < 6){
            contentTitle = contentDao.queryContentById(wodContent.getContentId()).getName();
        }else{
            contentTitle = (wodContent.getContentTitle() == null ? "Metcon" : wodContent.getContentTitle());
        }
        weightlifting.setMaxSource(title);
        weightlifting.setName(contentTitle);
        weightlifting.setSerieses(serieses);
        System.out.println(new Gson().toJson(serieses));
        weightlifting.setHistorySourceCount(sportResultList.size());
        return weightlifting;
    }

    @Override
    public List<HistorySource> queryWeightHistoryByPage(int boxId, int memberId, int contentId, int page) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int begin = (page-1) * 5;
        List<Object[]> sportResultList = sportResultDao.queryListByPage(boxId,memberId, contentId,begin,5);
        List<HistorySource> historyList = new ArrayList<>();
        for(Object[] obj : sportResultList){
            //成绩历史记录
            HistorySource weightHistory = new HistorySource();
            weightHistory.setRecordSource(obj[0] + "x" + obj[1] + " @ " + obj[2] + getSportUnit(Integer.valueOf(obj[3].toString())));
            weightHistory.setRemark(obj[4] == null ? "" : obj[4].toString() );
            weightHistory.setTime(sdf.format(obj[5]));
            historyList.add(weightHistory);
        }
        return historyList;
    }

    @Override
    public Weightlifting queryGymHistoryData(int boxId, int memberId, int contentId, int wodContentId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Weightlifting weightlifting = new Weightlifting();
        List<SportResult> sportResultList = sportResultDao.queryListByMemberAndContent(boxId,memberId, contentId);
        List<SerieY> rxDataList = new ArrayList<>();
        List<SerieY> noRxDataList = new ArrayList<>();
        int maxReps = 0;
        List<HistorySource> historyList = new ArrayList<>();
        for(SportResult sr : sportResultList){
            Integer sets = sr.getSets();
            Integer reps = sr.getReps();
            Integer rx = sr.getIsRx();
            //计算最大的
            if(rx == 1){
                rxDataList.add(new SerieY(sdf.format(sr.getInsertTime()),reps));
            }else{
                noRxDataList.add(new SerieY(sdf.format(sr.getInsertTime()),reps));
            }
            maxReps = maxReps < reps ? reps : maxReps;

            HistorySource weightHistory = new HistorySource();
            weightHistory.setRecordSource(sr.getSets() + "x" + sr.getReps());
            weightHistory.setRemark(sr.getRemark() == null ? "" : sr.getRemark().toString());
            weightHistory.setTime(sdf.format(sr.getInsertTime()));
            historyList.add(weightHistory);
        }
        weightlifting.setHistorySource(historyList);
        //highchart
        Series realS = new Series();
        realS.setData(rxDataList);
        realS.setName("As Prescribed 曲线");
        Series esS = new Series();
        esS.setData(noRxDataList);
        esS.setName("Scale 曲线");
        List<Series> serieses = new ArrayList<Series>();
        serieses.add(realS);
        serieses.add(esS);
        //查询动作名称
        String contentTitle = "";
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        if(wodContent.getContentType() < 6){
            contentTitle = contentDao.queryContentById(wodContent.getContentId()).getName();
        }else{
            contentTitle = (wodContent.getContentTitle() == null ? "Metcon" : wodContent.getContentTitle());
        }
        weightlifting.setMaxSource("1次最大：" +maxReps);
        weightlifting.setName(contentTitle);
        weightlifting.setSerieses(serieses);
        weightlifting.setHistorySourceCount(sportResultList.size());
        return weightlifting;
    }

    @Override
    public SportResult querySportResultById(Integer sportResultId) {
        return sportResultDao.queryById(sportResultId);
    }

    private String getSportUnit(int type){
        switch (type){
            case 1:
                return "kg";
            case 2:
                return "lb";
            case 3:
                return "mile";
            case 4:
                return "meters";
        }
        return null;
    }

    /**
     * wod content记录方式
     * @param wodContent
     * @return
     */
    @Override
    public int getRecordType(WodContent wodContent){
            int wodContentType = wodContent.getContentType();
            if(wodContentType == 1){//常规warm up
                return 0;
            }else if(wodContentType == 2){//常规gymnastics
                return 11;

            }else if(wodContentType == 3){//常规weight
               return 10;
            }else if(wodContentType == 4 || wodContentType == 5){
                //1,Time 2,AMRAP-Rounds 3,AMRAP-Reps 4 AMRAP-Rounds and Reps 5,Each Round 6,Distance 7,Calories 8,No Measure 9,reps 10，weight
                wodContent.setContentEntity(contentDao.queryContentById(wodContent.getContentId()));
                // 4 常规metcon、5 popular wod
                return wodContent.getContentEntity().getRecordType();
            }else if(wodContentType == 6){  //自定义warm-up
               return 0;

            }else if(wodContentType == 7){ //自定义gymnastics
                return 11;
            }else if(wodContentType == 8){ //自定义常规weight

                return 10;
            }else if(wodContentType == 9){
                //1,Time 2,AMRAP-Rounds 3,AMRAP-Reps 4 AMRAP-Rounds and Reps 5,Each Round 6,Distance 7,Calories 8,No Measure 9,reps   12 自定义metcon weight
                int recordType = 0;
                if(null != wodContent.getContent() && !"".equals(wodContent.getContent())){
                    ContentContent contentEntity = new Gson().fromJson(wodContent.getContent(), ContentContent.class);
                    recordType = contentEntity.getMetconType();
                    if(contentEntity.getMetconType() == 10){ //自定义 举重 metcon 不需要set rep
                        recordType =  12;
                    }
                }else {
                    recordType = 1;
                }
                return recordType;
            }

        return 0;
    }
}
