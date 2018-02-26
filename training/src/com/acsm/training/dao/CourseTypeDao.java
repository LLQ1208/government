package com.acsm.training.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;

public interface CourseTypeDao extends BaseDao<CourseType> {

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public CourseType queryById(Integer id);

	/**
	 * 查询所有课程类型
	 * @param boxId
	 * @return
	 */
	public List<CourseType> queryList(Integer boxId);

	/**
	 * 获取分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<CourseType> queryPageByConditions(Map<String, Object> conditions);

	public PageHelper<CourseType> newQueryPageByConditions(Map<String, Object> conditions);

	public List<CourseType> queryListByUserId(int userId);

	List<CourseType> queryListByBoxIdAndDate(Integer boxId, Date date);
	
	
}
