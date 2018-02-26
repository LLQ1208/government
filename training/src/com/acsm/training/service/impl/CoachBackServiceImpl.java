package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/21.
 */


import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.model.page.ContentContent;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.model.AnalysisModel.AnalysisSeries;
import com.acsm.training.model.CoachBack.BoardModel;
import com.acsm.training.model.CoachBack.BoardPage;
import com.acsm.training.service.CoachBackService;
import com.acsm.training.util.Constans;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.WodUtil;
import com.google.gson.Gson;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lianglinqiang
 * @create 2017-12-21
 */
@Service
public class CoachBackServiceImpl implements CoachBackService{

    @Autowired
    SportResultDao sportResultDao;
    @Autowired
    WodContentDao wodContentDao;
    @Autowired
    CoachCourseRestDao coachCourseRestDao;
    @Autowired
    CoachCourseSignDao coachCourseSignDao;
    @Autowired
    CourseSignDao courseSignDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    ContentDao contentDao;
    @Autowired
    MemberDao memberDao;
    @Override
    public BoardPage queryBoardList(int wodContentId) {
        BoardPage boardPage = new BoardPage();
        List<BoardModel> maleList = new ArrayList<>();
        List<BoardModel> fmaleList = new ArrayList<>();
        WodContent wodContent = wodContentDao.queryWodContentById(wodContentId);
        List<SportResult> srList = sportResultDao.querySportResultByWodContent(wodContentId);
        if(null != srList && srList.size() > 0){
            int recordType = srList.get(0).getRecordType();
            if(recordType == 1){//time
                List<Object[]> sportList = sportResultDao.queryListOrderByTime(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[9].toString() + "'" + obj[10].toString() + "\"");
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 4){
                List<Object[]> sportList = sportResultDao.queryListOrderByRoundAndReps(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[6].toString() + "+"+obj[7].toString());
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 11 || recordType == 2 ){
                List<Object[]> sportList = sportResultDao.queryListOrderByGym(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[6].toString() + "x"+obj[7].toString());
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 3){
                List<Object[]> sportList = sportResultDao.queryListOrderByAmrap(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[7].toString() + "Reps");
                        boardModel.setRemark(obj[16] == null ? "" : obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 5){//each round
                List<Object[]> sportList = sportResultDao.queryListOrderByTotal(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        String roundJson = obj[13].toString();
                        JSONArray jsonArray = JSONArray.parseArray(roundJson);
                        int tempNum = 0;
                        for(int i=0; i<jsonArray.size(); i++){
                            JSONObject json = jsonArray.getJSONObject(i);
                            Integer num = Integer.valueOf(json.getString("num"));
                            tempNum += num;
                        }
                        String unitName = "Reps";
                        if(wodContent.getContentType() == 4 || wodContent.getContentType() == 5){
                            Content con = contentDao.queryContentById(wodContent.getContentId());
                            int measure = con.getEachRecordType();
                            if(measure == 1){
                                unitName = "Reps";
                            }else if(measure == 2){
                                unitName = "Time";
                            }else if(measure == 3){
                                unitName = "kg";
                            }else if(measure == 4){
                                unitName = "meters";
                            }else if(measure == 5){
                                unitName = "Cal";
                            }
                        }else{
                            if(wodContent.getContent() != null ){
                                ContentContent contentT = new Gson().fromJson(wodContent.getContent(),ContentContent.class);
                                int measure = contentT.getMeasures();
                                if(measure == 1){
                                    unitName = "Reps";
                                }else if(measure == 2){
                                    unitName = "Time";
                                }else if(measure == 3){
                                    unitName = WodUtil.wodUnitMap.get(contentT.getIn());
                                }else if(measure == 4){
                                    unitName = WodUtil.wodUnitMap.get(contentT.getIn());
                                }else if(measure == 5){
                                    unitName = "Cal";
                                }
                            }
                        }
                        boardModel.setRecord(tempNum + " total " + unitName);
                        boardModel.setTempNum(tempNum);
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 6){//
                List<Object[]> sportList = sportResultDao.queryListOrderByDistance(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        if(wodContent.getContentType() == 4 || wodContent.getContentType() == 5){
                            boardModel.setRecord(obj[12].toString() + "meters");
                        }else{
                            String contentStr = wodContent.getContent();
                            ContentContent contentEntiry = new Gson().fromJson(contentStr,ContentContent.class);
                            boardModel.setRecord(obj[12].toString() + WodUtil.wodUnitMap.get(contentEntiry.getUnit()));
                        }
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 7){//
                List<Object[]> sportList = sportResultDao.queryListOrderByCalor(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[11].toString() + "Cal");
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 10){//
                List<Object[]> sportList = sportResultDao.queryListOrderByWeight(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[6].toString() + "x" + obj[7].toString() + "@" + obj[8].toString() + WodUtil.wodUnitMap.get(Integer.valueOf(obj[14].toString())));
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 11){//
                List<Object[]> sportList = sportResultDao.queryListOrderByWeight(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord(obj[8].toString() + WodUtil.wodUnitMap.get(Integer.valueOf(obj[14].toString())));
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }else if(recordType == 12){//
                List<Object[]> sportList = sportResultDao.queryListOrderByWeight(wodContentId);
                if(null != sportList && sportList.size() > 0){
                    for(Object[] obj : sportList){
                        BoardModel boardModel = new BoardModel();
                        boardModel.setMemberName(obj[0].toString());
                        boardModel.setCourseTitle(obj[1].toString() + " " + obj[2].toString());
                        boardModel.setIsRx(Integer.valueOf(obj[3].toString()));
                        boardModel.setIco(obj[4] != null ? obj[4].toString() : "");
                        boardModel.setRecord( obj[8].toString() + WodUtil.wodUnitMap.get(Integer.valueOf(obj[14].toString())));
                        boardModel.setRemark(obj[16] == null ? "":obj[16].toString());
                        int memberType = Integer.valueOf(obj[15].toString());
                        if(memberType == 0){
                            maleList.add(boardModel);
                        }else if(memberType == 1){
                            fmaleList.add(boardModel);
                        }
                    }
                }
            }
        }
        boardPage.setMaleList(maleList);
        boardPage.setFmaleList(fmaleList);
        return boardPage;
    }

    @Override
    public CoachCourseRest queryCoachCourseRestById(int id) {
        return coachCourseRestDao.queryById(id);
    }

    @Override
    public CoachCourseRest queryCoachCourseRestByDate(Integer boxId, Integer bossId, Integer coachId, String date) {
        return coachCourseRestDao.queryByDate(boxId,bossId,coachId,date);
    }

    @Override
    public List<CoachCourseRest> queryCoachCourseRestList(Integer boxId, Integer coachId, String coachType, String beginTime, String endTime) {
        beginTime += " 00:00:00";
        endTime += " 23:59:59";
        Integer bossId = null;
        if("boss".equals(coachType)){
            bossId = coachId;
            coachId = null;
        }
        List<CoachCourseRest> coachCourseRestList = coachCourseRestDao.queryListByDate(boxId,coachId,bossId,beginTime,endTime);
        if(coachCourseRestList != null){
            for(CoachCourseRest coachCourseRest : coachCourseRestList){
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
                calendar.setTime(coachCourseRest.getDate());
                int week = calendar.get(Calendar.DAY_OF_WEEK);
                week = week-1;
                if(week == 0){
                    week = 7;
                }
                coachCourseRest.setWeekTitle(WodUtil.weekTitleMap.get(week));
            }
        }
        return coachCourseRestList;
    }

    @Override
    public void updateCoachCourseRest(CoachCourseRest coachCourseRest) {
        coachCourseRestDao.update(coachCourseRest);
    }

    @Override
    public void addCoachCourseRest(CoachCourseRest coachCourseRest) {
        coachCourseRestDao.add(coachCourseRest);
    }

    @Override
    public void addCoachCourseSign(CoachCourseSign coachCourseSign) {
        coachCourseSignDao.add(coachCourseSign);
    }

    @Override
    public void updateCoachCourseSign(CoachCourseSign coachCourseSign) {
        coachCourseSignDao.update(coachCourseSign);
    }

    @Override
    public CoachCourseSign queryCoachCourseSign(Integer boxId, Integer coachId, String coachType, String date, Integer coachCourseId) {
        Integer bossId = null;
        if("boss".equals(coachType)){
            bossId = coachId;
            coachId = null;
        }
        return coachCourseSignDao.querySign(boxId,coachId,bossId,date,coachCourseId);
    }

    @Override
    public void addCourseSign(CourseSign courseSign) {
        courseSignDao.add(courseSign);
    }

    @Override
    public void updateCourseSign(CourseSign courseSign) {
        courseSignDao.update(courseSign);
    }

    @Override
    public CourseSign queryCourseSign(Integer courseId, String date) {
        return courseSignDao.queryCourseSign(courseId,date);
    }

    @Override
    public JSONObject querySoprtDetail(Integer courseId, String date) {
        JSONObject resultJson = new JSONObject();
        Course course = courseDao.queryById(courseId);
        //查询这节课对应的所有的动作
        List<WodContent> wodContentList = wodContentDao.queryWodContentOfCourse(courseId,date);
        JSONArray contentArray = new JSONArray();
        JSONObject firstContentScore = new JSONObject();
        //查询动作对应的成绩
        if(null != wodContentList && wodContentList.size() > 0){
            for(int i=0;i<wodContentList.size();i++){
                WodContent wc = wodContentList.get(i);
                //动作列表
                JSONObject contentJson = new JSONObject();
                Content content = contentDao.queryContentById(wc.getContentId());
                contentJson.put("id",content.getContentId());
                contentJson.put("name",content.getName());
                contentJson.put("type",wc.getContentType());
                contentArray.add(contentJson);
                //获取动作的所有成绩
                if(i == 0){
                    firstContentScore = queryContentMaxScoreOfMember(wc.getContentId(),course.getBoxId());
                }
            }
        }
        resultJson.put("contentList",contentArray);
        resultJson.put("contentScore",firstContentScore);
        return resultJson;
    }

    @Override
    public JSONObject queryContentMaxScoreOfMember(Integer contentId,Integer boxId) {
        Content content = contentDao.queryContentById(contentId);
        List<SportResult> sportResultList = sportResultDao.queryListByContent(boxId,contentId);
        Map<Integer,String> memberMap = new HashMap<>();//记录名字
        Map<Integer,Integer> memberScoreMap = new HashMap<>();//记录最大成绩
        //女
        Map<Integer,String> fMemberMap = new HashMap<>();//记录名字
        Map<Integer,Integer> fMemberScoreMap = new HashMap<>();//记录最大成绩
        if(null != sportResultList && sportResultList.size() > 0){
            for(SportResult sr : sportResultList){
                Integer memberId = sr.getMemberId();
                Member member = memberDao.queryById(memberId);

                Integer sets = sr.getSets();
                Integer reps = sr.getReps();
                Integer weight = sr.getWeight() == null ? 0 : sr.getWeight().intValue();
                Integer unit = sr.getUnitType();
                int maxWeight = 0;
                if(content.getContentType() == 3){//重量
                    float unitTransfer = 1;
                    if(unit == null){
                        unit =1;
                    }
                    if(unit == 2){
                        unitTransfer = Constans.LB_TO_KG;
                    }
                    //计算最大的并且全部换算成kg
                    if(sets == 1 && reps == 1){
                        maxWeight = new Float(weight * unitTransfer).intValue();
                    }else{
                        Float tempFloat =  weight * (1+reps/30f) * unitTransfer;
                        maxWeight = tempFloat.intValue();
                    }
                }else if(content.getContentType() == 2){
                    if(sets == 1){
                        maxWeight = reps;
                    }else{
                        maxWeight = reps/sets;
                    }
                }
                if(member.getSex() == 0){
                    //存放最大的
                    if(null == memberScoreMap.get(memberId)){
                        memberScoreMap.put(memberId,maxWeight);
                    }else{
                        Integer oldMaxScore = memberScoreMap.get(memberId);
                        memberScoreMap.put(memberId,(maxWeight > oldMaxScore ? maxWeight:oldMaxScore));
                    }
                    memberMap.put(memberId,member.getName());
                }else if(member.getSex() == 1){
                    //存放最大的
                    if(null == fMemberScoreMap.get(memberId)){
                        fMemberScoreMap.put(memberId,maxWeight);
                    }else{
                        Integer oldMaxScore = fMemberScoreMap.get(memberId);
                        fMemberScoreMap.put(memberId,(maxWeight > oldMaxScore ? maxWeight:oldMaxScore));
                    }
                    fMemberMap.put(memberId,member.getName());
                }

            }
        }
        JSONObject resultJson = new JSONObject();
        /**----------男----start----------*/
        JSONObject maleJson = new JSONObject();
        //姓名
        JSONArray memberArray = new JSONArray();
        for(Map.Entry<Integer,String> mEntry : memberMap.entrySet()){
            memberArray.add(mEntry.getValue());
        }
        //记录成绩
        JSONObject scoreJson = new JSONObject();
        JSONArray scoreDataArray = new JSONArray();
        for(Map.Entry<Integer,Integer> sEntry : memberScoreMap.entrySet()){
            scoreDataArray.add(sEntry.getValue());
        }
        scoreJson.put("name","kg");
        scoreJson.put("data",scoreDataArray);
        maleJson.put("x",memberArray);
        maleJson.put("y",scoreJson);
        /**----------男----end----------*/
        /**----------女----start----------*/
        JSONObject fMaleJson = new JSONObject();
        //姓名
        JSONArray fMemberArray = new JSONArray();
        for(Map.Entry<Integer,String> mEntry : fMemberMap.entrySet()){
            fMemberArray.add(mEntry.getValue());
        }
        //记录成绩
        JSONObject fscoreJson = new JSONObject();
        JSONArray fscoreDataArray = new JSONArray();
        for(Map.Entry<Integer,Integer> sEntry : fMemberScoreMap.entrySet()){
            fscoreDataArray.add(sEntry.getValue());
        }
        fscoreJson.put("name","kg");
        fscoreJson.put("data",fscoreDataArray);
        fMaleJson.put("x",fMemberArray);
        fMaleJson.put("y",fscoreJson);
        /**----------女----end----------*/
        resultJson.put("male",maleJson);
        resultJson.put("fmale",fMaleJson);
        return resultJson;
    }

    @Override
    public JSONObject queryListOfPopulrWod(Integer bossId, Integer boxId,Integer populorType) {
        //查询所有的动作
        List<Content> contentList = contentDao.queryListOfPopulrWod(bossId,boxId,populorType);
        //根据类型查询排名前十的
        JSONArray rankList = new JSONArray();
        if(null != contentList && contentList.size() > 0){
            for(Content content : contentList){
                JSONObject contentJson = new JSONObject();
                Integer contentId = content.getContentId();
                JSONObject listJson = queryRankList(boxId,contentId);
                contentJson.put("contentName",content.getName());
                contentJson.put("maleList",listJson.get("maleList"));
                contentJson.put("fmaleList",listJson.get("fmaleList"));
                rankList.add(contentJson);
            }
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("result",rankList);
        return resultJson;
    }

    @Override
    public JSONObject queryRankList(Integer boxId, Integer contentId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Content content = contentDao.queryContentById(contentId);
        Integer recordType = content.getRecordType();
        JSONArray maleArray = new JSONArray();
        JSONArray fMaleArray = new JSONArray();
        if(recordType == 1){//time
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByTime(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[12].toString()+"‘"+sr[13].toString()+"''");
                        maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByTime(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[12].toString()+"‘"+sr[13].toString()+"''");
                        fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 2){
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByRoundReps(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                    Object[] sr = mSportResultList.get(i);
                    String scoreTime = sdf.format((Date)sr[18]);
                    String[] scoreTimeArr = scoreTime.split(" ");
                    Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                    JSONObject mJson = new JSONObject();
                    mJson.put("memberName",member.getName());
                    mJson.put("day",scoreTimeArr[0]);
                    mJson.put("time",sr[9].toString() + "x" + sr[10].toString());
                    maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByRoundReps(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                    Object[] sr = fmSportResultList.get(i);
                    String scoreTime = sdf.format((Date)sr[18]);
                    String[] scoreTimeArr = scoreTime.split(" ");
                    Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                    JSONObject mJson = new JSONObject();
                    mJson.put("memberName",member.getName());
                    mJson.put("day",scoreTimeArr[0]);
                    mJson.put("time",sr[9].toString() + "x" + sr[10].toString());
                    fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 3){
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByReps(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[10].toString() + "Reps");
                        maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByReps(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[10].toString() + "Reps");
                        fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 4){ //AMRAP-Rounds and Reps
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderBySetsReps(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                    if(i < 10){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[9].toString() + "+" + sr[10].toString());
                        maleArray.add(mJson);
                    }
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderBySetsReps(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[9].toString() + "+" + sr[10].toString());
                        fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 5){//each round
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByEachRound(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                    Object[] sr = mSportResultList.get(i);
                    String scoreTime = sdf.format((Date)sr[18]);
                    String[] scoreTimeArr = scoreTime.split(" ");
                    Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                    String roundStr = sr[16].toString();
                    JSONArray roundArr = JSONArray.parseArray(roundStr);
                    float total = 0;
                    for(int r=0;r<roundArr.size();r++){
                        JSONObject round = roundArr.getJSONObject(r);
                        total += Float.parseFloat(round.getString("num"));
                    }
                    JSONObject mJson = new JSONObject();
                    mJson.put("memberName",member.getName());
                    mJson.put("day",scoreTimeArr[0]);
                    mJson.put("time",total + WodUtil.wodUnitMap.get(sr[17].toString()));
                    maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByEachRound(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                    Object[] sr = fmSportResultList.get(i);
                    String scoreTime = sdf.format((Date)sr[18]);
                    String[] scoreTimeArr = scoreTime.split(" ");
                    Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                    String roundStr = sr[16].toString();
                    JSONArray roundArr = JSONArray.parseArray(roundStr);
                    float total = 0;
                    for(int r=0;r<roundArr.size();r++){
                        JSONObject round = roundArr.getJSONObject(r);
                        total += Float.parseFloat(round.getString("num"));
                    }
                    JSONObject mJson = new JSONObject();
                    mJson.put("memberName",member.getName());
                    mJson.put("day",scoreTimeArr[0]);
                    mJson.put("time",total + WodUtil.wodUnitMap.get(sr[17].toString()));
                    fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 6){//distance
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByDistance(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[15].toString() + WodUtil.wodUnitMap.get(sr[17].toString()));
                        maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByDistance(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[15].toString() + WodUtil.wodUnitMap.get(sr[17].toString()));
                        fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 7){//Calories
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByCalories(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[14].toString() + "Cal");
                        maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByCalories(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[14].toString() + "Cal");
                        fMaleArray.add(mJson);
                }
            }
        }else if(recordType == 10){//weight
            //查男
            List<Object[]> mSportResultList = sportResultDao.queryListOrderByWeight(contentId, boxId, 0);
            if(null != mSportResultList && mSportResultList.size() > 0){
                for(int i=0;i<mSportResultList.size();i++){
                        Object[] sr = mSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[9].toString()+"x"+sr[10].toString() + "@" + sr[11].toString() + WodUtil.wodUnitMap.get(sr[17].toString()));
                        maleArray.add(mJson);
                }
            }
            //女
            List<Object[]> fmSportResultList = sportResultDao.queryListOrderByWeight(contentId, boxId, 1);
            if(null != fmSportResultList && fmSportResultList.size() > 0){
                for(int i=0;i<fmSportResultList.size();i++){
                        Object[] sr = fmSportResultList.get(i);
                        String scoreTime = sdf.format((Date)sr[18]);
                        String[] scoreTimeArr = scoreTime.split(" ");
                        Member member = memberDao.queryById(Integer.valueOf(sr[4].toString()));
                        JSONObject mJson = new JSONObject();
                        mJson.put("memberName",member.getName());
                        mJson.put("day",scoreTimeArr[0]);
                        mJson.put("time",sr[9].toString()+"x"+sr[10].toString() + "@" + sr[11].toString() + WodUtil.wodUnitMap.get(sr[17].toString()));
                        fMaleArray.add(mJson);
                }
            }
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("maleList",maleArray);
        resultJson.put("fmaleList",fMaleArray);
        return resultJson;
    }

    @Override
    public JSONObject queryMemberScore(Integer boxId, Integer memberId, Integer contentType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        JSONObject resultJson = new JSONObject();
        JSONArray recordArr = new JSONArray();
        try{
            if(contentType == 2){
                List<Object[]> recordList = sportResultDao.queryMemberScoreGym(boxId,memberId,contentType);
                if(null != recordList && recordList.size() > 0){
                    for(Object[] obj : recordList){
                        JSONObject record = new JSONObject();
                        record.put("contentName",obj[2].toString());
                        record.put("score",obj[1].toString());
                        record.put("time",sdf.format((Date)obj[3]));
                        record.put("contentId",obj[0].toString());
                        recordArr.add(record);
                    }
                }
            }else if(contentType == 3){
                List<Object[]> recordList = sportResultDao.queryMemberScoreWeight(boxId, memberId, contentType);
                if(null != recordList && recordList.size() > 0){
                    for(Object[] obj : recordList){
                        JSONObject record = new JSONObject();
                        record.put("contentName",obj[2].toString());
                        record.put("score",obj[0]==null?"0":obj[0].toString());
                        record.put("time",sdf.format((Date)obj[3]));
                        record.put("contentId",obj[1]==null?"0":obj[1].toString());
                        recordArr.add(record);
                    }
                }
            }else if(contentType == 4 || contentType == 5){
                List<Object[]> recordList = sportResultDao.queryMemberScoreMetOrPopu(boxId, memberId, contentType);
                if(null != recordList && recordList.size() > 0){
                    for(Object[] obj : recordList){
                        JSONObject record = new JSONObject();
                        if(Integer.valueOf(obj[3].toString()) == 1){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[7].toString()+"'"+obj[8].toString()+"''");
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 2 || Integer.valueOf(obj[3].toString()) == 11){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[4].toString()+"x"+obj[5].toString());
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 3){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[5].toString());
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 4){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[4].toString()+"+"+obj[5].toString());
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 5){
                            Integer contentId = Integer.valueOf(obj[0].toString());
                            Content content = contentDao.queryContentById(contentId);
                            record.put("contentName",obj[1].toString());
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                            Integer measure = content.getEachMeasure();
                            String unitName = "";
                            if(null != measure){
                                if(measure == 1){
                                    unitName = "Reps";
                                }else if(measure == 2){
                                    unitName = "Time";
                                }else if(measure == 3){
                                    unitName = "kg";
                                }else if(measure == 4){
                                    unitName = "meters";
                                }else if(measure == 5){
                                    unitName = "Cal";
                                }
                            }
                            record.put("score",obj[12].toString()+unitName);
                        }else if(Integer.valueOf(obj[3].toString()) == 6){
                            record.put("contentName",obj[1].toString());
//                            Integer unit = Integer.valueOf(obj[11].toString());
                            if(null == obj[11]){
                                record.put("score",obj[10].toString()+"Meters");
                            }else{
                                record.put("score",obj[10].toString()+Constans.DISTANCE_UNIT_M.get(Integer.valueOf(obj[11].toString())));
                            }
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 7){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[9].toString()+"Cal");
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }else if(Integer.valueOf(obj[3].toString()) == 10){
                            record.put("contentName",obj[1].toString());
                            record.put("score",obj[12].toString());
                            record.put("time",sdf.format((Date)obj[2]));
                            record.put("contentId",obj[0].toString());
                        }
                        recordArr.add(record);
                    }
                }
            }
            resultJson.put("list",recordArr);
        }catch(Exception e){
            e.printStackTrace();
        }

        return resultJson;
    }

    @Override
    public JSONObject queryMemberScorePop(Integer boxId, Integer memberId, Integer contentId) {
        Content c = contentDao.queryContentById(contentId);
        JSONObject resultJson = new JSONObject();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        List<Object[]> recordList = sportResultDao.queryMemberScorePop(boxId, memberId, contentId);
        ArrayList<AnalysisSeries> serieses = new ArrayList<AnalysisSeries>();
        JSONArray showArr = new JSONArray();
        String popTitle = "";
        int index = 0;
        AnalysisSeries resultS = new AnalysisSeries();
        ArrayList<Object> lineList = new ArrayList<Object>();
        if(null != recordList && recordList.size() > 0){
            for(Object[] obj : recordList){
                lineList.add(new Object[]{DateUtil.StringToDate(obj[1].toString(), "yyyy-MM-dd HH:mm:ss").getTime(),obj[11] == null ? 0:Integer.valueOf(obj[11].toString())});
                if(index < 5){
                    JSONObject showRecord = new JSONObject();
                    if(Integer.valueOf(obj[2].toString()) == 1){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score",obj[6].toString()+"'"+obj[7].toString()+"''");
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "最短时间:" + obj[6].toString()+"'"+obj[7].toString()+"''";
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 2 || Integer.valueOf(obj[2].toString()) == 11){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score",obj[3].toString()+"x"+obj[4].toString());
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" + obj[3].toString()+"x"+obj[4].toString();
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 3){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score",obj[4].toString());
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" + obj[4].toString();
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 4){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score",obj[3].toString()+"+"+obj[4].toString());
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" +obj[3].toString()+"+"+obj[4].toString();
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 5){
                        Content content = contentDao.queryContentById(contentId);
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("time",sdf.format((Date)obj[1]));

                        Integer measure = content.getEachMeasure();
                        String unitName = "";
                        if(null != measure){
                            if(measure == 1){
                                unitName = "Reps";
                            }else if(measure == 2){
                                unitName = "Time";
                            }else if(measure == 3){
                                unitName = "kg";
                            }else if(measure == 4){
                                unitName = "meters";
                            }else if(measure == 5){
                                unitName = "Cal";
                            }
                        }
                        showRecord.put("score",(obj[11] == null ? 0 : obj[11].toString())+unitName);
                        if(index == 0){
                            popTitle = "1次最大:" +(obj[11] == null ? 0 : obj[11].toString());
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 6){
                        showRecord.put("remark",obj[13].toString());
//                            Integer unit = Integer.valueOf(obj[11].toString());
                        if(null == obj[11]){
                            showRecord.put("score",obj[9].toString()+"Meters");
                        }else{
                            showRecord.put("score",obj[9].toString()+Constans.DISTANCE_UNIT_M.get(Integer.valueOf(obj[11].toString())));
                        }
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" + obj[9].toString();
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 7){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score",obj[8].toString()+"Cal");
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" + obj[8].toString();
                        }
                    }else if(Integer.valueOf(obj[2].toString()) == 10){
                        showRecord.put("remark",obj[13].toString());
                        showRecord.put("score", obj[11] == null ? 0 : obj[11].toString());
                        showRecord.put("time",sdf.format((Date)obj[1]));
                        if(index == 0){
                            popTitle = "1次最大:" + (obj[11] == null ? 0 : obj[11].toString());
                            if(!"1".equals(obj[3].toString()) || !"1".equals(obj[4].toString())){
                                popTitle += "(估算)";
                            }
                        }
                    }
                    showArr.add(showRecord);
                }
                index ++;
            }
        }
        resultS.setName(c==null ? "":c.getName());
        resultS.setData(lineList);
        serieses.add(resultS);
        System.out.println(new Gson().toJson(serieses));
        resultJson.put("charList",serieses);
        resultJson.put("showArr",showArr);
        resultJson.put("popTitle",popTitle);
        resultJson.put("actionName",c==null ? "":c.getName());
        return resultJson;
    }

    @Override
    public JSONObject queryMemberScorePopByPage(Integer boxId, Integer memberId, Integer contentId, Integer page) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        JSONObject resultJson = new JSONObject();
        JSONArray recordArr = new JSONArray();
        List<Object[]> recordList = sportResultDao.queryMemberScorePopByPage(boxId, memberId, contentId,page,5);
        if(null != recordList && recordList.size() > 0){
            for(Object[] obj : recordList){
                JSONObject record = new JSONObject();
                if(Integer.valueOf(obj[2].toString()) == 1){
                    record.put("remark",obj[13].toString());
                    record.put("score",obj[6].toString()+"'"+obj[7].toString()+"''");
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 2 || Integer.valueOf(obj[2].toString()) == 11){
                    record.put("remark",obj[13].toString());
                    record.put("score",obj[3].toString()+"x"+obj[4].toString());
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 3){
                    record.put("remark",obj[13].toString());
                    record.put("score",obj[4].toString());
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 4){
                    record.put("remark",obj[13].toString());
                    record.put("score",obj[3].toString()+"+"+obj[4].toString());
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 5){
                    Content content = contentDao.queryContentById(contentId);
                    record.put("remark",obj[13].toString());
                    record.put("time",sdf.format((Date)obj[1]));

                    Integer measure = content.getEachMeasure();
                    String unitName = "";
                    if(null != measure){
                        if(measure == 1){
                            unitName = "Reps";
                        }else if(measure == 2){
                            unitName = "Time";
                        }else if(measure == 3){
                            unitName = "kg";
                        }else if(measure == 4){
                            unitName = "meters";
                        }else if(measure == 5){
                            unitName = "Cal";
                        }
                    }
                    record.put("score",(obj[11] == null ? 0 : obj[11].toString())+unitName);
                }else if(Integer.valueOf(obj[2].toString()) == 6){
                    record.put("remark",obj[13].toString());
//                            Integer unit = Integer.valueOf(obj[11].toString());
                    if(null == obj[11]){
                        record.put("score",obj[9].toString()+"Meters");
                    }else{
                        record.put("score",obj[9].toString()+Constans.DISTANCE_UNIT_M.get(Integer.valueOf(obj[11].toString())));
                    }
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 7){
                    record.put("remark",obj[13].toString());
                    record.put("score",obj[8].toString()+"Cal");
                    record.put("time",sdf.format((Date)obj[1]));
                }else if(Integer.valueOf(obj[2].toString()) == 10){
                    record.put("remark",obj[13].toString());
                    record.put("score", obj[11] == null ? 0 : obj[11].toString());
                    record.put("time",sdf.format((Date)obj[1]));
                }
                recordArr.add(record);
            }
        }
        resultJson.put("list",recordArr);
        return resultJson;
    }


}
