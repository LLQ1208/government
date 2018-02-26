package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.page.CoachCourseModel;
import com.acsm.training.model.CoachCourse;
import com.acsm.training.model.page.CoachCourseModel;

/**
 * Created by lq on 2017/12/12.
 */
public interface CoachCourseService {
    public void saveCoachCourse(CoachCourse coachCourse);

    public CoachCourse queryById(int coachCourseId);

    public List<CoachCourseModel> queryWeekListByCoachId(int coachCourseId,String coachType,String startTime,String endTime,int boxId);

    public void deleteCoachCourse(int coachCourseId);

	List<CoachCourse> queryListByCoachIdAndWeek(int coachId, int week);

    public List<CoachCourseModel> queryListOfCoachBack(int coachId,String coachType,String startTime,
                                                       String endTime,int boxId);

}
