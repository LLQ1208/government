package com.acsm.training.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="T_BOX")
public class Box implements Serializable{

	private static final long serialVersionUID = 886121222873206645L;
	
	private Integer id;
	private Integer userId;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Integer status;//1.可用，0.无用
	private String logo;
	private String contact;//负责人
    private String contactTel;//负责人电话
	private Date createTime;
	private List<RoleNew> RoleNewList;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="NAME",length=36)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="PHONE",length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="EMAIL",length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="ADDRESS",length=255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="LOGO")
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Column(name="STATUS",length=2)
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "CONTACT", length = 30)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "CONTACT_TEL", length = 11)
	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	@Column(name = "USER_ID", length = 11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Transient
	public List<RoleNew> getRoleNewList() {
		return RoleNewList;
	}

	public void setRoleNewList(List<RoleNew> roleNewList) {
		RoleNewList = roleNewList;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Box [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", address=" + address + ", logo=" + logo + "]";
	}
}
