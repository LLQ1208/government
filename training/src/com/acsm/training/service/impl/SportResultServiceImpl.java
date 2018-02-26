package com.acsm.training.service.impl;

import com.acsm.training.model.SportResult;
import com.acsm.training.service.SportResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.SportResultDao;

@Service("sportResultService")
public class SportResultServiceImpl implements SportResultService {

	@Autowired
	SportResultDao sportResultDao;
	
	@Override
	public void update(SportResult sr) {
		// TODO Auto-generated method stub
		sportResultDao.update(sr);
	}

	
}
