package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/24.
 */

import com.acsm.training.dao.CoachCourseSignDao;
import com.acsm.training.model.CoachCourseSign;
import org.springframework.stereotype.Repository;

/**
 * @Author lianglinqiang
 * @create 2017-12-24
 */
@Repository
public class CoachCourseSignDaoImpl extends BaseDaoImpl<CoachCourseSign> implements CoachCourseSignDao{
    @Override
    public CoachCourseSign querySign(Integer boxId, Integer coachId, Integer bossId, String date, Integer coachCourseId) {
        String hql = "from CoachCourseSign where boxId=" + boxId + " and isDelete=0"+
                " and date='" + date + "' and coachCourseId="+ coachCourseId;
        if(null != coachId){
            hql += " and coachId=" + coachId;
        }
        if(null != bossId){
            hql += " and bossId=" + bossId;
        }
        return (CoachCourseSign)this.Queryobject(hql);
    }
}
