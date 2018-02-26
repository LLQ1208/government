package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/11/30.
 */

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodDao;
import com.acsm.training.model.Wod;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
@Repository
public class WodDaoImpl  extends BaseDaoImpl<Wod>implements WodDao{
    @SuppressWarnings("unchecked")
    @Override
    public List<Wod> queryWodList(Map map) {
        Integer boxId = Integer.valueOf(map.get("boxId").toString());
        Integer courseId = Integer.valueOf(map.get("courseId").toString());
        String beginDate = map.get("beginDate").toString();
        String endDate = map.get("endDate").toString();
        String hql = "select w from Wod as w,WodTopRelation as wtr "
                + " where w.id=wtr.wodId " +
                " and wtr.boxId=" + boxId
                + " and wtr.courseId=" + courseId
               + " and w.wDate  between '" + beginDate + "' and '" + endDate + "'"
                + " and w.isDeleted=0 and wtr.isDeleted=0 "
                + " order by w.wDate asc";
        Session session = getSession();
        List<Wod> wodList = session.createQuery(hql).list();
        return wodList;
    }

    @Override
    public Wod queryWodById(int id) {
        String hql = "from Wod where id=?";
        return (Wod)this.Queryobject(hql,id);
    }

    @Override
    public Wod queryWodByDateAndType(Integer boxId, Integer courseTypeId, String date) {
        String hql = "select w from Wod as w,WodTopRelation as wtr "
                + " where w.id=wtr.wodId " +
                " and wtr.boxId=" + boxId
                + " and wtr.courseId=" + courseTypeId
                + " and w.wDate = '" + date + "'"
                + " and w.isDeleted=0 and wtr.isDeleted=0 "
                + " order by w.wDate asc";
        return (Wod)this.Queryobject(hql);
    }

    @Override
    public List<Wod> queryWodByDate(Integer boxId, String date) {
        String hql = "select w from Wod as w,WodTopRelation as wtr "
                + " where w.id=wtr.wodId " +
                " and wtr.boxId=" + boxId
                + " and w.wDate = '" + date + "'"
                + " and w.isDeleted=0 and wtr.isDeleted=0 "
                + " order by w.wDate asc";
        return (List<Wod>)this.list(hql);
    }

}
