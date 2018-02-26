package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.WodRecord;
import com.acsm.training.model.WodRecord;

/**
 * Created by lq on 2017/12/13.
 */
public interface WodRecordDao extends BaseDao<WodRecord>{
    List<WodRecord> queryRecordByWodId(int wodId,int orderType);

}
