package com.acsm.training.dao.impl;

import java.util.Date;

import com.acsm.training.util.DateUtil;
import org.springframework.stereotype.Repository;

import com.acsm.training.dao.TokenDao;
import com.acsm.training.model.Token;
import com.acsm.training.util.DateUtil;

@Repository("tokenDao")
public class TokenDaoImpl extends BaseDaoImpl<Token> implements TokenDao {

	@Override
	public Token queryByTokenKey(String tokenKey) {
		String hql = "from Token where tokenKey=? and expireTime>=?";
		Object[] obj = {tokenKey, DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")};
		return (Token)this.Queryobject(hql,obj);
	}

	@Override
	public Token queryByUserId(Integer userId) {
		String hql = "from Token where userId=? and expireTime>=?";
		Object[] obj = {userId, DateUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")};
		return (Token)this.Queryobject(hql,obj);
	}

}
