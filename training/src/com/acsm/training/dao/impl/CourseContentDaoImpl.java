package com.acsm.training.dao.impl;

import java.util.Date;
import java.util.List;

import com.acsm.training.dao.CourseContentDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CourseContentDao;
import com.acsm.training.model.CourseContent;

@Repository("courseContentDao")
public class CourseContentDaoImpl extends BaseDaoImpl<CourseContent> implements CourseContentDao {

	@Override
	public CourseContent queryById(Integer id) {
		String hql ="from CourseContent where id=?";
        return (CourseContent)this.Queryobject(hql,id);
	}

	@Override
	public CourseContent queryByBoxIdAndPlanDate(Integer boxId, Date planDate) {
		String hql ="from CourseContent where boxId=? and planDate=?";
		Object[] obj = {boxId, planDate};
        return (CourseContent)this.Queryobject(hql,obj);
	}

	@Override
	public List<CourseContent> queryByMonthAndBoxId(String date, Integer boxId) {
		String hql = "from CourseContent where boxId=? and planDate like '"+date+"%'";
		return this.list(hql,boxId);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public CourseContent queryByDateAndBoxId(String dateStr, Integer boxId) {
		String hql = "from CourseContent where boxId=:boxId and planDate=:planDate";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		q.setString("planDate", dateStr);
		List list = q.list();
		if(list!=null&&list.size()>0){
			return (CourseContent) list.get(0);
		}
		return null;
	}

	/**
	 * 获取月份中已添加课程安排的日期
	 *
	 * @param date
	 * @param boxId
	 * @param courseTypeId
	 * @return
	 */
	@Override
	public List<CourseContent> queryByMonthAndBoxIdAndCourseTypeId(String date, Integer boxId, int courseTypeId) {
		String hql = "from CourseContent where boxId=? and courseTypeId=? and planDate like '"+date+"%'";
		Object[] obj = {boxId, courseTypeId};
		return this.list(hql,obj);
	}

	/**
	 * @param boxId
	 * @param courseTypeId
	 * @param date
	 * @return
	 */
	@Override
	public CourseContent queryByBoxIdAndCourseTypeIdAndPlanDate(Integer boxId, int courseTypeId, Date date) {
		String hql ="from CourseContent where boxId=? and courseTypeId=? and planDate=?";
		Object[] obj = {boxId, courseTypeId, date};
		return (CourseContent)this.Queryobject(hql,obj);
	}

}
 