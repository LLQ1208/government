package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.model.Role;
import com.acsm.training.model.Supporter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.RoleEmployeeDao;
import com.acsm.training.model.Role;
import com.acsm.training.model.RoleEmployee;
import com.acsm.training.model.Supporter;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
@Repository
public class RoleEmplyeeDaoImpl extends BaseDaoImpl<RoleEmployee> implements RoleEmployeeDao{

    @Override
    public Role queryRoleByEmployeeId(int id) {
        String hql = "select r from RoleEmployee as re,Role as r,Supporter as e where re.roleId=r.id and re.employeeId=e.id and re.employeeId="+id;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<Role> list = q.list();
        if(list!=null) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Supporter> queryEmployeeByRoleId(int roleId) {
        String hql = "select e from RoleEmployee as re,Role as r,Supporter as e where re.roleId=r.id and re.employeeId=e.id and re.roleId="+roleId;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<Supporter> list = q.list();
        return list;
    }

    @Override
    public void deleteByRoleId(int roleId) {
        String hql = "delete RoleEmployee where roleId=?";
        this.Queryobject(hql, roleId);
    }

    @Override
    public List<RoleEmployee> querylistByRoleId(int id) {
        String hql = "from RoleEmployee where roleId=?";
        return this.list(hql, id);
    }

    @Override
    public RoleEmployee queryByEmployeeId(Integer employeeId) {
        String hql = "from RoleEmployee where employeeId=?";
        return (RoleEmployee) this.Queryobject(hql, employeeId);
    }
}
