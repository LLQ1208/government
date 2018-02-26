package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/13.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.WodRecordDao;
import com.acsm.training.model.WodRecord;
import com.acsm.training.service.WodRecordService;

/**
 * @Author lianglinqiang
 * @create 2017-12-13
 */
@Service
public class WodRecordServiceImpl implements WodRecordService{

    @Autowired
    WodRecordDao wodRecordDao;
    @Override
    public List<WodRecord> queryRecordOfWord(int wodId,int orderType) {
        return wodRecordDao.queryRecordByWodId(wodId,orderType);
    }

    @Override
    public void addWodRecord(WodRecord wodRecord) {
        wodRecordDao.add(wodRecord);
    }
}
