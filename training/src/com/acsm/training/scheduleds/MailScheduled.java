package com.acsm.training.scheduleds;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.acsm.training.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:27 2017/12/16
 */
@Component
public class MailScheduled {
    @Autowired
    MailService mailService;
    //如果第一个时间秒设置为0，那提醒时间将会晚一分钟 时间设置为0秒时，当时间到时例如23:10份提醒，23:10:00即时时间为23:09分并不是23:10分
//    @Scheduled(cron = "0 0 2 1/1 * ? ") //凌晨2点执行
    public void init() {
    }

    public void sendMail() throws ClientException, InterruptedException {
        Map<String, Object> conditions = new HashMap<String, Object>();
        mailService.mailSend(conditions);
    }
}
