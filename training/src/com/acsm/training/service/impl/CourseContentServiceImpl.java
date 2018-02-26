package com.acsm.training.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.acsm.training.dao.CourseContentDao;
import com.acsm.training.service.CourseContentService;
import com.acsm.training.util.DateUtil;
import org.springframework.stereotype.Service;

import com.acsm.training.model.CourseContent;

@Service("courseContentService")
public class CourseContentServiceImpl implements CourseContentService {
	
	@Resource
	private CourseContentDao courseContentDao;

	@Override
	public CourseContent queryById(Integer id) {
		return courseContentDao.queryById(id);
	}

	@Override
	public CourseContent queryByBoxIdAndPlanDate(Integer boxId, Date planDate) {
		return courseContentDao.queryByBoxIdAndPlanDate(boxId, planDate);
	}

	@Override
	public CourseContent save(CourseContent courseContent) {
		return courseContentDao.add(courseContent);
	}

	@Override
	public List<CourseContent> queryByMonthAndBoxId(String date,Integer boxId) {
		return courseContentDao.queryByMonthAndBoxId(date, boxId);
	}

	@Override
	public void update(CourseContent courseContent) {
		courseContentDao.update(courseContent);
	}

	@Override
	public CourseContent queryByDateAndBoxId(String dateStr, Integer boxId) {
		return courseContentDao.queryByDateAndBoxId(dateStr,boxId);
	}

	/**
	 * 获取月份中已添加课程安排的日期
	 *
	 * @param date
	 * @param boxId
	 * @param courseTypeId
	 * @return
	 */
	@Override
	public List<CourseContent> queryByMonthAndBoxIdAndCourseTypeId(String date, Integer boxId, int courseTypeId) {
		return courseContentDao.queryByMonthAndBoxIdAndCourseTypeId(date, boxId, courseTypeId);
	}

	/**
	 * @param boxId
	 * @param courseTypeId
	 * @param date
	 * @return
	 */
	@Override
	public CourseContent queryByBoxIdAndCourseTypeIdAndPlanDate(Integer boxId, int courseTypeId, Date date) {
		date= DateUtil.getNowDateShort(date);
		return courseContentDao.queryByBoxIdAndCourseTypeIdAndPlanDate(boxId, courseTypeId, date);
	}

}
