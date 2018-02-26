package com.acsm.training.service;

import javax.servlet.http.HttpSession;

import com.acsm.training.model.User;
import com.acsm.training.model.User;
import com.acsm.training.vo.CommonResult;

/**
 * 
 * @author liuxiaobing
 */
public interface UserService {
	
	/**
	 * 登录验证
	 * @param userName 登录名
	 * @param password 密码
	 * @return
	 */
	public CommonResult login(HttpSession session, String userName, String password);
	
	/**
	 * @param userName
	 * @param password
	 * @return
	 */
	public User login(String userName, String password);
	
	/**
	 * 查询用户信息
	 * @return
	 */
	public User queryUserByRelatedIdAndUserType(int relatedId,int userType);
	
	/**
	 * @param id
	 * @return
	 */
	public User queryById(Integer id);
	
	/**
	 * 删除
	 * @param relatedId
	 * @param userType
	 */
	public void deleteByRelatedIdAndUserType(Integer relatedId, Integer userType);
	
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return
	 */
	public User queryByUserName(String userName);

	/**
	 * @param userName
	 * @param oldUserName
	 * @return
	 */
	public User queryByUserName(String userName, String oldUserName);

	/**
	 * 移动端手机登录
	 * @return
	 */
	public User loginForPhone(String phone, String password);

	/**
	 * 注册
	 * @param phone
	 * @param password
	 * @return
	 */
    public boolean addRegister(String phone, String password);

	/**
	 * 更新用户信息
	 * @param user
	 */
	void update(User user);

	void saveUser(User user);

	boolean queryPhoneIsExist(String phone);

	User queryPhone(String phone);
}

