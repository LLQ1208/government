package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.acsm.training.model.Course;
import com.acsm.training.model.MemberCard;
import com.acsm.training.model.SportResult;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodTopRelation;

/**
 * 公共的DAO处理对象，这个对象中包含了Hibernate的所有基本操作和对SQL的操作
 * @author Administrator
 *
 * @param <T>
 */

public interface BaseDao<T> {
	
	public Session getSession();
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(int id);
	
	public void merge(T t);
	
	public void saveOrUpdate(T t);
}

