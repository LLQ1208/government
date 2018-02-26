package com.acsm.training.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.MenuDao;
import com.acsm.training.dao.RoleDao;
import com.acsm.training.dao.RoleEmployeeDao;
import com.acsm.training.dao.RoleMenuDao;
import com.acsm.training.dao.SupporterDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.enums.UserType;
import com.acsm.training.model.Menu;
import com.acsm.training.model.Role;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.RoleMenu;
import com.acsm.training.service.RoleService;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMenuDao roleMenuDao;

    @Resource
    private RoleDao roleDao;
    
    @Resource
    private MenuDao menuDao;

    @Resource
    private RoleEmployeeDao roleEmployeeDao;

    @Resource
    private SupporterDao supporterDao;

    @Resource
    private UserDao userDao;

    @Override
    public List<Role> queryListByBoxId(int boxId) {
        List<Role> roles = roleDao.queryListByBoxId(boxId);
        List<Menu> menuList = menuDao.queryList();
        for(Role role:roles){
        	List<Menu> initMenus = initMenuList(menuList);
            Map<Integer, Menu> menuMap = convertMenuMap(initMenus);
            List<Menu> menus = roleMenuDao.queryMenuListByRoleId(role.getId());
            for(Menu menu:menus) {
                menuMap.put(menu.getId(), menu);
            }
            role.setMenus(convertMenuList(menuMap));
        }
        return roles;
    }

    @Override
    public Map<Integer, Menu> queryRoleMenuByRoleId(int roleId){
        List<Menu> roleMenuList = roleMenuDao.queryMenuListByRoleId(roleId);
        Map<Integer, Menu> menuMap = convertMenuMap(menuDao.queryList());
        for(Menu menu:roleMenuList) {
            menuMap.put(menu.getId(), menu);
        }
        return menuMap;
    }


    @Override
    public Role save(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role queryById(int id) {
        return roleDao.queryById(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    private List<Menu> initMenuList(List<Menu> menuList){
    	List<Menu> menus = new ArrayList<Menu>();
    	for(Menu menu:menuList){
    		Menu m = new Menu();
    		m.setId(menu.getId());
    		m.setName(menu.getName());
    		m.setSelected(0);
    		m.setUrl(menu.getUrl());
    		menus.add(m);
    	}
    	return menus;
    }

    @Override
    public Map<Integer,Menu> convertMenuMap(List<Menu> menuList){
        Map<Integer, Menu> map = new LinkedHashMap<Integer, Menu>();
        for(Menu menu:menuList){
        	Menu m = new Menu();
        	m.setId(menu.getId());
        	m.setName(menu.getName());
        	m.setSelected(menu.getSelected());
        	m.setUrl(menu.getUrl());
            map.put(menu.getId(), m);
        }
        return map;
    }

    @Override
    public void deleteRole(int id) {
        roleDao.delete(id);//删除角色
        List<RoleEmployee> roleEmployee = roleEmployeeDao.querylistByRoleId(id);
        List<RoleMenu> roleMenus = roleMenuDao.queryListByRoleId(id);
        for(RoleEmployee re : roleEmployee) {
            roleEmployeeDao.delete(re.getId());//删除角色用户关联表
            supporterDao.delete(re.getEmployeeId());//删除员工表数据，
            User user = userDao.queryUserByRelatedIdAndUserType(re.getEmployeeId(), UserType.EMPLOYEE.CODE);
            userDao.delete(user.getId());//删除用户表数据
        }
        for(RoleMenu rm:roleMenus){
            roleMenuDao.delete(rm.getId());
        }
    }

    @Override
    public boolean updateMenu(int roleId, String menuIds) {
        try {
            List<RoleMenu> roleMenuList = roleMenuDao.queryListByRoleId(roleId);
            for (RoleMenu roleMenu : roleMenuList) {
                roleMenuDao.delete(roleMenu.getId());
            }
            if (StringUtils.isBlank(menuIds)) {
                return true;
            }
            String[] menuIdArray = menuIds.split(",");
            for (String menuId : menuIdArray) {
                RoleMenu rm = new RoleMenu();
                rm.setMenuId(Integer.parseInt(menuId));
                rm.setRoleId(roleId);
                roleMenuDao.add(rm);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private List<Menu> convertMenuList(Map<Integer, Menu> menuMap){
        List<Menu> menuList = new ArrayList<Menu>();
        for(Map.Entry<Integer, Menu> entry : menuMap.entrySet()){
            Menu menu = entry.getValue();
            menuList.add(menu);
        }
        return menuList;
    }
}
