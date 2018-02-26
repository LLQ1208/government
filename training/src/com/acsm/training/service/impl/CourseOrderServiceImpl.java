package com.acsm.training.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.util.DateUtil;
import com.acsm.training.vo.WeekCourseMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.ContentDao;
import com.acsm.training.dao.CourseDao;
import com.acsm.training.dao.CourseOrderDao;
import com.acsm.training.dao.MemberBoxDao;
import com.acsm.training.dao.MemberCardDao;
import com.acsm.training.dao.MemberDao;
import com.acsm.training.dao.WodContentDao;
import com.acsm.training.dao.WodDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.Member;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.MemberCard;
import com.acsm.training.model.User;
import com.acsm.training.service.CourseOrderService;

@Service
public class CourseOrderServiceImpl implements CourseOrderService {
	
	@Resource(name="courseOrderDao")
	private CourseOrderDao courseOrderDao;
	
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	@Resource(name="courseDao")
	private CourseDao courseDao;

	@Resource
	private MemberBoxDao memberBoxDao;

	@Autowired
	WodDao wodDao;

	@Autowired
	WodContentDao wodContentDao;
	@Autowired
	ContentDao contentDao;

	@Override
	public CourseOrder queryById(Integer id) {
		return courseOrderDao.queryById(id);
	}

	@Override
	public List<CourseOrder> queryListByCourseId(Integer courseId, String date) {
		return courseOrderDao.queryListByCourseId(courseId, date);
	}

	@Override
	public List<CourseOrder> queryListByMemberId(Integer memberId, String date) {
		return courseOrderDao.queryListByMemberId(memberId, date);
	}

	
	@Override
	public CourseOrder queryByMemberId(Integer courseId, Integer memberId, String dateStr) {
		return courseOrderDao.queryByMemberId(courseId, memberId, dateStr);
	}

	@Override
	public CourseOrder save(CourseOrder courseOrder) {
		return courseOrderDao.add(courseOrder);
	}

	@Override
	public void delete(Integer id) {
		CourseOrder courseOrder = courseOrderDao.queryById(id);
		courseOrder.setIsDeleted(1);
		courseOrderDao.update(courseOrder);
	}

	@Override
	public List<WeekCourseMember> getWeekCourseMemberList(User user, Integer week, String date, int courseType) {
		List<Course> courseList = courseDao.queryListByWeekAndBoxId(week, user.getBoxId());
		List<WeekCourseMember> weekCourseMemberList = new ArrayList<WeekCourseMember>();
		for(Course course:courseList){
			if(courseType==0 || courseType == course.getCourseType().getId()) {
				String name = course.getStartTime() + "-" + course.getEndTime() + course.getCourseType().getName();
				List<CourseOrder> courseOrderList = courseOrderDao.queryListByCourseId(course.getId(), date);
				WeekCourseMember weekCourseMember = new WeekCourseMember();
				weekCourseMember.setName(name);
				weekCourseMember.setCourse(course);
				weekCourseMember.setCourseOrderList(courseOrderList);
				weekCourseMemberList.add(weekCourseMember);
			}
		}
		return weekCourseMemberList;
	}
	
	@Override
	public Map<String, List<Member>> getWeekCourseMemberMap(User user, Integer week, String dateToString) {
		List<Course> courseList = courseDao.queryListByWeekAndBoxId(week, user.getBoxId());
		Map<String,List<Member>> map = new LinkedHashMap<String,List<Member>>();
		for(Course course:courseList){
			String courseName = course.getStartTime()+"-"+course.getEndTime()+course.getCourseType().getName();
			List<CourseOrder> courseOrderList = courseOrderDao.queryListByCourseId(course.getId(),dateToString);
			List<Member> memberList = new ArrayList<Member>();
			for(CourseOrder courseOrder:courseOrderList){
				memberList.add(courseOrder.getMember());
			}
			map.put(courseName, memberList);
		}
		return map;
	}

	@Override
	public CourseOrder savePatch(CourseOrder courseOrder) {
		return courseOrderDao.add(courseOrder);
	}

	@Override
	public List<CourseOrder> queryCalendarListByMemberId(Integer memberId) {
		return courseOrderDao.queryCalendarListByMemberId(memberId);
	}

	@Override
	public void updateSignStatus(CourseOrder courseOrder, int i) {
		courseOrder.setSignStatus(i);
		courseOrderDao.update(courseOrder);
	}

