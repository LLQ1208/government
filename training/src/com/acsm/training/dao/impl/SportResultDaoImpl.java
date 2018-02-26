package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/18.
 */

import java.util.List;
import java.util.Map;

import com.acsm.training.model.SportResult;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.SportResultDao;
import com.acsm.training.model.SportResult;

/**
 * @Author lianglinqiang
 * @create 2017-12-18
 */
@Repository
public class SportResultDaoImpl extends BaseDaoImpl<SportResult> implements SportResultDao{
    @Override
    public SportResult queryById(int sportResultId) {
        String hql = " from SportResult where sportResultId = " + sportResultId;
        return (SportResult)this.Queryobject(hql);
    }

    @Override
    public List<SportResult> queryListByMemberAndContent(int boxId,int memberId, int contentId) {
        String hql = " from SportResult where memberId=" + memberId + " and contentId=" + contentId+
                " and boxId=" + boxId;
        return getSession().createQuery(hql).list();
    }

    @Override
    public List<SportResult> queryListByContent(int boxId, int contentId) {
        String hql = " from SportResult where  contentId=" + contentId+
                " and boxId=" + boxId;
        return getSession().createQuery(hql).list();
    }

    @Override
    public SportResult queryListByWodContent(int wodContentId, int memberId,int courseOrderId) {
        String hql = " from SportResult where memberId=" + memberId + " and  wodContentId=" + wodContentId+
                         " and courseOrderId = " + courseOrderId;
        return (SportResult)this.Queryobject(hql);
    }

    @Override
    public List<Object[]> queryListByPage(int boxId, int memberId, int contentId, int begin, int num) {
        String hql = " select t.sets,t.reps,t.weight,t.unit_type," +
                " t.remark,t.insert_time " +
                " from t_sport_result t " +
                " where t.box_id= " + boxId +
                " and t.member_id= " + memberId +
                " and t.content_id= " + contentId +
                " limit "+ begin + "," + num;

        return this.queryBySql(hql);
    }

