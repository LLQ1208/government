package com.acsm.training.dao.impl;

import com.acsm.training.dao.MemberRenewHistoryDao;
import com.acsm.training.model.MemberRenewHistory;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MemberRenewHistoryDao;
import com.acsm.training.model.MemberRenewHistory;

import java.util.List;

@Repository("memberRenewHistoryDao")
public class MemberRenewHistoryDaoImpl extends BaseDaoImpl<MemberRenewHistory> implements MemberRenewHistoryDao {

    @Override
    public List<MemberRenewHistory> queryStopCardList(int cardId) {
        String hql = "from MemberRenewHistory where memberCardId=" + cardId + " and type=2 order by id desc";
        return this.list(hql);
    }

    @Override
    public List<MemberRenewHistory> queryListByCardId(int cardId) {
        String hql = "from MemberRenewHistory where memberCardId=" + cardId + " order by id desc";
        return this.list(hql);
    }
}
