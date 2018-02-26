package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.Member;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.User;
import com.acsm.training.vo.WeekCourseMember;
import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.Member;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.MemberCard;
import com.acsm.training.model.User;
import com.acsm.training.vo.WeekCourseMember;

public interface CourseOrderService {
	
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
	public List<CourseOrder> queryListByCourseId(Integer courseId,String date);
	
	/**
	 * 根据会员编号获取约课列表
	 * @param memberId
	 * @param date
	 * @return
	 */
	public List<CourseOrder> queryListByMemberId(Integer memberId, String date);

	/**
	 * @param courseId
	 * @param memberId
	 * @param dateStr
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
	 * @param courseOrder
	 * @return
	 */
	public CourseOrder save(CourseOrder courseOrder);

	/**
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * @param week
	 * @param date
	 * @return
	 */
	public List<WeekCourseMember> getWeekCourseMemberList(User user, Integer week, String date, int courseType);

	/**
	 * @param user
	 * @param week
	 * @param dateToString
	 * @return
	 */
	public Map<String, List<Member>> getWeekCourseMemberMap(User user, Integer week, String dateToString);

	/**
	 * 添加临时约课
	 * @param courseOrder
	 */
	public CourseOrder savePatch(CourseOrder courseOrder);

	/**
	 * 获取预约过的日期
	 * @param memberId
	 * @return
	 */
    public List<CourseOrder> queryCalendarListByMemberId(Integer memberId);

	/**
	 * 更新签到状态
	 * @param courseOrder
	 * @param i
	 */
	public void updateSignStatus(CourseOrder courseOrder, int i);

	/**
	 * 取消预约
	 * @param id
	 * @param memberBox
	 */
	void delete(Integer id, MemberBox memberBox);

	/**
	 * 预约
	 * @param newCourseOrder
	 * @param memberBox
	 */
	void save(CourseOrder newCourseOrder, MemberBox memberBox);

	/**
	 * 获取预约过的日期
	 * @param memberId
	 * @param boxId
	 * @return
	 */
	List<CourseOrder> queryCalendarListByMemberIdAndBoxId(int memberId, int boxId);

	void save(CourseOrder newCourseOrder, MemberBox memberBox, MemberCard mc);

	void delete(Integer id, MemberBox memberBox, MemberCard mc);




}
