package com.acsm.training.service;

import com.acsm.training.model.Coach;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by baojie.yang on 2017/12/29.
 */
public interface EmployeeNewService {
    public void save(EmployeeNew employeeNew, RoleEmployeeMapping roleEmployeeMapping, User user);
    public void save(EmployeeNew employeeNew, RoleEmployeeMapping roleEmployeeMapping, User user, Coach coach);
    public EmployeeNew queryById(int id);
    public void update(EmployeeNew employeeNew);
    public void delete(int id);
    public void saveInfo(EmployeeNew employeeNew);
}
