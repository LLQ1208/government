package com.acsm.training.dao;

import java.util.Map;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Notification;
import com.acsm.training.model.basic.PageHelper;

/**
 * @ClassName NotificationDao
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月13日 下午7:42:40
 *
 */
public interface NotificationDao extends BaseDao<Notification>{
	
	public Notification queryById(int id);
	
	public PageHelper<Notification> queryPageByConditions(Map<String,Object> conditions);

}
