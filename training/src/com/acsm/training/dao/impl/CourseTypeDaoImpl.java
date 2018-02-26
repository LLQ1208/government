package com.acsm.training.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.acsm.training.dao.CourseTypeDao;
import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CourseTypeDao;
import com.acsm.training.model.CourseType;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

@Repository("courseTypeDao")
public class CourseTypeDaoImpl extends BaseDaoImpl<CourseType> implements CourseTypeDao {

	@Override
	public CourseType queryById(Integer id) {
		String hql ="from CourseType where id=?";
        return (CourseType)this.Queryobject(hql,id);
	}

	@Override
	public List<CourseType> queryList(Integer boxId) {
		String hql = "from CourseType where userId=? and isDeleted=0";
		return this.list(hql,boxId);
	}

	@Override
	public List<CourseType> queryListByBoxIdAndDate(Integer boxId,Date date) {
		String hql = "from CourseType where boxId=:boxId and isDeleted=0 and TO_DAYS(insertTime) =to_days(date(:date)) ";
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		q.setInteger("boxId", boxId);
		q.setDate("date", date);
		return  q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<CourseType> queryPageByConditions(Map<String, Object> conditions) {
		String hql="from CourseType where 1=1 ";
		String hqlCount="select count(*) from CourseType where 1=1 ";
		int pageSize = 8;
		int pageIndex = 1;
		String keyword = "";
		int boxId = 0;
		if(conditions.get("pageSize")!=null){
			pageSize = (Integer) conditions.get("pageSize");
		}
		if(conditions.get("pageIndex")!=null){
			pageIndex = (Integer) conditions.get("pageIndex");
		}
		if(conditions.get("keyword")!=null){
			keyword = (String) conditions.get("keyword");
		}
		if(conditions.get("boxId")!=null){
			boxId = (Integer) conditions.get("boxId");
		}
		if(StringUtils.isNotEmpty(keyword)){
			hql += " and  (name like '%"+keyword+"%')";
			hqlCount += " and  (name like '%"+keyword+"%')";;
		}
		hql+=" and boxId="+boxId;
		hqlCount+=" and userId="+ boxId;
		hql+=" and isDeleted=0";
		hqlCount += " and isDeleted=0";
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<CourseType> list = q.list();
		//数据列表
		PageHelper<CourseType> page = new PageHelper<CourseType>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<CourseType> newQueryPageByConditions(Map<String, Object> conditions) {
		String hql="from CourseType where 1=1 ";
		String hqlCount="select count(*) from CourseType where 1=1 ";
		int pageSize = 8;
		int pageIndex = 1;
		String keyword = "";
//		int boxId = 0;
		Integer userId = null;
		if(conditions.get("pageSize")!=null){
			pageSize = (Integer) conditions.get("pageSize");
		}
		if(conditions.get("pageIndex")!=null){
			pageIndex = (Integer) conditions.get("pageIndex");
		}
		if(conditions.get("keyword")!=null){
			keyword = (String) conditions.get("keyword");
		}
		if(conditions.get("userId")!=null){
			userId = (Integer) conditions.get("userId");
		}
		if(StringUtils.isNotEmpty(keyword)){
			hql += " and  (name like '%"+keyword+"%')";
			hqlCount += " and  (name like '%"+keyword+"%')";;
		}
		if(null != userId){
			hql+=" and userId="+userId;
			hqlCount+=" and userId="+ userId;
		}
		hql+=" and isDeleted=0";
		hqlCount += " and isDeleted=0";

		if( null != conditions.get("sortType") && (Integer)conditions.get("sortType") != 0){
			hql+=" order by name";
			hqlCount+=" order by name";
		}
		Session session = getSession();
		Query q = null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<CourseType> list = q.list();
		//数据列表
		PageHelper<CourseType> page = new PageHelper<CourseType>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

	@Override
	public List<CourseType> queryListByUserId(int userId) {
		String hql = "from CourseType where userId=? and isDeleted=0";
		return this.list(hql,userId);
	}

}
