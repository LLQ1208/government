package com.acsm.training.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.model.basic.PageHelper;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.CourseTypeDao;
import com.acsm.training.model.CourseType;
import com.acsm.training.service.CourseTypeService;

/**
 * 
 * @author liuxiaobing
 *
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl implements CourseTypeService {
	
	@Resource(name="courseTypeDao")
	private CourseTypeDao courseTypeDao;

	@Override
	public CourseType queryById(Integer id) {
		return courseTypeDao.queryById(id);
	}

	@Override
	public CourseType save(CourseType courseType) {
		return courseTypeDao.add(courseType);
	}

	@Override
	public void update(CourseType courseType) {
		courseTypeDao.update(courseType);
	}

	@Override
	public List<CourseType> queryList(Integer boxId) {
		return courseTypeDao.queryList(boxId);
	}

	@Override
	public PageHelper<CourseType> queryPageByConditions(Map<String, Object> conditions) {
		return courseTypeDao.queryPageByConditions(conditions);
	}
	@Override
	public PageHelper<CourseType> newQueryPageByConditions(Map<String, Object> conditions) {
		return courseTypeDao.newQueryPageByConditions(conditions);
	}

	@Override
	public void delete(int id) {
		CourseType courseType = courseTypeDao.queryById(id);
		courseType.setIsDeleted(1);
		courseTypeDao.update(courseType);
	}

	@Override
	public List<CourseType> queryCourseTypListOfUser(int userId) {
		return courseTypeDao.queryListByUserId(userId);
	}

	@Override
	public List<CourseType> queryListByBoxIdAndDate(Integer boxId,Date date) {
		return courseTypeDao.queryListByBoxIdAndDate(boxId, date);
	}
}
