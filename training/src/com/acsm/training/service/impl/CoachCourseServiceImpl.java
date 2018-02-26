package com.acsm.training.service.impl;/**
										* Created by lq on 2017/12/12.
										*/

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acsm.training.dao.CoachCourseOrderDao;
import com.acsm.training.model.CoachCourse;
import com.acsm.training.model.CoachCourseOrder;
import com.acsm.training.model.page.CoachCourseModel;
import com.acsm.training.service.CoachCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.CoachCourseDao;
import com.acsm.training.util.WodUtil;

/**
 * @Author lianglinqiang
 * @create 2017-12-12
 */
@Service
public class CoachCourseServiceImpl implements CoachCourseService {

	@Autowired
	CoachCourseDao coachCourseDao;
	@Autowired
	CoachCourseOrderDao coachCourseOrderDao;

	@Override
	public void saveCoachCourse(CoachCourse coachCourse) {
		if (null == coachCourse.getId()) {
			coachCourseDao.add(coachCourse);
		} else {
			coachCourseDao.update(coachCourse);
		}
	}

	@Override
	public CoachCourse queryById(int coachCourseId) {
		return coachCourseDao.queryById(coachCourseId);
	}

	@Override
	public List<CoachCourseModel> queryWeekListByCoachId(int coachId,String coachType, String startTime, String endTime,int boxId) {

		// 判断是周还是天
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			List<CoachCourseModel> coachCourseModelList = new ArrayList<>();
			Date mondyDate = WodUtil.getDateWeekMonday(startDate);
			// 一周日历map
			Map<Integer, String> weekDateMap = new HashMap<>();
			for (int i = 0; i < 7; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mondyDate);
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date thisDate = calendar.getTime();
				weekDateMap.put(i + 1, sdf.format(thisDate));
			}

			List<CoachCourse> coachCourseList;
			if("boss".equals(coachType)){
				coachCourseList = coachCourseDao.queryListByBossId(coachId,boxId);
			}else{
				coachCourseList = coachCourseDao.queryListByCoachId(coachId);
			}
			if (null != coachCourseList && coachCourseList.size() > 0) {
				for (CoachCourse course : coachCourseList) {
					CoachCourseModel coachCourseModel = new CoachCourseModel();
					coachCourseModel.setId(course.getId());
					coachCourseModel.setWeek(course.getWeek());
					coachCourseModel.setEndTime(course.getEndTime());
					coachCourseModel.setDate(weekDateMap.get(course.getWeek()));
					coachCourseModel.setStartTime(course.getStartTime());
					int reserNum = 0;
					List<CoachCourseOrder> orderList = coachCourseOrderDao.queryListByCourseId(course.getId(),coachCourseModel.getDate());
					if(null != orderList && orderList.size() > 0){
						reserNum = orderList.size();

					}
					coachCourseModel.setReservNum(reserNum);
					coachCourseModel.setCapacity(reserNum + "/" + course.getPeopleLimit());
					coachCourseModel.setPeopleLimit(course.getPeopleLimit());
					coachCourseModelList.add(coachCourseModel);
				}
			}
			return coachCourseModelList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteCoachCourse(int coachCourseId) {
		coachCourseDao.delete(coachCourseId);
	}

	@Override
	public List<CoachCourse> queryListByCoachIdAndWeek(int coachId, int week) {
		return coachCourseDao.queryListByCoachIdAndWeek(coachId, week);
	}

	@Override
	public List<CoachCourseModel> queryListOfCoachBack(int coachId, String coachType, String startTime, String endTime, int boxId) {
		// 判断是周还是天
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
			Date startDate = sdf.parse(startTime);
			Date endDate = sdf.parse(endTime);
			List<CoachCourseModel> coachCourseModelList = new ArrayList<>();
			//获取日期map
			Map<Integer, String> weekDateMap = new HashMap<>();
			Date mondyDate;
				mondyDate = WodUtil.getDateWeekMonday(startDate);
			// 一周日历map
			for (int i = 0; i < 7; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(mondyDate);
				calendar.add(Calendar.DAY_OF_MONTH, i);
				Date thisDate = calendar.getTime();
				weekDateMap.put(i + 1, sdf.format(thisDate));
			}

			List<CoachCourse> coachCourseList;
			if("boss".equals(coachType)){
				coachCourseList = coachCourseDao.queryListByBossId(coachId,boxId);
			}else{
				coachCourseList = coachCourseDao.queryListByCoachId(coachId);
			}
			if (null != coachCourseList && coachCourseList.size() > 0) {
				for (CoachCourse course : coachCourseList) {
					CoachCourseModel coachCourseModel = new CoachCourseModel();
					coachCourseModel.setId(course.getId());
					coachCourseModel.setWeek(course.getWeek());
					coachCourseModel.setEndTime(course.getEndTime());
					coachCourseModel.setDate(weekDateMap.get(course.getWeek()));
					coachCourseModel.setStartTime(course.getStartTime());
					int reserNum = 0;
					List<CoachCourseOrder> orderList = coachCourseOrderDao.queryListByCourseId(course.getId(),coachCourseModel.getDate());
					if(null != orderList && orderList.size() > 0){
						reserNum = orderList.size();

					}
					coachCourseModel.setReservNum(reserNum);
					coachCourseModel.setCapacity(reserNum + "/" + course.getPeopleLimit());
					coachCourseModel.setPeopleLimit(course.getPeopleLimit());
					coachCourseModelList.add(coachCourseModel);
				}
			}
			return coachCourseModelList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
