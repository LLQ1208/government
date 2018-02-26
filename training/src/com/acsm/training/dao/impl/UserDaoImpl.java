package com.acsm.training.dao.impl;

import java.util.List;

import com.acsm.training.model.User;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.UserDao;
import com.acsm.training.model.User;


@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {


	@Override
	public User queryUserByUserName(String userName) {
		String hql ="from User where userName=? and isDeleted=0";
        return (User)this.Queryobject(hql,userName);
	}

	@Override
	public User queryUserByRelatedIdAndUserType(int relatedId,int userType) {
		String hql ="from User where relatedId=? and userType=?";
		Object[] obj = {relatedId,userType};
        return (User)this.Queryobject(hql,obj);
	}

	@Override
	public User queryById(Integer id) {
		String hql = "from User where id=?";
		return (User) this.Queryobject(hql, id);
	}

	@Override
	public User queryUserByUserName(String userName, String oldUserName) {
		String hql ="from User where userName=? and userName!=? and isDeleted=0";
		Object[] obj = {userName, oldUserName};
        return (User) this.Queryobject(hql, obj);
	}

	@Override
	public User queryUserByPhone(String phone) {
		String hql = "from User where userName=? and isDeleted=0";
		return (User) this.Queryobject(hql, phone);
	}

	@Override
	public List<User> queryUserPhone(String phone) {
		String hql = "from User where phone=? and isDeleted=0";
		return this.list(hql,phone);
	}

	@Override
	public User queryUserOpenId(String openId) {
		String hql = "from User where open_id=? and isDeleted=0";
		return (User) this.Queryobject(hql, openId);
	}
}
