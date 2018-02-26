package com.acsm.training.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.acsm.training.util.DateUtil;
import com.acsm.training.util.DateUtil;

/**
 * @ClassName Notification
 * @Description TODO
 * @author xiaobing.liu
 * @date 2017年8月13日 下午7:33:16
 *
 */
@Entity
@Table(name="T_NOTIFICATION")
public class Notification implements Serializable{
	
	private static final long serialVersionUID = -849091887773444623L;
	private int id;
	private int boxId;//所属训练馆
	private String cover;//封面
	private String title;//标题
	private String content;//内容
	private int pushObject;//推送对象
	private int pushSex;//推送性别
	private Date pushTime;//推送时间
	private Date createTime;//创建时间
	private int heat;//阅读量

	private String pushTimeFormat;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	@Column(name="COVER",length=50)
	public String getCover() {
		return cover;
	}


	public void setCover(String cover) {
		this.cover = cover;
	}

	@Column(name="TITLE",length=100)
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	@Column(name="PUSH_OBJECT")
	public int getPushObject() {
		return pushObject;
	}


	public void setPushObject(int pushObject) {
		this.pushObject = pushObject;
	}

	@Column(name="PUSH_SEX")
	public int getPushSex() {
		return pushSex;
	}


	public void setPushSex(int pushSex) {
		this.pushSex = pushSex;
	}

	@Column(name="PUSH_TIME")
	public Date getPushTime() {
		return pushTime;
	}


	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}

	@Column(name="CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	@Column(name="HEAT")
	public int getHeat() {
		return heat;
	}


	public void setHeat(int heat) {
		this.heat = heat;
	}
	
	
	
	@Column(name="BOX_ID")
	public int getBoxId() {
		return boxId;
	}


	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}

	@Transient
	public String getPushTimeFormat() {
		return DateUtil.format(pushTime, "yyyy/MM/dd");
	}

	public void setPushTimeFormat(String pushTimeFormat) {
		this.pushTimeFormat = pushTimeFormat;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", boxId=" + boxId + ", cover="
				+ cover + ", title=" + title + ", content=" + content
				+ ", pushObject=" + pushObject + ", pushSex=" + pushSex
				+ ", pushTime=" + pushTime + ", createTime=" + createTime
				+ ", heat=" + heat + "]";
	}
	
}
