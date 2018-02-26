package com.acsm.training.service;

import java.util.Date;
import java.util.List;

import com.acsm.training.model.CourseContent;

public interface CourseContentService {
	
	/**
	 * 根据编号查询课程安排
	 * @param id
	 * @return
	 */
	public CourseContent queryById(Integer id);
	
	/**
	 * 根据训练馆编号和课程安排日期查询课程安排
	 * @param boxId
	 * @param planDate
	 * @return
	 */
	public CourseContent queryByBoxIdAndPlanDate(Integer boxId, Date planDate);

	/**
	 * 保存
	 * @param courseContent
	 * @return
	 */
	public CourseContent save(CourseContent courseContent);

	/**
	 * 获取月份中已添加课程安排的日期
	 * @param date “yyyy-MM”
	 * @return
	 */
	public List<CourseContent> queryByMonthAndBoxId(String date, Integer boxId);

	/**
	 * 更新
	 * @param courseContent
	 */
	public void update(CourseContent courseContent);

	/**
	 * @param dateStr
	 * @param boxId
	 * @return
	 */
	public CourseContent queryByDateAndBoxId(String dateStr, Integer boxId);

	/**
	 * 获取月份中已添加课程安排的日期
	 * @param date
	 * @param boxId
	 * @param courseTypeId
	 * @return
	 */
    List<CourseContent> queryByMonthAndBoxIdAndCourseTypeId(String date, Integer boxId, int courseTypeId);

	/**
	 *
	 * @param boxId
	 * @param courseTypeId
	 * @param date
	 * @return
	 */
    CourseContent queryByBoxIdAndCourseTypeIdAndPlanDate(Integer boxId, int courseTypeId, Date date);
}
