package com.acsm.training.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.RoleMenuDao;
import com.acsm.training.model.Menu;
import com.acsm.training.model.RoleMenu;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu> implements RoleMenuDao {
    @Override
    public List<Menu> queryMenuListByRoleId(int roleId) {
        String hql = "select m from RoleMenu as rm,Role as r,Menu as m where rm.roleId=r.id and rm.menuId=m.id and rm.roleId="+roleId;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<Menu> list = q.list();
        for(Menu menu:list){
        	menu.setSelected(1);
        }
        return list;
    }

    @Override
    public List<RoleMenu> queryListByRoleId(int roleId) {
        String hql = "from RoleMenu where roleId=?";
        return this.list(hql, roleId);
    }
}
