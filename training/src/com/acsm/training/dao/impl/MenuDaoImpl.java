package com.acsm.training.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acsm.training.dao.MenuDao;
import com.acsm.training.model.Menu;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class MenuDaoImpl extends BaseDaoImpl<Menu> implements MenuDao{

    @Override
    public List<Menu> queryList() {
        String hql = "from Menu";
        return this.list(hql);
    }
}
