package com.acsm.training.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.annotation.Resource;

import com.acsm.training.dao.CourseDao;
import com.acsm.training.dao.CourseTypeDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.service.CourseService;
import com.acsm.training.util.WodUtil;
import com.acsm.training.dao.CourseOrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
	private static final Logger log = LoggerFactory.getLogger(CourseService.class);
	@Resource(name="courseDao")
	private CourseDao courseDao;
	@Autowired
	CourseTypeDao courseTypeDao;
	@Autowired
	CourseOrderDao courseOrderDao;

	@Override
	public Course queryById(Integer id) {
		Course course = courseDao.queryById(id);
		course.setCourseType(courseTypeDao.queryById(course.getCourseTypeId()));
		return course;
	}

	@Override
	public List<Course> queryListByBoxId(Integer boxId) {
		return courseDao.queryListByBoxId(boxId);
	}

	@Override
	public Map<String, List<Course>> getWeekPlanMap(Integer boxId) {
		List<Course> coursePlanList = courseDao.queryListByBoxId(boxId);
		Map<String,List<Course>> map = new HashMap<String, List<Course>>();
		List<Course> week1 = new ArrayList<Course>();
		List<Course> week2 = new ArrayList<Course>();
		List<Course> week3 = new ArrayList<Course>();
		List<Course> week4 = new ArrayList<Course>();
		List<Course> week5 = new ArrayList<Course>();
		List<Course> week6 = new ArrayList<Course>();
		List<Course> week7 = new ArrayList<Course>();
		for(Course course:coursePlanList){
			Integer week = course.getWeek();
			if(week==1){
				week1.add(course);
			}else if(week==2){
				week2.add(course);
			}else if(week==3){
				week3.add(course);
			}else if(week==4){
				week4.add(course);
			}else if(week==5){
				week5.add(course);
			}else if(week==6){
				week6.add(course);
			}else if(week==7){
				week7.add(course);
			}
		}
		map.put("monday", week1);
		map.put("tuesday", week2);
		map.put("wednesday", week3);
		map.put("thursday", week4);
		map.put("friday", week5);
		map.put("saturday", week6);
		map.put("sunday", week7);
		return map;
	}

	@Override
	public void update(Course course) {
		courseDao.update(course);
	}

	@Override
	public void save(Course course) {
		course.setOperation(1);
		courseDao.add(course);
	}

	@Override
	public void delete(int id) {
		Course course = courseDao.queryById(id);
		course.setIsDeleted(1);
		courseDao.update(course);
	}

	@Override
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId) {
		return courseDao.queryListByWeekAndBoxId(week, boxId);
	}

	@Override
	public List<Course> queryListByCoureseTypeIdAndBoxId(Integer courseTypeId, Integer boxId) {
		return courseDao.queryListByCoureseTypeIdAndBoxId(courseTypeId,boxId);
	}

	@Override
	public List<Course> queryListByWeekAndBoxIdAndUserId(Integer week, Integer boxId, Integer userId,String courseTypeIds,long trainingDate) {
		return courseDao.queryListByWeekAndBoxIdAndUserId(week, boxId, userId,courseTypeIds,trainingDate);
	}
	@Override
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId, Integer courseTypeId) {
		return courseDao.queryListByWeekAndBoxId(week, boxId, courseTypeId);
	}

	@Override
	public List<Course> queryCourseListOfUser(int userId) {
		return null;
	}

	@Override
	public List<CourseModel> queryCourseOfWeek(Integer boxId, Integer courseTypeId, Integer coachId,
											String startTime,String endTime) {
		//判断是周还是天
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			List<CourseModel> courseModelList = new ArrayList<>();
			//不论请求的是天还是周都计算一周的数据
			Date mondyDate = null;
			if(startDate.getTime() - endDate.getTime() > 2*24*60*60*1000){//如果开始时间-结束时间大于两天，肯定为周
				mondyDate = startDate;
			}else{
				mondyDate = WodUtil.getDateWeekMonday(startDate);
			}
			//一周日历map
			Map<Integer,String> weekDateMap = new HashMap<>();
			for(int i=0;i<7;i++ ){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mondyDate);
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date thisDate = calendar.getTime();
				weekDateMap.put(i+1,sdf.format(thisDate));
			}
			List<Course> courseList = courseDao.queryCourseListById(boxId, courseTypeId, coachId);
			if(null != courseList && courseList.size() > 0){
				for(Course course : courseList){
					CourseModel courseModel = new CourseModel();
					courseModel.setBeginTime(course.getStartTime());
					String date = weekDateMap.get(course.getWeek());
					List<CourseOrder> courseOrderList = courseOrderDao.queryReservationList(boxId,course.getId(),date);
					courseModel.setCapacity((courseOrderList == null ?  0:courseOrderList.size())  + "/" + course.getPeopleLimit());
					courseModel.setCrouseTypeName(course.getCourseType().getName());
					courseModel.setEndTime(course.getEndTime());
					courseModel.setMasterName(course.getCoach() == null ? "" : course.getCoach().getName());
					courseModel.setId(course.getId() + "");
					courseModel.setDate(weekDateMap.get(course.getWeek()));
					courseModel.setColor(null == course.getCourseType().getColor() || "".equals(course.getCourseType().getColor()) ?
							"red" : "#" + course.getCourseType().getColor());
					courseModel.setWeek(course.getWeek());
					courseModel.setCourseTypeId(course.getCourseTypeId());
					courseModel.setCoachId(course.getCoach() != null ? course.getCoach().getId():0);
					courseModel.setNum(course.getPeopleLimit());
					courseModelList.add(courseModel);
				}
			}
			return courseModelList;
		} catch (Exception e) {
			e.printStackTrace();
			log.info("courseService queryCourseOfWeek exception",e);
		}
		return null;
	}

	@Override
	public Set<CourseModel> queryCourseColorOfWeek(Integer boxId, Integer courseTypeId, Integer coachId) {
		List<Course> courseList = courseDao.queryCourseListById(boxId, courseTypeId, coachId);
		Set<String>  courseTypeIdSet = new HashSet<>();
		Set<CourseModel> courseModelSet = new HashSet<>();
		for(Course c : courseList){
			if(!courseTypeIdSet.contains(c.getCourseTypeId().toString())){
				courseTypeIdSet.add(c.getCourseTypeId().toString());
				CourseModel courseModel = new CourseModel();
				courseModel.setCrouseTypeName(c.getCourseType().getName());
				courseModel.setColor(c.getCourseType().getColor() != null && !"".equals(c.getCourseType().getColor()) ?
						"#"+c.getCourseType().getColor() : "red");
				courseModelSet.add(courseModel);
			}
		}
		return courseModelSet;
	}

	@Override
	public List<CourseModel> queryCourseListOfDay(Integer boxId, Integer courseTypeId, Integer coachId, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			calendar.setTime(sdf.parse(date));
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			week = week-1;
			if(week == 0){
				week = 7;
			}
			List<CourseModel> courseModelList = new ArrayList<>();
			List<Course> courseList = courseDao.queryCourseListOfDay(boxId, courseTypeId, coachId, week,date);
			if(null != courseList && courseList.size() > 0){
				for(Course course : courseList){
					CourseModel courseModel = new CourseModel();
					courseModel.setBeginTime(course.getStartTime());
					List<CourseOrder> courseOrderList = courseOrderDao.queryReservationList(boxId,course.getId(),date);
					courseModel.setCapacity((courseOrderList == null ?  0:courseOrderList.size()) + "/" + course.getPeopleLimit());
					courseModel.setCrouseTypeName(course.getCourseType().getName());
					courseModel.setEndTime(course.getEndTime());
					courseModel.setMasterName(course.getCoach() == null ? "" : course.getCoach().getName());
					courseModel.setId(course.getId() + "");
					courseModel.setDate(date);
					courseModel.setWeek(course.getWeek());
					courseModel.setCourseTypeId(course.getCourseTypeId());
					courseModel.setCoachId(course.getCoach() != null ? course.getCoach().getId():0);
					courseModel.setNum(course.getPeopleLimit());
					courseModel.setOperation(course.getOperation());
					courseModelList.add(courseModel);
				}
			}
			return courseModelList;
		}catch(Exception e){
			log.info("courseService queryCourseListOfDay exception",e);
		}
		return null;
	}

	@Override
	public List<CourseModel> queryReservationClassOfDay(Integer boxId, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			calendar.setTime(sdf.parse(date));
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			week = week-1;
			if(week == 0){
				week = 7;
			}
			List<CourseModel> courseModelList = new ArrayList<>();
			List<Course> courseList = courseDao.queryReservationClassOfDay(boxId,week,date);
			if(null != courseList && courseList.size() > 0){
				for(Course course : courseList){
					CourseModel courseModel = new CourseModel();
					courseModel.setBeginTime(course.getStartTime());
					courseModel.setCapacity(0 + "/" + course.getPeopleLimit());
					courseModel.setCrouseTypeName(course.getCourseType().getName());
					courseModel.setEndTime(course.getEndTime());
					courseModel.setMasterName(course.getCoach() == null ? "" : course.getCoach().getName());
					courseModel.setId(course.getId() + "");
					courseModel.setDate(date);
					courseModel.setWeek(course.getWeek());
					courseModel.setCourseTypeId(course.getCourseTypeId());
					courseModel.setCoachId(course.getCoach() != null ? course.getCoach().getId():0);
					courseModel.setNum(course.getPeopleLimit());
					courseModel.setOperation(course.getOperation());
					courseModelList.add(courseModel);
				}
			}
			return courseModelList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseModel> queryCoachReservationClassOfDay(Integer boxId, Integer coachId, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
			calendar.setTime(sdf.parse(date));
			int week = calendar.get(Calendar.DAY_OF_WEEK);
			week = week-1;
			if(week == 0){
				week = 7;
			}
			List<CourseModel> courseModelList = new ArrayList<>();
			List<Course> courseList = courseDao.queryCoachReservationClassOfDay(boxId,coachId,week,date);
			if(null != courseList && courseList.size() > 0){
				for(Course course : courseList){
					CourseModel courseModel = new CourseModel();
					courseModel.setBeginTime(course.getStartTime());
					courseModel.setCapacity(0 + "/" + course.getPeopleLimit());
					courseModel.setCrouseTypeName(course.getCourseType().getName());
					courseModel.setEndTime(course.getEndTime());
					courseModel.setMasterName(course.getCoach() == null ? "" : course.getCoach().getName());
					courseModel.setId(course.getId() + "");
					courseModel.setDate(date);
					courseModel.setWeek(course.getWeek());
					courseModel.setCourseTypeId(course.getCourseTypeId());
					courseModel.setCoachId(course.getCoach() != null ? course.getCoach().getId():0);
					courseModel.setNum(course.getPeopleLimit());
					courseModel.setOperation(course.getOperation());
					courseModelList.add(courseModel);
				}
			}
			return courseModelList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
