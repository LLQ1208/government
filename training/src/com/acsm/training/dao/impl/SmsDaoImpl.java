package com.acsm.training.dao.impl;

import com.acsm.training.dao.SmsDao;
import com.acsm.training.model.SmsCode;
import com.acsm.training.util.DateUtil;
import com.acsm.training.dao.SmsDao;
import com.acsm.training.model.SmsCode;
import com.acsm.training.util.DateUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:02 2017/11/27
 */
@Repository("smsDao")
public class SmsDaoImpl extends BaseDaoImpl<SmsCode> implements SmsDao {

    @Override
    public SmsCode addSendSms(String phone)  {
        //验证码
        String hql ="from SmsCode where phone = ?";
        SmsCode smsCode = (SmsCode) this.Queryobject(hql,phone);
        return smsCode;
    }

    @Override
    public SmsCode querySms30min(String phone) {
        //验证码
        String hql ="from SmsCode where phone = ?";
        SmsCode smsCode = (SmsCode) this.Queryobject(hql,phone);
        return smsCode;
    }

    @Override
    public List<Object[]> findBySmsSendList() {
        String nowTime = DateUtil.DateToString(new Date(), DateUtil.YYYY_MM_DD);
        StringBuffer sql = new StringBuffer();
        sql.append(" select *,if(t.after_time = t.newDate,1,0) as isSend from  ");
        sql.append(" (select tb.name as boxName,tcy.name,tc.start_time, ");
        //如果有企业设置取企业设置时间+10分钟(需求),如果没有值，默认30+10;
        sql.append(" DATE_FORMAT(date_sub(CONCAT(tco.date,' ',tc.start_time),INTERVAL if(ts.class_reservation_minus is not null,ts.class_reservation_minus+10,40) MINUTE),'%Y-%m-%d %H:%i') as after_time, ");
        sql.append(" DATE_FORMAT(now(),'%Y-%m-%d %H:%i') as newDate,ts.class_reservation_minus,tc.id,tm.phone,DATE_FORMAT(CONCAT(tco.date,' ',tc.start_time) ,'%Y-%m-%d %H:%i') as crouseTime ");
        sql.append(" from t_user tu ");
        sql.append(" left join t_member tm on tm.id = tu.related_id ");
        sql.append(" left join t_course_order tco on tm.id = tco.member_id ");
        sql.append(" left join t_course tc on tc.id = tco.course_id  ");
        sql.append(" left join t_course_type tcy on tc.course_type_id = tcy.id ");
        sql.append(" left join t_box tb on tb.id = tc.box_id ");
        sql.append(" left join t_setting ts on ts.user_id = tb.user_id ");
        sql.append(" where tco.date = '").append(nowTime).append("'");
        sql.append(" ) t ");
        return this.queryBySql(sql.toString());
    }


}
