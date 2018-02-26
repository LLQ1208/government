package com.acsm.training.dao;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.basic.PageHelper;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 22:06 2017/12/3
 */
public interface WodActionModelDao extends BaseDao<Object[]>{
    public PageHelper querySectionListChange(Integer userId, Integer searchType, Integer pageSize, Integer pageIndex,String keyword);
    public PageHelper queryContentListChange(Integer userId, Integer searchType,Integer pageSize,Integer pageIndex,Integer contentType,String keyword);
}
