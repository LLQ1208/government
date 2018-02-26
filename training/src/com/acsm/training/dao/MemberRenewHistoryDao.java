package com.acsm.training.dao;

import com.acsm.training.model.MemberRenewHistory;

import java.util.List;

public interface MemberRenewHistoryDao extends BaseDao<MemberRenewHistory> {

    List<MemberRenewHistory> queryStopCardList(int cardId);

    List<MemberRenewHistory> queryListByCardId(int cardId);
}
