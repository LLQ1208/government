package com.acsm.training.service;

import com.acsm.training.model.Member;

public interface MemberRenewHistoryService {
	
	/**
	 * 添加历史记录
	 * @param oldMember
	 * @param newMember
	 */
	public void save(Member oldMember, Member newMember);

}
