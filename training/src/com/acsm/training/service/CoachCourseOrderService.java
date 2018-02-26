package com.acsm.training.service;

import com.acsm.training.model.CoachCourseOrder;
import com.acsm.training.model.CoachCourseOrder;
import com.acsm.training.model.MemberCard;

import java.util.List;

public interface CoachCourseOrderService {
	

	/**
	 * 根据会员编号获取约课列表
	 * @param memberId
	 * @param date
	 * @return
	 */
	public CoachCourseOrder queryByMemberId(Integer courseId,Integer memberId, String date);

	void add(CoachCourseOrder coachCourseOrder,List<MemberCard> mcs);

	public void update(CoachCourseOrder coachCourseOrder,List<MemberCard> mcs) ;

	CoachCourseOrder  load(int id);

	List<CoachCourseOrder> queryListByCourseAndDate(int coachCourseId,String date);
}
