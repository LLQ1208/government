package com.acsm.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="T_MEMBER_RENEW_HISTORY")
public class MemberRenewHistory implements Serializable{

	private static final long serialVersionUID = -2717817225441643342L;
	
	private Integer id;
	private Integer memberId;
	private Date oldStartTime;
	private Date oldEndTime;
	private Date newStartTime;
	private Date newEndTime;
	private Integer oldMemberType;
	private Integer newMemberType;
	private Integer addNums;
	private long addDays;
	private Integer type;  // 2停卡  3开卡
	private Integer memberCardId;//会员卡id
	private Date stopCardTime;//停卡时间
	private Date openCardTime;//开卡时间
	private Date addDaysTime;//延长的操作时间
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="MEMBER_ID",length=11)
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@Column(name="OLD_START_TIME")
	public Date getOldStartTime() {
		return oldStartTime;
	}
	public void setOldStartTime(Date oldStartTime) {
		this.oldStartTime = oldStartTime;
	}
	
	@Column(name="OLD_END_TIME")
	public Date getOldEndTime() {
		return oldEndTime;
	}
	public void setOldEndTime(Date oldEndTime) {
		this.oldEndTime = oldEndTime;
	}
	
	@Column(name="NEW_START_TIME")
	public Date getNewStartTime() {
		return newStartTime;
	}
	public void setNewStartTime(Date newStartTime) {
		this.newStartTime = newStartTime;
	}
	
	@Column(name="NEW_END_TIME")
	public Date getNewEndTime() {
		return newEndTime;
	}
	public void setNewEndTime(Date newEndTime) {
		this.newEndTime = newEndTime;
	}
	
	@Column(name="OLD_MEMBER_TYPE")
	public Integer getOldMemberType() {
		return oldMemberType;
	}
	public void setOldMemberType(Integer oldMemberType) {
		this.oldMemberType = oldMemberType;
	}
	
	@Column(name="NEW_MEMBER_TYPE")
	public Integer getNewMemberType() {
		return newMemberType;
	}
	public void setNewMemberType(Integer newMemberType) {
		this.newMemberType = newMemberType;
	}
	
	@Column(name="ADD_NUMS")
	public Integer getAddNums() {
		return addNums;
	}
	public void setAddNums(Integer addNums) {
		this.addNums = addNums;
	}
	
	@Column(name="ADD_DAYS")
	public long getAddDays() {
		return addDays;
	}
	public void setAddDays(long addDays) {
		this.addDays = addDays;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="member_card_id")
	public Integer getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(Integer memberCardId) {
		this.memberCardId = memberCardId;
	}
	@Column(name="stop_card_time")
	public Date getStopCardTime() {
		return stopCardTime;
	}

	public void setStopCardTime(Date stopCardTime) {
		this.stopCardTime = stopCardTime;
	}
	@Column(name="open_card_time")
	public Date getOpenCardTime() {
		return openCardTime;
	}

	public void setOpenCardTime(Date openCardTime) {
		this.openCardTime = openCardTime;
	}
	@Column(name="add_days_time")
	public Date getAddDaysTime() {
		return addDaysTime;
	}

	public void setAddDaysTime(Date addDaysTime) {
		this.addDaysTime = addDaysTime;
	}
}
