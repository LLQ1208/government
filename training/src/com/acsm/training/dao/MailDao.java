package com.acsm.training.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:03 2017/11/22
 */
public interface MailDao {
    public void sendMail(String eMails, String message, JSONObject mailContent);
}
