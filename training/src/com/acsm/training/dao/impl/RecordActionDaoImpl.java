package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.model.RecordAction;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.RecordActionDao;
import com.acsm.training.model.RecordAction;

@Repository
public class RecordActionDaoImpl extends BaseDaoImpl<RecordAction> implements RecordActionDao {

    @Override
    public List<RecordAction> queryListByRecordId(int recordId) {
        String hql = "from RecordAction where recordId=?";
        return this.list(hql, recordId);
    }

    @Override
    public void deleteByRecordId(int recordId) {
        String hql = "delete RecordAction where recordId=?";
        this.UpdateByHql(hql, recordId);
    }
}
