package com.acsm.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.dao.ConfigDao;
import com.acsm.training.model.Config;
import com.acsm.training.service.ConfigService;
import org.springframework.stereotype.Service;

@Service("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Resource(name="configDao")
	private ConfigDao configDao;

	@Override
	public Config queryByKey(Integer boxId,String key) {
		return configDao.queryByKey(boxId, key);
	}

	@Override
	public List<Config> queryList(Integer boxId) {
		return configDao.queryList(boxId);
	}

	@Override
	public Map<String, Config> queryMap(Integer boxId) {
		List<Config> list = configDao.queryList(boxId);
		Map<String,Config> map = new HashMap<String, Config>();
		for(Config config:list){
			map.put(config.getConfigKey(), config);
		}
		return map;
	}

	@Override
	public void update(int orderCancelTimeValue, int peopleLimitValue, int orderDayValue, int boxId) {
		Config ORDER_CANCEL_TIME = configDao.queryByKey(boxId, "ORDER_CANCEL_TIME");
		Config PEOPLE_LIMIT = configDao.queryByKey(boxId, "PEOPLE_LIMIT");
		Config ORDER_DAY = configDao.queryByKey(boxId, "ORDER_DAY");
		if(ORDER_CANCEL_TIME == null){
			ORDER_CANCEL_TIME = new Config("ORDER_CANCEL_TIME", 120, boxId);
			configDao.add(ORDER_CANCEL_TIME);
		}else {
			ORDER_CANCEL_TIME.setConfigValue(orderCancelTimeValue);
			configDao.update(ORDER_CANCEL_TIME);
		}
		if(PEOPLE_LIMIT == null){
			PEOPLE_LIMIT = new Config("PEOPLE_LIMIT", 18, boxId);
			configDao.add(PEOPLE_LIMIT);
		}else {
			PEOPLE_LIMIT.setConfigValue(peopleLimitValue);
			configDao.update(PEOPLE_LIMIT);
		}
		if(ORDER_DAY == null){
			ORDER_DAY = new Config("ORDER_DAY", 15, boxId);
			configDao.add(ORDER_DAY);
		}else {
			ORDER_DAY.setConfigValue(orderDayValue);
			configDao.update(ORDER_DAY);
		}
	}
}
