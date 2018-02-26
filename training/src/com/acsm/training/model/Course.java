package com.acsm.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_COURSE")
public class Course implements Serializable{

	private static final long serialVersionUID = -7318959123075010815L;
	
	private Integer id;//课程编号
	private Integer courseTypeId;//课程类型
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String courseLevel;//针对基础课
	private Integer week;//周几？1-7
	private Integer boxId;//所属训练馆
	private Integer peopleLimit;//课堂人数上限
	private Integer isDeleted;//是否删除
	private CourseType courseType;
	private Coach coach; //教练
	private Integer operation=1;//操作  1开始状态  2暂停状态
	private Date date;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="COURSE_TYPE_ID",length=11)
	public Integer getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}
	
	@Column(name="START_TIME")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Column(name="END_TIME")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Column(name="COURSE_LEVEL")
	public String getCourseLevel() {
		return courseLevel;
	}
	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}
	
	@Column(name="WEEK",length=2)
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
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

	@Column(name = "PEOPLE_LIMIT", length = 11)
	public Integer getPeopleLimit() {
		return peopleLimit;
	}

	public void setPeopleLimit(Integer peopleLimit) {
		this.peopleLimit = peopleLimit;
	}

	@Transient
	public CourseType getCourseType() {
		return courseType;
	}
	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}
	@ManyToOne
	@JoinColumn(name="COACH_ID")
	public Coach getCoach() {
		return coach;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	@Column(name="OPERATION")
	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	@Column(name="DATE")

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
