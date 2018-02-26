package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Menu;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
public interface CoachMenuService {

    public Map<Integer, Menu> queryMenuMapByBoxId(int boxId);
    
    public Map<Integer, Menu> queryCoachMenuMapByBoxId(int boxId);

    public List<Menu> queryMenuListByBoxId(int boxId);

    public boolean updateCoachMenu(int boxId, String menus);
}
