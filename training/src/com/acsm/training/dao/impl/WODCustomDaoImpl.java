package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.dao.WODCustomDao;
import com.acsm.training.model.WodCustom;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WODCustomDao;
import com.acsm.training.model.WodCustom;


@Repository("wodCustomDao")
public class WODCustomDaoImpl extends BaseDaoImpl<WodCustom> implements WODCustomDao {


	@Override
	public List<WodCustom> queryByMember(Integer memberId,Integer pageIndex,Integer pageSize) {
		int start=(pageIndex-1)*pageSize;
		String hql ="from WodCustom where userId="+memberId+" order By createTime desc ";
		Query query = this.getSession().createQuery(hql); 
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
        return query.list();
	}
}
