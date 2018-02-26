package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.MemberCard;
import com.acsm.training.model.MemberCard;

public interface MemberCardService {

	public List<MemberCard> getCards(int memberId, int boxId, int group);
}
