package com.acsm.training.dao;

import com.acsm.training.model.Setting;
import com.acsm.training.model.Setting;

/**
 * Created by lq on 2017/12/8.
 */
public interface SettingDao extends BaseDao<Setting>{
    Setting queryWodSetting(int userId);
}
