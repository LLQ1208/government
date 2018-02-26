package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Menu;
import com.acsm.training.model.Menu;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
public interface MenuDao extends BaseDao<Menu>{

    public List<Menu> queryList();

}
