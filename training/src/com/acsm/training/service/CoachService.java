package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Coach;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Coach;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;

public interface CoachService {
	
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
	 * 获取教练分页
	 * @return
	 */
	public PageHelper<Coach> queryPageByConditions(Map<String,Object> conditions);

	/**
	 * 保存
	 * @param coach
	 * @param user
	 */
	public void save(Coach coach, User user);

	/**
	 * 更新
	 * @param coach
	 * @param user
	 */
	public void update(Coach coach, User user);

	/**
	 * 更新
	 * @param coach
	 */
	public void update(Coach coach);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);

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
