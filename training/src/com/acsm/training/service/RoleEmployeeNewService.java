package com.acsm.training.service;

import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.Role;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
public interface RoleEmployeeNewService {
    public List<EmployeeNew> queryEmployeeByRoleId(int roleId);
}
