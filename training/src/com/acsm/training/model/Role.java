package com.acsm.training.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @ClassName Role
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月14日 下午9:48:02
 *
 */
@Entity
@Table(name="T_ROLE")
public class Role implements Serializable{
	
	private static final long serialVersionUID = 7959149086166514358L;
	private int id;
	private int boxId;
	private String name;

	private List<Menu> menus;
	
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
	public int getBoxId() {
		return boxId;
	}
	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
	
	@Column(name="NAME",length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", boxId=" + boxId + ", name=" + name + "]";
	}
	
}
