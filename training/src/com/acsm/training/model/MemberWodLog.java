package com.acsm.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员所属训练馆
 */
@Entity
@Table(name = "T_MEMBER_WOD_LOG")
public class MemberWodLog {

	private int id;
	private int memberId;
	private int wodId;
	private int boxId;
	private String pics;
	private Date date;

	@Id
	@GeneratedValue
	@Column(name = "ID", length = 11)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "MEMBER_ID", length = 11)
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@Column(name = "BOX_ID", length = 11)
	public int getBoxId() {
		return boxId;
	}

	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	@Column(name = "WOD_ID")
	public int getWodId() {
		return wodId;
	}

	public void setWodId(int wodId) {
		this.wodId = wodId;
	}

	@Column(name = "PICS")
	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	@Column(name = "DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