	/**
	 * 取消预约
	 *
	 * @param id
	 * @param memberBox
	 */
	@Override
	public void delete(Integer id, MemberBox memberBox) {
		//删除预约记录
		CourseOrder courseOrder = courseOrderDao.queryById(id);
		courseOrder.setIsDeleted(1);
		courseOrderDao.update(courseOrder);
		if(memberBox==null){
			return;
		}
		//次卡会员次数加一
		if(memberBox.getMemberType() == 2){
			memberBox.setMemberRemainNum(memberBox.getMemberRemainNum() + 1);
			memberBoxDao.update(memberBox);
			//如果是主训练馆需要更新主训练馆信息
			if(memberBox.getIsMain() == 1){
				Member member = memberDao.queryById(memberBox.getMemberId());
				member.setMemberRemainNum(member.getMemberRemainNum() + 1);
				memberDao.update(member);
			}
		}
	}

	@Override
	public void delete(Integer id, MemberBox memberBox,MemberCard mc) {
		//删除预约记录
		CourseOrder courseOrder = courseOrderDao.queryById(id);
		courseOrder.setIsDeleted(1);
		courseOrderDao.update(courseOrder);
		if(memberBox==null){
			return;
		}
		//次卡会员次数加一
		if(mc.getCustomCardType() == 2){
			mc.setRemainNum(mc.getRemainNum()+1);
			memberCardDao.update(mc);
//			//如果是主训练馆需要更新主训练馆信息
//			if(memberBox.getIsMain() == 1){
//				Member member = memberDao.queryById(memberBox.getMemberId());
//				member.setMemberRemainNum(member.getMemberRemainNum() + 1);
//				memberDao.update(member);
//			}
		}
	}
	/**
	 * 预约
	 *
	 * @param newCourseOrder
	 * @param memberBox
	 */
	@Override
	public void save(CourseOrder newCourseOrder, MemberBox memberBox) {
		//添加预约记录
		courseOrderDao.add(newCourseOrder);
		if(memberBox==null){
			return;
		}
		//次卡会员次数减1
		if(memberBox.getMemberType() == 2){
			//次卡会员第一次使用，设置激活并计算开始结束时间
			if(memberBox.getIsActive() == 0){
				memberBox.setIsActive(1);
				Date memberEndTime = DateUtil.convertDate(memberBox.getMemberDays());
				memberBox.setMemberStartTime(DateUtil.StringToDate(DateUtil.getCurrentDate("yyyy/MM/dd"), "yyyy/MM/dd"));
				memberBox.setMemberEndTime(memberEndTime);
			}
			//次数减1
			memberBox.setMemberRemainNum(memberBox.getMemberRemainNum() - 1);
			memberBoxDao.update(memberBox);
			//如果是主训练馆需要更新主训练馆信息
			if(memberBox.getIsMain() == 1){
				Member member = memberDao.queryById(memberBox.getMemberId());
				member.setMemberRemainNum(member.getMemberRemainNum() - 1);
				memberDao.update(member);
			}
		}
	}
	@Autowired
	MemberCardDao memberCardDao;
	@Override
	public void save(CourseOrder newCourseOrder, MemberBox memberBox,MemberCard mc) {
		//添加预约记录
		courseOrderDao.add(newCourseOrder);
		if(memberBox==null){
			return;
		}
		//次卡会员次数减1
		if(mc.getCustomCardType() == 2){
			//次卡会员第一次使用，设置激活并计算开始结束时间
//			if(memberBox.getIsActive() == 0){
//				memberBox.setIsActive(1);
//				Date memberEndTime = DateUtil.convertDate(memberBox.getMemberDays());
//				memberBox.setMemberStartTime(DateUtil.StringToDate(DateUtil.getCurrentDate("yyyy/MM/dd"), "yyyy/MM/dd"));
//				memberBox.setMemberEndTime(memberEndTime);
//			}
			//次数减1
			mc.setRemainNum(mc.getRemainNum()-1);
//			memberBox.setMemberRemainNum(memberBox.getMemberRemainNum() - 1);
			memberCardDao.update(mc);
			//如果是主训练馆需要更新主训练馆信息
//			if(memberBox.getIsMain() == 1){
//				Member member = memberDao.queryById(memberBox.getMemberId());
//				member.setMemberRemainNum(member.getMemberRemainNum() - 1);
//				memberDao.update(member);
//			}
		}
	}

	/**
	 * 获取预约过的日期
	 *
	 * @param memberId
	 * @param boxId
	 * @return
	 */
	@Override
	public List<CourseOrder> queryCalendarListByMemberIdAndBoxId(int memberId, int boxId) {
		return courseOrderDao.queryCalendarListByMemberIdAndBoxId(memberId, boxId);
	}


}
