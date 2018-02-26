package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Course;
import com.acsm.training.model.Course;

/**
 * 
 * @author liuxiaobing
 *
 */
public interface CourseDao extends BaseDao<Course> {
	/**
	 * 根据ID获取课程安排
	 * @param id
	 * @return
	 */
	public Course queryById(Integer id); 
	
	/**
	 * 获取训练馆的课程计划
	 * @param boxId
	 * @return
	 */
	public List<Course> queryListByBoxId(Integer boxId);

	/**
	 * @param week
	 * @param boxId
	 * @return
	 */
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId);

	/**
	 * @param courseTypeId
	 * @param boxId
	 * @return
	 */
	public List<Course> queryListByCoureseTypeIdAndBoxId(Integer courseTypeId, Integer boxId);

	public List<Course> queryListByWeekAndBoxIdAndUserId(Integer week,Integer boxId, Integer userId,String courseTypeIds,long trainingDate);
	/**
	 * @param week
	 * @param boxId
	 * @param courseTypeId
	 * @return
	 */
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId, Integer courseTypeId);


	public List<Course> queryCourceListByUserId(int userId);

	public List<Course> queryCourseListById(Integer boxId,
												 Integer courseTypeId,Integer coachId);

	public List<Course> queryCourseListOfDay(Integer boxId,Integer courseTypeId,Integer coachId,int week,String date);

	public List<Course> queryReservationClassOfDay(Integer boxId,int week, String date);

	public List<Course> queryCoachReservationClassOfDay(Integer boxId,Integer coachId,int week, String date);
	
	public List<Course> queryListByCoureseTypeIdAndBoxIdAndWeek(Integer courseTypeId, Integer boxId, int week);
	
}
