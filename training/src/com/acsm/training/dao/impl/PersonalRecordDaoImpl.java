package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.model.PersonalRecord;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.PersonalRecordDao;
import com.acsm.training.model.PersonalRecord;

@Repository
public class PersonalRecordDaoImpl extends BaseDaoImpl<PersonalRecord> implements PersonalRecordDao{

    @Override
    public List<PersonalRecord> queryList(int userId) {
        String hql = "from PersonalRecord where userId=?";
        return this.list(hql, userId);
    }

    @Override
    public PersonalRecord queryByUserIdAndActionId(int userId, int actionId) {
        String hql = "from PersonalRecord where userId=? and actionId=?";
        Object[] obj = {userId, actionId};
        List<PersonalRecord> list = this.list(hql, obj);
        return list!=null&&list.size()>0?list.get(0):null;
    }
}
