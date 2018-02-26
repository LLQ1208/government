package com.acsm.training.service;

import com.acsm.training.model.Role;
import com.acsm.training.model.Role;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
public interface RoleEmployeeService {

    public void deleteByRoleId(int roleId);

    public Role queryRoleByEmployeeId(int id);
}
