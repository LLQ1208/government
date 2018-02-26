package com.acsm.training.dao.impl;

import com.acsm.training.dao.RoleNewMenuDao;
import com.acsm.training.model.MenuNew;
import com.acsm.training.model.RoleMenuNew;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class RoleNewMenuDaoImpl extends BaseDaoImpl<RoleMenuNew> implements RoleNewMenuDao {
    @Override
    public List<MenuNew> queryMenuListByRoleId(int roleId) {
        String hql = "select m from RoleMenuNew as rm,RoleNew as r,MenuNew as m where rm.roleId=r.id and rm.menuId=m.id and rm.roleId="+roleId;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<MenuNew> list = q.list();
        for(MenuNew menu:list){
        	menu.setSelected(1);
        }
        return list;
    }

    @Override
    public List<RoleMenuNew> queryListByRoleId(int roleId) {
        String hql = "from RoleMenuNew where roleId=?";
        return this.list(hql, roleId);
    }
}
