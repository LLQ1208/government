package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.dao.BoxDao;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.BoxDao;
import com.acsm.training.model.Box;

/**
 * 
 * @author liuxiaobing
 *
 */
@Repository("boxDao")
public class BoxDaoImpl extends BaseDaoImpl<Box> implements BoxDao {

	@Override
	public Box queryById(Integer id) {
		String hql ="from Box where id=?";
        return (Box)this.Queryobject(hql,id);
	}

	@Override
	public List<Box> queryList() {
		String hql = "from Box where status=1";
		return this.list(hql);
	}

	@Override
	public List<Box> queryBoxListByUser(int userId) {
		String hql = "from Box where status=1 and userId=?";
		return this.list(hql,userId);
	}
	@Override
	public List<Object[]> queryVaildList(Integer boxId){
		StringBuffer sql = new StringBuffer();
		sql.append(" select m.id from t_member m");
		sql.append(" left join t_member_card mc on m.id = mc.memer_id ");
		sql.append(" left join t_member_card_template mct on mct.id = mc.member_card_template_id ");
		sql.append(" where m.box_id= ").append(boxId);
		sql.append(" and (( mc.custom_card_type = 1 and mc.card_start_time <= date_format(sysdate(), '%y-%m-%d') and mc.card_end_time >= date_format(sysdate(), '%y-%m-%d'))");
		sql.append(" or ( mc.custom_card_type = 2 and mc.card_start_time <= date_format(sysdate(), '%y-%m-%d') and mc.card_end_time >= date_format(sysdate(), '%y-%m-%d') and mc.remain_num > 0)");
		sql.append(" or ( mct.template_type = 1 and mc.card_start_time <= date_format(sysdate(), '%y-%m-%d') and mc.card_end_time >= date_format(sysdate(), '%y-%m-%d'))");
		sql.append(" or ( mct.template_type = 2 and mc.card_start_time <= date_format(sysdate(), '%y-%m-%d') and mc.card_end_time >= date_format(sysdate(), '%y-%m-%d') and mc.remain_num > 0))");
		return queryBySql(sql.toString());
	}
	@Override
	public List<Box> queryBoxListByBoss(int userId) {
		String hql = "from Box where status != 2 and userId=?";
		return this.list(hql,userId);
	}
}
