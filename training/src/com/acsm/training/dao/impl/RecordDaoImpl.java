package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.dao.RecordDao;
import com.acsm.training.model.basic.PageHelper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.RecordDao;
import com.acsm.training.model.Record;
import com.acsm.training.model.basic.PageHelper;

@Repository
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {

    @Override
    public List<Record> queryListByUserId(int userId) {
        String hql = "from Record where userId=? order by createTime desc";
        return this.list(hql, userId);
    }

    @Override
    public PageHelper<Record> queryListByUserId(int userId, int pageIndex, int pageSize) {
        String hql = "from Record where userId="+userId+" order by createTime desc";
        String hqlCount = "select count(*) from Record where userId="+userId;
        Session session = getSession();
        Query q = session.createQuery(hql);
        Query qCount = session.createQuery(hqlCount);
        //获取数据总条数
        int total = Integer.parseInt(qCount.uniqueResult().toString());
        q.setFirstResult((pageIndex-1) * pageSize);
        q.setMaxResults(pageSize);
        List<Record> list = q.list();
        //数据列表
        PageHelper<Record> page = new PageHelper<>();
        page.setList(list);
        page.setTotalRow(total);
        return page;
    }

    @Override
    public Record queryById(int recordId) {
        String hql = "from Record where id=?";
        return (Record) this.Queryobject(hql, recordId);
    }
}
