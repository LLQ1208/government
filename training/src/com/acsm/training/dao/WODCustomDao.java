package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.dao.BaseDao;
import com.acsm.training.model.WodCustom;


/**
 * 
 * @author lusi
 *
 */
public interface WODCustomDao extends BaseDao<WodCustom> {
	

	/**
	 * 自定义wod列表
	 * @param memberId
	 * @return
	 */
	List<WodCustom> queryByMember(Integer memberId,Integer pageIndex,Integer pageSize);
	

}
