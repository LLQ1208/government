package com.acsm.training.service;

import java.util.Map;

import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.Notification;
import com.acsm.training.model.basic.PageHelper;

/**
 * @ClassName NotificationService
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月13日 下午7:56:36
 *
 */
public interface NotificationService {
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public Notification queryById(Integer id);

	/**
	 * 根据查询条件获取推送分页
	 * @param conditions
	 * @return
	 */
	public PageHelper<Notification> queryPageByConditions(Map<String,Object> conditions);

	/**
	 * 添加推送信息
	 * @param notification
	 */
	public void save(Notification notification);

}
