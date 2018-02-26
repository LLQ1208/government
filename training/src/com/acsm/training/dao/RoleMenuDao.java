package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.RoleMenu;
import com.acsm.training.model.Menu;
import com.acsm.training.model.RoleMenu;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
public interface RoleMenuDao extends BaseDao<RoleMenu>{

    public List<Menu> queryMenuListByRoleId(int roleId);

    public List<RoleMenu> queryListByRoleId(int roleId);
}
