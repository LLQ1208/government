package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Record;
import com.acsm.training.model.basic.PageHelper;

public interface RecordDao extends BaseDao<Record>{

    public List<Record> queryListByUserId(int userId);

    public PageHelper<Record> queryListByUserId(int userId, int pageIndex, int pageSize);

    public Record queryById(int recordId);
}
