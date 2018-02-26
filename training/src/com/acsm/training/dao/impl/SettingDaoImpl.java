package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/8.
 */

import com.acsm.training.dao.SettingDao;
import com.acsm.training.model.Setting;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.SettingDao;
import com.acsm.training.model.Setting;

/**
 * @Author lianglinqiang
 * @create 2017-12-08
 */
@Repository
public class SettingDaoImpl extends BaseDaoImpl<Setting> implements SettingDao {
    @Override
    public Setting queryWodSetting(int userId) {
        String hql = "from Setting where userId=?";
        return (Setting)this.Queryobject(hql,userId);
    }
}
