package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.CoachMenu;
import com.acsm.training.model.CoachMenu;
import com.acsm.training.model.Menu;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
public interface CoachMenuDao extends BaseDao<CoachMenu>{

    public List<Menu> queryMenuListByBoxId(int coachId);

    public List<CoachMenu> queryListByBoxId(int coachId);

}
