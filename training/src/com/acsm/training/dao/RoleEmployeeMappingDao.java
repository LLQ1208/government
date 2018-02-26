package com.acsm.training.dao;

import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.RoleEmployeeMapping;


public interface RoleEmployeeMappingDao extends BaseDao<RoleEmployeeMapping>{
    RoleEmployeeMapping findById(Integer userId,Integer userType);
    RoleEmployeeMapping queryByBoxManage(Integer roleId); //店长
}
