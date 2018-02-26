package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.Content;
import com.acsm.training.model.Content;

/**
 * Created by lq on 2017/12/2.
 */
public interface ContentDao extends BaseDao<Content>{
    List<Content> queryContentList();
    Content queryContentById(int id);

    List<Content> queryListOfBoss(int bossId);

    List<Content> queryListOfPopulrWod(Integer bossId,Integer boxId,Integer populrType);
}
