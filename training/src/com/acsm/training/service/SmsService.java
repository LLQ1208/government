package com.acsm.training.service;

import com.acsm.training.model.SmsCode;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.acsm.training.model.SmsCode;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:18 2017/11/27
 */
public interface SmsService {
    public boolean addSendSms(String phone, String mode, JSONObject sendParam) throws InterruptedException, ClientException;
    public SmsCode querySms(String phone);
    public List<Object[]> findBySmsSendList();
}
