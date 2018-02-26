package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/13.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.acsm.training.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.WodRecordDao;
import com.acsm.training.model.User;
import com.acsm.training.model.WodRecord;

/**
 * @Author lianglinqiang
 * @create 2017-12-13
 */
@Repository
public class WodRecordDaoImpl extends BaseDaoImpl<WodRecord> implements WodRecordDao{
    @Override
    public List<WodRecord> queryRecordByWodId(int wodId,int orderType) {
        String hql = "select wr,u from WodRecord as wr,User as u where wr.userId=u.id " +
                "and wr.wodId=:wodId";
        if(orderType == 1){//倒叙
            hql += " order by wr.id desc";
        }else if(orderType == 2){
            hql += " order by wr.id asc";
        }
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setInteger("wodId", wodId);
        List list = q.list();
        List<WodRecord> wodRecordList = new ArrayList<WodRecord>();
        if(list != null && list.size()>=0){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] ob = (Object[])it.next();
                WodRecord wodRecord = (WodRecord) ob[0];
                User user = (User) ob[1];
                wodRecord.setOperatorUser(user);
                wodRecordList.add(wodRecord);
            }
        }
        return wodRecordList;
    }
}
