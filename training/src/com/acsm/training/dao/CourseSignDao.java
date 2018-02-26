package com.acsm.training.dao;

import com.acsm.training.model.CourseSign;
import com.fasterxml.jackson.databind.deser.Deserializers;

/**
 * Created by lq on 2017/12/24.
 */
public interface CourseSignDao extends BaseDao<CourseSign>{

    public CourseSign queryCourseSign(Integer courseId,String date);
}
