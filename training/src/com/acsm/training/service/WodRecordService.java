package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.WodRecord;
import com.acsm.training.model.WodRecord;

/**
 * Created by lq on 2017/12/13.
 */
public interface WodRecordService {

    public List<WodRecord> queryRecordOfWord(int wodId,int orderType);

    public void addWodRecord(WodRecord wodRecord);
}
