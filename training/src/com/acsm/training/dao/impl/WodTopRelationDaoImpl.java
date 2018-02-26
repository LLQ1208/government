package com.acsm.training.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodTopRelationDao;
import com.acsm.training.model.WodTopRelation;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:46 2017/12/2
 */
@Repository
public class WodTopRelationDaoImpl extends BaseDaoImpl<WodTopRelation>implements WodTopRelationDao{

    @Override
    public void save(WodTopRelation wodTopRelation) {
    }

    @Override
    public List<WodTopRelation> queryListByWodId(int wodId) {
        String hql = "from WodTopRelation where wodId=? and isDeleted=0";
        return this.list(hql, wodId);
    }

    @Override
    public WodTopRelation queryListByWodAndBox(int wodId,int boxId) {
        String hql = "from WodTopRelation where wodId="+wodId+" and boxId="+boxId+" and isDeleted=0";
        return (WodTopRelation)this.Queryobject(hql);
    }
    @Override
    public WodTopRelation queryByCondition(int boxId, int courseTypeId, String date) {
        String hql = "select wtr from WodTopRelation as wtr,Wod as w " +
                " where w.id=wtr.wodId" +
                " and wtr.boxId="+boxId+" and wtr.isDeleted=0 and wtr.courseId="+courseTypeId+
                " and w.isDeleted=0 and w.wDate='" + date + "'";
        return (WodTopRelation)this.Queryobject(hql);
    }
}
