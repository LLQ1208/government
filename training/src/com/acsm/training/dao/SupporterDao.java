package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Supporter;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Supporter;
import com.acsm.training.model.basic.PageHelper;

public interface SupporterDao extends BaseDao<Supporter> {

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
}
