package com.acsm.training.dao;

import com.acsm.training.model.SmsCode;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:00 2017/11/27
 */
public interface SmsDao extends  BaseDao<SmsCode>{
    public SmsCode addSendSms(String phone);

    public SmsCode querySms30min(String phone);

    public List<Object[]> findBySmsSendList();
}
