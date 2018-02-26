package com.acsm.training.dao;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.MemberCardTemplate;
import com.acsm.training.model.basic.PageHelper;

import java.util.List;

/**
 * Created by lq on 2018/1/18.
 */
public interface MemberCardTemplateDao extends BaseDao<MemberCardTemplate>{

    MemberCardTemplate queryById(int id);

    PageHelper queryByPage(int bossId,int pageSize,int pageIndex);

    List<Object[]> queryList(int bossId);

    List<MemberCardTemplate> queryListByName(String name, Integer userId,Integer templateId);
}
