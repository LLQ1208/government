package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Config;
import com.acsm.training.model.Config;

public interface ConfigService {
	
	/**
	 * 根据关键字查询配置信息
	 * @param key
	 * @return
	 */
	public Config queryByKey(Integer boxId, String key);
	
	/**
	 * @return
	 */
	public List<Config> queryList(Integer boxId);
	
	/**
	 * @return
	 */
	public Map<String, Config> queryMap(Integer boxId);

	/**
	 * 更新
	 * @param orderCancelTime
	 * @param peopleLimit
	 * @param orderDay
	 */
	public void update(int orderCancelTimeValue, int peopleLimitValue, int orderDayValue, int boxId);
}
