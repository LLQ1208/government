package com.acsm.training.dao.impl;

import com.acsm.training.model.EmployeeNew;
import com.acsm.training.dao.RoleEmployeeNewDao;
import com.acsm.training.model.RoleEmployeeMapping;
import com.acsm.training.model.RoleNew;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
@Repository
public class RoleEmplyeeDaoNewImpl extends BaseDaoImpl<RoleEmployeeMapping> implements RoleEmployeeNewDao {

    @Override
    public RoleNew queryRoleByEmployeeId(int id) {
        String hql = "select r from RoleEmployeeMapping as re,RoleNew as r,EmployeeNew as e where re.roleId=r.id and re.employeeId=e.id and re.employeeId="+id;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<RoleNew> list = q.list();
        if(list!=null) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<EmployeeNew> queryEmployeeByRoleId(int roleId) {
        String hql = "select e from RoleEmployeeMapping as re,RoleNew as r,EmployeeNew as e where re.roleId=r.id and re.employeeId=e.id and re.roleId="+roleId;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<EmployeeNew> list = q.list();
        return list;
    }

    @Override
    public void deleteByRoleId(int roleId) {
        String hql = "delete RoleEmployeeMapping where roleId=?";
        this.Queryobject(hql, roleId);
    }

    @Override
    public List<RoleEmployeeMapping> querylistByRoleId(int id) {
        String hql = "from RoleEmployeeMapping where roleId=?";
        return this.list(hql, id);
    }

    @Override
    public RoleEmployeeMapping queryByEmployeeId(Integer employeeId) {
        String hql = "from RoleEmployeeMapping where employeeId=?";
        return (RoleEmployeeMapping) this.Queryobject(hql, employeeId);
    }
}
