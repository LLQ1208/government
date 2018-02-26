package com.acsm.training.dao;

import com.acsm.training.model.CoachCourseSign;
import com.acsm.training.model.CoachCourseSign;

/**
 * Created by lq on 2017/12/24.
 */
public interface CoachCourseSignDao extends BaseDao<CoachCourseSign>{
    CoachCourseSign querySign(Integer boxId,Integer coachId,Integer bossId,String date,Integer coachCourseId);

}
