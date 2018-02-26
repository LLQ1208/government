package com.acsm.training.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.acsm.training.dao.TokenDao;
import com.acsm.training.model.Token;
import com.acsm.training.service.TokenService;
import com.acsm.training.util.DateUtil;

@Service("tokenService")
public class TokenServiceImpl implements TokenService {
	
	@Resource(name="tokenDao")
	private TokenDao tokenDao;

	@Override
	public Token queryByTokenKey(String tokenKey) {
		return tokenDao.queryByTokenKey(tokenKey);
	}

	@Override
	public Token queryByUserId(Integer userId) {
		return tokenDao.queryByUserId(userId);
	}

	@Override
	public Token save(Integer userId, String tokenKey) {
		Token token = new Token();
		token.setUserId(userId);
		token.setTokenKey(tokenKey);
		Date convertDate = DateUtil.convertDate(30);
		token.setExpireTime(DateUtil.DateToString(convertDate, "yyyy-MM-dd HH:mm:ss"));
		token.setCreateTime(new Date());
		return tokenDao.add(token);
	}

}
