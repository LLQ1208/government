package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.Wod;

/**
 * Created by lq on 2017/11/30.
 */
public interface WodDao extends BaseDao<Wod>{
    List<Wod> queryWodList(Map map);
    Wod queryWodById(int id);

    Wod queryWodByDateAndType(Integer boxId, Integer courseTypeId, String date);

	List<Wod> queryWodByDate(Integer boxId, String date);
}
