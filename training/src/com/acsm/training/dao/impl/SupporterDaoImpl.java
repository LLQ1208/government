package com.acsm.training.dao.impl;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.SupporterDao;
import com.acsm.training.model.Supporter;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

@Repository("supporterDao")
public class SupporterDaoImpl extends BaseDaoImpl<Supporter> implements SupporterDao {

	@Override
	public Supporter queryById(Integer id) {
		String hql ="from Supporter where id=?";
        return (Supporter)this.Queryobject(hql,id);
	}

	@Override
	public List<Supporter> queryListByBoxId(Integer boxId) {
		String hql = "from Supporter where boxId=?";
		return this.list(hql, boxId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<Supporter> queryPageByConditions(Map<String, Object> conditions) {
		String hql="from Supporter where 1=1 ";
		String hqlCount="select count(*) from Supporter where 1=1 ";
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
			hql += " and  (name like '%"+keyword+"%' or phone like '%"+keyword+"%' or email like '%"+keyword+"%')";
			hqlCount += " and  (name like '%"+keyword+"%' or phone like '%"+keyword+"%' or email like '%"+keyword+"%')";;
		}
		hql+=" and boxId="+boxId;
		hqlCount+=" and boxId="+boxId;
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<Supporter> list = q.list();
		//数据列表
		PageHelper<Supporter> page = new PageHelper<Supporter>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

}
