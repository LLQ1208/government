package com.acsm.training.dao.impl;

import com.acsm.training.dao.RoleNewDao;
import com.acsm.training.model.RoleNew;
import com.acsm.training.dao.RoleNewDao;
import com.acsm.training.model.RoleNew;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class RoleNewDaoImpl extends BaseDaoImpl<RoleNew> implements RoleNewDao {

    @Override
    public List<RoleNew> queryListByBoxId(int boxId) {
        String hql = "from RoleNew where boxId=?";
        return this.list(hql, boxId);
    }

    @Override
    public RoleNew queryById(int id) {
        String hql = "from RoleNew where id=?";
        return (RoleNew) this.Queryobject(hql,id);
    }

    @Override
    public RoleNew queryByName(String name,int boxId) {
        String hql = "from RoleNew where name=:name AND boxId=:boxId";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setString("name", name);
        q.setInteger("boxId", boxId);
        List list = q.list();
        if(list!=null&&list.size()>0){
            return (RoleNew) list.get(0);
        }
        return null;
    }

}
