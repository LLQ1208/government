package com.acsm.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.CoachCourseOrderDao;
import com.acsm.training.dao.MemberCardDao;
import com.acsm.training.model.CoachCourseOrder;
import com.acsm.training.model.MemberCard;
import com.acsm.training.service.CoachCourseOrderService;

import java.util.List;

@Service
public class CoachCourseOrderServiceImpl implements CoachCourseOrderService {
	
	@Autowired
	CoachCourseOrderDao coachCourseOrderDao;

	@Override
	public CoachCourseOrder queryByMemberId(Integer courseId,Integer memberId, String date) {
		return coachCourseOrderDao.queryByMemberId(courseId, memberId, date);
	}

	@Override
	public void add(CoachCourseOrder coachCourseOrder,List<MemberCard> mcs) {
		 coachCourseOrderDao.add(coachCourseOrder);
		 if(!mcs.isEmpty()) {
				MemberCard mc=mcs.get(0);
			mc.setRemainNum(mc.getRemainNum()-1);
			memberCardDao.update(mc);
		}
	}
	@Autowired
	MemberCardDao memberCardDao;

	@Override
	public void update(CoachCourseOrder coachCourseOrder,List<MemberCard> mcs) {
		 coachCourseOrderDao.update(coachCourseOrder);
		 if(!mcs.isEmpty()) {
				MemberCard mc=mcs.get(0);
			mc.setRemainNum(mc.getRemainNum() + 1);
			memberCardDao.update(mc);
		}
	}
	
	@Override
	public CoachCourseOrder load(int id) {
		 return coachCourseOrderDao.load(id);
	}

	@Override
	public List<CoachCourseOrder> queryListByCourseAndDate(int coachCourseId, String date) {
		return coachCourseOrderDao.queryListByCourseId(coachCourseId,date);
	}
}
