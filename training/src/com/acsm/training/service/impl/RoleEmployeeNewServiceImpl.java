package com.acsm.training.service.impl;

import com.acsm.training.dao.RoleEmployeeNewDao;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.service.RoleEmployeeNewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by baojie.yang on 2017/12/29.
 */
@Service
public class RoleEmployeeNewServiceImpl implements RoleEmployeeNewService {

    @Resource
    private RoleEmployeeNewDao roleEmployeeNewDao;


    @Override
    public List<EmployeeNew> queryEmployeeByRoleId(int roleId) {
        return roleEmployeeNewDao.queryEmployeeByRoleId(roleId);
    }
}
