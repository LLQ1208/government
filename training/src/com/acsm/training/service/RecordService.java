package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Record;
import com.acsm.training.model.basic.PageHelper;

public interface RecordService {

    public void add(Record record);

    public void addRecordAndRecordActions(Record record);

    public List<Record> queryListByUserId(int userId);

    public PageHelper<Record> queryListByUserId(int userId, int pageIndex, int pageSize);

    public void updateRecordAndRecordActions(Record record);

    public Record queryById(int recordId);
}
