package com.acsm.training.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.model.Coach;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.UserService;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.CoachDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.model.UserType;
import com.acsm.training.service.CoachService;

@Service("coachService")
public class CoachServiceImpl implements CoachService {
	
	@Resource(name="coachDao")
	private CoachDao coachDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="userService")
	private UserService userService;
	

	@Override
	public Coach queryById(Integer id) {
		return coachDao.queryById(id);
	}

	@Override
	public List<Coach> queryList() {
		return coachDao.queryList();
	}

	@Override
	public PageHelper<Coach> queryPageByConditions(Map<String,Object> conditions) {
		return coachDao.queryPageByCondition(conditions);
	}

	@Override
	public void save(Coach coach, User user) {
		Coach saveCoach = coachDao.add(coach);
		user.setRelatedId(saveCoach.getId());
		userDao.add(user);
	}

	@Override
	public void update(Coach coach, User user) {
		coachDao.update(coach);
		userDao.update(user);
	}

	/**
	 * 更新
	 *
	 * @param coach
	 */
	@Override
	public void update(Coach coach) {
		coachDao.update(coach);
	}

	@Override
	public void delete(int id) {
		Coach coach = coachDao.queryById(id);
		coach.setIsDeleted(1);
		coachDao.update(coach);
		userService.deleteByRelatedIdAndUserType(id, UserType.COACH.getCode());
	}

	@Override
	public List<Coach> queryListByBoxId(int boxId) {
		return coachDao.queryListByBoxId(boxId);
	}

	@Override
	public List<Coach> queryAllCoachListOfBoss(int userId) {

		return coachDao.queryAllCoachListOfBoss(userId);
	}

}
