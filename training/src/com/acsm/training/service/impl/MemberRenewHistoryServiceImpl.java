package com.acsm.training.service.impl;

import javax.annotation.Resource;

import com.acsm.training.dao.MemberRenewHistoryDao;
import com.acsm.training.service.MemberRenewHistoryService;
import com.acsm.training.util.DateUtil;
import org.springframework.stereotype.Service;

import com.acsm.training.model.Member;
import com.acsm.training.model.MemberRenewHistory;

@Service("memberRenewHistoryService")
public class MemberRenewHistoryServiceImpl implements MemberRenewHistoryService {
	
	@Resource(name="memberRenewHistoryDao")
	private MemberRenewHistoryDao memberRenewHistoryDao;

	@Override
	public void save(Member oldMember, Member newMember) {
		MemberRenewHistory memberRenewHistory = new MemberRenewHistory();
		Integer memberRemainNum = 0;
		if(oldMember!=null){
			memberRenewHistory.setOldStartTime(oldMember.getMemberStartTime());
			memberRenewHistory.setOldEndTime(oldMember.getMemberEndTime());
			memberRenewHistory.setOldMemberType(oldMember.getMemberType());
			memberRenewHistory.setAddDays(DateUtil.getDatePoorDay(oldMember.getMemberEndTime(), newMember.getMemberEndTime()));
			memberRemainNum = oldMember.getMemberRemainNum();
		}else{
			memberRenewHistory.setAddDays(DateUtil.getDatePoorDay(newMember.getMemberStartTime(), newMember.getMemberEndTime()));
		}
		memberRenewHistory.setMemberId(newMember.getId());
		memberRenewHistory.setNewStartTime(newMember.getMemberStartTime());
		memberRenewHistory.setNewEndTime(newMember.getMemberEndTime());
		memberRenewHistory.setNewMemberType(newMember.getMemberType());
		if(newMember.getMemberType()==2){
			if(oldMember!=null&&oldMember.getMemberType()==1){
				memberRenewHistory.setAddNums(newMember.getMemberRemainNum());
			}else{
				memberRenewHistory.setAddNums(newMember.getMemberRemainNum()-memberRemainNum);
			}
		}
		memberRenewHistoryDao.add(memberRenewHistory);
	}

}
