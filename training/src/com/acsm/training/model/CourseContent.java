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

import com.acsm.training.util.DateUtil;
import com.acsm.training.vo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.util.DateUtil;
import com.acsm.training.vo.Content;

@Entity
@Table(name="T_COURSE_CONTENT")
public class CourseContent implements Serializable{

	private static final long serialVersionUID = -6926892969966173727L;
	
	private Integer id;//编号
	private Date planDate;//计划日期
	private Date openTime;//公布时间
	private String content;//课程安排
	private Integer coachId;//教练编号
	private Integer boxId;//所属训练馆
	private Integer courseTypeId;//课程类型

	private List<com.acsm.training.vo.Content> contentList;
	private String openTimeStr;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="PLAN_DATE")
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	@Column(name="OPEN_TIME")
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="COACH_ID",length=11)
	public Integer getCoachId() {
		return coachId;
	}
	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}
	
	@Column(name="BOX_ID",length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	@Column(name = "COURSE_TYPE_ID", length = 11)
	public Integer getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(Integer courseTypeId) {
		this.courseTypeId = courseTypeId;
	}

	@Transient
	public List<com.acsm.training.vo.Content> getContentList() {
		JSONArray contentArr = JSON.parseArray(this.content);
		List<com.acsm.training.vo.Content> list = new ArrayList<com.acsm.training.vo.Content>();
		for(int i=0;i<contentArr.size();i++){
			JSONObject contentJson = contentArr.getJSONObject(i);
			String title = contentJson.getString("title");
			String detail = contentJson.getString("detail");
			String remark = contentJson.getString("remark");
			Integer time = contentJson.getInteger("time");
			com.acsm.training.vo.Content content = new com.acsm.training.vo.Content(title, detail, remark, time);
			list.add(content);
		}
		return list;
	}
	public void setContentList(List<com.acsm.training.vo.Content> contentList) {
		this.contentList = contentList;
	}
	
	@Transient
	public String getOpenTimeStr() {
		String dateToString = DateUtil.DateToString(this.openTime, "HH:mm");
		return dateToString;
	}
	public void setOpenTimeStr(String openTimeStr) {
		this.openTimeStr = openTimeStr;
	}

	@Override
	public String toString() {
		return "CourseContent [id=" + id + ", planDate=" + planDate + ", openTime=" + openTime + ", content=" + content + ", coachId=" + coachId + ", boxId=" + boxId + ", contentList=" + contentList + ", openTimeStr=" + openTimeStr + "]";
	}
}
