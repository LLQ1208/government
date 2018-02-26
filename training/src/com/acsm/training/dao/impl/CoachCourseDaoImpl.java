package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/12.
 */

import java.util.List;

import com.acsm.training.dao.CoachCourseDao;
import com.acsm.training.model.CoachCourse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CoachCourseDao;
import com.acsm.training.model.CoachCourse;

/**
 * @Author lianglinqiang
 * @create 2017-12-12
 */
@Repository
public class CoachCourseDaoImpl extends BaseDaoImpl<CoachCourse> implements CoachCourseDao {
    @Override
    public CoachCourse queryById(int coachCourseId) {
        String hql = "from CoachCourse where id=?";
        return (CoachCourse)this.Queryobject(hql,coachCourseId);
    }

    @Override
    public List<CoachCourse> queryListByCoachId(int coachId) {
        String hql = "from CoachCourse where coachId=? and isDelete = 0";
        return this.list(hql,coachId);
    }
    
    @Override
    public List<CoachCourse> queryListByCoachIdAndWeek(int coachId,int week) {
        String hql = "from CoachCourse where coachId=:coachId and week=:week and isDelete = 0 order by startTime asc";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
		q.setInteger("coachId", coachId);
		q.setInteger("week", week);
		return q.list();
    }

    @Override
    public List<CoachCourse> queryListByBossId(int bossId,int boxId) {
        String hql = "from CoachCourse where bossId=? and isDelete = 0 and boxId="+ boxId;
        return this.list(hql,bossId);
    }
}
