package com.acsm.training.dao.impl;/**
 * Created by lq on 2017/12/2.
 */

import java.util.List;

import com.acsm.training.dao.SectionDao;
import com.acsm.training.model.Section;
import com.acsm.training.util.Constans;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.SectionDao;
import com.acsm.training.model.Section;
import com.acsm.training.util.Constans;

/**
 * @Author lianglinqiang
 * @create 2017-12-02
 */
@Repository
public class SectionDaoImpl extends BaseDaoImpl<Section> implements SectionDao {
    @Override
    public List<Section> querySectionList() {
        String hql = "from Section";
        return this.list(hql);
    }

    @Override
    public Section querySectionById(int id) {
        String hql = "from Section where sectionId = ? and isDeleted=0";
        return (Section) this.Queryobject(hql, id);
    }

    @Override
    public boolean updateSection(Integer sectionId, String title) {
        StringBuffer hql = new StringBuffer();
        hql.append(" update t_section set title = '").append(title).append("'");
        hql.append(" where section_id = ").append(sectionId);
        try {
            this.updateBySql(hql.toString());
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<Section> queryListOfBoss(int bossId) {
        String hql = "from Section where (userId = ? or userId = "+ Constans.ADMIN_ID+") and isDeleted=0";
        return this.list(hql,bossId);
    }

    @Override
    public Section queryById(int id) {
        String hql = "from Section where sectionId = ?";
        return (Section) this.Queryobject(hql, id);
    }
}
