package com.acsm.training.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Role
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月14日 下午9:48:02
 *
 */
@Entity
@Table(name="T_ROLE_NEW")
public class RoleNew implements Serializable{
	
	private static final long serialVersionUID = 7959149086166514358L;
	private int id;
	private Integer boxId;
	private String name;
	private int allowDelete;

	private List<MenuNew> menus;

	private List<EmployeeNew> employees;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="BOX_ID", length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	
	@Column(name="NAME",length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="ALLOW_DELETE",length=11)
	public int getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(int allowDelete) {
		this.allowDelete = allowDelete;
	}

	@Transient
	public List<MenuNew> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuNew> menus) {
		this.menus = menus;
	}

	@Transient
	public List<EmployeeNew> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeNew> employees) {
		this.employees = employees;
	}



	@Override
	public String toString() {
		return "Role [id=" + id + ", boxId=" + boxId + ", name=" + name + ", allowDelete=" + allowDelete + "]";
	}
	
}
