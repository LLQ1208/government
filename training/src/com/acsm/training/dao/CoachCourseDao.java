package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.CoachCourse;
import com.acsm.training.model.CoachCourse;

/**
 * Created by lq on 2017/12/12.
 */
public interface CoachCourseDao extends BaseDao<CoachCourse> {
	public CoachCourse queryById(int coachCourseId);

	public List<CoachCourse> queryListByCoachId(int coachId);

	List<CoachCourse> queryListByCoachIdAndWeek(int coachId, int week);

	public List<CoachCourse> queryListByBossId(int bossId,int boxId);

}
