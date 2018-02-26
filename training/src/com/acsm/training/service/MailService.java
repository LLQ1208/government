package com.acsm.training.service;

import com.acsm.training.model.Mail;
import com.acsm.training.model.basic.PageHelper;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:59 2017/11/22
 */
public interface MailService {

    public void sendMail(String eMails,String message, JSONObject mailContent);

    public Mail quneryById(Integer mailId);

    public void addMail(Mail mail);

    public void updateMail(Mail mail);

    public PageHelper<Mail> queryMailList(Map<String, Object> conditions);

    public void mailSend(Map<String, Object> conditions);

//    public void addMailRecord(Integer memberCardId);

    public void deletedMailRecord(Integer memberCardId);

//    public List<Object[]> queryMemberBirthdayList(Integer bossUserId,String BirthdayStr);
//
//    public List<Object[]> queryFutureTenseMemberWarnTime(Integer bossUserId);
//
//    public List<Object[]> queryMemberWarnTime(Integer bossUserId);

    public List<Mail> queryListByBossId(int bossId);

}
