package com.acsm.training.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.acsm.training.util.StringUtils;

@Entity
@Table(name="T_COURSE_TYPE")
public class CourseType implements Serializable{
	
	private static final long serialVersionUID = 1654246216702829666L;
	
	private Integer id;
	private String name;
	private Integer userId;
	private Integer hasLevel;
	private String level;
	private Integer boxId;
	private Integer isDeleted;
	private List<String> levelList;
	private int selected = 0;
	private Date insertTime;
	private String describe;
	private String color;

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
	
	@Column(name="BOX_ID",length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}
	
	@Column(name="IS_DELETED",length=2)
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name="HAS_LEVEL",length=2)
	public Integer getHasLevel() {
		return hasLevel;
	}
	public void setHasLevel(Integer hasLevel) {
		this.hasLevel = hasLevel;
	}
	
	@Column(name="LEVEL",length=255)
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	@Transient
	public List<String> getLevelList() {
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotEmpty(this.level)){
			JSONArray levelArr = JSON.parseArray(this.level);
			for(int i=0; i<levelArr.size(); i++){
				String levelName = levelArr.getString(i);
				list.add(levelName);
			}
		}
		return list;
	}
	public void setLevelList(List<String> levelList) {
		this.levelList = levelList;
	}

	@Transient
	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
	@Column(name="USER_ID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	@Column(name="INSERT_TIME")
	public Date getInsertTime() {
		return insertTime;
	}

	@Column(name="DESCRIBES")
	public String getDescribe() {
		return describe;
	}

	@Column(name="COLOR")
	public String getColor() {
		return color;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "CourseType [id=" + id + ", name=" + name + ", hasLevel=" + hasLevel + ", level=" + level + ", boxId=" + boxId + ", isDeleted=" + isDeleted + ", levelList=" + levelList + "]";
	}
}
