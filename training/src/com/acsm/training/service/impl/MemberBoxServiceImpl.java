package com.acsm.training.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.dao.BoxDao;
import com.acsm.training.model.MemberBox;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.MemberBoxService;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.MemberBoxDao;
import com.acsm.training.dao.MemberDao;
import com.acsm.training.model.Box;

@Service
public class MemberBoxServiceImpl implements MemberBoxService {

    @Resource
    private MemberBoxDao memberBoxDao;

    @Resource
    private BoxDao boxDao;

    @Resource
    private MemberDao memberDao;

    @Override
    public List<MemberBox> queryListByMemberId(int memberId) {
        return memberBoxDao.queryListByMemberId(memberId);
    }

    @Override
    public List<MemberBox> queryListByBoxId(int boxId) {
        return memberBoxDao.queryListByBoxId(boxId);
    }

    @Override
    public MemberBox queryByBoxIdAndMemberId(int boxId, int memberId) {
        return memberBoxDao.queryByBoxIdAndMemberId(boxId, memberId);
    }

    @Override
    public List<MemberBox> queryListExtByMemberId(int memberId) {
        List<MemberBox> memberBoxes = memberBoxDao.queryListByMemberId(memberId);
        for(MemberBox memberBox:memberBoxes){
            Box box = boxDao.queryById(memberBox.getBoxId());
            memberBox.setBox(box);
        }
        return memberBoxes;
    }

    @Override
    public void update(MemberBox memberBox) {
        memberBoxDao.update(memberBox);
    }

    @Override
    public PageHelper<MemberBox> queryPageByConditions(Map<String, Object> conditions){
        return memberBoxDao.queryPageByConditions(conditions);
    }

    @Override
    public MemberBox queryInfoByBoxIdAndMemberId(int boxId, int memberId) {
        return memberBoxDao.queryInfoByBoxIdAndMemberId(boxId, memberId);
    }

    @Override
    public List<MemberBox> queryAll() {
        return memberBoxDao.queryAll();
    }
}
