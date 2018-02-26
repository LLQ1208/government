package com.acsm.training.dao;

import com.acsm.training.model.Token;

public interface TokenDao extends BaseDao<Token> {

	/**
	 * @param tokenKey
	 * @return
	 */
	public Token queryByTokenKey(String tokenKey);
	
	/**
	 * @param userId
	 * @return
	 */
	public Token queryByUserId(Integer userId);
}
