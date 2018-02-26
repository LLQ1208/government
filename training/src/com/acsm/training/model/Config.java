package com.acsm.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_CONFIG")
public class Config implements Serializable{

	private static final long serialVersionUID = 5504263822261502895L;
	
	private Integer id;
	private String configKey;
	private Integer configValue;
	private Integer boxId;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="CONFIG_KEY",length=36)
	public String getConfigKey() {
		return configKey;
	}
	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	
	@Column(name="CONFIG_VALUE",length=11)
	public Integer getConfigValue() {
		return configValue;
	}
	public void setConfigValue(Integer configValue) {
		this.configValue = configValue;
	}
	
	@Column(name="BOX_ID",length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	
	
	public Config() {
	}
	public Config(String configKey, Integer configValue, Integer boxId) {
		super();
		this.configKey = configKey;
		this.configValue = configValue;
		this.boxId = boxId;
	}
	
}
