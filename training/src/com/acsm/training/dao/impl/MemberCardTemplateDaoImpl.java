package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/1/18.
 */

import com.acsm.training.dao.MemberCardTemplateDao;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.dao.MemberCardTemplateDao;
import com.acsm.training.model.MemberCardTemplate;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-18
 */
@Repository("memberCardTemplateDao")
public class MemberCardTemplateDaoImpl extends BaseDaoImpl<MemberCardTemplate> implements MemberCardTemplateDao {

    @Override
    public MemberCardTemplate queryById(int id) {
        String hql ="from MemberCardTemplate where id=?";
        return (MemberCardTemplate)this.Queryobject(hql,id);
    }

    @Override
    public PageHelper queryByPage(int bossId, int pageSize, int pageIndex) {
        PageHelper pageHelper = new PageHelper();
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.template_name,t.template_type,t.money,GROUP_CONCAT(c.`NAME`),t.bout_card_type ");
        sql.append(" from t_member_card_template t ");
        sql.append(" LEFT JOIN t_course_type c on FIND_IN_SET(c.id,t.course_type_ids) ");
        sql.append(" where t.is_deleted=0  ");
        sql.append(" and t.user_id= ").append(bossId);
        sql.append(" GROUP BY t.id ");
        sql.append(" ORDER BY t.insert_time desc ");
        pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
        StringBuffer pageSql = new StringBuffer();
        pageSql.append(sql);
        pageSql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);
        pageHelper.setList(this.queryBySql(pageSql.toString()));
        return pageHelper;
    }

    @Override
    public List<Object[]> queryList(int bossId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select t.id,t.template_name,t.template_type,t.money,GROUP_CONCAT(c.`NAME`) ");
        sql.append(" from t_member_card_template t ");
        sql.append(" LEFT JOIN t_course_type c on FIND_IN_SET(c.id,t.course_type_ids) ");
        sql.append(" where t.is_deleted=0  ");
        sql.append(" and t.user_id= ").append(bossId);
        sql.append(" GROUP BY t.id ");
        sql.append(" ORDER BY t.insert_time desc ");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<MemberCardTemplate> queryListByName(String name, Integer userId, Integer templateId) {
        String hql ="from MemberCardTemplate where templateName=:templateName and userId=:userId";
        if(null != templateId){
            hql += " and id !=" + templateId;
        }
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setString("templateName", name);
        q.setInteger("userId", userId);
        List list = q.list();
        return list;
    }
}
