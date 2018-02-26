package com.acsm.training.dao;

import com.acsm.training.model.MemberWodLog;
import com.acsm.training.model.MemberWodLog;

public interface MemberWodLogDao extends BaseDao<MemberWodLog> {

	public MemberWodLog queryListByBoxAndMemberAndWod(int memberId, int wodId);

	public void updateBysql(String pics, int id);
}
