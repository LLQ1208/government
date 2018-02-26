package com.acsm.training.dao.impl;

import com.acsm.training.util.DateUtil;
import com.acsm.training.dao.AnalysisDao;
import com.acsm.training.dao.BaseDao;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.WodUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 14:10 2017/12/22
 */
@Repository
public class AnalysisDaoImpl extends BaseDaoImpl<Object[]> implements AnalysisDao{
    @Override
    public List<Object[]> memberCourseAnalysisList(Integer bossId,Integer boxId, Integer courseTypeId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tct.id) as count,tco.date,tct.id,tct.name ");
        sql.append(" from t_course_order tco ");
        sql.append(" left join t_course tc on tco.course_id = tc.id ");
        sql.append(" left join t_course_type tct on tct.id = tc.course_type_id ");
        sql.append(" left join t_box tb on tb.id = tc.box_id ");
        sql.append(" where tco.is_deleted = 0 and  tco.date >= '").append(beginTime).append("' ");
        sql.append(" and tco.date <= '").append(endTime).append("' ");
        if(null != boxId && boxId != -1){
            sql.append(" and tb.id =").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        if(null != courseTypeId && courseTypeId != -1){
            sql.append(" and tct.id = ").append(courseTypeId);
        }else{
            String courseTypeIdArr = getBelongCourseTypeId(bossId);
            sql.append(" and tct.id in (").append(courseTypeIdArr).append(") ");
        }
        sql.append(" and tco.sign_status=1 ");
        sql.append(" group by tct.id,tco.date order by tco.date");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> memberCountChangeAddAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tm.CREATE_TIME) as count ,DATE_FORMAT(tm.CREATE_TIME,'%Y-%m-%d') as time ,tm.id,tm.name ");
        sql.append(" from t_member tm  ");
        sql.append(" left join t_box tb on tm.box_id = tb.id ");
        sql.append(" where tm.CREATE_TIME >= '").append(beginTime).append("'");
        sql.append(" and tm.CREATE_TIME <= '").append(endTime).append("'");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" group by DATE_FORMAT(tm.CREATE_TIME,'%Y-%m-%d') ");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> memberCountChangeDecreaseAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tm.member_end_time) as count ,DATE_FORMAT(tm.member_end_time,'%Y-%m-%d') as time ,tm.id,tm.name ");
        sql.append(" from t_member tm ");
        sql.append(" left join t_box tb on tm.box_id = tb.id  ");
        sql.append(" where tm.member_end_time >= '").append(beginTime).append("'");
        sql.append(" and tm.member_end_time <= '").append(endTime).append("'");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" group by DATE_FORMAT(tm.member_end_time,'%Y-%m-%d');");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> memberSumAnalysisList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        List<Date> timeArr = WodUtil.getBetweenDates(DateUtil.StringToDate(beginTime, DateUtil.YYYY_MM_DD),DateUtil.StringToDate(endTime,DateUtil.YYYY_MM_DD));
        for (int i = 0; i < timeArr.size(); i++) {
            String nowDate = DateUtil.DateToString(timeArr.get(i),DateUtil.YYYY_MM_DD);
            sql.append(" select count(tm.id),'").append(nowDate).append("' as nowDate from t_member tm ");
            sql.append(" left join t_box tb on tb.id = tm.box_id ");
            sql.append(" where tm.member_end_time >='").append(nowDate).append("'");
            if(null != boxId && -1 != boxId){
                sql.append(" and tb.id = ").append(boxId);
            }else{
                String boxIdArr = getBelongBoxId(bossId);
                sql.append(" and tb.id in (").append(boxIdArr).append(") ");
            }
            if(i != timeArr.size()-1){
                sql.append(" union all ");
            }
        }
        System.out.println("sql------------"+sql.toString());
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> coachTakeClassesAnalysisGroupList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tco.id),tco.date,tcoach.id,tcoach.name from t_course_order tco");
        sql.append(" left join t_course tc on tc.id = tco.course_id ");
        sql.append(" left join t_coach tcoach on tcoach.id = tc.coach_id  ");
        sql.append(" left join t_box tb on tb.id = tc.box_id ");
        sql.append(" where tco.is_deleted = 0 and tco.sign_status = 1 and tco.date >= '").append(beginTime).append("'");
        sql.append(" and tco.date <='").append(endTime).append("'");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" group by tco.date,tcoach.id order by tco.date ");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> coachAttendanceAnalysisGroupList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tcs.id),tcs.date,tc.id,tc.name from t_course_sign tcs ");
        sql.append(" left join t_coach tc on tc.id = tcs.coach_id");
        sql.append(" left join t_box tb on tb.id = tcs.box_id  ");
        sql.append(" where tcs.date >='").append(beginTime).append("'");
        sql.append(" and tcs.date <='").append(endTime).append("'");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" group by tcs.date,tc.id ");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> coachTakeClassAnalysisPersonalList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(tcco.id),tcco.date,tcoach.id,tcoach.name from t_coach_course_order tcco ");
        sql.append(" left join t_coach_course tcc on tcc.id = tcco.coach_course_id ");
        sql.append(" left join t_coach tcoach on tcoach.id = tcc.coach_id ");
        sql.append(" left join t_box tb on tb.id = tcc.box_id ");
        sql.append(" where tcco.is_deleted = 0 and tcco.sign_status = 1 and tcco.date >= '").append(beginTime).append("'");
        sql.append(" and tcco.date <='").append(endTime).append("'");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" group by tcco.date,tcoach.id order by tcco.date ");
        return this.queryBySql(sql.toString());
    }

    @Override
    public List<Object[]> coachAttendanceAnalysisPersonalList(Integer bossId,Integer boxId, String beginTime, String endTime) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(t.id),t.date,t.id,t.name from (");
        sql.append(" select date_format(tccs.date,'%Y-%m-%d') as date,if(tc.id is null,tu.id+10000,tc.id) as id ,if(tc.name is null,tu.user_name,tc.name) as name from t_coach_course_sign tccs ");
        sql.append(" left join t_coach tc on tc.id = tccs.coach_id ");
        sql.append(" left join t_user tu on tu.id = tccs.boss_id ");
        sql.append(" left join t_box tb on tb.id = tccs.box_id  ");
        sql.append(" where tccs.date >='").append(beginTime).append("'");
        sql.append(" and tccs.date <='").append(endTime).append(" ");
        if(null != boxId && -1 != boxId){
            sql.append(" and tb.id = ").append(boxId);
        }else{
            String boxIdArr = getBelongBoxId(bossId);
            sql.append(" and tb.id in (").append(boxIdArr).append(") ");
        }
        sql.append(" ')t group by t.date,t.id");
        return this.queryBySql(sql.toString());
    }
    //获取所属boxId
    private String getBelongBoxId(Integer userId){
        StringBuffer sql = new StringBuffer();
        StringBuffer idStr = new StringBuffer();
        sql.append("select id from t_box where user_id = ").append(userId);
        List<Integer> idArr = this.queryBySql(sql.toString());
        for (int i = 0; i < idArr.size(); i++) {
            idStr.append(idArr.get(i));
            if(i != idArr.size() -1){
                idStr.append(",");
            }
        }
        return idStr.toString();
    }

    //获取所属课程类型Id
    private String getBelongCourseTypeId(Integer userId){
        StringBuffer sql = new StringBuffer();
        StringBuffer idStr = new StringBuffer();
        sql.append("select id from t_course_type where user_id = ").append(userId);
        List<Integer> idArr = this.queryBySql(sql.toString());
        for (int i = 0; i < idArr.size(); i++) {
            idStr.append(idArr.get(i));
            if(i != idArr.size() -1){
                idStr.append(",");
            }
        }
        return idStr.toString();
    }
}
