package com.acsm.training.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acsm.training.dao.RoleDao;
import com.acsm.training.model.Role;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public List<Role> queryListByBoxId(int boxId) {
        String hql = "from Role where boxId=?";
        return this.list(hql, boxId);
    }

    @Override
    public Role queryById(int id) {
        String hql = "from Role where id=?";
        return (Role) this.Queryobject(hql,id);
    }
}
