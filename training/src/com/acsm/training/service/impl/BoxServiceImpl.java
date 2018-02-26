package com.acsm.training.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.acsm.training.dao.BoxDao;
import com.acsm.training.model.User;
import com.acsm.training.service.BoxService;
import com.acsm.training.dao.RoleNewDao;
import com.acsm.training.model.RoleNew;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.ConfigDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.model.Box;
import com.acsm.training.model.Config;

@Service("boxService")
public class BoxServiceImpl implements BoxService {
	
	@Resource(name="boxDao")
	private BoxDao boxDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="configDao")
	private ConfigDao configDao;

	@Resource
	private RoleNewDao roleNewDao;

	@Override
	public Box queryById(Integer id) {
		return boxDao.queryById(id);
	}

	@Override
	public void save(Box box, User user) {
		Box saveBox = boxDao.add(box);
		Integer boxId = saveBox.getId();
		user.setBoxId(saveBox.getId());
		user.setRelatedId(boxId);
		userDao.add(user);
		Config configOrderCancelTime = new Config("ORDER_CANCEL_TIME", 120, boxId);
		Config configPeopleLimit = new Config("PEOPLE_LIMIT", 18, boxId);
		Config configOrderDay = new Config("ORDER_DAY", 15, boxId);
		configDao.add(configOrderCancelTime);
		configDao.add(configPeopleLimit);
		configDao.add(configOrderDay);
	}

	@Override
	public List<Box> queryList() {
		return boxDao.queryList();
	}

	@Override
	public void saveBox(Box box, int userId) {
		Box saveBox = boxDao.add(box);
		User user = userDao.queryById(userId);
//		user.setRelatedId(saveBox.getId());
		userDao.update(user);
		RoleNew roleNew = new RoleNew();
		roleNew.setName("店长");
		roleNew.setBoxId(saveBox.getId());
		roleNew.setAllowDelete(2);
		roleNewDao.add(roleNew);
		roleNew = new RoleNew();
		roleNew.setName("教练");
		roleNew.setBoxId(saveBox.getId());
		roleNew.setAllowDelete(1);
		roleNewDao.add(roleNew);
		return;
	}

	@Override
	public List<Box> queryBoxListOfUser(int userId) {
		return boxDao.queryBoxListByUser(userId);
	}

	@Override
	public void update(Box box) {
		boxDao.update(box);
	}

	@Override
	public List<Object[]> queryVaildList(Integer boxId) {
		return boxDao.queryVaildList(boxId);
	}
	@Override
	public List<Box> queryBoxListByBoss(int userId){
		return boxDao.queryBoxListByBoss(userId);
	}
}
