package com.acsm.training.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.CoachMenuDao;
import com.acsm.training.model.CoachMenu;
import com.acsm.training.model.Menu;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
@Repository
public class CoachMenuDaoImpl extends BaseDaoImpl<CoachMenu> implements CoachMenuDao{

    @Override
    public List<Menu> queryMenuListByBoxId(int boxId) {
        String hql = "select m from CoachMenu as cm,Menu as m where cm.menuId=m.id and cm.boxId="+boxId;
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        List<Menu> list = q.list();
        return list;
    }

    @Override
    public List<CoachMenu> queryListByBoxId(int boxId) {
        String hql = "from CoachMenu where boxId=?";
        return this.list(hql, boxId);
    }
}
