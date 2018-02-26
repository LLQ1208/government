package com.acsm.training.dao;

import java.util.List;

import com.acsm.training.model.User;
import com.acsm.training.model.User;


/**
 * 
 * @author liuxiaobing
 *
 */
public interface UserDao extends BaseDao<User> {
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public User queryUserByUserName(String userName);

	/**
	 * 根据会员编号查询用户信息
	 * @return
	 */
	public User queryUserByRelatedIdAndUserType(int relatedId,int userType);
	
	/**
	 * @param id
	 * @return
	 */
	public User queryById(Integer id);

	/**
	 * @param userName
	 * @param oldUserName
	 * @return
	 */
	public User queryUserByUserName(String userName, String oldUserName);

	/**
	 * 根据手机号查询用户
	 * @param phone
	 * @return
	 */
    public User queryUserByPhone(String phone);

	/**
	 * 查询手机号存不存在
	 */
	public List<User> queryUserPhone(String phone);
	
	public User queryUserOpenId(String openId);
}
