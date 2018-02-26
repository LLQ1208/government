package com.acsm.training.dao.impl;

import com.acsm.training.dao.MailManageDao;
import com.acsm.training.model.Mail;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:21 2018/1/24
 */
@Repository("MailManageDao")
public class MailManageDaoImpl extends BaseDaoImpl<Mail> implements MailManageDao {

    @SuppressWarnings("unchecked")
    @Override
    public PageHelper<Mail> queryMailList(Map<String, Object> conditions) {
        String hql="from Mail where 1=1 ";
        String hqlCount="select count(*) from Mail where 1=1 ";
        int pageSize = 10;
        int pageIndex = 1;
        String keyword = "";
//		int boxId = 0;
        Integer userId = null;
        if(conditions.get("pageSize")!=null){
            pageSize = (Integer) conditions.get("pageSize");
        }
        if(conditions.get("pageIndex")!=null){
            pageIndex = (Integer) conditions.get("pageIndex");
        }
        if(conditions.get("userId")!=null){
            userId = (Integer) conditions.get("userId");
        }
        if(null != userId){
            hql+=" and bossUserId="+userId;
            hqlCount+=" and bossUserId="+ userId;
        }
        hql+=" and isDeleted=0";
        hqlCount += " and isDeleted=0";
        hql+=" order by insertTime";
        hqlCount+=" order by insertTime";

        Session session = getSession();
        Query q = null;
        Query qCount = null;
        q = session.createQuery(hql);
        qCount = session.createQuery(hqlCount);
        //获取数据总条数
        int total = Integer.parseInt(qCount.uniqueResult().toString());
        q.setFirstResult((pageIndex-1)*pageSize);
        q.setMaxResults(pageSize);
        List<Mail> list = q.list();
        //数据列表
        PageHelper<Mail> page = new PageHelper<Mail>();
        page.setList(list);
        page.setTotalRow(total);
        return page;
    }

    @Override
    public Mail queryById(Integer mailId) {
        String hql = "from Mail where id = ?";
        return (Mail) this.Queryobject(hql, mailId);
    }

    @Override
    public List<Mail> queryMailListByAll() {
        String hql="from Mail where 1 = 1 ";
        hql+=" and isDeleted=0";
        hql+=" order by insertTime";
        Session session = getSession();
        Query q = null;
        q = session.createQuery(hql);
        List<Mail> list = q.list();
        return list;
    }

    @Override
    public void addMailRecord(Integer memberCardId) {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into t_mail_record values(");
        sql.append(memberCardId).append(")");
        queryBySql(sql.toString());
    }

    @Override
    public void deletedMailRecord(Integer memberCardId) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from t_mail_record where member_card_id =");
        sql.append(memberCardId);
        queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> queryMemberBirthdayList(Integer bossUserId, String BirthdayStr) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select tm.id,tm.name,tm.email from t_user tu ");
        sql.append(" left join t_box tb on tb.user_id = tu.id ");
        sql.append(" left join t_member tm on tm.box_id = tb.id ");
        sql.append(" where tu.id =  ").append(bossUserId);
        sql.append(" and DATE_FORMAT(tm.BIRTHDAY,'%m-%d') ='").append(BirthdayStr).append("'");
        return  queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> queryFutureTenseMemberWarnTime(Integer bossUserId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (");
        sql.append(" select ");
        sql.append(" if(tmc.member_card_template_id = 0,DATE_SUB(tmc.card_end_time,INTERVAL tmc.card_expire_day day),DATE_SUB(DATE_ADD(tmc.card_start_time,INTERVAL tmct.validity day),INTERVAL tmct.expiry_reminder day)) as warnTime, ");
        sql.append(" tm.id,tm.name,tm.email,tmc.id as tmcId");
        sql.append(" from t_user tu");
        sql.append(" left join t_box tb on tb.user_id = tu.id ");
        sql.append(" left join t_member tm on tm.box_id = tb.id ");
        sql.append(" left join t_member_card tmc on tmc.memer_id = tm.id ");
        sql.append(" left join t_member_card_template tmct on tmct.id = tmc.member_card_template_id ");
        sql.append(" where tm.id is not null and tmc.id is not null");
        sql.append(" and tu.id = ").append(bossUserId);
        sql.append(" )t where DATE_FORMAT(t.warnTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')");
        return  queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> queryMemberExpiry(Integer bossUserId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select * from (select ");
        sql.append(" if(tmc.member_card_template_id = 0,tmc.card_end_time,DATE_ADD(tmc.card_start_time,INTERVAL tmct.validity day)) as warnTime, ");
        sql.append(" tm.id,tm.name,tm.email,tmc.id as tmcId,1 as cardType");
        sql.append(" from t_user tu ");
        sql.append(" left join t_box tb on tb.user_id = tu.id ");
        sql.append(" left join t_member tm on tm.box_id = tb.id ");
        sql.append(" left join t_member_card tmc on tmc.memer_id = tm.id ");
        sql.append(" left join t_member_card_template tmct on tmct.id = tmc.member_card_template_id ");
        sql.append(" where tm.id is not null and tmc.id is not null and (tmc.custom_card_type = 1 or tmct.template_type = 1) ");
        sql.append(" and tu.id = ").append(bossUserId).append(" ) t");
        sql.append(" where DATE_FORMAT(t.warnTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') ");
        sql.append(" union all ");
        sql.append(" select 0 as warnTime,tm.id,tm.name,tm.email,tmc.id as tmcId,2 as cardType ");
        sql.append(" from t_user tu ");
        sql.append(" left join t_box tb on tb.user_id = tu.id ");
        sql.append(" left join t_member tm on tm.box_id = tb.id ");
        sql.append(" left join t_member_card tmc on tmc.memer_id = tm.id ");
        sql.append(" left join t_member_card_template tmct on tmct.id = tmc.member_card_template_id ");
        sql.append(" left join t_mail_record tmr on tmr.member_card_id = tmc.id ");
        sql.append(" where tm.id is not null and tmc.id is not null and (tmc.custom_card_type = 2 or tmct.template_type = 2) ");
        sql.append(" and tmc.card_remain_num <= 0 and tmr.id is null ");
        sql.append(" and tu.id = ").append(bossUserId);
        return  queryBySql(sql.toString());
    }


    @Override
    public List<Mail> queryListByBossId(int bossId) {
        String hql = "from Mail where bossUserId=? and isDeleted=0";
        return this.list(hql,bossId);
    }
}
