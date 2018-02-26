package com.acsm.training.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.dao.MailDao;
import com.acsm.training.dao.MemberDao;
import com.acsm.training.enums.MailType;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.dao.MailManageDao;
import com.acsm.training.model.Mail;
import com.acsm.training.model.Member;
import org.springframework.stereotype.Service;

import com.acsm.training.service.MailService;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:02 2017/11/22
 */
@Service("mailService")
public class MailServiceImpl implements MailService {

    @Resource(name="mailDao")
    private MailDao sendMailDao;

    @Resource(name="MailManageDao")
    private MailManageDao mailManageDao;

    @Resource(name="memberDao")
    private MemberDao memberDao;

    @Override
    public void sendMail(String eMails, String message, JSONObject mailContent) {
        sendMailDao.sendMail(eMails,message,mailContent);
    }

    @Override
    public Mail quneryById(Integer mailId) {
        return mailManageDao.queryById(mailId);
    }

    @Override
    public void addMail(Mail mail) {
        mailManageDao.add(mail);
    }

    @Override
    public void updateMail(Mail mail) {
        mailManageDao.update(mail);
    }

    @Override
    public PageHelper<Mail> queryMailList(Map<String, Object> conditions) {
        return mailManageDao.queryMailList(conditions);
    }

    @Override
    public void mailSend(Map<String, Object> conditions) {
        long startTime = System.currentTimeMillis();
        if( null != conditions.get("mailIds") && conditions.containsKey("memberId") &&
                !conditions.get("mailIds").toString().equals("")){   //创建会员时勾选发送的邮件
            System.out.println("进入会员卡发送邮件-------------------------------");
            String[] mailStr = conditions.get("mailIds").toString().split(",");
            Member member = memberDao.queryById((Integer)conditions.get("memberId"));
            for (String mailId : mailStr) {
                Mail mail = mailManageDao.queryById(Integer.valueOf(mailId));
                if( null != mail.getMailSendRule() && mail.getMailSendRule() == -1){
                    JSONObject mailContent = new JSONObject();
                    mailContent.put("title",mail.getTitle());
                    sendMailDao.sendMail(member.getEmail(),mail.getContent().replace("$name",member.getName()),mailContent);
                }
            }
        }else{ //定时器每天校验发送的邮件
            System.out.println("进入定时器-----------------------");
            List<Mail> mailAll = mailManageDao.queryMailListByAll();
            for (Mail mail : mailAll) {
                if(mail.getMailSendRule() == MailType.BIRTHDAY.CODE){ //生日
                    List<Object[]> birthDayArr = mailManageDao.queryMemberBirthdayList(mail.getBossUserId(), DateUtil.DateToString(new Date(), DateUtil.MM_DD));
                    for (Object[] objects : birthDayArr) {
                        if(null != objects[1] && null != objects[2]){
                            JSONObject mailContent = new JSONObject();
                            mailContent.put("title",mail.getTitle());
                            sendMailDao.sendMail(objects[2].toString(),mail.getContent().replace("$name",objects[1].toString()),mailContent);
                        }
                    }
                }else if(mail.getMailSendRule() == MailType.FUTURETENSE.CODE){ //即将到期
                    List<Object[]> futuretenseArr = mailManageDao.queryFutureTenseMemberWarnTime(mail.getBossUserId());
                    for (Object[] objects : futuretenseArr) {
                        if(null != objects[2] && null != objects[3]){
                            JSONObject mailContent = new JSONObject();
                            mailContent.put("title",mail.getTitle());
                            sendMailDao.sendMail(objects[3].toString(),mail.getContent().replace("$name",objects[2].toString()),mailContent);
                        }
                    }

                }else if(mail.getMailSendRule() == MailType.EXPIRY.CODE){ //到期
                    List<Object[]> expiryArr = mailManageDao.queryMemberExpiry(mail.getBossUserId());
                    for (Object[] objects : expiryArr) {
                        if(null != objects[2] && null != objects[3]){
                            JSONObject mailContent = new JSONObject();
                            mailContent.put("title",mail.getTitle());
                            sendMailDao.sendMail(objects[3].toString(),mail.getContent().replace("$name",objects[2].toString()),mailContent);
                            if(null!= objects[5] && Integer.valueOf(objects[5].toString()) == 2 && null!= objects[4] && 0 != Integer.valueOf(objects[5].toString())){
                                mailManageDao.addMailRecord(Integer.valueOf(objects[5].toString()));
                            }
                        }
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();

        float seconds = (endTime - startTime) / 1000F;

        System.out.println( " 邮件发送总共执行 "+Float.toString(seconds) + " 秒.");
    }

//    public void addMailRecord(Integer memberCardId) {
//        mailManageDao.addMailRecord(memberCardId);
//    }

    @Override
    public void deletedMailRecord(Integer memberCardId) {
        mailManageDao.deletedMailRecord(memberCardId);
    }

    @Override
    public List<Mail> queryListByBossId(int bossId) {
        return mailManageDao.queryListByBossId(bossId);
    }

//    public List<Object[]> queryMemberBirthdayList(Integer bossUserId, String BirthdayStr) {
//        return null;
//    }
//
//    public List<Object[]> queryFutureTenseMemberWarnTime(Integer bossUserId) {
//        return null;
//    }
//
//    public List<Object[]> queryMemberWarnTime(Integer bossUserId) {
//        return null;
//    }
}
