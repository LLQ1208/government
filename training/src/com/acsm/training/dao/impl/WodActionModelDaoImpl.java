package com.acsm.training.dao.impl;

import com.acsm.training.model.basic.PageHelper;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodActionModelDao;
import com.acsm.training.model.basic.PageHelper;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 22:06 2017/12/3
 */
@Repository
public class WodActionModelDaoImpl extends BaseDaoImpl<Object[]> implements WodActionModelDao {
    @Override
    public PageHelper querySectionListChange(Integer userId, Integer searchType, Integer pageSize, Integer pageIndex,String keyword) {
        PageHelper pageHelper = new PageHelper();
        StringBuffer hql = new StringBuffer();
        hql.append(" select t.section_id,t.title,t.user_id,DATEDIFF(t.insert_time,now()) as time,t.insert_time from (");
        hql.append(" select tw.insert_time,ts.section_id,ts.title,ts.user_id from t_section ts left join t_wod_section tw on tw.section_id = ts.section_id where ts.is_deleted = 0 ");
        //searchType 1,all 2,system 3,custom
        if(searchType == 1){
            hql.append(" and (ts.user_id = 174 or ts.user_id = ").append(userId).append(")");
        }else if(searchType == 2){
            hql.append(" and ts.user_id = 174 ");
        }else if(searchType == 3){
            hql.append(" and ts.user_id = ").append(userId);
        }
        if(!"".equals(keyword) && null != keyword){
            hql.append(" and ts.title like '%").append(keyword).append("%' ");
        }
        hql.append(" order by tw.insert_time desc ) t GROUP BY t.section_id order by t.title");
        StringBuffer sql = new StringBuffer();
        sql.append(hql);
        pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
        hql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);

        pageHelper.setList(this.queryBySql(hql.toString()));
        return pageHelper;
    }

    @Override
    public PageHelper queryContentListChange(Integer userId, Integer searchType, Integer pageSize, Integer pageIndex,Integer contentType,String keyword) {
        PageHelper pageHelper = new PageHelper();
        StringBuffer hql = new StringBuffer();
        hql.append("select t.content_id,t.name,t.user_id,DATEDIFF(t.insert_time,now()) as time,DATE_FORMAT(t.insert_time,'%Y-%m-%d %H:%i'),t.record_name,t.description,t.record_type,t.is_rx,t.famous_wod_record_type,t.each_measure,t.each_record_type from (");
        hql.append("select tw.insert_time,tc.content_id,tc.name,tc.user_id,tc.name as record_name,tc.description,tc.record_type,tc.is_rx,tc.famous_wod_record_type,tc.each_measure,tc.each_record_type from t_content tc left join t_wod_content tw on tw.content_id = tc.content_id where tc.is_deleted = 0 ");
        if(searchType == 1){
            hql.append(" and (tc.user_id = 174 or tc.user_id = ").append(userId).append(")");
        }else if(searchType == 2){
            hql.append(" and tc.user_id = 174 ");
        }else if(searchType == 3){
            hql.append(" and tc.user_id = ").append(userId);
        }
        if(!"".equals(keyword) && null != keyword){
            hql.append(" and tc.name like '%").append(keyword).append("%' ");
        }
        //contype 1，Warm-Ups 2，Gymnastics 3，Weightlifting 4，Metcons 5，Popular Wod
        hql.append(" and tc.content_type = ").append(contentType);

        hql.append(" order by tw.insert_time desc ) t GROUP BY t.content_id order by t.name ");

        StringBuffer sql = new StringBuffer();
        sql.append(hql);
        pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
        hql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);
        pageHelper.setList(this.queryBySql(hql.toString()));
        return pageHelper;
    }
}
