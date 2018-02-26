package com.acsm.training.service;

import com.acsm.training.model.Setting;

import javax.servlet.http.HttpSession;

/**
 * Created by lq on 2017/12/12.
 */
public interface SettingService {
    /**
     * 老板设置
     * @param userId
     * @return
     */
    public Setting queryWodSetting(int userId);

    public void saveWodSetting(Setting wodSetting);

    public int queryReservationDay(int userId);

    public int queryReservationMinus(int userId);
}
