package com.acsm.training.service;

import com.acsm.training.model.Token;

public interface TokenService {
	
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

	/**
	 * @param id
	 * @param tokenKey
	 */
	public Token save(Integer id, String tokenKey);

}
