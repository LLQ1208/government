package com.acsm.training.service.impl;

import com.acsm.training.dao.CoachDao;
import com.acsm.training.dao.EmployeeNewDao;
import com.acsm.training.dao.RoleEmployeeMappingDao;
import com.acsm.training.dao.UserDao;
import com.acsm.training.model.Coach;
import com.acsm.training.model.EmployeeNew;
import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.User;
import com.acsm.training.service.EmployeeNewService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by baojie.yang on 2017/12/29.
 */
@Service
public class EmployeeNewServiceImpl implements EmployeeNewService {

    @Resource
    private EmployeeNewDao employeeNewDao;

    @Resource
    private UserDao userDao;

    @Resource
    private RoleEmployeeMappingDao roleEmployeeMappingDao;

    @Resource
    private CoachDao coachDao;


    @Override
    public void save(EmployeeNew employeeNew, RoleEmployeeMapping roleEmployeeMapping, User user) {
        EmployeeNew saveEmployee = employeeNewDao.add(employeeNew);
        user.setRelatedId(saveEmployee.getId());
        userDao.add(user);
        roleEmployeeMapping.setEmployeeId(saveEmployee.getId());
        roleEmployeeMapping.setUserType(user.getUserType());
        roleEmployeeMappingDao.add(roleEmployeeMapping);
    }

    @Override
    public void save(EmployeeNew employeeNew, RoleEmployeeMapping roleEmployeeMapping, User user, Coach coach) {
        Coach saveCoach = coachDao.add(coach);
        user.setRelatedId(saveCoach.getId());
        roleEmployeeMapping.setEmployeeId(coach.getId());
        roleEmployeeMapping.setUserType(user.getUserType());
        roleEmployeeMappingDao.add(roleEmployeeMapping);
        userDao.add(user);
    }

    @Override
    public EmployeeNew queryById(int id) {
        return employeeNewDao.queryById(id);
    }

    @Override
    public void update(EmployeeNew employeeNew) {
        employeeNewDao.update(employeeNew);
    }

    @Override
    public void delete(int id) {
        employeeNewDao.delete(id);
    }

    @Override
    public void saveInfo(EmployeeNew employeeNew) {
        employeeNewDao.add(employeeNew);
    }
}
