package com.acsm.training.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.acsm.training.dao.CoachMenuDao;
import com.acsm.training.dao.MenuDao;
import com.acsm.training.model.CoachMenu;
import com.acsm.training.model.Menu;
import com.acsm.training.service.CoachMenuService;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
@Service
public class CoachMenuServiceImpl implements CoachMenuService {

    @Resource
    private CoachMenuDao coachMenuDao;

    @Resource
    private MenuDao meanDao;

    @Override
    public Map<Integer, Menu> queryMenuMapByBoxId(int boxId) {
        List<Menu> menus = coachMenuDao.queryMenuListByBoxId(boxId);
        Map<Integer, Menu> menuMap = convertMenuMap(meanDao.queryList());
        for(Menu menu:menus){
            menu.setSelected(1);
            menuMap.put(menu.getId(), menu);
        }
        return menuMap;
    }

    @Override
    public Map<Integer, Menu> queryCoachMenuMapByBoxId(int boxId) {
        List<Menu> menus = coachMenuDao.queryMenuListByBoxId(boxId);
        Map<Integer, Menu> menuMap = convertMenuMap(menus);
        return menuMap;
    }

    @Override
    public List<Menu> queryMenuListByBoxId(int boxId) {
        Map<Integer, Menu> menuMap = queryMenuMapByBoxId(boxId);
        List<Menu> menus = convertMenuList(menuMap);
        return menus;
    }

    @Override
    public boolean updateCoachMenu(int boxId, String menus) {
        try {
            List<CoachMenu> coachMenus = coachMenuDao.queryListByBoxId(boxId);
            for (CoachMenu cm : coachMenus) {
                coachMenuDao.delete(cm.getId());//删除
            }
            if (StringUtils.isBlank(menus)) {
                return true;
            }
            String[] menuArray = menus.split(",");
            for (String menuId : menuArray) {
                CoachMenu cm = new CoachMenu();
                cm.setMenuId(Integer.parseInt(menuId));
                cm.setBoxId(boxId);
                coachMenuDao.add(cm);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Map<Integer,Menu> convertMenuMap(List<Menu> menuList){
        Map<Integer, Menu> map = new LinkedHashMap<Integer, Menu>();
        for(Menu menu:menuList){
            Menu m = new Menu();
            m.setId(menu.getId());
            m.setName(menu.getName());
            m.setSelected(0);
            m.setUrl(menu.getUrl());
            map.put(menu.getId(), m);
        }
        return map;
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
