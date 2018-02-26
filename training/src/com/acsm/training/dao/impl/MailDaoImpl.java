package com.acsm.training.dao.impl;

import com.acsm.training.dao.MailDao;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.dao.MailDao;
import com.acsm.training.util.IpUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:06 2017/11/22
 */
@Repository("mailDao")
public class MailDaoImpl implements MailDao {

    private static Logger logger = Logger.getLogger(MailDaoImpl.class);
    private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /**
    *@Author : RedCometJ
    *@Description :
    *@params  [emails, emailMsg]
    *@return void
    *@Date : 2017/11/23
    */
    @Override
    public void sendMail(String emails, String emailMsg, JSONObject mailContent){

        // 1.创建一个程序与邮件服务器会话对象 Session
        final Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host", ALIDM_SMTP_HOST);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        //邮箱发送服务器端口,这里设置为465端口
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // 发件人的账号
//        props.put("mail.user", "cfersystem@cferclub.com");
//        // 访问SMTP服务时需要提供的密码
//        props.put("mail.password", "sysCFer.club");

        try {
            props.setProperty("mail.smtp.localhost", IpUtils.getLocalIP()); //上线改地址
        } catch (Exception e) {
            System.out.println("get localhost ip fail ! " + e);
        }

        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("cfernotice@cferclub.com", "noticeCFer12"); //要改
            }
        };
        Session session = Session.getInstance(props, auth);

//        for (int i = 0; i < emails.size(); i++) {
            try {
                System.out.println("1 init");
                String email = emails;
                // 2.创建一个Message，它相当于是邮件内容
                Message message = new MimeMessage(session);
                System.out.println("2  message init");
                // 设置发送者
                message.setFrom(new InternetAddress("cfernotice@cferclub.com"));
                System.out.println("3  message init");
                // 设置发送方式与接收者
                message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
                System.out.println("4 message init");
                // 设置主题
                message.setSubject(mailContent.getString("title"));
                System.out.println("5 message init");
                // 设置内容
                message.setContent(emailMsg, "text/html;charset=utf-8");
                System.out.println("6 message init");

                // 3.创建 Transport用于将邮件发送
                Transport.send(message);
                System.out.println("7 message init");
            } catch (Exception e) {
                logger.error("mail send fail ! "+e);
            }
    }
}
