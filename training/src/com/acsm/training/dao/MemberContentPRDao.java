package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.MemberContentPR;
import com.acsm.training.model.MemberContentPR;

public interface MemberContentPRDao extends BaseDao<MemberContentPR> {

	List<MemberContentPR> queryListByBoxAndContentOrderByPr(int boxId, int contentId);

	public MemberContentPR queryListByBoxAndMemberAndContent(int boxId, int memberId, int contentId);

    public List<MemberContentPR> queryListByBoxAndSexAndContent(int boxId,int sex,int contentId);
	
	public void updateBysql(float pr, int id);
}
