package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.WodTopRelation;
import com.acsm.training.model.WodTopRelation;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 16:05 2017/12/2
 */
public interface WodTopRelationDao extends BaseDao<WodTopRelation>{
    public void save(WodTopRelation wodTopRelation );
    public List<WodTopRelation> queryListByWodId(int wodId);
	WodTopRelation queryListByWodAndBox(int wodId, int boxId);
    public WodTopRelation queryByCondition(int boxId,int courseTypeId, String date);
}
