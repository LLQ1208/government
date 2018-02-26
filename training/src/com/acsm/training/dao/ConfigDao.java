package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Config;

public interface ConfigDao extends BaseDao<Config> {
	
	/**
	 * @param key
	 * @return
	 */
	public Config queryByKey(Integer boxId, String key);
	
	/**
	 * @return
	 */
	public List<Config> queryList(Integer boxId);
}
