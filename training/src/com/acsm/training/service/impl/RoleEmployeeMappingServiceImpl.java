package com.acsm.training.service.impl;

import com.acsm.training.dao.RoleEmployeeMappingDao;
import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.service.RoleEmployeeMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:23 2018/1/4
 */
@Service
public class RoleEmployeeMappingServiceImpl implements RoleEmployeeMappingService{
    @Resource
    RoleEmployeeMappingDao roleEmployeeMappingDao;

    @Override
    public RoleEmployeeMapping findById(Integer userId,Integer userType) {
        return roleEmployeeMappingDao.findById(userId,userType);
    }

    @Override
    public void save(RoleEmployeeMapping roleEmployeeMapping) {
        roleEmployeeMappingDao.add(roleEmployeeMapping);
    }

    @Override
    public RoleEmployeeMapping queryByBoxManage(Integer roleId) {
        return null;
    }
}
