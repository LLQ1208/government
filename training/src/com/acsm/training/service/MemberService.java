package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Member;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.vo.ResponseObject;
import com.acsm.training.model.Member;
import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.vo.ResponseObject;

public interface MemberService {
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Member queryById(Integer id);

	/**
	 * 根据查询条件获取会员分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<Member> queryPageByConditions(Map<String,Object> conditions);

	/**
	 * 添加会员信息
	 * @param member
	 * @param user
	 */
	public void save(Member member, User user);

	/**
	 * 更新会员信息
	 * @param member
	 * @param user
	 */
	public void update(Member oldMember,Member member, User user);

	/**
	 * 添加临时会员
	 * @param name
	 * @return
	 */
    public Member addPatchMember(String name);

	/**
	 * 增加会员天数
	 * @param memberIds
	 * @param days
	 * @return
	 */
	public boolean addDay(String[] memberIds, int days);

	/**
	 * 获取所有注册会员
	 * @return
	 */
	public List<Member> queryList();
	public String[] queryMembeIds();

	/**
	 * 根据性别获取所有注册的会员
	 * @return
	 */
	public List<Member> queryListBySex(int sex);
	public String[] queryMemberIdBySex(int sex);

	/**
	 * 更新
	 * @param member
	 */
    public void update(Member member);

	/**
	 * 更新主训练馆
	 * @param memberId,
	 * @param mainBoxId
	 */
	public void updateMainBox(int memberId, int mainBoxId);

	/**
	 * 训练馆添加会员
	 * @param phone
	 * @param memberType
	 * @param memberStartTimeStr
	 * @param memberEndTimeStr
	 * @param dayLimit
	 * @param memberRemainNum
	 * @param courseTypeIds
	 */
	public ResponseObject addMember(String phone, int memberType, String memberStartTimeStr, String memberEndTimeStr, int dayLimit, int memberRemainNum, String courseTypeIds, int boxId);

	/**
	 * 新更新会员信息
	 * @param memberId
	 * @param phone
	 * @param memberType
	 * @param memberStartTimeStr
	 * @param memberEndTimeStr
	 * @param dayLimit
	 * @param memberRemainNum
	 * @param courseTypeIds
	 * @param boxId
	 * @return
	 */
	ResponseObject update(int memberId, String phone, int memberType, String memberStartTimeStr, String memberEndTimeStr, int dayLimit, int memberRemainNum, String courseTypeIds, int boxId);

	/**
	 * 查询过期会员
	 * @param boxId
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	PageHelper<Member> queryExpiredMembers(Integer boxId, Integer pageSize, Integer pageIndex);

	/**
	 * 查询训练馆的会员
	 * @param boxId
	 * @return
	 */
	public List<Member> queryListByBox(Integer boxId);

}
