package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/24.
 */

import com.acsm.training.dao.CourseSignDao;
import com.acsm.training.dao.CourseSignDao;
import com.acsm.training.model.CourseSign;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2017-12-24
 */
@Repository
public class CourseSignDaoImpl extends BaseDaoImpl<CourseSign> implements CourseSignDao {
    @Override
    public CourseSign queryCourseSign(Integer courseId, String date) {
        String hql = "from CourseSign where courseId=:courseId and " +
                " date=:date and isDelete=0";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setInteger("courseId", courseId);
        q.setString("date", date);
        List list = q.list();
        if(null != list && list.size() > 0){
            return (CourseSign)q.list().get(0);
        }
        return null;
    }
}
