package com.acsm.training.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsm.training.dao.RoleEmployeeDao;
import com.acsm.training.model.Role;
import com.acsm.training.service.RoleEmployeeService;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
@Service
public class RoleEmployeeServiceImpl implements RoleEmployeeService{
    @Resource
    private RoleEmployeeDao roleEmployeeDao;

    @Override
    public void deleteByRoleId(int roleId) {
        roleEmployeeDao.deleteByRoleId(roleId);
    }

    @Override
    public Role queryRoleByEmployeeId(int id) {
        return roleEmployeeDao.queryRoleByEmployeeId(id);
    }
}
