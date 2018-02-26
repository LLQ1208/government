package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.PersonalRecord;
import com.acsm.training.model.RecordAction;
import com.acsm.training.model.PersonalRecord;
import com.acsm.training.model.RecordAction;

public interface PersonalRecordService {

    /**
     * 添加最佳成绩
     * @param pr
     */
    public PersonalRecord add(PersonalRecord pr);

    /**
     * 获取pr列表
     * @return
     */
    public List<PersonalRecord> queryList(int userId);

    public PersonalRecord queryByUserIdAndActionId(int userId, int actionId);

    public void update(PersonalRecord pr);

    void updateByRecord(int userId, List<RecordAction> recordActionList);
}
