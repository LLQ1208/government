package com.acsm.training.service;

import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.RoleEmployeeMapping;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:22 2018/1/4
 */
public interface RoleEmployeeMappingService {
    public RoleEmployeeMapping findById(Integer userId,Integer userType);
    public void save(RoleEmployeeMapping roleEmployeeMapping);
    public RoleEmployeeMapping queryByBoxManage(Integer roleId); //查询店长
}
