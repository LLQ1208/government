package com.acsm.training.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.acsm.training.model.Course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CourseOrderDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseOrder;
import com.acsm.training.model.Member;

@Repository("courseOrderDao")
public class CourseOrderDaoImpl extends BaseDaoImpl<CourseOrder> implements CourseOrderDao {

	@Override
	public CourseOrder queryById(Integer id) {
		String hql = "from CourseOrder where id=?";
		return (CourseOrder) this.Queryobject(hql, id);
	}

	@SuppressWarnings({ "rawtypes"})
	@Override
	public List<CourseOrder> queryListByCourseId(Integer courseId, String date) {
		String hql = "select o,m,c from CourseOrder as o,Member as m,Course as c where o.courseId=c.id and o.memberId=m.id and o.courseId=:courseId and o.date=:date and o.isDeleted=0"; 
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("courseId", courseId);
		q.setString("date", date);
		List list = q.list();
		List<CourseOrder> courseOrderList = new ArrayList<CourseOrder>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				CourseOrder courseOrder = (CourseOrder) ob[0];
				Member member = (Member) ob[1];
				Course course = (Course) ob[2];
				courseOrder.setCourse(course);
				courseOrder.setMember(member);
				courseOrderList.add(courseOrder);
			}
		}
		return courseOrderList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<CourseOrder> queryListByMemberId(Integer memberId,String date) {
		String hql = "select o,m,c from CourseOrder as o,Member as m,Course as c where o.courseId=c.id and o.memberId=m.id and o.memberId=:memberId and o.date=:date and o.isDeleted=0"; 
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		q.setString("date", date);
		List list = q.list();
		List<CourseOrder> courseOrderList = new ArrayList<CourseOrder>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				CourseOrder courseOrder = (CourseOrder) ob[0];
				Member member = (Member) ob[1];
				Course course = (Course) ob[2];
				courseOrder.setCourse(course);
				courseOrder.setMember(member);
				courseOrderList.add(courseOrder);
			}
		}
		return courseOrderList;
	}

