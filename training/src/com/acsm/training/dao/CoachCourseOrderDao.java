package com.acsm.training.dao;

import com.acsm.training.model.CoachCourseOrder;
import com.acsm.training.model.CoachCourse;
import com.acsm.training.model.CoachCourseOrder;

import java.math.BigInteger;
import java.util.List;

public interface CoachCourseOrderDao extends BaseDao<CoachCourseOrder> {
	
	/**
	 * @param courseId
	 * @param memberId
	 * @param dateStr
	 * @return
	 */
	public CoachCourseOrder queryByMemberId(Integer courseId, Integer memberId, String dateStr);

	public List<CoachCourseOrder> queryListByCourseId(Integer courseId,String dateStr);
	
	public BigInteger queryOrderNumByIdAndDate(Integer coachCourseId,String dateStr);

	List<CoachCourseOrder> queryLastOfMember(int memberId,Integer boxId);

	List<CoachCourseOrder> queryNowCardCount(int memberId,String beginTime,Integer boxId);

	List<CoachCourseOrder> querCardCountByTime(int memberId,String beginTime,String endTime,Integer boxId);

	List<Object[]> queryOrderList(int memberId);
}
