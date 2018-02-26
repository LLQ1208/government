package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/2.
 */

import java.util.List;

import com.acsm.training.model.Section;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.ContentDao;
import com.acsm.training.model.Content;
import com.acsm.training.util.Constans;

import javax.management.Query;

/**
 * @Author lianglinqiang
 * @create 2017-12-02
 */
@Repository
public class ContentDaoImpl extends BaseDaoImpl<Content> implements ContentDao{
    @Override
    public List<Content> queryContentList() {
        String hql = "from Content where isDeleted=0 and contentType < 6";
        return this.list(hql);
    }

    @Override
    public Content queryContentById(int id) {
        String hql = "from Content where contentId=?";
        return (Content)this.Queryobject(hql,id);
    }

    @Override
    public List<Content> queryListOfBoss(int bossId) {
        String hql = "from Content where isDeleted=0 and (userId=? or userId= "+ Constans.ADMIN_ID +")";
        return this.list(hql,bossId);
    }

    @Override
    public List<Content> queryListOfPopulrWod(Integer bossId, Integer boxId,Integer populrType) {
        String hql = "select c from Content as c,SportResult as sr "+
                " where c.contentId=sr.contentId " +
                " and (c.userId=174 or c.userId=:bossId) "+
                " and c.isDeleted=0 " +
                " and c.contentType=5 " +
                " and c.famousWodRecordType=:populrType " +
                " and sr.boxId =:boxId " +
                " group by c.contentId ";
        Session session = this.getSession();
        org.hibernate.Query q = session.createQuery(hql);
        q.setInteger("bossId",bossId);
        q.setInteger("boxId",boxId);
        q.setInteger("populrType",populrType);
        return q.list();
    }
}
