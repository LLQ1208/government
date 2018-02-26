package com.acsm.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.acsm.training.util.DateUtil;

/**
 * 会员所属训练馆
 */
@Entity
@Table(name = "T_MEMBER_CONTENT_PR")
public class MemberContentPR {

	private int id;
	private int memberId;
	private int sex;
	private int boxId;
	private int contentId;
	private float pr;
	private Date updateTime;

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

	@Column(name = "SEX")
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(name = "CONTENT_ID")
	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	@Column(name = "PR")
	public float getPr() {
		return pr;
	}

	public void setPr(float pr) {
		this.pr = pr;
	}

	@Column(name = "UPDATE_TIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
