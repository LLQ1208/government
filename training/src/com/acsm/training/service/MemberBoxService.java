package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;

public interface MemberBoxService {

    public List<MemberBox> queryListByMemberId(int memberId);

    public List<MemberBox> queryListByBoxId(int boxId);

    public MemberBox queryByBoxIdAndMemberId(int boxId, int memberId);

    public List<MemberBox> queryListExtByMemberId(int memberId);

    void update(MemberBox memberBox);

    public PageHelper<MemberBox> queryPageByConditions(Map<String, Object> conditions);

    MemberBox queryInfoByBoxIdAndMemberId(int boxId, int memberId);

    List<MemberBox> queryAll();
}
