package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/23.
 */

import com.acsm.training.dao.CoachCourseRestDao;
import com.acsm.training.model.CoachCourseRest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-23
 */
@Repository
public class CoachCourseRestDaoImpl extends BaseDaoImpl<CoachCourseRest> implements CoachCourseRestDao{

    @Override
    public CoachCourseRest queryById(Integer id) {
        String hql = "from CoachCourseRest where id=?";
        return (CoachCourseRest)this.Queryobject(hql,id);
    }

    @Override
    public List<CoachCourseRest> queryListByDate(Integer boxId, Integer coachId, Integer bossId, String beginTime, String endTime) {
        String hql = "from CoachCourseRest where boxId="+boxId+
                " and isDelete=0 ";
        if(coachId != null){
            hql += " and coachId=" + coachId;
        }
        if(null != bossId){
            hql += " and bossId=" + bossId;
        }
        hql += " and date between '" + beginTime + "' and '" + endTime + "'";
        return this.list(hql);
    }

    @Override
    public CoachCourseRest queryByDate(Integer boxId, Integer coachId, Integer bossId, String date) {
        String hql = "from CoachCourseRest where boxId="+boxId+
                " and isDelete=0 ";
        if(coachId != null){
            hql += " and coachId=" + coachId;
        }
        if(null != bossId){
            hql += " and bossId=" + bossId;
        }
        hql += " and date='" + date + "'";
        return (CoachCourseRest)this.Queryobject(hql);
    }
}
