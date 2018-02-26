package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.Supporter;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;

public interface SupporterService {
	/**
	 * 根据编号查询
	 * @param id
	 * @return
	 */
	public Supporter queryById(Integer id);
	
	/**
	 * @param boxId
	 * @return
	 */
	public List<Supporter> queryListByBoxId(Integer boxId);
	
	/**
	 * 根据查询条件获取客服分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<Supporter> queryPageByConditions(Map<String,Object> conditions);

	/**
	 * 保存
	 * @param supporter
	 * @param user
	 */
	public void save(Supporter supporter, User user, RoleEmployee re);

	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);

	/**
	 * 更新
	 * @param supporter
	 * @param user
	 */
	public void update(Supporter supporter, User user, RoleEmployee re);

}
