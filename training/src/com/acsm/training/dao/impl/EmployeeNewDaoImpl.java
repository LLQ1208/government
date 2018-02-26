package com.acsm.training.dao.impl;


import com.acsm.training.dao.EmployeeNewDao;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.dao.EmployeeNewDao;
import com.acsm.training.model.EmployeeNew;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeNewDaoImpl extends BaseDaoImpl<EmployeeNew> implements EmployeeNewDao {

    @Override
    public EmployeeNew queryById(int id) {
        String hql = "from EmployeeNew where id=?";
        return  (EmployeeNew)this.Queryobject(hql,id);
    }
}
