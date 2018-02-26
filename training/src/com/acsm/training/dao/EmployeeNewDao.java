package com.acsm.training.dao;

import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.EmployeeNew;


public interface EmployeeNewDao extends BaseDao<EmployeeNew>{
    public EmployeeNew queryById(int id);
}
