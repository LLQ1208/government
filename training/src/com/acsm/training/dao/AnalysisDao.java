package com.acsm.training.dao;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 14:09 2017/12/22
 */
public interface AnalysisDao extends BaseDao<Object[]>{
    //会员上课统计
    public List<Object[]> memberCourseAnalysisList(Integer bossId, Integer boxId, Integer courseTypeId, String beginTime, String endTime);

    //人员变动增加
    public List<Object[]> memberCountChangeAddAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //人员变动减少
    public List<Object[]> memberCountChangeDecreaseAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //人员总数
    public List<Object[]> memberSumAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //教练团体课上课人数统计
    public List<Object[]> coachTakeClassesAnalysisGroupList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //教练团体课出勤统计
    public List<Object[]> coachAttendanceAnalysisGroupList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //教练私教课上课人数统计
    public List<Object[]> coachTakeClassAnalysisPersonalList(Integer bossId,Integer boxId, String beginTime, String endTime);

    //教练私教课出勤统计
    public List<Object[]> coachAttendanceAnalysisPersonalList(Integer bossId,Integer boxId, String beginTime, String endTime);
}
