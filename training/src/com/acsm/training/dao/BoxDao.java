package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Box;

public interface BoxDao extends BaseDao<Box> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Box queryById(Integer id);

	/**
	 * @return
	 */
	public List<Box> queryList();

	public List<Box> queryBoxListByUser(int userId);

	public List<Object[]> queryVaildList(Integer boxId);

	public List<Box> queryBoxListByBoss(int userId);
}
