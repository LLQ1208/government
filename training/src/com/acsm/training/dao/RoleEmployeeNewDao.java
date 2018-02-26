package com.acsm.training.dao;

import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.RoleNew;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.RoleNew;
import com.acsm.training.model.RoleEmployeeMapping;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
public interface RoleEmployeeNewDao extends BaseDao<RoleEmployeeMapping> {
    public RoleNew queryRoleByEmployeeId(int id);

    public List<EmployeeNew> queryEmployeeByRoleId(int roleId);

    public void deleteByRoleId(int roleId);

    public List<RoleEmployeeMapping> querylistByRoleId(int id);

    public RoleEmployeeMapping queryByEmployeeId(Integer boxId);
}
