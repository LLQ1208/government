package com.acsm.training.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.acsm.training.model.Course;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.model.Course;
import com.acsm.training.model.page.CourseModel;

public interface CourseService {
	
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
	 * 获取训练馆的周计划
	 * @param boxId
	 * @return
	 */
	public Map<String,List<Course>> getWeekPlanMap(Integer boxId);

	/**
	 * 更新
	 * @param course
	 */
	public void update(Course course);

	/**
	 * 添加
	 * @param course
	 */
	public void save(Course course);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);

	/**
	 * @param week
	 * @param boxId
	 * @return
	 */
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId);

	/**
	 * 根据训练馆编号和课程内容
	 * @param courseTypeId
	 * @param boxId
	 * @return
	 */
	public List<Course> queryListByCoureseTypeIdAndBoxId(Integer courseTypeId, Integer boxId);

	/**
	 * @param week
	 * @param boxId
	 * @param courseTypeId
	 * @return
	 */
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId, Integer courseTypeId);


	public List<Course> queryCourseListOfUser(int userId);

	/**
	 * 查一周的课程
	 * @param boxId 训练馆
	 * @param courseTypeId 课程类型
	 * @param coachId 教练
	 * @return
	 */
	public List<CourseModel> queryCourseOfWeek(Integer boxId,Integer courseTypeId,
											Integer coachId,String startTime,String endTime);


	/**
	 * 查课的颜色
	 * @param boxId
	 * @param courseTypeId
	 * @param coachId
	 * @return
	 */
	public Set<CourseModel> queryCourseColorOfWeek(Integer boxId,Integer courseTypeId, Integer coachId);
	/**
	 * 查询该日期的课程
	 * @param boxId
	 * @param courseTypeId
	 * @param coachId
	 * @param date
	 * @return
	 */
	public List<CourseModel> queryCourseListOfDay(Integer boxId,Integer courseTypeId,
												  Integer coachId,String date);


	/**
	 * 查询当前日期的所有课
	 * @param boxId
	 * @param date
	 * @return
	 */

	public  List<CourseModel> queryReservationClassOfDay(Integer boxId,String date);

	public  List<CourseModel> queryCoachReservationClassOfDay(Integer boxId,Integer coachId,String date);

	List<Course> queryListByWeekAndBoxIdAndUserId(Integer week, Integer boxId, Integer userId,String courseTypeIds,long trainingDate);
}
