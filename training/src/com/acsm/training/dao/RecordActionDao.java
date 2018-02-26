package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.RecordAction;
import com.acsm.training.model.RecordAction;

public interface RecordActionDao extends BaseDao<RecordAction>{

    public List<RecordAction> queryListByRecordId(int recordId);

    public void deleteByRecordId(int recordId);
}
