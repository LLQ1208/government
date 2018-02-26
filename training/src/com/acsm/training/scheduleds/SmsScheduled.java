package com.acsm.training.scheduleds;

import com.acsm.training.service.SmsService;
import com.acsm.training.util.SmsUnits;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:27 2017/12/16
 */
@Component
public class SmsScheduled {
    @Autowired
    SmsService smsService;

    public void init() {
    }

    //如果第一个时间秒设置为0，那提醒时间将会晚一分钟 时间设置为0秒时，当时间到时例如23:10份提醒，23:10:00即时时间为23:09分并不是23:10分
//    @Scheduled(cron = "01 0/1 * * * ? ")
    public void sendSms() throws ClientException, InterruptedException {
        System.out.println("定时发送短信业务-----------------------------go");
        List<Object[]>  sendList = smsService.findBySmsSendList();
        Map<Integer,JSONArray> resultList = new HashMap<Integer,JSONArray>();

        //得到需要发短信集合
        for (Object[] obj : sendList) {
            System.out.println("参数1  "+obj[2]);
            System.out.println("参数2  "+obj[3]);
            System.out.println("参数3  "+obj[4]);
            System.out.println(0 != Integer.valueOf(obj[9].toString()));
            System.out.println(null != obj[6]);
            System.out.println(null != obj[7]);
            System.out.println( !"".equals(obj[7]));
            if( 0 != Integer.valueOf(obj[9].toString()) && null != obj[6] && null != obj[7] && !"".equals(obj[7].toString())){ //过滤无效数据
                if(resultList.containsKey(obj[6])){
                    resultList.get(obj[6]).add(obj);
                }else{
                    JSONArray objArr = new JSONArray();
                    objArr.add(obj);
                    resultList.put(Integer.valueOf(obj[6].toString()),objArr);
                }
            }
        }
        for(Integer key : resultList.keySet()){
            StringBuffer phoneStr = new StringBuffer();
            JSONArray jsonArray = resultList.get(key);
            JSONObject param = new JSONObject();
            for (int i = 0; i < jsonArray.size(); i++) {
                Object[] obj = (Object[]) jsonArray.get(i);
                 if(0 == i){
                     System.out.println("参数8   "+obj[8]);
                     param.put("courseTime",obj[8]);
                     param.put("train",obj[0]);
                     param.put("crouseName",obj[1]);
                     param.put("downTime",null != obj[5] ? Integer.valueOf(obj[5].toString()) + 10 : 40); //如果没有设置时间默认30+10
                 }
                 phoneStr.append(obj[7]).append(",");
            }
            if(phoneStr.length() > 0 ){
                smsService.addSendSms(phoneStr.toString().substring(0,phoneStr.toString().length()-1), SmsUnits.SUBSCRIBE_SMS,param);
            }
        }
        System.out.println("定时发送短信业务-----------------------------end");
    }
}
