package com.acsm.training.dao;

import com.acsm.training.model.MenuNew;
import com.acsm.training.model.RoleMenu;
import com.acsm.training.model.RoleMenuNew;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
public interface RoleNewMenuDao extends BaseDao<RoleMenuNew>{

    public List<MenuNew> queryMenuListByRoleId(int roleId);

    public List<RoleMenuNew> queryListByRoleId(int roleId);
}
