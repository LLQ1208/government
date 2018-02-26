package com.acsm.training.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.acsm.training.dao.MemberCardDao;
import com.acsm.training.dao.MemberCardTemplateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acsm.training.model.MemberCard;
import com.acsm.training.model.MemberCardTemplate;
import com.acsm.training.service.MemberCardService;
import com.acsm.training.util.StringUtils;

@Service
public class MemberCardServiceImpl implements MemberCardService {

	@Autowired
	MemberCardDao memberCardDao;

	@Autowired
	MemberCardTemplateDao cardTemplateDao;

	public List<MemberCard> getCards(int memberId, int boxId, int group) {// 0 all 1group 2 private
		List<MemberCard> mcgs = memberCardDao.queryByMemberIdByBetweenDate(memberId);
		List<MemberCard> groups = new ArrayList();
		List<MemberCard> privates = new ArrayList();

		for (int i = 0; i < mcgs.size(); i++) {
			memberCardDao.getSession().flush();
			memberCardDao.getSession().clear();
			MemberCard memberCard = mcgs.get(i);
			if (memberCard.getMemberCardTemplateId() == 0) {// 自定义
				if (memberCard.getCustomCardType() == 1) {// limit date
					groups.add(memberCard);
				} else {
					if (memberCard.getCustomCardCourseType() == 1) {
						groups.add(memberCard);
					} else {
						privates.add(memberCard);
					}
				}
			} else {
				MemberCardTemplate tm = cardTemplateDao.queryById(memberCard.getMemberCardTemplateId());
				if (tm == null)
					continue;
				memberCard.setCustomCardType(tm.getTemplateType());
				memberCard.setCardExpireDay(tm.getExpiryReminder());
				memberCard.setCustomCourseTypeIds(tm.getCourseTypeIds());
				memberCard.setTemplate(tm);
				List<String> ids = null;
				if (StringUtils.isNotEmpty(tm.getBoxId())) {
					ids = Arrays.asList(tm.getBoxId().split(","));
					if (ids.contains(boxId + "")) {
						if (tm.getTemplateType() == 1) {
							memberCard.setCustomCardCourseType(1);
							groups.add(memberCard);
						} else {
							memberCard.setCustomCardCourseType(tm.getBoutCardType());
							if (tm.getBoutCardType() == 2)
								privates.add(memberCard);
							else
								groups.add(memberCard);
						}
						continue;
					}
				}
			}

		}
		if (group == 1)
			return groups;
		else if (group == 2)
			return privates;
		return new ArrayList();
	}
}
