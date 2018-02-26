package com.acsm.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户实体
 * @author vicent
 *
 */
@Entity
@Table(name="T_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = -6081835273155771877L;
	
	private Integer id;//编号
	private String userName;//用户名
	private String password;//密码
	private Integer userType;//用户类型，1.老板、2.客服，3.教练，4.会员
	private Integer relatedId;//关联主键
	private Integer boxId;//所属训练馆
	private Integer isDeleted;
	private String phone;//电话
	private String openId;//openid
	private String ico; //老板用户的头像
	private String email;//老板用户的email
	private Box box;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="BOX_ID",length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	
	@Column(name="USER_NAME",length=36)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="PASSWORD",length=36)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="USER_TYPE",length=2)
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@Column(name="RELATED_ID",length=11)
	public Integer getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(Integer relatedId) {
		this.relatedId = relatedId;
	}

	@Column(name="IS_DELETED",length=2)
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Transient
	public Box getBox() {
		return box;
	}
	public void setBox(Box box) {
		this.box = box;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", userType=" + userType + ", relatedId=" + relatedId + ", boxId=" + boxId + "]";
	}
	@Column(name="PHONE",length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name="OPEN_ID")
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name="ICO")
	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
