package com.acsm.training.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.dao.SupporterDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.Supporter;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.SupporterService;
import com.acsm.training.service.UserService;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.RoleEmployeeDao;
import com.acsm.training.model.UserType;

@Service("supporterService")
public class SupporterServiceImpl implements SupporterService {
	
	@Resource
	private SupporterDao supporterDao;
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private UserService userService;

	@Resource
	private RoleEmployeeDao roleEmployeeDao;

	@Override
	public Supporter queryById(Integer id) {
		return supporterDao.queryById(id);
	}

	@Override
	public List<Supporter> queryListByBoxId(Integer boxId) {
		return supporterDao.queryListByBoxId(boxId);
	}

	@Override
	public PageHelper<Supporter> queryPageByConditions(Map<String, Object> conditions) {
		return supporterDao.queryPageByConditions(conditions);
	}

	@Override
	public void save(Supporter supporter, User user, RoleEmployee re) {
		Supporter saveSupporter = supporterDao.add(supporter);
		user.setRelatedId(saveSupporter.getId());
		userDao.add(user);
		re.setEmployeeId(saveSupporter.getId());
		roleEmployeeDao.add(re);
	}

	@Override
	public void delete(int id) {
		supporterDao.delete(id);
		userService.deleteByRelatedIdAndUserType(id, UserType.SUPPORTER.getCode());
	}

	@Override
	public void update(Supporter supporter, User user, RoleEmployee re) {
		supporterDao.update(supporter);
		userDao.update(user);
		RoleEmployee roleEmployee = roleEmployeeDao.queryByEmployeeId(supporter.getBoxId());
		roleEmployeeDao.delete(roleEmployee.getId());
		roleEmployeeDao.add(re);
	}

}