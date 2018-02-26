package com.acsm.training.dao;

import com.acsm.training.model.Action;
import com.acsm.training.model.Action;

public interface ActionDao extends BaseDao<Action>{

    public Action queryById(int id);
}
