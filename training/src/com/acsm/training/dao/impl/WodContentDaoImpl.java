package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/1.
 */

import java.util.List;

import com.acsm.training.model.WodContent;
import com.acsm.training.model.CourseContent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodContentDao;
import com.acsm.training.model.WodContent;

/**
 * @Author lianglinqiang
 * @create 2017-12-01
 */
@Repository
public class WodContentDaoImpl extends BaseDaoImpl<WodContent> implements WodContentDao {
    @Override
    public List<WodContent> queryWodContentListByWodSectionId(int wodSectionId) {
        String hql = "from WodContent where  isDeleted=0 and wodSectionId=" + wodSectionId
                +" order by tIndex asc ";
        return getSession().createQuery(hql).list();
    }

    @Override
    public List<WodContent> queryWodContentListDesc(int wodSectionId, int wodId) {
        String hql = "from WodContent where  isDeleted=0 and wodSectionId=" + wodSectionId
                +" and wodId= "+wodId+" order by tIndex desc";
        return getSession().createQuery(hql).list();
    }
    @Override
    public List<WodContent> queryWodContentListAsc(int wodSectionId, int wodId) {
        String hql = "from WodContent where  isDeleted=0 and wodSectionId=" + wodSectionId
                +" and wodId= "+wodId+" order by tIndex asc";
        return getSession().createQuery(hql).list();
    }

    @Override
    public List<WodContent> queryListByWodId(int wodId) {
        String hql = "select wc from WodContent as wc, WodSection as ws   " +
                " where  wc.wodSectionId=ws.wodSectionId " +
                " and wc.isDeleted=0 " +
                " and ws.isDeleted = 0 "
                +" and wc.wodId= "+wodId+" order by wc.tIndex asc";
        return getSession().createQuery(hql).list();
    }

    @Override
    public List<WodContent> queryListByWodIdASC(int wodId) {
        String hql = "select wc from WodContent as wc, WodSection as ws  " +
                " where  wc.wodSectionId=ws.wodSectionId  " +
                " and wc.isDeleted=0 "+
                " and ws.isDeleted = 0 "
                +" and wc.wodId= "+wodId+" order by wc.tIndex asc";
        return getSession().createQuery(hql).list();
    }
    
    @Override
    public List<WodContent> queryWodContentOfCourse(Integer courseId, String date) {
        String sql = "select wc from Course as  c,WodTopRelation as  wtr,Wod as  w,WodContent as  wc " +
                " where c.courseTypeId=wtr.courseId " +
                " and c.boxId=wtr.boxId " +
                " and w.wDate= '" + date + "' "+
                " and c.isDeleted=0  and w.isDeleted=0 and wtr.isDeleted=0 and wc.isDeleted=0 " +
                " and wtr.wodId=w.id " +
                " and c.id= " + courseId  +
                " and w.id=wc.wodId " +
                " and (wc.contentType=2 or wc.contentType=3) group by wc.contentId";
        Session session = this.getSession();
        Query q = session.createQuery(sql);
        List list = q.list();
        return list;
    }

    @Override
    public WodContent queryWodContentById(int wodContentId) {
        String hql = "from WodContent where wodContentId=?";
        return (WodContent) this.Queryobject(hql, wodContentId);
    }


}
