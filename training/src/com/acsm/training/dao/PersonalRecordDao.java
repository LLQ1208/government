package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.PersonalRecord;
import com.acsm.training.model.PersonalRecord;

public interface PersonalRecordDao extends BaseDao<PersonalRecord>{

    public List<PersonalRecord> queryList(int userId);

    public PersonalRecord queryByUserIdAndActionId(int userId, int actionId);
}
