package com.acsm.training.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsm.training.dao.PersonalRecordDao;
import com.acsm.training.model.PersonalRecord;
import com.acsm.training.model.RecordAction;
import com.acsm.training.service.PersonalRecordService;

@Service
public class PersonalRecordServiceImpl implements PersonalRecordService{

    @Resource
    private PersonalRecordDao personalRecordDao;

    @Override
    public PersonalRecord add(PersonalRecord pr) {
        return personalRecordDao.add(pr);
    }

    @Override
    public List<PersonalRecord> queryList(int userId) {
        return personalRecordDao.queryList(userId);
    }

    @Override
    public PersonalRecord queryByUserIdAndActionId(int userId, int actionId) {
        return personalRecordDao.queryByUserIdAndActionId(userId, actionId);
    }

    @Override
    public void update(PersonalRecord pr) {
        personalRecordDao.update(pr);
    }

    @Override
    public void updateByRecord(int userId, List<RecordAction> recordActionList) {
        for(RecordAction ra : recordActionList){
            int actionId = ra.getActionId();
            PersonalRecord pr = personalRecordDao.queryByUserIdAndActionId(userId, actionId);
            if(pr == null){
                pr = new PersonalRecord();
                pr.setUserId(userId);
                pr.setLastUpdateTime(new Date());
                pr.setActionId(actionId);
                pr.setActionName(ra.getActionName());
                pr.setPr(ra.getUnitValue());
                pr.setPrUnit(ra.getUnit());
                personalRecordDao.add(pr);
            }else{
                if(pr.getPr() < ra.getUnitValue()){
                    pr.setLastUpdateTime(new Date());
                    pr.setActionName(ra.getActionName());
                    pr.setPr(ra.getUnitValue());
                    pr.setPrUnit(ra.getUnit());
                    personalRecordDao.update(pr);
                }
            }
        }
    }
}
