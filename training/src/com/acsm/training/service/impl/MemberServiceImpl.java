package com.acsm.training.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.model.User;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.MemberRenewHistoryService;
import com.acsm.training.util.UserRegisterValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.MemberBoxDao;
import com.acsm.training.dao.MemberDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.enums.UserType;
import com.acsm.training.model.Member;
import com.acsm.training.model.MemberBox;
import com.acsm.training.service.MemberService;
import com.acsm.training.util.DateUtil;
import com.acsm.training.vo.ResponseObject;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Resource(name="memberDao")
	private MemberDao memberDao;
	
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="memberRenewHistoryService")
	private MemberRenewHistoryService memberRenewHistoryService;

	@Resource
	private MemberBoxDao memberBoxDao;

	@Override
	public Member queryById(Integer id) {
		Member member = memberDao.queryById(id);
		String comment = memberDao.queryCommentById(id);
		member.setComment(comment);
		return member;
	}

	@Override
	public PageHelper<Member> queryPageByConditions(Map<String, Object> conditions) {
		return memberDao.queryPageByConditions(conditions);
	}

	@Override
	public void save(Member member, User user) {
		Member saveMember = memberDao.add(member);
		user.setRelatedId(saveMember.getId());
		userDao.add(user);
		memberRenewHistoryService.save(null, saveMember);
	}

	@Override
	public void update(Member oldMember, Member member, User user) {
		memberDao.update(member);
		userDao.update(user);
		memberRenewHistoryService.save(oldMember, member);
	}

	@Override
	public Member addPatchMember(String name) {
		Member member = new Member();
		member.setType(2);
		member.setName(name);
		member.setMemberStartTime(new Date());
        return memberDao.add(member);
	}

	@Override
	public boolean addDay(String[] memberIds, int days) {
		try{
			for(String id:memberIds) {
				Member member = memberDao.queryById(Integer.parseInt(id));
				Date memberEndTime = member.getMemberEndTime();
				Date newMemberEndTime = DateUtil.getDate(memberEndTime, days);
				member.setMemberEndTime(newMemberEndTime);
				memberDao.update(member);
			}
			return true;
		}catch (Exception e){
			log.error("add member days exception", e);
			return false;
		}
	}

	@Override
	public List<Member> queryList() {
		return memberDao.queryList();
	}

	@Override
	public String[] queryMembeIds() {
		List<Member> members = memberDao.queryList();
		String[] memberIds = new String[members.size()];
		for(int i=0; i<members.size(); i++){
			memberIds[i] = members.get(i).getId().toString();
		}
		return memberIds;
	}

	@Override
	public List<Member> queryListBySex(int sex) {
		return memberDao.queryListBySex(sex);
	}

	@Override
	public String[] queryMemberIdBySex(int sex) {
		List<Member> members = memberDao.queryListBySex(sex);
		String[] memberIds = new String[members.size()];
		for(int i=0; i<members.size(); i++){
			memberIds[i] = members.get(i).getId().toString();
		}
		return memberIds;
	}

	@Override
	public void update(Member member) {
		memberDao.update(member);
	}

	@Override
	public void updateMainBox(int memberId, int mainBoxId) {
		Member member = memberDao.queryById(memberId);
		if(member.getBoxId()!=null){
			MemberBox oldMainBox = memberBoxDao.queryByBoxIdAndMemberId(member.getBoxId(), memberId);
			oldMainBox.setIsMain(0);
			memberBoxDao.update(oldMainBox);
		}
		MemberBox newMainBox = memberBoxDao.queryByBoxIdAndMemberId(mainBoxId, memberId);
		newMainBox.setIsMain(1);
		memberBoxDao.update(newMainBox);
		buildMainBoxMemberInfoByMemberBox(newMainBox, member);
		memberDao.update(member);
	}

	@Override
	public ResponseObject addMember(String phone, int memberType, String memberStartTimeStr, String memberEndTimeStr,
						  int dayLimit, int memberRemainNum, String courseTypeIds, int boxId) {
		ResponseObject response = new ResponseObject();
		response.setCode(0);
		response.setMessage("success");
		Member member = memberDao.queryByPhone(phone);
		if(member == null){
			//没有会员信息，设置会员信息和主box
			member = new Member();
			member.setCreateTime(new Date());
			member.setPhone(phone);
			member.setType(1);
			member.setStatus(1);
			member.setName(phone);
			buildMainBoxMemberInfo(boxId, courseTypeIds, memberType, dayLimit, memberRemainNum, memberStartTimeStr, memberEndTimeStr, member);
			Member saveMember = memberDao.add(member);
			//添加MemberBox表信息
			int isMain = 1;
			MemberBox memberBox = buildMemberBox(boxId,courseTypeIds,memberType,dayLimit,memberRemainNum,memberStartTimeStr,memberEndTimeStr,saveMember.getId(), isMain);
			if(memberType == 2){
				memberBox.setIsActive(0);//次卡需在第一次使用后激活
			}else if(memberType == 1){
				memberBox.setIsActive(1);//月卡直接激活
			}
			memberBoxDao.add(memberBox);
			//添加用户信息
			User user = new User();
			user.setUserName(phone);
			user.setPassword(UserRegisterValidateUtil.encodePassword("88888888", "SHA"));
			user.setRelatedId(saveMember.getId());
			user.setUserType(UserType.MEMBER.CODE);
			user.setIsDeleted(0);
			userDao.add(user);
			return response;
		}else{
			MemberBox exitMemberBox = memberBoxDao.queryByBoxIdAndMemberId(boxId, member.getId());
			if(exitMemberBox != null){
				//已经添加过
				response.setCode(2);
				response.setMessage("会员在训练馆中已存在");
				return response;
			}
			int isMain = 0;
			//存在会员，判断是否有主box
			if(member.getBoxId() == null){
				//没有主训练馆讲该训练馆设置成主
				isMain = 1;
				buildMainBoxMemberInfo(boxId,courseTypeIds,memberType,dayLimit,memberRemainNum,memberStartTimeStr,memberEndTimeStr,member);
			}
			//添加MemberBox
			MemberBox memberBox = buildMemberBox(boxId,courseTypeIds,memberType,dayLimit,memberRemainNum,memberStartTimeStr,memberEndTimeStr,member.getId(), isMain);
			if(memberType == 2){
				memberBox.setIsActive(0);//次卡需在第一次使用后激活
			}else if(memberType == 1){
				memberBox.setIsActive(1);//月卡直接激活
			}
			memberBoxDao.add(memberBox);
			return response;
		}
	}

	/**
	 * 新更新会员信息
	 *
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
	@Override
	public ResponseObject update(int memberId, String phone, int memberType, String memberStartTimeStr, String memberEndTimeStr, int dayLimit, int memberRemainNum, String courseTypeIds, int boxId) {
		ResponseObject response = new ResponseObject();
		response.setCode(0);
		response.setMessage("success");
		try {
			MemberBox memberBox = memberBoxDao.queryByBoxIdAndMemberId(boxId, memberId);
			memberBox.setCourseTypeIds(courseTypeIds);
			memberBox.setMemberType(memberType);
			buildMemberBoxInfo(dayLimit, memberRemainNum, memberStartTimeStr, memberEndTimeStr, memberBox);
			memberBoxDao.update(memberBox);
			if (memberBox.getIsMain() == 1) {
				Member member = memberDao.queryById(memberId);
				buildMainBoxMemberInfo(boxId, courseTypeIds, memberType, dayLimit, memberRemainNum, memberStartTimeStr, memberEndTimeStr, member);
				member.setName(phone);
				memberDao.update(member);
			}
		}catch (Exception e){
			response.setCode(1);
			response.setMessage("fail");
		}
		return response;
	}

	/**
	 * 构建MemberBox
	 * @param boxId
	 * @param courseTypeIds
	 * @param memberType
	 * @param dayLimit
	 * @param memberRemainNum
	 * @param memberStartTimeStr
	 * @param memberEndTimeStr
	 * @param memberId
	 * @return
	 */
	private MemberBox buildMemberBox(int boxId, String courseTypeIds, int memberType, int dayLimit, int memberRemainNum, String memberStartTimeStr, String memberEndTimeStr, int memberId, int isMain){
		MemberBox memberBox = new MemberBox();
		memberBox.setBoxId(boxId);
		memberBox.setMemberId(memberId);
		memberBox.setCourseTypeIds(courseTypeIds);
		memberBox.setCreateTime(new Date());
		memberBox.setMemberType(memberType);
		memberBox.setIsMain(isMain);
		buildMemberBoxInfo(dayLimit,memberRemainNum,memberStartTimeStr,memberEndTimeStr,memberBox);
		return memberBox;
	}


	/**
	 * 添加会员主训练馆会员信息
	 * @param boxId
	 * @param courseTypeIds
	 * @param memberType
	 * @param dayLimit
	 * @param memberRemainNum
	 * @param memberStartTimeStr
	 * @param memberEndTimeStr
	 * @param member
	 */
	private void buildMainBoxMemberInfo(int boxId, String courseTypeIds, int memberType, int dayLimit, int memberRemainNum, String memberStartTimeStr, String memberEndTimeStr, Member member){
		if(memberType == 2){
			member.setMemberRemainNum(memberRemainNum);
			member.setMemberDays(dayLimit);
			Date memberEndTime = DateUtil.convertDate(dayLimit);
			member.setMemberStartTime(DateUtil.StringToDate(DateUtil.getCurrentDate("yyyy/MM/dd"), "yyyy/MM/dd"));
			member.setMemberEndTime(memberEndTime);
		}else{
			member.setMemberStartTime(DateUtil.StringToDate(memberStartTimeStr, "yyyy/MM/dd"));
			Date memberEndTime = DateUtil.StringToDate(memberEndTimeStr, "yyyy/MM/dd");
			member.setMemberEndTime(memberEndTime);
		}
		member.setBoxId(boxId);
		member.setCourseTypeIds(courseTypeIds);
		member.setMemberType(memberType);
	}

	private void buildMainBoxMemberInfoByMemberBox(MemberBox memberBox, Member member){
		member.setMemberRemainNum(memberBox.getMemberRemainNum());
		member.setMemberDays(memberBox.getMemberDays());
		member.setMemberStartTime(memberBox.getMemberStartTime());
		member.setMemberEndTime(memberBox.getMemberEndTime());
		member.setBoxId(memberBox.getBoxId());
		member.setCourseTypeIds(memberBox.getCourseTypeIds());
		member.setMemberType(memberBox.getMemberType());
	}

	private void buildMemberBoxInfo(int dayLimit, int memberRemainNum, String memberStartTimeStr, String memberEndTimeStr, MemberBox memberBox){
		if(memberBox.getMemberType() == 2){
			memberBox.setMemberRemainNum(memberRemainNum);
			memberBox.setMemberDays(dayLimit);
			Date memberEndTime = DateUtil.convertDate(dayLimit);
			memberBox.setMemberStartTime(DateUtil.StringToDate(DateUtil.getCurrentDate("yyyy/MM/dd"), "yyyy/MM/dd"));
			memberBox.setMemberEndTime(memberEndTime);
		}else{
			memberBox.setMemberStartTime(DateUtil.StringToDate(memberStartTimeStr, "yyyy/MM/dd"));
			Date memberEndTime = DateUtil.StringToDate(memberEndTimeStr, "yyyy/MM/dd");
			memberBox.setMemberEndTime(memberEndTime);
		}
	}
	
	@Override
	public PageHelper<Member> queryExpiredMembers(Integer boxId, Integer pageSize, Integer pageIndex) {
		return memberDao.queryExpiredMembers(boxId, pageSize, pageIndex);
	}

	@Override
	public List<Member> queryListByBox(Integer boxId) {
		return memberDao.queryListByBox(boxId);
	}


}
