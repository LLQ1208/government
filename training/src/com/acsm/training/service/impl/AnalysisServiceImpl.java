package com.acsm.training.service.impl;

import com.acsm.training.dao.AnalysisDao;
import com.acsm.training.model.AnalysisModel.Analysis;
import com.acsm.training.model.AnalysisModel.AnalysisSeries;
import com.acsm.training.service.AnalysisService;
import com.acsm.training.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 14:07 2017/12/22
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    AnalysisDao analysisDao;

    @Override
    public Analysis memberCourseAnalysisList(Integer bossId,Integer boxId, Integer courseTypeId, String beginTime, String endTime) {
        Analysis analysis = new Analysis();
        List<Object[]> resultObj =  analysisDao.memberCourseAnalysisList(bossId,boxId,courseTypeId,beginTime,endTime);
        Map<Integer,JSONObject>  memberLineMap = new LinkedHashMap<Integer,JSONObject>();
        String startNum = "";
        for (Object[] objects : resultObj) {
            if(null != objects[2] && !"".equals(objects[2])){
                startNum = startNum.compareTo(objects[1].toString()) < 0 ? startNum :objects[1].toString();
                if(memberLineMap.containsKey(Integer.valueOf(objects[2].toString()))){
                    JSONObject eachPoint = new JSONObject();
                    eachPoint.put("count",objects[0]);
                    eachPoint.put("time",objects[1]);
                    memberLineMap.get(objects[2]).getJSONArray("lineArr").add(eachPoint);
                }else{
                    JSONObject lineEach = new JSONObject();
                    JSONObject point = new JSONObject();
                    point.put("count",objects[0]);
                    point.put("time",objects[1]);
                    lineEach.put("name",null != objects[3] && !"".equals(objects[3]) ? objects[3]:"无");
                    JSONArray lineArr = new JSONArray();
                    lineArr.add(point);
                    lineEach.put("lineArr",lineArr);
                    memberLineMap.put(Integer.valueOf(objects[2].toString()),lineEach);
                }
            }
        }
        //highchart数据组装
        ArrayList<AnalysisSeries> serieses = new ArrayList<AnalysisSeries>();

        for (Integer key : memberLineMap.keySet()) {
            ArrayList<Object> lineList = new ArrayList<Object>();
            JSONObject lineJsonObj = memberLineMap.get(key);
            AnalysisSeries resultS = new AnalysisSeries();
            resultS.setName(lineJsonObj.getString("name"));
            for (int i = 0; i < lineJsonObj.getJSONArray("lineArr").size(); i++) {
                JSONObject lineObj = lineJsonObj.getJSONArray("lineArr").getJSONObject(i);
//                JSONObject resultLine = new JSONObject();
//                resultLine.put("y",lineObj.getInteger("count"));
//                resultLine.put("name",lineObj.getString("time"));
//                lineList.add(resultLine);
                lineList.add(new Object[]{DateUtil.StringToDate(lineObj.getString("time"), DateUtil.YYYY_MM_DD).getTime(),lineObj.getInteger("count")});
//                lineList.add(new Object[]{Date.UTC(Integer.valueOf(lineObj.getString("time").substring(0,4)),Integer.valueOf(lineObj.getString("time").substring(5,7)),Integer.valueOf(lineObj.getString("time").substring(8,10)),0,0,0),lineObj.getInteger("count")});
//                  lineList.add(new Object[]{lineObj.getString("time"),lineObj.getInteger("count")});
//                  lineList.add(new Object[]{DateUtil.StringToDate(lineObj.getString("time"),DateUtil.YYYY_MM_DD).getTime(),lineObj.getInteger("count")});
            }
            resultS.setData(lineList);
            serieses.add(resultS);
        }
        analysis.setSerieses(serieses);
        return analysis;
    }

    @Override
    public Analysis memberCountChangeAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        Analysis analysis = new Analysis();
        //会员增加曲线
        List<Object[]> memberAddLine = analysisDao.memberCountChangeAddAnalysisList(bossId,boxId,beginTime,endTime);
        //会员减小曲线
        List<Object[]> memberDecreaseLine = analysisDao.memberCountChangeDecreaseAnalysisList(bossId,boxId,beginTime,endTime);
        //会员每天总数
        List<Object[]> memberSumLine = analysisDao.memberSumAnalysisList(bossId,boxId,beginTime,endTime);
        //highchart数据组装
        ArrayList<AnalysisSeries> serieses = new ArrayList<AnalysisSeries>();
        AnalysisSeries resultS = new AnalysisSeries();
        ArrayList<Object> lineList = new ArrayList<Object>();
        for (Object[] addObj : memberAddLine) {
//            JSONObject resultLine = new JSONObject();
//            resultLine.put("y",addObj[0]);
//            resultLine.put("name",addObj[1]);
//            lineList.add(resultLine);
            lineList.add(new Object[]{DateUtil.StringToDate(addObj[1].toString(),DateUtil.YYYY_MM_DD).getTime(),addObj[0]});
        }
        resultS.setName("增长曲线");
        resultS.setData(lineList);
        serieses.add(resultS);
        resultS = new AnalysisSeries();
        lineList = new ArrayList<Object>();
        for (Object[] decreaseObj : memberDecreaseLine) {
//            JSONObject resultLine = new JSONObject();
//            resultLine.put("y",decreaseObj[0]);
//            resultLine.put("name",decreaseObj[1]);
            lineList.add(new Object[]{DateUtil.StringToDate(decreaseObj[1].toString(),DateUtil.YYYY_MM_DD).getTime(),decreaseObj[0]});
//            lineList.add(resultLine);
        }
        resultS.setName("到期曲线");
        resultS.setData(lineList);
        serieses.add(resultS);
        resultS = new AnalysisSeries();
        lineList = new ArrayList<Object>();
        for (Object[] sumObj : memberSumLine) {
//            JSONObject resultLine = new JSONObject();
//            resultLine.put("y",sumObj[0]);
//            resultLine.put("name",sumObj[1]);
            lineList.add(new Object[]{DateUtil.StringToDate(sumObj[1].toString(),DateUtil.YYYY_MM_DD).getTime(),sumObj[0]});
//            lineList.add(resultLine);
        }
        resultS.setName("总体曲线");
        resultS.setData(lineList);
        serieses.add(resultS);
        analysis.setSerieses(serieses);
        return analysis;
    }

    @Override
    public Analysis coachTakeClassesAnalysisGroupList(Integer bossId,Integer courseType,Integer boxId, String beginTime, String endTime) {
        Analysis analysis = new Analysis();
        List<Object[]> resultObj = new ArrayList<Object[]>();
        if(courseType == 1){
            resultObj = analysisDao.coachTakeClassesAnalysisGroupList(bossId,boxId,beginTime,endTime);
        }
        if(courseType == 2){
            resultObj = analysisDao.coachTakeClassAnalysisPersonalList(bossId,boxId,beginTime,endTime);
        }

        Map<Integer,JSONObject>  memberLineMap = new LinkedHashMap<Integer,JSONObject>();
        String startNum = "";
        for (Object[] objects : resultObj) {
            if(null != objects[2] && !"".equals(objects[2])){
                startNum = startNum.compareTo(objects[1].toString()) < 0 ? startNum :objects[1].toString();
                if(memberLineMap.containsKey(Integer.valueOf(objects[2].toString()))){
                    JSONObject eachPoint = new JSONObject();
                    eachPoint.put("count",objects[0]);
                    eachPoint.put("time",objects[1]);
                    memberLineMap.get(objects[2]).getJSONArray("lineArr").add(eachPoint);
                }else{
                    JSONObject lineEach = new JSONObject();
                    JSONObject point = new JSONObject();
                    point.put("count",objects[0]);
                    point.put("time",objects[1]);
                    lineEach.put("name",null != objects[3] && !"".equals(objects[3]) ? objects[3]:"无");
                    JSONArray lineArr = new JSONArray();
                    lineArr.add(point);
                    lineEach.put("lineArr",lineArr);
                    memberLineMap.put(Integer.valueOf(objects[2].toString()),lineEach);
                }
            }
        }
        //highchart数据组装
        ArrayList<AnalysisSeries> serieses = new ArrayList<AnalysisSeries>();

        for (Integer key : memberLineMap.keySet()) {
            ArrayList<Object> lineList = new ArrayList<Object>();
            JSONObject lineJsonObj = memberLineMap.get(key);
            AnalysisSeries resultS = new AnalysisSeries();
            resultS.setName(lineJsonObj.getString("name"));
            for (int i = 0; i < lineJsonObj.getJSONArray("lineArr").size(); i++) {
                JSONObject lineObj = lineJsonObj.getJSONArray("lineArr").getJSONObject(i);
//                JSONObject resultLine = new JSONObject();
//                resultLine.put("y",lineObj.getInteger("count"));
//                resultLine.put("name",lineObj.getString("time"));
//                resultLine.put("name",DateUtil.StringToDate(lineObj.getString("time"),DateUtil.YYYY_MM_DD));
//                lineList.add(resultLine);
//                lineList.add(new Object[]{Date.UTC(Integer.valueOf(lineObj.getString("time").substring(0,4)),Integer.valueOf(lineObj.getString("time").substring(5,7)),Integer.valueOf(lineObj.getString("time").substring(8,10)),0,0,0),lineObj.getInteger("count")});
//                  lineList.add(new Object[]{lineObj.getString("time"),lineObj.getInteger("count")});
                  lineList.add(new Object[]{DateUtil.StringToDate(lineObj.getString("time"),DateUtil.YYYY_MM_DD).getTime(),lineObj.getInteger("count")});
            }
            resultS.setData(lineList);
            serieses.add(resultS);
        }
        analysis.setSerieses(serieses);
        return analysis;
    }


    @Override
    public Analysis coachAttendanceAnalysisGroupList(Integer bossId,Integer courseType,Integer boxId, String beginTime, String endTime) {
        Analysis analysis = new Analysis();
        List<Object[]> resultObj = new ArrayList<Object[]>();
        if(courseType == 1){
            resultObj = analysisDao.coachAttendanceAnalysisGroupList(bossId,boxId,beginTime,endTime);
        }
        if(courseType == 2){
            resultObj = analysisDao.coachAttendanceAnalysisPersonalList(bossId,boxId,beginTime,endTime);
        }
        Map<Integer,JSONObject>  memberLineMap = new LinkedHashMap<Integer,JSONObject>();
        String startNum = "";
        for (Object[] objects : resultObj) {
            if(null != objects[2] && !"".equals(objects[2])){
                startNum = startNum.compareTo(objects[1].toString()) < 0 ? startNum :objects[1].toString();
                if(memberLineMap.containsKey(Integer.valueOf(objects[2].toString()))){
                    JSONObject eachPoint = new JSONObject();
                    eachPoint.put("count",objects[0]);
                    eachPoint.put("time",objects[1]);
                    memberLineMap.get(objects[2]).getJSONArray("lineArr").add(eachPoint);
                }else{
                    JSONObject lineEach = new JSONObject();
                    JSONObject point = new JSONObject();
                    point.put("count",objects[0]);
                    point.put("time",objects[1]);
                    lineEach.put("name",null != objects[3] && !"".equals(objects[3]) ? objects[3]:"无");
                    JSONArray lineArr = new JSONArray();
                    lineArr.add(point);
                    lineEach.put("lineArr",lineArr);
                    memberLineMap.put(Integer.valueOf(objects[2].toString()),lineEach);
                }
            }
        }
        //highchart数据组装
        ArrayList<AnalysisSeries> serieses = new ArrayList<AnalysisSeries>();

        for (Integer key : memberLineMap.keySet()) {
            ArrayList<Object> lineList = new ArrayList<Object>();
            JSONObject lineJsonObj = memberLineMap.get(key);
            AnalysisSeries resultS = new AnalysisSeries();
            resultS.setName(lineJsonObj.getString("name"));
            for (int i = 0; i < lineJsonObj.getJSONArray("lineArr").size(); i++) {
                JSONObject lineObj = lineJsonObj.getJSONArray("lineArr").getJSONObject(i);
//                JSONObject resultLine = new JSONObject();
//                resultLine.put("y",lineObj.getInteger("count"));
//                resultLine.put("name",lineObj.getString("time"));
//                resultLine.put("name",DateUtil.StringToDate(lineObj.getString("time"),DateUtil.YYYY_MM_DD));
//                lineList.add(resultLine);
//                lineList.add(new Object[]{Date.UTC(Integer.valueOf(lineObj.getString("time").substring(0,4)),Integer.valueOf(lineObj.getString("time").substring(5,7)),Integer.valueOf(lineObj.getString("time").substring(8,10)),0,0,0),lineObj.getInteger("count")});
//                  lineList.add(new Object[]{lineObj.getString("time"),lineObj.getInteger("count")});
                  lineList.add(new Object[]{DateUtil.StringToDate(lineObj.getString("time"),DateUtil.YYYY_MM_DD).getTime(),lineObj.getInteger("count")});
            }
            resultS.setData(lineList);
            serieses.add(resultS);
        }
        analysis.setSerieses(serieses);
        return analysis;
    }
}
