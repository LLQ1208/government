package com.acsm.training.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acsm.training.dao.ConfigDao;
import com.acsm.training.model.Config;

@Repository("configDao")
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	@Override
	public Config queryByKey(Integer boxId, String key) {
		String hql ="from Config where boxId=? and configKey=?";
		Object[] obj = {boxId, key};
        return (Config)this.Queryobject(hql,obj);
	}

	@Override
	public List<Config> queryList(Integer boxId) {
		String hql ="from Config where boxId=?";
		return this.list(hql,boxId);
	}

}
