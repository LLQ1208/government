package com.acsm.training.model;

/**
 * 类名称：SecurityStatus.java
 * 描述：
 * 创建人： liuxiaobing
 * 创建日期：2017年2月11日 下午18:12:44
 */
public enum UserType {
	
	BOSS(1,"boss"),SUPPORTER(2,"supporter"),COACH(3,"coach"),MEMBER(4,"member");
	
	private final Integer code;
	private final String value;
	/**
	 * @param code
	 * @param value
	 */
	private UserType(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static String getValue(String code){
		for(UserType s:values()){
			if(s.getCode().equals(code)){
				return s.value;
			}
		}
		return null;
	}
	
	public static Integer getCode(Integer value){
		for(UserType s:values()){
			if(s.getValue().equals(value)){
				return s.code;
			}
		}
		return null;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getValue() {
		return value;
	}

}
