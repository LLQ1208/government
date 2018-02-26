package com.acsm.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="T_COACH_COURSE_ORDER")
public class CoachCourseOrder implements Serializable{

	private static final long serialVersionUID = -7892936894899352417L;
	
	private Integer id;
	private Integer memberId;
	private Integer coachCourseId;//课程编号
	private Date date;//上课日期
	private Integer isDeleted;
	private Integer signStatus;//签到状态


	private Member member;
	private Course course;

	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="MEMBER_ID")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="COACH_COURSE_ID")
	public Integer getCoachCourseId() {
		return coachCourseId;
	}
	public void setCoachCourseId(Integer coachCourseId) {
		this.coachCourseId = coachCourseId;
	}
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="IS_DELETED")
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Transient
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@Transient
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "SIGN_STATUS", length = 2)
	public Integer getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}
}
