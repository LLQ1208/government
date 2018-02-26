package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.Box;
import com.acsm.training.model.User;

public interface BoxService {
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public Box queryById(Integer id);
	
	/**
	 * 保存
	 * @param box
	 * @param user
	 */
	public void save(Box box, User user);
	
	/**
	 * 获取所有训练馆信息
	 * @return
	 */
	public List<Box> queryList();

	/**
	 * 添加box
	 */
   public void saveBox(Box box, int userId);

   public List<Box> queryBoxListOfUser(int userId);

   public void update(Box box);

   public List<Object[]> queryVaildList(Integer boxId);

	public List<Box> queryBoxListByBoss(int userId);
}