    @Override
    public List<Object[]> queryListOrderByTime(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark  " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.minutes desc,sr.seconds desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByGym(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark,(sr.sets * sr.reps) as suma   " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by suma desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByRoundAndReps(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark,(sr.sets * sr.reps) as suma   " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.sets desc, sr.reps  desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByTotal(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark,(sr.sets * sr.reps) as suma   " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.record_total  desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByAmrap(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark  " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.reps desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByDistance(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark  " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.meters desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByCalor(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type ,m.SEX,sr.remark " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.calories desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByWeight(int wodContentId) {
        String sql = "select m.`NAME`,c.START_TIME,ct.`NAME` as typeName, sr.isRx," +//0-3
                "m.AVATAR,sr.record_type,sr.sets,sr.reps,sr.weight,sr.minutes,sr.seconds,sr.calories," +//4-11
                "sr.meters,sr.each_round_json,sr.unit_type,m.SEX,sr.remark   " +
                "from t_course_order as co,  " +
                "t_sport_result as sr, " +
                "t_member as m , " +
                "t_course as c, " +
                "t_course_type as ct " +
                "where co.id=sr.course_order_id " +
                "and co.MEMBER_ID=m.ID " +
                "and co.COURSE_ID=c.ID " +
                "and ct.id=c.COURSE_TYPE_ID " +
                "and sr.wod_content_id="+wodContentId +
                " order by sr.weight desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByTime(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " ORDER BY sr.minutes desc,sr.seconds desc ) as test " +
                " GROUP BY test.member_id order by test.minutes desc,test.seconds desc";

        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByReps(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID  " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.reps desc ) as test GROUP BY test.member_id ORDER BY test.reps desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByRoundReps(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*,(sr.reps * sr.sets) as total  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by (sr.reps * sr.sets) desc) as test " +
                " GROUP BY test.member_id " +
                " order by test.total ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderBySetsReps(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.record_total desc ) as test GROUP BY test.member_id order by test.record_total desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByEachRound(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.record_total desc ) as test GROUP BY test.member_id order by test.record_total desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByDistance(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.record_total desc ) as test GROUP BY test.member_id order by test.record_total desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByCalories(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.calories desc  ) as test GROUP BY test.member_id order by test.calories desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryListOrderByWeight(Integer contentId, Integer boxId, Integer sex) {
        String sql = "select * from (select sr.*  from t_sport_result sr LEFT JOIN t_member m on sr.member_id=m.ID " +
                " where sr.content_id="+contentId +
//                " and m.status = 0 "+
                " and sr.box_id=" + boxId  +
                " and m.SEX="+sex  +
                " order by sr.record_total desc ) as test GROUP BY test.member_id order by test.record_total desc";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryMemberScoreGym(Integer boxId, Integer memberId, Integer contentType) {
        String sql = "select test.* from " +
                " (select sr.content_id,sr.sets,c.`name`,sr.insert_time " +
                " from t_sport_result sr,t_content c "+
                " where sr.content_id=c.content_id "+
                " and sr.member_id="+ memberId +
                " and c.content_type="+ contentType +
                " and sr.box_id="+ boxId +
                " order BY sr.sets ) as test " +
                " GROUP BY test.content_id " +
                " ORDER BY test.insert_time desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryMemberScoreWeight(Integer boxId, Integer memberId, Integer contentType) {
        String sql = "select test.* from " +
                " (select sr.record_total,sr.content_id,c.`name`,sr.insert_time " +
                " from t_sport_result sr,t_content c "+
                " where sr.content_id=c.content_id "+
                " and sr.member_id="+ memberId +
                " and c.content_type="+ contentType +
                " and sr.box_id="+ boxId +
                " order BY sr.record_total desc) as test " +
                " GROUP BY test.content_id " +
                " ORDER BY test.insert_time desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryMemberScoreMetOrPopu(Integer boxId, Integer memberId, Integer contentType) {
        String sql = "select test.* from " +
                " (select sr.content_id,c.`name`,sr.insert_time,sr.record_type," + //0-3
                " sr.sets,sr.reps,sr.weight,sr.minutes, " +//4-7
                " sr.seconds,sr.calories,sr.meters,sr.unit_type,sr.record_total,sr.wod_content_id " +//8-13
                " from t_sport_result sr,t_content c "+
                " where sr.content_id=c.content_id "+
                " and sr.member_id="+ memberId +
                " and c.content_type="+ contentType +
                " and sr.box_id="+ boxId +
                " order BY sr.record_total ) as test " +
                " GROUP BY test.content_id " +
                " ORDER BY test.insert_time desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryMemberScorePop(Integer boxId, Integer memberId, Integer contentId) {
        String sql = " select sr.content_id,sr.insert_time,sr.record_type," + //0-2
                " sr.sets,sr.reps,sr.weight,sr.minutes, " +//3-6
                " sr.seconds,sr.calories,sr.meters,sr.unit_type,sr.record_total,sr.wod_content_id,sr.remark " +//7-13
                " from t_sport_result sr "+
                " where "+
                " sr.member_id="+ memberId +
                " and sr.content_id="+ contentId +
                " and sr.box_id="+ boxId +
                " order by sr.insert_time desc ";
        return this.queryBySql(sql);
    }

    @Override
    public List<Object[]> queryMemberScorePopByPage(Integer boxId, Integer memberId, Integer contentId, Integer beginIndex, Integer size) {
        String sql = " select sr.content_id,sr.insert_time,sr.record_type," + //0-2
                " sr.sets,sr.reps,sr.weight,sr.minutes, " +//3-6
                " sr.seconds,sr.calories,sr.meters,sr.unit_type,sr.record_total,sr.wod_content_id,sr.remark  " +//7-12
                " from t_sport_result sr "+
                " where "+
                " sr.member_id="+ memberId +
                " and sr.content_id="+ contentId +
                " and sr.box_id="+ boxId +
                " order BY sr.insert_time desc " +
                " limit " + (size * (beginIndex-1)) + "," + size;
        return this.queryBySql(sql);
    }

    @Override
    public List<SportResult> querySportResultByWodContent(int wodContentId) {
        String hql = " from SportResult where wodContentId=" + wodContentId ;
        return getSession().createQuery(hql).list();
    }

    @Override
    public List<SportResult> querySportResultByContentAndMemberOrderbyDate(int contentId,int memberId) {
        String hql = "from SportResult where memberId=:memberId and contentId=:contentId order by insertTime desc";//and wodContentId=:wodContentId 
        Query q =null;
		Session session = getSession();
		q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		q.setInteger("contentId", contentId);
		return q.list();  
    }

    @Override
    public List<SportResult> querySportResultByContentAndMemberOrderbyDate(int wodContentId,int contentId,int memberId) {
        String hql = "from SportResult where memberId=:memberId and (contentId=:contentId or contentId is null ) and wodContentId=:wodContentId order by insertTime desc";//and wodContentId=:wodContentId 
        Query q =null;
		Session session = getSession();
		q = session.createQuery(hql);
		q.setInteger("memberId", memberId);
		q.setInteger("contentId", contentId);
		q.setInteger("wodContentId", wodContentId);
		return q.list();  
    }
    
    @Override
    public List<SportResult> querySportResultByContentAndMemberOrderbyRerord(int contentId,int memberId,String order) {
        String hql = " from SportResult where memberId="+memberId+" and contentId="+contentId+"  order by "+order ;//and wodContentId=" + wodContentId+"
        return getSession().createQuery(hql).list();
    }
    
    @Override
    public List<SportResult> querySportResultBy1x1(int contentId,int memberId) {
        String hql = " from SportResult where memberId="+memberId+" and contentId="+contentId+" and sets=1 and reps=1  order by weight desc" ;//and wodContentId=" + wodContentId+"
        return getSession().createQuery(hql).list();
    }
    
    @Override
    public List queryWodIdByMemberLoged(int memberId,int pageIndex,int pageSize) {
    		int start=(pageIndex-1)*pageSize;
        String hql = "select wod_id from (SELECT distinct wc.wod_id FROM cfer.t_sport_result sr left join t_wod_content wc on wc.wod_content_id=sr.wod_content_id where member_id="+memberId+" and wod_id is not null) a order by a.wod_id desc  limit "+start+","+pageSize;
        return getSession().createSQLQuery(hql).list();
    }
   // "select * from (SELECT distinct wc.wod_id FROM cfer.t_sport_result sr left join t_wod_content wc on wc.wod_content_id=sr.wod_content_id where member_id=77 and wod_id is not null) a order by a.wod_id desc  limit 0,20 "
}
