package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.WodCustom;

/**
 * 
 * @author liuxiaobing
 */
public interface WODCustomService {
	
	/**
	 * 记录自定义个wod
	 * @param wodCustom
	 * @return
	 */
    public boolean add(WodCustom wodCustom);

    /**
     * 自定义个wod列表
     * @param memberId
     * @return
     */
	List<WodCustom> queryByMember(Integer memberId,Integer pageIndex,Integer pageSize);

	/**
	 * 更新
	 * @param wodCustom
	 * @return
	 */
	boolean update(WodCustom wodCustom);

	/**
	 * query one result by id
	 * @param id
	 * @return
	 */
	WodCustom queryById(Integer id);

}

