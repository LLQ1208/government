package com.acsm.training.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import com.acsm.training.dao.NotificationDao;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.service.NotificationService;
import org.springframework.stereotype.Service;

import com.acsm.training.model.Notification;

/**
 * @ClassName NotificationServiceImpl
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月13日 下午7:58:31
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Resource
	private NotificationDao notificationDao;

	/* (non-Javadoc)
	 * @see NotificationService#queryById(java.lang.Integer)
	 */
	@Override
	public Notification queryById(Integer id) {
		return notificationDao.queryById(id);
	}

	/* (non-Javadoc)
	 * @see NotificationService#queryPageByConditions(java.util.Map)
	 */
	@Override
	public PageHelper<Notification> queryPageByConditions(
			Map<String, Object> conditions) {
		return notificationDao.queryPageByConditions(conditions);
	}

	/* (non-Javadoc)
	 * @see NotificationService#save(Notification)
	 */
	@Override
	public void save(Notification notification) {
		notificationDao.add(notification);
	}

}
