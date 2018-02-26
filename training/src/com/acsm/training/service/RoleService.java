package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Role;
import com.acsm.training.model.Menu;
import com.acsm.training.model.Role;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
public interface RoleService {

    public List<Role> queryListByBoxId(int boxId);

    public Role save(Role role);

    public Role queryById(int id);

    public void update(Role role);

    public Map<Integer, Menu> queryRoleMenuByRoleId(int roleId);

    public Map<Integer,Menu> convertMenuMap(List<Menu> menuList);

    public void deleteRole(int id);

    public boolean updateMenu(int roleId, String menuIds);

}
