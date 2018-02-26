package com.acsm.training.model;


import org.springframework.http.HttpStatus;

public class TokenResult {
	
	private Token token;
	
	private HttpStatus httpStatus;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
