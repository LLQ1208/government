package com.acsm.training.service.impl;

import javax.annotation.Resource;

import com.acsm.training.model.Action;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.ActionDao;
import com.acsm.training.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{

    @Resource
    private ActionDao actionDao;

    @Override
    public Action queryById(int id) {
        return actionDao.queryById(id);
    }
}
