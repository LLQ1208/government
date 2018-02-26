package com.acsm.training.service;

import com.acsm.training.model.AnalysisModel.Analysis;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 13:55 2017/12/22
 */
public interface AnalysisService {
    //上课统计
    public Analysis memberCourseAnalysisList(Integer bossId,Integer boxId, Integer courseTypeId, String beginTime, String endTime);

    //人员变动
    public Analysis memberCountChangeAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //教练上课人数统计
    public Analysis coachTakeClassesAnalysisGroupList(Integer bossId,Integer courseType,Integer boxId, String beginTime, String endTime);

    //教练出勤统计
    public Analysis coachAttendanceAnalysisGroupList(Integer bossId,Integer courseType,Integer boxId, String beginTime, String endTime);
}
