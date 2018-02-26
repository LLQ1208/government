package com.acsm.training.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;

/**
 * @author liuxiaobing
 */
public interface CourseTypeService {
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public CourseType queryById(Integer id);
	
	/**
	 * 保存
	 * @param courseType
	 */
	public CourseType save(CourseType courseType);
	
	/**
	 * 更新
	 * @param courseType
	 */
	public void update(CourseType courseType);
	
	/**
	 * 查询课程类型列表
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

	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);

	public List<CourseType> queryCourseTypListOfUser(int userId);

	List<CourseType> queryListByBoxIdAndDate(Integer boxId, Date date);
}
