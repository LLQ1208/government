package com.acsm.training.dao.impl;

import java.util.List;
import java.util.Map;

import com.acsm.training.dao.NotificationDao;
import com.acsm.training.model.Notification;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.NotificationDao;
import com.acsm.training.model.Notification;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

/**
 * @ClassName NotificationDaoImpl
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月13日 下午7:45:29
 *
 */
@Repository
public class NotificationDaoImpl extends BaseDaoImpl<Notification> implements NotificationDao {

	/* (non-Javadoc)
	 * @see NotificationDao#queryById(int)
	 */
	@Override
	public Notification queryById(int id) {
		String hql ="from Notification where id=?";
        return (Notification)this.Queryobject(hql,id);
	}

	/* (non-Javadoc)
	 * @see NotificationDao#queryPageByConditions(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<Notification> queryPageByConditions(
			Map<String, Object> conditions) {
		String hql="from Notification where 1=1 ";
		String hqlCount="select count(*) from Notification where 1=1 ";
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
			hql += " and  (title like '%"+keyword+"%' or content like '%"+keyword+"%')";
			hqlCount += " and  (title like '%"+keyword+"%' or content like '%"+keyword+"%')";
		}
		hql+=" and boxId="+boxId;
		hqlCount+=" and boxId="+boxId;
		hql+=" order by id desc";
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<Notification> list = q.list();
		//数据列表
		PageHelper<Notification> page = new PageHelper<Notification>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}
}
