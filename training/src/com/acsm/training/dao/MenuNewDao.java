package com.acsm.training.dao;

import com.acsm.training.model.MenuNew;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
public interface MenuNewDao extends BaseDao<MenuNew>{

    public List<MenuNew> queryList();

    public MenuNew queryById(int id);

    public List<MenuNew> queryListByParentId(int parentId);
}
