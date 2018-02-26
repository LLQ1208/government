package com.acsm.training.dao.impl;

import java.util.List;
import java.util.Map;

import com.acsm.training.dao.CoachDao;
import com.acsm.training.model.Coach;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CoachDao;
import com.acsm.training.model.Coach;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

@Repository("coachDao")
public class CoachDaoImpl extends BaseDaoImpl<Coach> implements CoachDao {

	@Override
	public Coach queryById(Integer id) {
		String hql ="from Coach where id=?";
        return (Coach)this.Queryobject(hql,id);
	}

	@Override
	public List<Coach> queryList() {
		return this.list("from Coach where isDeleted=0");
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<Coach> queryPageByCondition(Map<String, Object> conditions) {
		String hql="from Coach where 1=1 ";
		String hqlCount="select count(*) from Coach where 1=1 ";
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
			hql += " and  (name like '%"+keyword+"%' or phone like '%"+keyword+"%')";
			hqlCount += " and  (name like '%"+keyword+"%' or phone like '%"+keyword+"%')";;
		}
		hql+=" and boxId="+boxId;
		hqlCount+=" and boxId="+boxId;
		hql+=" and isDeleted=0";
		hqlCount += " and isDeleted=0";
		hql+=" order by createTime desc";
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<Coach> list = q.list();
		//数据列表
		PageHelper<Coach> page = new PageHelper<Coach>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

	@Override
	public List<Coach> queryListByBoxId(int boxId) {
		String hql = "from Coach where isDeleted=0 and boxId=?";
		return this.list(hql,boxId);
	}

	@Override
	public List<Coach> queryAllCoachListOfBoss(int userId) {
		String hql = "select c from Coach as c where c.boxId in (select b.id from Box as b where b.userId=?) and c.isDeleted = 0";
		return this.list(hql,userId);
	}

}
