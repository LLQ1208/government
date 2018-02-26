package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;

public interface MemberBoxDao extends BaseDao<MemberBox>{

    public List<MemberBox> queryListByMemberId(int memberId);

    public List<MemberBox> queryListByBoxId(int boxId);

    public MemberBox queryByBoxIdAndMemberId(int boxId, int memberId);

    public PageHelper<MemberBox> queryPageByConditions(Map<String, Object> conditions);

    MemberBox queryInfoByBoxIdAndMemberId(int boxId, int memberId);

    List<MemberBox> queryAll();
}
