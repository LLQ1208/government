package com.acsm.training.dao.impl;


import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.dao.EmployeeNewDao;
import com.acsm.training.dao.RoleEmployeeMappingDao;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.RoleEmployeeMapping;
import org.springframework.stereotype.Repository;


@Repository
public class RoleEmployeeMappingDaoImpl extends BaseDaoImpl<RoleEmployeeMapping> implements RoleEmployeeMappingDao {

    @Override
    public RoleEmployeeMapping findById(Integer userId,Integer userType) {
        String hql = "from RoleEmployeeMapping where employeeId=? ";
        if(userType == 3 || userType == 5){
            hql += "and userType = "+userType;
        }
        return (RoleEmployeeMapping)this.Queryobject(hql,userId);
    }

    @Override
    public RoleEmployeeMapping queryByBoxManage(Integer roleId) {
        String hql = "from RoleEmployeeMapping where roleId=? ";
        return (RoleEmployeeMapping)this.Queryobject(hql,roleId);
    }
}
