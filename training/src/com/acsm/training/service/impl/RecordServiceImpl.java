package com.acsm.training.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.acsm.training.dao.RecordDao;
import com.acsm.training.model.basic.PageHelper;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.ActionDao;
import com.acsm.training.dao.RecordActionDao;
import com.acsm.training.model.Record;
import com.acsm.training.model.RecordAction;
import com.acsm.training.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{

    @Resource
    private RecordDao recordDao;

    @Resource
    private RecordActionDao recordActionDao;

    @Resource
    private ActionDao actionDao;

    @Override
    public void add(Record record) {
        recordDao.add(record);
    }

    @Override
    public void addRecordAndRecordActions(Record record) {
        Record saveRecord = recordDao.add(record);
        for(RecordAction ra:record.getRecordActionList()) {
            ra.setRecordId(saveRecord.getId());
            recordActionDao.add(ra);
        }
    }

    @Override
    public List<Record> queryListByUserId(int userId) {
        List<Record> records = recordDao.queryListByUserId(userId);
        for(Record record:records){
            List<RecordAction> recordActionList = recordActionDao.queryListByRecordId(record.getId());
            record.setRecordActionList(recordActionList);
        }
        return records;
    }

    @Override
    public PageHelper<Record> queryListByUserId(int userId, int pageIndex, int pageSize) {
        PageHelper<Record> page = recordDao.queryListByUserId(userId, pageIndex, pageSize);
        for(Record record:page.getList()){
            List<RecordAction> recordActionList = recordActionDao.queryListByRecordId(record.getId());
            record.setRecordActionList(recordActionList);
        }
        return page;
    }

    @Override
    public void updateRecordAndRecordActions(Record record) {
        int recordId = record.getId();
        recordActionDao.deleteByRecordId(recordId);
        for(RecordAction ra:record.getRecordActionList()) {
            ra.setRecordId(recordId);
            recordActionDao.add(ra);
        }
        recordDao.update(record);
    }

    @Override
    public Record queryById(int recordId) {
        return recordDao.queryById(recordId);
    }
}
