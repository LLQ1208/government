package com.acsm.training.service.impl;

import com.acsm.training.dao.*;
import com.acsm.training.model.*;
import com.acsm.training.service.RoleNewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by baojie.yang on 2017/12/29.
 */
@Service
public class RoleNewServiceImpl implements RoleNewService {

    @Resource
    private RoleNewMenuDao roleNewMenuDao;

    @Resource
    private RoleNewDao roleNewDao;
    
    @Resource
    private MenuNewDao menuNewDao;

    @Resource
    private RoleEmployeeNewDao roleEmployeeNewDao;


    @Resource
    private CoachDao coachDao;

    @Override
    public List<RoleNew> queryListByBoxId(int boxId) {
        List<RoleNew> roles = roleNewDao.queryListByBoxId(boxId);
        List<MenuNew> menuList = menuNewDao.queryList();
        for(RoleNew role:roles){
        	List<MenuNew> initMenus = initMenuList(menuList);
            Map<Integer, MenuNew> menuMap = convertMenuMap(initMenus);
            List<MenuNew> menus = roleNewMenuDao.queryMenuListByRoleId(role.getId());
            List<EmployeeNew> employeeNews=new ArrayList<>();
            if(role.getAllowDelete()==1){//教练
                List<Coach> listC = coachDao.queryListByBoxId(role.getBoxId());
                for(int i=0;i<listC.size();i++){
                    EmployeeNew employeeNew=new EmployeeNew();
                    Coach coach=listC.get(i);
                    employeeNew.setName(coach.getName());
                    employeeNew.setId(coach.getId());
                    employeeNew.setPhone(coach.getPhone());
                    employeeNew.setEmail(coach.getEmail());
                    employeeNews.add(employeeNew);
                }
            }else{
                employeeNews =roleEmployeeNewDao.queryEmployeeByRoleId(role.getId());
            }

            for(MenuNew menu:menus) {
                menuMap.put(menu.getId(), menu);
            }
            role.setMenus(convertMenuList(menuMap));
            role.setEmployees(employeeNews);
        }
        return roles;
    }

//    @Override
//    public Map<Integer, Menu> queryRoleMenuByRoleId(int roleId){
//        List<Menu> roleMenuList = roleMenuDao.queryMenuListByRoleId(roleId);
//        Map<Integer, Menu> menuMap = convertMenuMap(menuDao.queryList());
//        for(Menu menu:roleMenuList) {
//            menuMap.put(menu.getId(), menu);
//        }
//        return menuMap;
//    }
//
//
    @Override
    public RoleNew save(RoleNew role) {
        return roleNewDao.add(role);
    }

    @Override
    public RoleNew queryByName(String name,int boxId) {
        return roleNewDao.queryByName(name,boxId);
    }

    @Override
    public RoleNew queryById(int id) {
        return roleNewDao.queryById(id);
    }

    @Override
    public void update(RoleNew role) {
        roleNewDao.update(role);
    }

    private List<MenuNew> initMenuList(List<MenuNew> menuList){
    	List<MenuNew> menus = new ArrayList<MenuNew>();
    	for(MenuNew menu:menuList){
            MenuNew m = new MenuNew();
    		m.setId(menu.getId());
    		m.setName(menu.getName());
    		m.setSelected(0);
    		m.setUrl(menu.getUrl());
    		m.setParentId(menu.getParentId());
    		menus.add(m);
    	}
    	return menus;
    }

    @Override
    public Map<Integer,MenuNew> convertMenuMap(List<MenuNew> menuList){
        Map<Integer, MenuNew> map = new LinkedHashMap<Integer, MenuNew>();
        for(MenuNew menu:menuList){
            MenuNew m = new MenuNew();
        	m.setId(menu.getId());
        	m.setName(menu.getName());
        	m.setSelected(menu.getSelected());
        	m.setUrl(menu.getUrl());
        	m.setParentId(menu.getParentId());
            map.put(menu.getId(), m);
        }
        return map;
    }

    @Override
    public void deleteRole(int id) {
        roleNewDao.delete(id);//删除角色
        List<RoleMenuNew> roleMenus = roleNewMenuDao.queryListByRoleId(id);
        for(RoleMenuNew rm:roleMenus){
            roleNewMenuDao.delete(rm.getId());
        }
    }

    @Override
    public boolean updateMenu(int roleId, String menuIds) {
        try {
            List<RoleMenuNew> roleMenuList = roleNewMenuDao.queryListByRoleId(roleId);
            for (RoleMenuNew roleMenu : roleMenuList) {
                roleNewMenuDao.delete(roleMenu.getId());
            }
            if (StringUtils.isBlank(menuIds)) {
                return true;
            }
            String[] menuIdArray = menuIds.split(",");
            for (String menuId : menuIdArray) {
                MenuNew menuNew = menuNewDao.queryById(Integer.parseInt(menuId));
                if(menuNew.getParentId()==0){
                    List<MenuNew> listChild=menuNewDao.queryListByParentId(Integer.parseInt(menuId));
                    List<String> listC=new ArrayList<>();
                    for(int i=0;i<listChild.size();i++){
                        listC.add(String.valueOf(listChild.get(i).getId()));
                    }
                    List<String> listA = Arrays.asList(menuIdArray);
                    List<String> listB = new ArrayList<String>(listA);
                    boolean result = Collections.disjoint(listB,listC);
                    if(result){
                        continue;
                    }
                }
                RoleMenuNew rm = new RoleMenuNew();
                rm.setMenuId(Integer.parseInt(menuId));
                rm.setRoleId(roleId);
                roleNewMenuDao.add(rm);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private List<MenuNew> convertMenuList(Map<Integer, MenuNew> menuMap){
        List<MenuNew> menuList = new ArrayList<MenuNew>();
        for(Map.Entry<Integer, MenuNew> entry : menuMap.entrySet()){
            MenuNew menu = entry.getValue();
            menuList.add(menu);
        }
        return menuList;
    }
}
