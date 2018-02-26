package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Coach;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Coach;
import com.acsm.training.model.basic.PageHelper;

public interface CoachDao extends BaseDao<Coach> {

	/**
	 * 根据编号查询教练
	 * @param id
	 * @return
	 */
	public Coach queryById(Integer id);
	
	/**
	 * 获取所有教练
	 * @return
	 */
	public List<Coach> queryList();

	/**
	 * 查询教练分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<Coach> queryPageByCondition(Map<String, Object> conditions);

	/**
	 * @param boxId
	 * @return
	 */
	public List<Coach> queryListByBoxId(int boxId);

	/**
	 * 查询当前老板的所有的教练
	 * @param userId
	 * @return
	 */
	public List<Coach> queryAllCoachListOfBoss(int userId);
}
