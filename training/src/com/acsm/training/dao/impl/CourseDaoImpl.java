package com.acsm.training.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.acsm.training.dao.CourseDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseType;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.DateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CourseDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseType;

@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	@Override
	public Course queryById(Integer id) {
		String hql ="from Course where id=?";
        return (Course)this.Queryobject(hql,id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByBoxId(Integer boxId) {
//		String hql = "from Course where boxId=? and isDeleted=0 order by startTime asc";
//		return this.list(hql,boxId);
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach  " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				"and c.boxId=:boxId order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByWeekAndBoxId(Integer week,Integer boxId) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				"and c.week=:week and c.boxId=:boxId order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("week", week);
		q.setInteger("boxId", boxId);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByWeekAndBoxIdAndUserId(Integer week,Integer boxId, Integer userId,String courseTypeIds,long trainingDate) {
		List<Integer> ids = new ArrayList<Integer>();
		if(null != courseTypeIds){
			String[] courseTypesArr = courseTypeIds.split(",");
			ids = new ArrayList<Integer>();
			if(courseTypesArr.length > 0){
				ids = new ArrayList<Integer>();
				for (String id : courseTypesArr) {
					ids.add(Integer.valueOf(id));
				}
			}else{
				List<Course> courseList = new ArrayList<Course>();
				return courseList;
			}
		}

//		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
//				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
//				" and c.coach.id=coach.id " +
//				" and coach.isDeleted = 0 "+
//				" and ct.isDeleted=0 and (c.week=:week or c.date=:date) and c.boxId=:boxId and c.operation = 1" +
//				" and ct.id in:ids " +
//				" order by c.startTime asc";
		StringBuffer sql = new StringBuffer();
		sql.append(" select c,ct from Course as c,CourseType as ct, Coach as coach ");
		sql.append( " where c.courseTypeId=ct.id and c.isDeleted= 0 ");
		sql.append(" and c.coach.id=coach.id and coach.isDeleted = 0 ");
		sql.append(" and ct.isDeleted=0 and (c.week=:week or c.date=:date) and c.boxId=:boxId and c.operation = 1");
		if(null != courseTypeIds){
			sql.append(" and ct.id in:ids ");
		}
		sql.append(" order by c.startTime asc ");
		Session session = this.getSession();
		Query q = session.createQuery(sql.toString());
		q.setInteger("week", week);
		q.setString("date", DateUtil.DateToString(new Date(trainingDate), DateUtil.YYYY_MM_DD));
		q.setInteger("boxId", boxId);
		if(null != courseTypeIds){
			q.setParameterList("ids", ids);
		}

		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByCoureseTypeIdAndBoxId(Integer courseTypeId, Integer boxId) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
				"and c.courseTypeId=:courseTypeId " +
				" and c.coach.id=coach.id " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				"and c.boxId=:boxId order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("courseTypeId", courseTypeId);
		q.setInteger("boxId", boxId);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByCoureseTypeIdAndBoxIdAndWeek(Integer courseTypeId, Integer boxId,int week) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach  " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				"and c.courseTypeId=:courseTypeId " +
				"and c.boxId=:boxId and week=:week order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("courseTypeId", courseTypeId);
		q.setInteger("boxId", boxId);
		q.setInteger("week", week);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Course> queryListByWeekAndBoxId(Integer week, Integer boxId, Integer courseTypeId) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach  " +
				" where c.courseTypeId=ct.id and c.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and c.week=:week and c.boxId=:boxId " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				" and c.courseTypeId=:courseTypeId order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("week", week);
		q.setInteger("boxId", boxId);
		q.setInteger("courseTypeId", courseTypeId);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@Override
	public List<Course> queryCourceListByUserId(int userId) {
		return null;
	}

	@Override
	public List<Course> queryCourseListById(Integer boxId, Integer courseTypeId, Integer coachId) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id " +
				" and c.coach.id=coach.id " +
				" and c.isDeleted=0 " +
				" and c.boxId=:boxId " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 ";
		if(null != courseTypeId){
			hql += " and c.courseTypeId=:courseTypeId ";
		}
		if(null != coachId){
			hql += "and c.coach.id=:coachId ";
		}
		hql += " order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		if(null != courseTypeId){
			q.setInteger("courseTypeId", courseTypeId);
		}
		if(null != coachId){
			q.setInteger("coachId", coachId);
		}
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@Override
	public List<Course> queryCourseListOfDay(Integer boxId, Integer courseTypeId, Integer coachId, int week, String date) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id " +
				" and c.coach.id=coach.id " +
				" and c.isDeleted=0 " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				" and c.boxId=:boxId and (c.week=:week or c.date=:date) ";
		if(null != courseTypeId){
			hql += " and c.courseTypeId=:courseTypeId ";
		}
		if(null != coachId){
			hql += " and c.coach.id=:coachId ";
		}
		hql += " order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		q.setInteger("week", week);
		q.setString("date", date);
		if(null != courseTypeId){
			q.setInteger("courseTypeId", courseTypeId);
		}
		if(null != coachId){
			q.setInteger("coachId", coachId);
		}
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@Override
	public List<Course> queryReservationClassOfDay(Integer boxId, int week, String date) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 and ct.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and coach.isDeleted = 0 "+
				" and c.boxId=:boxId and (c.week=:week or c.date=:date) " +
				" and c.operation = 1 " +
				" order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		q.setInteger("week", week);
		q.setString("date", date);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}

	@Override
	public List<Course> queryCoachReservationClassOfDay(Integer boxId, Integer coachId, int week, String date) {
		String hql = "select c,ct from Course as c,CourseType as ct, Coach as coach " +
				"where c.courseTypeId=ct.id and c.isDeleted=0 " +
				" and c.coach.id=coach.id " +
				" and ct.isDeleted=0 " +
				" and coach.isDeleted = 0 "+
				" and c.boxId=:boxId and (c.week=:week or c.date=:date) " +
				" and c.operation = 1 ";
		if(null != coachId){
			hql += " and c.coach.id="+coachId;
		}
				hql += " order by c.startTime asc";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		q.setInteger("week", week);
		q.setString("date", date);
		List list = q.list();
		List<Course> courseList = new ArrayList<Course>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				Course course = (Course) ob[0];
				CourseType courseType = (CourseType) ob[1];
				course.setCourseType(courseType);
				courseList.add(course);
			}
		}
		return courseList;
	}
}
