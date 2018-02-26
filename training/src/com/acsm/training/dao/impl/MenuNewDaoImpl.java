package com.acsm.training.dao.impl;

import com.acsm.training.dao.MenuNewDao;
import com.acsm.training.dao.MenuNewDao;
import com.acsm.training.model.MenuNew;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Repository
public class MenuNewDaoImpl extends BaseDaoImpl<MenuNew> implements MenuNewDao {

    @Override
    public List<MenuNew> queryList() {
        String hql = "from MenuNew ";
        return this.list(hql);
    }

    @Override
    public MenuNew queryById(int id) {
        String hql = "from MenuNew where id=?";
        return (MenuNew) this.Queryobject(hql,id);
    }

    @Override
    public List<MenuNew> queryListByParentId(int parentId) {
        String hql = "from MenuNew where parentId=?";
        return this.list(hql,parentId);
    }
}
