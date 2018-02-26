package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Member;
import com.acsm.training.model.basic.PageHelper;

public interface MemberDao extends BaseDao<Member> {
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Member queryById(Integer id);

	public String queryCommentById(Integer id);

	/**
	 * 根据查询条件获取会员分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<Member> queryPageByConditions(Map<String,Object> conditions);

	/**
	 * 查询所有注册会员
	 * @return
	 */
    public List<Member> queryList();

	/**
	 * 根据性别获取所有注册的会员
	 * @return
	 */
	public List<Member> queryListBySex(int sex);

	/**
	 * 更新主训练馆
	 * @param memberId
	 * @param mainBoxId
	 */
    public void updateMainBox(int memberId, int mainBoxId);

	/**
	 * 根据手机号查询会员信息
	 * @param phone
	 * @return
	 */
	public Member queryByPhone(String phone);

	public List<Object[]> queryMemberByPhont(String phone);
	
	PageHelper<Member> queryExpiredMembers(Integer boxId, Integer pageSize, Integer pageIndex);


	List<Member> queryListByBox(Integer boxId);

	public Member queryByPhoneBoxName(String memberName,String phone,Integer boxId);

	public PageHelper queryGroupMemberList(String boxIds,Integer boxId, String search, Integer timeType,
												   Integer sex,Integer templateId,Integer orderType, Integer orderDesc,
												   Integer pageIndex,Integer pageSize,String memberCardIds);



	public PageHelper queryPrivateMemberList(String boxIds,Integer boxId, String search, Integer timeType,
										   Integer sex,Integer templateId,Integer orderType, Integer orderDesc,
										   Integer pageIndex,Integer pageSize,String memberCardIds);
	List<Member> queryListByTemplateId(int templateId);


	List<Member> queryByBoxAndPhone(Integer boxId, String phone);



	/**
	 * ---定时器---start
	 */
	List<Member> queryAllMember();


	List<Member> queryListByPhoneAndNullBox(int boxId,String phone);
}
