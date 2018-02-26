package com.acsm.training.dao.impl;

import com.acsm.training.model.Action;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.ActionDao;
import com.acsm.training.model.Action;

@Repository
public class ActionDaoImpl extends BaseDaoImpl<Action> implements ActionDao {

    @Override
    public Action queryById(int id) {
        String hql = "from Action where id=?";
        return (Action)this.Queryobject(hql,id);
    }
}
