package com.acsm.training.dao;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Mail;
import com.acsm.training.model.basic.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:20 2018/1/24
 */
public interface MailManageDao extends BaseDao<Mail> {
    public PageHelper<Mail> queryMailList(Map<String, Object> conditions);

    public Mail queryById(Integer mailId);

    public List<Mail> queryMailListByAll();

    public void addMailRecord(Integer memberCardId);

    public void deletedMailRecord(Integer memberCardId);

    public List<Object[]> queryMemberBirthdayList(Integer bossUserId,String BirthdayStr);

    public List<Object[]> queryFutureTenseMemberWarnTime(Integer bossUserId);

    public List<Object[]> queryMemberExpiry(Integer bossUserId);

    public List<Mail> queryListByBossId(int bossId);
}
