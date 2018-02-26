package com.acsm.training.dao;

import com.acsm.training.model.CoachCourseRest;

import java.util.List;

/**
 * Created by lq on 2017/12/23.
 */
public interface CoachCourseRestDao extends BaseDao<CoachCourseRest>{
    CoachCourseRest queryById(Integer id);
    List<CoachCourseRest> queryListByDate(Integer boxId,Integer coachId, Integer bossId,
                                          String beginTime,String endTime);
    CoachCourseRest queryByDate(Integer boxId,Integer coachId, Integer bossId,String date);
}
