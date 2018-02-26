package com.acsm.training.service;

import com.acsm.training.model.RoleNew;
import com.acsm.training.model.MenuNew;
import com.acsm.training.model.RoleNew;

import java.util.List;
import java.util.Map;

/**
 * Created by baojie.yang on 2017/12/29.
 */
public interface RoleNewService {

    public List<RoleNew> queryListByBoxId(int boxId);

    public RoleNew save(RoleNew role);

    public RoleNew queryByName(String name, int boxId);

    public RoleNew queryById(int id);

    public void update(RoleNew role);
//
//    public Map<Integer, Menu> queryRoleMenuByRoleId(int roleId);
//
    public Map<Integer,MenuNew> convertMenuMap(List<MenuNew> menuList);
//
    public void deleteRole(int id);
//
    public boolean updateMenu(int roleId, String menuIds);
}
