package com.acsm.training.dao.impl;

import java.util.Date;

import com.acsm.training.model.MemberWodLog;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MemberWodLogDao;
import com.acsm.training.model.MemberWodLog;

@Repository
public class MemberWodLogDaoImpl extends BaseDaoImpl<MemberWodLog> implements MemberWodLogDao {

	public MemberWodLog queryListByBoxAndMemberAndWod( int memberId, int wodId) {
		String hql = "from MemberWodLog where  memberId=" + memberId + " and wodId=" + wodId;
		return (MemberWodLog) this.Queryobject(hql);
	}

	@Override
	public void updateBysql(String pics, int id) {
		Object[] args = { pics, new Date(), id };
		this.UpdateByHql("update MemberWodLog set pics =? , date=? where id=?", args);
	}

}