//	@Override
//	public CourseOrder queryListByMemberId(Integer courseId, Integer memberId, Date date) {
//		String hql = "from CourseOrder where courseId=? and memberId=? and date=? and isDeleted=0";
//		Object[] obj = {courseId, memberId, date};
//		return (CourseOrder) this.Queryobject(hql,obj);
//	}

	@SuppressWarnings("rawtypes")
	@Override
	public CourseOrder queryByMemberId(Integer courseId, Integer memberId, String dateStr) {
		String hql = "from CourseOrder where courseId=:courseId and memberId=:memberId and date=:date and isDeleted=0";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("courseId", courseId);
		q.setInteger("memberId", memberId);
		q.setString("date", dateStr);
		List list = q.list();
		if(list!=null&&list.size()>0){
			return (CourseOrder) list.get(0);
		}
		return null;
	}

	@Override
	public List<CourseOrder> queryCalendarListByMemberId(Integer memberId) {
		String hql = "from CourseOrder where memberId=:memberId and isDeleted=0 group by date";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		List<CourseOrder> list = q.list();
		return list;
	}

	@Override
	public List<CourseOrder> queryCalendarListByMemberIdAndBoxId(int memberId, int boxId) {
		String hql = "select co from CourseOrder as co,Course as c where co.courseId=c.id and c.boxId=:boxId and co.memberId=:memberId and co.isDeleted=0 group by co.date";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		q.setInteger("boxId", boxId);
		List<CourseOrder> list = q.list();
		return list;
	}

	@Override
	public List<CourseOrder> queryReservationList(Integer boxId, Integer courseId, String date) {
		String hql = "select o,m,c from CourseOrder as o,Member as m,Course as c " +
				"where o.courseId=c.id and " +
				"o.memberId=m.id and o.date=:date and o.isDeleted=0 ";
		if(null != boxId){
			hql += " and c.boxId="+boxId;
		}
		if(null != courseId){
			hql += " and c.id=" + courseId;
		}
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setString("date", date);
		List list = q.list();
		List<CourseOrder> courseOrderList = new ArrayList<CourseOrder>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				CourseOrder courseOrder = (CourseOrder) ob[0];
				Member member = (Member) ob[1];
				Course course = (Course) ob[2];
				courseOrder.setCourse(course);
				courseOrder.setMember(member);
				courseOrderList.add(courseOrder);
			}
		}
		return courseOrderList;
	}

	@Override
	public List<CourseOrder> queryCoachReservationList(Integer boxId, Integer courseId, Integer coachId, String date) {
		String hql = "select o,m,c from CourseOrder as o,Member as m,Course as c where o.courseId=c.id and " +
				"o.memberId=m.id and o.date=:date and o.isDeleted=0 ";
		if(null != boxId){
			hql += " and c.boxId="+boxId;
		}
		if(null != courseId){
			hql += " and c.id=" + courseId;
		}
		if(null != coachId){
			hql += " and c.coach.id="+ coachId;
		}
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setString("date", date);
		List list = q.list();
		List<CourseOrder> courseOrderList = new ArrayList<CourseOrder>();
		if(list != null && list.size()>=0){
			Iterator it = list.iterator();
			while(it.hasNext()){
				Object[] ob = (Object[])it.next();
				CourseOrder courseOrder = (CourseOrder) ob[0];
				Member member = (Member) ob[1];
				Course course = (Course) ob[2];
				courseOrder.setCourse(course);
				courseOrder.setMember(member);
				courseOrderList.add(courseOrder);
			}
		}
		return courseOrderList;
	}

	@Override
	public List<Object[]> queryLastOrderOfMember(int memberId,Integer boxId) {
		String hql = "select co.DATE,c.START_TIME from t_course_order co LEFT JOIN t_course c on co.COURSE_ID=c.ID "+
				" LEFT JOIN t_coach t on t.ID=c.COACH_ID "+
				" where co.MEMBER_ID=" + memberId+
				" and t.BOX_ID="+boxId +
				" order by co.id desc ";
		return this.queryBySql(hql);
	}

	@Override
	public List<CourseOrder> queryNowOrderCount(int memberId, String beginTime,Integer boxId) {
		String hql = "select co from CourseOrder as co,Course as c " +
				" where " +
				" co.courseId=c.id and c.boxId="+boxId +
				" and co.memberId=:memberId and co.date >=:beginTime ";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		q.setString("beginTime",beginTime);
		List<CourseOrder> list = q.list();
		return list;
	}

	@Override
	public List<CourseOrder> queryLastMonthOrder(int memberId, String cardBeginTime, String lastMonthTime,Integer boxId) {
		String hql = "select co from CourseOrder as co,Course as c " +
				" where co.courseId=c.id " +
				" and c.boxId="+boxId +
				" and co.memberId=:memberId and co.signStatus=1 and co.date >='"+ cardBeginTime+"'  " +
				" and DATE_FORMAT(co.date,'%Y-%m') = '"+lastMonthTime +"'";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		List<CourseOrder> list = q.list();
		return list;
	}

	@Override
	public List<CourseOrder> queryThisMonthOrder(int memberId, String thisMonthTime,Integer boxId) {
		String hql = "select co from CourseOrder as co,Course as c" +
				" where co.courseId=c.id " +
				" and c.boxId=" + boxId +
				" and co.memberId=:memberId and co.signStatus=1 " +
				" and DATE_FORMAT(co.date,'%Y-%m') = '"+thisMonthTime +"'";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		List<CourseOrder> list = q.list();
		return list;
	}

	@Override
	public List<Object[]> queryOrderList(int memberId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" DATE_FORMAT(co.DATE, '%Y/%m/%d'), "); //0
		sql.append(" b.`NAME`, ");//1
		sql.append(" ct.`NAME` AS typeName, ");//2
		sql.append(" c.START_TIME, ");//3
		sql.append(" co.SIGN_STATUS, ");//4
		sql.append(" c.END_TIME, ");//5
		sql.append(" co.IS_DELETED ");//6
		sql.append(" FROM t_course_order co ");
		sql.append(" LEFT JOIN t_course c ON co.COURSE_ID = c.ID ");
		sql.append(" LEFT JOIN t_course_type ct ON ct.ID = c.COURSE_TYPE_ID ");
		sql.append(" LEFT JOIN t_box b ON c.BOX_ID = b.ID ");
		sql.append(" WHERE co.MEMBER_ID =").append(memberId);
		sql.append(" order by co.id desc ");
		return this.queryBySql(sql.toString());
	}


}
