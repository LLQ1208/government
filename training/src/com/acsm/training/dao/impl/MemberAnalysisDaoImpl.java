package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/1/29.
 */

import com.acsm.training.dao.MemberAnalysisDao;
import com.acsm.training.model.MemberAnalysis;
import com.acsm.training.dao.MemberAnalysisDao;
import com.acsm.training.model.MemberAnalysis;
import com.acsm.training.model.MemberCard;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-29
 */
@Repository("memberAnalysisDao")
public class MemberAnalysisDaoImpl extends BaseDaoImpl<MemberAnalysis> implements MemberAnalysisDao {


    @Override
    public Integer queryGroupCardDays(Integer memberId,Integer boxId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" TIMESTAMPDIFF(DAY,m.card_start_time,if(SYSDATE() < m.card_end_time,SYSDATE(),m.card_end_time)) as days ");
        sql.append("  from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where (");
        sql.append(" (m.member_card_template_id=0 and m.custom_card_type=1) ");
        sql.append(" or (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=1) ");
        sql.append(" or (t.template_type=1) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=1)) ");
        sql.append(" and m.memer_id=").append(memberId);
        sql.append(" and m.box_id=").append(boxId);

        StringBuffer lastSql = new StringBuffer();
        lastSql.append(" select sum(if(test.days < 0,0,test.days)) ");
        lastSql.append(" from ( ").append(sql);
        lastSql.append(" ) as test  ");
        List<Object> list = this.queryBySql(lastSql.toString());
        if(list == null || list.size() < 1){
            return 0;
        }else{
            return list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
        }
    }

    @Override
    public Integer queryNowGroupCard(Integer memberId,Integer boxId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select m.id ");
        sql.append(" from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where ");
        sql.append(" ((m.member_card_template_id=0 and m.custom_card_type=1 and m.card_start_time <= SYSDATE() and m.card_end_time >= SYSDATE())  ");
        sql.append(" or (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=1 and m.card_start_time <= SYSDATE() and m.card_end_time >= SYSDATE() and m.remain_num > 0) ");
        sql.append(" or (t.template_type=1 and  m.card_start_time <= SYSDATE() and m.card_end_time >= SYSDATE()) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=1 and m.card_start_time <= SYSDATE() and m.card_end_time >= SYSDATE() and m.remain_num > 0)) ");
        sql.append(" and m.memer_id= ").append(memberId);
        sql.append(" and m.box_id=").append(boxId);
        List<Object> list = this.queryBySql(sql.toString());
        if(list == null || list.size() < 1){
            return null;
        }else{
            return list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
        }
    }

    @Override
    public List<Object> queryFutureGroupCard(Integer memberId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select m.id ");
        sql.append(" from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where ");
        sql.append(" ((m.member_card_template_id=0 and m.custom_card_type=1 and m.card_start_time > SYSDATE() )  ");
        sql.append(" or (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=1 and m.card_start_time > SYSDATE() ) ");
        sql.append(" or (t.template_type=1 and  m.card_start_time > SYSDATE() ) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=1 and m.card_start_time > SYSDATE() )) ");
        sql.append(" and m.memer_id= ").append(memberId);
        List<Object> list = this.queryBySql(sql.toString());
        if(list == null || list.size() < 1){
            return null;
        }else{
            return list;
        }
    }

    @Override
    public Integer queryPrivateCardDays(Integer memberId,Integer boxId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" TIMESTAMPDIFF(DAY,m.card_start_time,if(SYSDATE() < m.card_end_time,SYSDATE(),m.card_end_time)) as days ");
        sql.append("  from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where (");
        sql.append(" (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=2) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=2)) ");
        sql.append(" and  m.memer_id=").append(memberId);
        sql.append(" and m.box_id=").append(boxId);

        StringBuffer lastSql = new StringBuffer();
        lastSql.append(" select sum(if(test.days < 0,0,test.days)) ");
        lastSql.append(" from ( ").append(sql);
        lastSql.append(" ) as test  ");
        List<Object> list = this.queryBySql(lastSql.toString());
        if(list == null || list.size() < 1){
            return 0;
        }else{
            return list.get(0) == null ? 0 : Integer.valueOf(list.get(0).toString());
        }
    }

    @Override
    public Integer queryNowPrivateCard(Integer memberId,Integer boxId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" m.id ");
        sql.append("  from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where (");
        sql.append(" (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=2 and m.remain_num > 0) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=2 and m.remain_num > 0)) ");
        sql.append(" and m.memer_id=").append(memberId);
        sql.append(" and m.box_id= ").append(boxId);

        List<Object> list = this.queryBySql(sql.toString());
        if(list == null || list.size() < 1){
            return null;
        }else{
            return list.get(0) == null ? 0 :Integer.valueOf(list.get(0).toString());
        }
    }

    @Override
    public List<Object> queryFuturePrivateCard(Integer memberId) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ");
        sql.append(" m.id ");
        sql.append("  from t_member_card m ");
        sql.append(" LEFT JOIN t_member_card_template t on m.member_card_template_id=t.id ");
        sql.append(" where (");
        sql.append(" (m.member_card_template_id=0 and m.custom_card_type=2 and m.custom_card_course_type=2 and m.card_start_time > SYSDATE()) ");
        sql.append(" or (t.template_type=2 and t.bout_card_type=2  and m.card_start_time > SYSDATE())) ");
        sql.append(" and m.memer_id=").append(memberId);

        List<Object> list = this.queryBySql(sql.toString());
        if(list == null || list.size() < 1){
            return null;
        }else{
            return list;
        }
    }

    @Override
    public MemberAnalysis queryByMemberId(int memberId,Integer boxId) {
        String hql = "from MemberAnalysis where memberId=? and boxId="+boxId;
        Object obj = this.Queryobject(hql,memberId);
        if(null == obj){
            return null;
        }else{
            return (MemberAnalysis)obj;
        }
    }
}
