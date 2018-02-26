package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/1.
 */

import java.util.List;

import com.acsm.training.model.WodSection;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodSectionDao;
import com.acsm.training.model.WodSection;

/**
 * @Author lianglinqiang
 * @create 2017-12-01
 */
@Repository
public class WodSectionDaoImpl extends BaseDaoImpl<WodSection> implements WodSectionDao {
    @Override
    public List<WodSection> queryWodSectionByWodIdAsc(int wodId) {
        String hql = "from WodSection where isDeleted=0 " +
                "and wodId=? order by tIndex asc";
        return this.list(hql, wodId);
    }

    @Override
    public List<WodSection> queryWodSectionByWodIdDesc(int wodId) {
        String hql = "from WodSection where isDeleted=0 " +
                "and wodId=? order by tIndex desc";
        return this.list(hql, wodId);
    }

    @Override
    public WodSection queryWodSectoinById(int wodSectionId) {
        String hql = "from WodSection where wodSectionId=?";
        return (WodSection) this.Queryobject(hql, wodSectionId);
    }

    @Override
    public WodSection queryWodSectionByType(int wodId, int type) {
        String hql = "from WodSection where isDeleted=0 " +
                "and wodId=? and type="+type;
        return (WodSection) this.Queryobject(hql, wodId);
    }
}
