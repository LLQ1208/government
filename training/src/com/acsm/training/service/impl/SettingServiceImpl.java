package com.acsm.training.service.impl;/**
 * Created by lq on 2017/12/12.
 */

import com.acsm.training.dao.SettingDao;
import com.acsm.training.model.Setting;
import com.acsm.training.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author lianglinqiang
 * @create 2017-12-12
 */
@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingDao settingDao;

    @Override
    public Setting queryWodSetting(int userId) {
        return settingDao.queryWodSetting(userId);
    }

    @Override
    public void saveWodSetting(Setting wodSetting) {
        if(null != wodSetting.getId()){
            settingDao.update(wodSetting);
        }else{
            settingDao.add(wodSetting);
        }
    }

    /**
    *@Author : RedCometJ
    *@Description : 预约多少天
    *@params
    *@return
    *@Date : 2018/1/16
    */
    @Override
    public int queryReservationDay(int userId) {
        Setting setting = settingDao.queryWodSetting(userId);
        int reservationDay = 7;
        if(null != setting && null != setting.getReservationDay()){
            reservationDay = setting.getReservationDay();
        }
        return reservationDay;
    }

    /**
    *@Author : RedCometJ
    *@Description : 预约多少时间
    *@params
    *@return
    *@Date : 2018/1/16
    */
    @Override
    public int queryReservationMinus(int userId) {
        int classReservationMinus = 30;
        Setting setting = settingDao.queryWodSetting(userId);
        if(null != setting && null != setting.getClassReservationMinus()){
            classReservationMinus = setting.getClassReservationMinus();
        }
        return classReservationMinus;
    }

}
