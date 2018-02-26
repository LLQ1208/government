package com.acsm.training.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.WODCustomDao;
import com.acsm.training.model.WodCustom;
import com.acsm.training.service.WODCustomService;

@Service("wodCustomService")
public class WODCustomServiceImpl implements WODCustomService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	WODCustomDao wodCustomDao;

	@Override
	public boolean add(WodCustom wodCustom) {
		if (wodCustomDao.add(wodCustom) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean update(WodCustom wodCustom) {
		try {
			wodCustomDao.update(wodCustom);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<WodCustom> queryByMember(Integer memberId,Integer pageIndex,Integer pageSize) {
		return wodCustomDao.queryByMember(memberId,pageIndex,pageSize);
	}
	
	@Override
	public WodCustom queryById(Integer id) {
		return wodCustomDao.load(id);
	}
}
