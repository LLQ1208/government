package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.CourseOrder;

public interface CourseOrderDao extends BaseDao<CourseOrder> {
	
	/**
	 * 根据编号获取约课信息
	 * @param id
	 * @return
	 */
	public CourseOrder queryById(Integer id);
	
	/**
	 * 根据课程编号获取约课列表
	 * @param courseId
	 * @return
	 */
	public List<CourseOrder> queryListByCourseId(Integer courseId, String date);
	
	/**
	 * 根据会员编号获取约课列表
	 * @param memberId
	 * @return
	 */
	public List<CourseOrder> queryListByMemberId(Integer memberId,String date);

	/**
	 * @param courseId
	 * @param memberId
	 * @param date
	 * @return
	 */
//	public CourseOrder queryListByMemberId(Integer courseId, Integer memberId, Date date);

	/**
	 * @param courseId
	 * @param memberId
	 * @param dateStr
	 * @return
	 */
	public CourseOrder queryByMemberId(Integer courseId, Integer memberId, String dateStr);

	/**
	 * @param memberId
	 * @return
	 */
    public List<CourseOrder> queryCalendarListByMemberId(Integer memberId);

    List<CourseOrder> queryCalendarListByMemberIdAndBoxId(int memberId, int boxId);

	List<CourseOrder> queryReservationList(Integer boxId, Integer courseId, String date);

	List<CourseOrder> queryCoachReservationList(Integer boxId, Integer courseId,Integer coachId, String date);

	//查询最近一次上课
	List<Object[]> queryLastOrderOfMember(int memberId,Integer boxId);

	List<CourseOrder> queryNowOrderCount(int memberId,String beginTime,Integer boxId);

	List<CourseOrder> queryLastMonthOrder(int memberId,String cardBeginTime,String lastMonthTime,Integer boxId);

	List<CourseOrder> queryThisMonthOrder(int memberId,String thisMonthTime,Integer boxId);


	List<Object[]> queryOrderList(int memberId);
}
