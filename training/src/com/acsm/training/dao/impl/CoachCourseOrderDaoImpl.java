package com.acsm.training.dao.impl;

import java.math.BigInteger;
import java.util.List;

import com.acsm.training.dao.CoachCourseOrderDao;
import com.acsm.training.model.CoachCourseOrder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CoachCourseOrderDao;
import com.acsm.training.model.CoachCourseOrder;

@Repository("coachCourseOrderDao")
public class CoachCourseOrderDaoImpl extends BaseDaoImpl<CoachCourseOrder> implements CoachCourseOrderDao {

	@SuppressWarnings("rawtypes")
	@Override
	public CoachCourseOrder queryByMemberId(Integer coachCourseId, Integer memberId, String dateStr) {
		String hql = "from CoachCourseOrder where coachCourseId=:coachCourseId and memberId=:memberId and date=:date and isDeleted=0";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("coachCourseId", coachCourseId);
		q.setInteger("memberId", memberId);
		q.setString("date", dateStr);
		List list = q.list();
		if(list!=null&&list.size()>0){
			return (CoachCourseOrder) list.get(0);
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public BigInteger queryOrderNumByIdAndDate(Integer coachCourseId,String dateStr) {
		String hql = "select count(id) from T_COACH_COURSE_ORDER where coach_Course_Id=:coachCourseId and date=:date and is_Deleted=0";
		Session session = this.getSession();
		Query q = session.createSQLQuery(hql);
		q.setInteger("coachCourseId", coachCourseId);
		q.setString("date", dateStr);
		List list = q.list();
		if(list!=null&&list.size()>0){
			return (BigInteger) list.get(0);
		}
		return new BigInteger("0");
	}

	@Override
	public List<CoachCourseOrder> queryLastOfMember(int memberId,Integer boxId) {
		String hql = "select co from CoachCourseOrder as co,CoachCourse as cc " +
				" where co.coachCourseId=cc.id " +
				" and co.memberId="+memberId+
				" and cc.boxId=" + boxId
				+ " and co.isDeleted=0  order by co.id desc ";
		return this.list(hql);
	}

	@Override
	public List<CoachCourseOrder> queryNowCardCount(int memberId, String beginTime,Integer boxId) {
		String hql = "select co from CoachCourseOrder as co,CoachCourse cc " +
				"  where co.coachCourseId=cc.id" +
				" and co.memberId="+memberId +
				" and cc.boxId=" + boxId
				+ " and co.isDeleted=0 and co.signStatus = 1 and co.date >='"+beginTime+"' order by co.id desc ";
		return this.list(hql);
	}

	@Override
	public List<CoachCourseOrder> querCardCountByTime(int memberId, String beginTime, String endTime,Integer boxId) {
		String hql = "select co from CoachCourseOrder as co,CoachCourse cc" +
				"  where co.coachCourseId=cc.id " +
				" and cc.boxId=" + boxId +
				" and co.memberId="+memberId
				+ " and co.isDeleted=0 and co.signStatus = 1 and co.date >='"+beginTime+"'and co.date <='"+endTime+"' ";
		return this.list(hql);
	}

	@Override
	public List<Object[]> queryOrderList(int memberId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		sql.append(" DATE_FORMAT(co.DATE, '%Y-%m-%d'), "); //0
		sql.append(" b.`NAME`, ");//1
		sql.append(" '私教', ");//2
		sql.append(" c.START_TIME, ");//3
		sql.append(" co.SIGN_STATUS, ");//4
		sql.append(" c.END_TIME, ");//5
		sql.append(" co.IS_DELETED ");//6
		sql.append(" FROM t_coach_course_order co ");
		sql.append(" LEFT JOIN t_coach_course c ON co.COACH_COURSE_ID= c.ID ");
		sql.append(" LEFT JOIN t_box b ON c.BOX_ID = b.ID ");
		sql.append(" WHERE co.MEMBER_ID =").append(memberId);
		return this.queryBySql(sql.toString());
	}

	@Override
	public List<CoachCourseOrder> queryListByCourseId(Integer courseId, String dateStr) {
		String hql = "from CoachCourseOrder where coachCourseId="+courseId
				+ " and date='"+dateStr+"' and isDeleted=0";
		return this.list(hql);
	}

}
