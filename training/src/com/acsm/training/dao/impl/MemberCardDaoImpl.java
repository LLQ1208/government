package com.acsm.training.dao.impl;/**
									* Created by lq on 2018/1/26.
									*/

import com.acsm.training.dao.MemberCardDao;
import com.acsm.training.model.MemberCard;
import com.acsm.training.util.DateUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-01-26
 */
@Repository("memberCardDao")
public class MemberCardDaoImpl extends BaseDaoImpl<MemberCard> implements MemberCardDao {
	@Override
	public MemberCard queryById(int id) {
		String hql = "from MemberCard where id=?";
		return (MemberCard) this.Queryobject(hql, id);
	}

	@Override
	public List<MemberCard> queryByMemberId(int memberId, int customCardCourseType) {
		String hql = "from MemberCard where memerId=?  and status=0 order by cardEndTime desc ";
		return (List<MemberCard>) this.list(hql, new Object[] { memberId, customCardCourseType });
	}

	@Override
	public List<MemberCard> queryByMemberIdByBetweenDate(int memberId) {
//		String part = "memberCardTemplateId=0 ";
//		if (custom == 1)
//			part = "memberCardTemplateId!=0 ";
		String hql = "from MemberCard where memerId=? and  cardEndTime>now() and cardStartTime<now()  and status=0 order by cardStartTime desc ";
		return (List<MemberCard>) this.list(hql, new Object[] { memberId });
	}

	@Override
	public List<Object[]> queryGroupList(int memberId,Integer boxId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" mc.id, ");// 0 卡id
		sql.append(" mc.member_card_template_id, ");// 1模板id
		sql.append(" mc.money, ");// 2钱
		sql.append(" mc.card_start_time, ");// 3开始时间
		sql.append(" mc.card_end_time, ");// 4 结束时间
		sql.append(" mc.custom_card_type, ");// 5自定义卡类型
		sql.append(" mc.custom_card_course_type, ");// 6
		sql.append(" mct.template_type, ");// 7
		sql.append(" mct.bout_card_type, ");// 8
		sql.append(" mc.remain_num, ");// 9
		sql.append(" GROUP_CONCAT(c1.`NAME`), ");// 10
		sql.append(" GROUP_CONCAT(c2.`NAME`), ");// 11
		sql.append(" mct.template_name, ");// 12
		sql.append(" mc.card_expire_day, ");// 13
		sql.append(" mct.expiry_reminder,");// 14
		sql.append(" mc.insert_user, ");// 15
		sql.append(" mc.status ");// 16
		sql.append(" from t_member_card mc  ");
		sql.append(" LEFT JOIN t_member_card_template mct on mc.member_card_template_id=mct.id ");
		sql.append(" LEFT JOIN t_course_type c1 on FIND_IN_SET(c1.id,mc.custom_course_type_ids) ");
		sql.append(" LEFT JOIN t_course_type c2 on FIND_IN_SET(c2.id,mct.course_type_ids) ");
		sql.append(" where mc.memer_id= ").append(memberId);
		sql.append(" and mc.box_id=").append(boxId);
		sql.append(" and ( ");
		sql.append(" (mc.member_card_template_id=0 and mc.custom_card_type=1 ) ");
		sql.append(" or (mc.member_card_template_id=0 and mc.custom_card_type=2 and mc.custom_card_course_type=1) ");
		sql.append(" or (mc.member_card_template_id != 0 and mct.template_type = 1) ");
		sql.append(" or (mc.member_card_template_id !=0 and mct.template_type=2 and mct.bout_card_type=1) ");
		sql.append(" ) GROUP BY mc.id desc ");
		return this.queryBySql(sql.toString());
	}

	@Override
	public List<Object[]> queryPrivateList(int memberId,Integer boxId) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select ");
		sql.append(" mc.id, ");// 0 卡id
		sql.append(" mc.member_card_template_id, ");// 1模板id
		sql.append(" mc.money, ");// 2钱
		sql.append(" mc.card_start_time, ");// 3开始时间
		sql.append(" mc.card_end_time, ");// 4 结束时间
		sql.append(" mc.custom_card_type, ");// 5自定义卡类型
		sql.append(" mc.custom_card_course_type, ");// 6
		sql.append(" mct.template_type, ");// 7
		sql.append(" mct.bout_card_type, ");// 8
		sql.append(" mc.remain_num, ");// 9
		sql.append(" '' as course2, ");// 10
		sql.append(" '' as course1, ");// 11
		sql.append(" mct.template_name, ");// 12
		sql.append(" mc.card_expire_day, ");// 13
		sql.append(" mct.expiry_reminder,");// 14
		sql.append(" mc.insert_user, ");// 15
		sql.append(" mc.status ");// 16
		sql.append(" from t_member_card mc  ");
		sql.append(" LEFT JOIN t_member_card_template mct on mc.member_card_template_id=mct.id ");
		sql.append(" where mc.memer_id= ").append(memberId);
		sql.append(" and mc.box_id=").append(boxId);
		sql.append(" and ( ");
		sql.append("  (mc.member_card_template_id=0 and mc.custom_card_type=2 and mc.custom_card_course_type=2) ");
		sql.append(" or (mc.member_card_template_id !=0 and mct.template_type=2 and mct.bout_card_type=2) ");
		sql.append(" ) GROUP BY mc.id desc ");
		return this.queryBySql(sql.toString());
	}

}
