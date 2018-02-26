package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Role;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.Supporter;
import com.acsm.training.model.Role;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.Supporter;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
public interface RoleEmployeeDao extends BaseDao<RoleEmployee> {
    public Role queryRoleByEmployeeId(int id);

    public List<Supporter> queryEmployeeByRoleId(int roleId);

    public void deleteByRoleId(int roleId);

    public List<RoleEmployee> querylistByRoleId(int id);

    public RoleEmployee queryByEmployeeId(Integer boxId);
}
