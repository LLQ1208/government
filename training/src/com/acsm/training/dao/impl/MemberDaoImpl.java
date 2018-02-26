package com.acsm.training.dao.impl;

import java.util.List;
import java.util.Map;

import com.acsm.training.dao.MemberDao;
import com.acsm.training.model.Member;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MemberDao;
import com.acsm.training.model.Member;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.util.StringUtils;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDaoImpl<Member> implements MemberDao {

	@Override
	public Member queryById(Integer id) {
		String hql ="from Member where id=?";
        return (Member)this.Queryobject(hql,id);
	}

	@Override
	public String queryCommentById(Integer id) {
		String sql = "select t.`comment` from t_member t where t.ID=" + id;
		List<Object> objectList = this.queryBySql(sql);
		if(null == objectList || objectList.size() < 1 || objectList.get(0) == null){
			return null;
		}else{
			return objectList.get(0).toString();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<Member> queryPageByConditions(Map<String, Object> conditions) {
		String hql="select m from MemberBox as mb, Member as m where mb.memberId=m.id";
		String hqlCount="select count(*) from MemberBox as mb, Member as m where mb.memberId=m.id";
		int pageSize = 8;
		int pageIndex = 1;
		String keyword = "";
		int boxId = 0;
		if(conditions.get("pageSize")!=null){
			pageSize = (Integer) conditions.get("pageSize");
		}
		if(conditions.get("pageIndex")!=null){
			pageIndex = (Integer) conditions.get("pageIndex");
		}
		if(conditions.get("keyword")!=null){
			keyword = (String) conditions.get("keyword");
		}
		if(conditions.get("boxId")!=null){
			boxId = (Integer) conditions.get("boxId");
		}
		if(StringUtils.isNotEmpty(keyword)){
			hql += " and  (m.name like '%"+keyword+"%' or m.phone like '%"+keyword+"%' or m.email like '%"+keyword+"%' or m.pinCode like '%"+keyword+"%')";
			hqlCount += " and  (m.name like '%"+keyword+"%' or m.phone like '%"+keyword+"%' or m.email like '%"+keyword+"%' or m.pinCode like '%"+keyword+"%')";;
		}
		hql+=" and m.type=1 and mb.boxId="+boxId;
		hqlCount+=" and m.type=1 and mb.boxId="+boxId;
		hql+=" order by mb.createTime desc";
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<Member> list = q.list();
		//数据列表
		PageHelper<Member> page = new PageHelper<Member>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

	@Override
	public List<Member> queryList() {
		String hql = "from Member where type=1";
		return this.list(hql);
	}

	@Override
	public List<Member> queryListBySex(int sex) {
		String hql = "from Member where type=1 and sex=?";
		return this.list(hql, sex);
	}

	@Override
	public void updateMainBox(int memberId, int mainBoxId) {
		String hql = "update Member set boxId=? where id=?";
		Object[] obj = {mainBoxId, memberId};
		this.UpdateByHql(hql, obj);
	}

	@Override
	public Member queryByPhone(String phone) {
		String hql = "from Member where phone=?";
		List<Member> list = this.list(hql, phone);
		return list!=null && list.size()>0?list.get(0):null;
	}

	@Override
	public List<Object[]> queryMemberByPhont(String phone) {
		String sql = "select * from t_member t where t.PHONE='"+phone+"'";
		return this.queryBySql(sql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageHelper<Member> queryExpiredMembers(Integer boxId,Integer pageSize,Integer pageIndex) {
		String hql="select m from MemberBox as mb, Member as m where mb.memberId=m.id";
		String hqlCount="select count(*) from MemberBox as mb, Member as m where mb.memberId=m.id";
		String keyword = "";
		hql+=" and m.type=1 and mb.boxId="+boxId;
		hqlCount+=" and m.type=1 and mb.boxId="+boxId;
		hql+=" and now()<=m.memberEndTime and " + 
				"DATEDIFF(m.memberEndTime,now())<=10 order by m.memberEndTime desc";
		hqlCount+=" and now()<=m.memberEndTime and " + 
				"DATEDIFF(m.memberEndTime,now())<=10 ";
		Session session = getSession();
		Query q =null;
		Query qCount = null;
		q = session.createQuery(hql);
		qCount = session.createQuery(hqlCount);
		//获取数据总条数
		int total = Integer.parseInt(qCount.uniqueResult().toString());
		q.setFirstResult((pageIndex-1)*pageSize);
		q.setMaxResults(pageSize);
		List<Member> list = q.list();
		//数据列表
		PageHelper<Member> page = new PageHelper<Member>();
		page.setList(list);
		page.setTotalRow(total);
		return page;
	}

	@Override
	public List<Member> queryListByBox(Integer boxId) {
		String hql = "from Member where boxId=? and type=1";
		return this.list(hql,boxId);
	}

	@Override
	public Member queryByPhoneBoxName(String memberName, String phone, Integer boxId) {
		return null;
	}

	@Override
	public PageHelper queryGroupMemberList(String boxIds,Integer boxId, String search, Integer timeType,
										 Integer sex,Integer templateId, Integer orderType, Integer orderDesc,
										 Integer pageIndex,Integer pageSize,String memberCardIds) {
		PageHelper pageHelper = new PageHelper();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append("select ");
		sql.append(" m.ID, ");//0 会员id
		sql.append(" m.`NAME`, ");//1 会员名字
		sql.append(" m.SEX, ");// 2 性别
		sql.append(" m.BIRTHDAY as birthday, ");//3 生日
		sql.append(" m.PHONE, ");//4 电话
		sql.append(" m.EMAIL, ");//5 邮箱
		sql.append(" m.CREATE_TIME as first_card_time, ");//6 初次办卡时间
		sql.append(" mc.card_start_time as continue_card_time, ");//7 续卡时间
		sql.append(" mc.card_end_time  as card_end_time, ");//8 到期时间
		sql.append(" mc.member_card_template_id, ");//9 模板id
		sql.append(" mct.template_name, ");//10 模板名字
		sql.append(" mc.custom_card_type, ");//11 自定义卡类型
		sql.append(" mct.template_type, ");//12 卡模板类型
		sql.append(" mc.card_total_count, ");//13次卡次数
		sql.append(" ma.group_course_last_time, ");//14 上次上课时间
		sql.append(" ma.group_course_all_count as all_count, ");//15总次数
		sql.append(" ma.group_course_all_rate as all_rate, ");//16总频率
		sql.append(" ma.group_course_this_count as this_count, ");//17当前次数
		sql.append(" ma.group_course_this_rate as this_rate, ");//18当前频率
		sql.append(" ma.group_course_change_rate  as change_rate, ");//19出勤频率变化
		sql.append(" mc.card_expire_day, ");//20自定义卡过期提醒时间
		sql.append(" mct.expiry_reminder, ");//21模板卡过期提醒时间
		sql.append(" mc.remain_num, ");//22次卡剩余次数
		sql.append(" ma.group_course_this_card_id, ");//23当前卡id
		sql.append(" mc.box_id ");//24
		sql.append(" from t_member m  ");
		sql.append(" INNER JOIN t_member_analysis ma ON m.id = ma.member_id ");
		sql.append(" INNER JOIN t_member_card mc ON ma.group_course_this_card_id = mc.id ");
		sql.append(" LEFT JOIN t_member_card_template mct ON mc.member_card_template_id = mct.id ");
		sql.append(" LEFT JOIN t_member_box mb on mb.MEMBER_ID=m.ID and mb.BOX_ID=mc.box_id ");
		sql.append(" where 1=1 ");
		if(null != memberCardIds && !"".equals(memberCardIds)){
			sql.append(" and mc.id in (").append(memberCardIds).append(")");
		}
		//馆
		if(null == boxId || boxId == -1){//老板下所有的馆
			sql.append(" and mb.BOX_ID in(").append(boxIds).append(")");
		}else{
			sql.append("  and mb.BOX_ID =").append(boxId);
		}
		//搜索条件
		if(null != search && !"".equals(search)){
			sql.append(" and (m.`NAME` like '%").append(search).append("%' or m.PHONE like '%").append(search).append("%')");
		}
		//过期类型
		if(null == timeType || timeType == -1){//全部

		}else if(timeType == 1){//有效
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num>0) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num > 0))");
		}else if(timeType == 2){//即将过期
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mc.card_expire_day ) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num>0 and (TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mc.card_expire_day or mc.remain_num < 5)) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mct.expiry_reminder) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num > 0 and (TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mct.expiry_reminder or mc.remain_num <5))) ");
		}else if(timeType == 3){//已过期
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and (mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') or mc.remain_num=0)) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and (mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') or mc.remain_num = 0))) ");
		}
		//性别
		if(null == sex || sex != -1 ){//全部
			sql.append(" and m.SEX=").append(sex);
		}
		//模板id
		if(templateId == null || templateId == -1){//全部

		}else{
			sql.append(" and mc.member_card_template_id=").append(templateId);
		}
		sql.append(" ) as test ");
		if(orderType == null || orderType == -1){
			sql.append(" order by test.id ");
		}else if(orderType == 1){//出生日期
			sql.append(" ORDER BY test.birthday ");
		}else if(orderType == 2){//初次办卡时间
			sql.append(" ORDER BY test.first_card_time ");
		}else if(orderType == 3){//续卡时间
			sql.append(" ORDER BY test.continue_card_time ");
		}else if(orderType == 4){//到期时间
			sql.append(" ORDER BY test.card_end_time ");
		}else if(orderType == 5){//累计约课次数
			sql.append(" ORDER BY test.all_count ");
		}else if(orderType == 6){//累计约课频率
			sql.append(" ORDER BY test.all_rate ");
		}else if(orderType == 7){//当前约课次数
			sql.append(" ORDER BY test.this_count ");
		}else if(orderType == 8){//当前约课频率
			sql.append(" ORDER BY test.this_rate ");
		}else if(orderType == 9){//出勤率
			sql.append(" ORDER BY test.change_rate ");
		}
		if(orderDesc == 1){
			sql.append(" desc ");
		}else{
			sql.append(" asc ");
		}
		pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
		StringBuffer pageSql = new StringBuffer();
		pageSql.append(sql);
		if(pageIndex != null && pageSize != null){
			pageSql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);
		}
		pageHelper.setList(this.queryBySql(pageSql.toString()));
		return pageHelper;
	}

	@Override
	public PageHelper queryPrivateMemberList(String boxIds, Integer boxId, String search, Integer timeType,
											 Integer sex, Integer templateId, Integer orderType,
											 Integer orderDesc, Integer pageIndex, Integer pageSize,String memberCardIds) {
		PageHelper pageHelper = new PageHelper();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (");
		sql.append("select ");
		sql.append(" m.ID, ");//0 会员id
		sql.append(" m.`NAME`, ");//1 会员名字
		sql.append(" m.SEX, ");// 2 性别
		sql.append(" m.BIRTHDAY as birthday, ");//3 生日
		sql.append(" m.PHONE, ");//4 电话
		sql.append(" m.EMAIL, ");//5 邮箱
		sql.append(" m.CREATE_TIME as first_card_time, ");//6 初次办卡时间
		sql.append(" mc.card_start_time as continue_card_time, ");//7 续卡时间
		sql.append(" mc.card_end_time  as card_end_time, ");//8 到期时间
		sql.append(" mc.member_card_template_id, ");//9 模板id
		sql.append(" mct.template_name, ");//10 模板名字
		sql.append(" mc.custom_card_type, ");//11 自定义卡类型
		sql.append(" mct.template_type, ");//12 卡模板类型
		sql.append(" mc.card_total_count, ");//13次卡次数
		sql.append(" ma.private_course_last_time, ");//14 上次上课时间
		sql.append(" ma.private_course_all_count as all_count, ");//15总次数
		sql.append(" ma.private_course_all_rate as all_rate, ");//16总频率
		sql.append(" ma.private_course_this_count as this_count, ");//17当前次数
		sql.append(" ma.private_course_this_rate as this_rate, ");//18当前频率
		sql.append(" ma.private_course_change_rate  as change_rate, ");//19出勤频率变化
		sql.append(" mc.card_expire_day, ");//20自定义卡过期提醒时间
		sql.append(" mct.expiry_reminder, ");//21模板卡过期提醒时间
		sql.append(" mc.remain_num, ");//22次卡剩余次数
		sql.append(" ma.private_course_this_card_id, ");//23当前卡id
		sql.append(" mc.box_id ");//24
		sql.append(" from t_member m  ");
		sql.append(" INNER JOIN t_member_analysis ma ON m.id = ma.member_id ");
		sql.append(" INNER JOIN t_member_card mc ON ma.private_course_this_card_id = mc.id ");
		sql.append(" LEFT JOIN t_member_card_template mct ON mc.member_card_template_id = mct.id ");
		sql.append(" LEFT JOIN t_member_box mb on mb.MEMBER_ID=m.ID and mb.BOX_ID=mc.box_id ");
		sql.append(" where 1=1 ");
		if(null != memberCardIds && !"".equals(memberCardIds)){
			sql.append(" and mc.id in (").append(memberCardIds).append(")");
		}
		//馆
		if(null == boxId || boxId == -1){//老板下所有的馆
			sql.append(" and mb.BOX_ID in(").append(boxIds).append(")");
		}else{
			sql.append("  and mb.BOX_ID =").append(boxId);
		}
		//搜索条件
		if(null != search && !"".equals(search)){
			sql.append(" and (m.`NAME` like '%").append(search).append("%' or m.PHONE like '%").append(search).append("%')");
		}
		//过期类型
		if(null == timeType || timeType == -1){//全部

		}else if(timeType == 1){//有效
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num>0) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num > 0))");
		}else if(timeType == 2){//即将过期
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mc.card_expire_day ) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num>0 and (TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mc.card_expire_day or mc.remain_num < 5)) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mct.expiry_reminder) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time >= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.remain_num > 0 and (TIMESTAMPDIFF(DAY,SYSDATE(),mc.card_end_time) < mct.expiry_reminder or mc.remain_num <5))) ");
		}else if(timeType == 3){//已过期
			sql.append(" and ((mc.custom_card_type=1 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d')) ");
			sql.append(" or (mc.custom_card_type=2 and mc.card_start_time <= DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and (mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') or mc.remain_num=0)) ");
			sql.append(" or (mct.template_type=1 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') ) ");
			sql.append(" or (mct.template_type=2 and mc.card_start_time<=DATE_FORMAT(SYSDATE(),'%Y-%m-%d') and (mc.card_end_time < DATE_FORMAT(SYSDATE(),'%Y-%m-%d') or mc.remain_num = 0)) ");
		}
		//性别
		if(null == sex || sex != -1 ){//全部
			sql.append(" and m.SEX=").append(sex);
		}
		//模板id
		if(templateId == null || templateId == -1){//全部

		}else{
			sql.append(" and mc.member_card_template_id=").append(templateId);
		}
		sql.append(" ) as test ");
		if(orderType == null || orderType == -1){
			sql.append(" order by test.id ");
		}else if(orderType == 1){//出生日期
			sql.append(" ORDER BY test.birthday ");
		}else if(orderType == 2){//初次办卡时间
			sql.append(" ORDER BY test.first_card_time ");
		}else if(orderType == 3){//续卡时间
			sql.append(" ORDER BY test.continue_card_time ");
		}else if(orderType == 4){//到期时间
			sql.append(" ORDER BY test.card_end_time ");
		}else if(orderType == 5){//累计约课次数
			sql.append(" ORDER BY test.all_count ");
		}else if(orderType == 6){//累计约课频率
			sql.append(" ORDER BY test.all_rate ");
		}else if(orderType == 7){//当前约课次数
			sql.append(" ORDER BY test.this_count ");
		}else if(orderType == 8){//当前约课频率
			sql.append(" ORDER BY test.this_rate ");
		}else if(orderType == 9){//出勤率
			sql.append(" ORDER BY test.change_rate ");
		}
		if(orderDesc == 1){
			sql.append(" desc ");
		}else{
			sql.append(" asc ");
		}
		pageHelper.setTotalRow(this.queryBySql(sql.toString()).size());
		StringBuffer pageSql = new StringBuffer();
		pageSql.append(sql);
		if(pageIndex != null && pageSize != null){
			pageSql.append(" limit ").append(pageSize * (pageIndex-1)).append(",").append(pageSize);
		}
		pageHelper.setList(this.queryBySql(pageSql.toString()));
		return pageHelper;
	}

	@Override
	public List<Member> queryListByTemplateId(int templateId) {
		String hql = "select m from Member m,MemberBox mb " +
				"where m.id=mb.memberId " +
				" and and mb.MEMBER_START_TIME <= SYSDATE() and mb.MEMBER_END_TIME >= SYSDATE() and mb.IS_ACTIVE=1 "+
				" and memberCardTemplateId=?";
		return this.list(hql,templateId);
	}

	@Override
	public List<Member> queryByBoxAndPhone(Integer boxId, String phone) {
		String hql = "select m from Member m,MemberBox mb " +
				"where m.id=mb.memberId " +
				" and m.phone= '"+ phone +"'" +
				" and mb.boxId="+boxId;
		return this.list(hql);
	}



	/**
	 * ---定时器---start
	 */
	@Override
	public List<Member> queryAllMember() {
		String hql = "from Member";
		return this.list(hql);
	}

	@Override
	public List<Member> queryListByPhoneAndNullBox(int boxId, String phone) {
		String hql = "from Member where phone='" + phone + "' and boxId is null";
		return this.list(hql);
	}
}
