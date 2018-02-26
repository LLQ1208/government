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

@Entity
@Table(name="T_MEMBER")
public class Member implements Serializable{

	private static final long serialVersionUID = -2686228335529353248L;
	
	private Integer id;//编号
	private String name;//会员姓名
	private String nickName;//昵称
	private String phone;//电话
	private Integer sex = 0;//性别0.man,1.woman
	private Integer height;//身高
	private Float weight;//体重
	private String pinCode;//身份证号
	private String email;//邮箱
	private Integer memberType;//会员卡类型
	private Date memberStartTime;//会员开始时间,开卡时间
	private Date memberEndTime;//会员结束时间
//	private Integer memberTotalNum;//会员卡总次数（次卡）
	private Integer memberDays;//会员卡天数，
	private Integer memberRemainNum;//会员卡剩余次数（次卡）
	private String avatar;//头像
	private Integer status;//会员状态，1.有效，0.无效
	private Integer type;//1.注册会员，2.临时会员
	private Integer boxId;//所属训练馆，主训练馆
	private String memberStartTimeFormat;
	private String memberEndTimeFormat;
	private Integer timeStatus;//1.正常 2.即将过期，3.已经过期，
	private String courseTypeIds;//可预约课程
	private Date birthday;
	private Date createTime;
	private String comment;

	private int age;
	
	@Id
	@GeneratedValue
	@Column(name="ID",length=11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="NAME",length=30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="SEX",length=2)
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Column(name="PHONE",length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="HEIGHT",length=3)
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@Column(name="WEIGHT",length=4)
	public Float getWeight() {
		return weight;
	}
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	@Column(name="PIN_CODE",length=20)
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	@Column(name="EMAIL",length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="MEMBER_TYPE",length=2)
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	
	@Column(name="MEMBER_START_TIME")
	public Date getMemberStartTime() {
		return memberStartTime;
	}
	public void setMemberStartTime(Date memberStartTime) {
		this.memberStartTime = memberStartTime;
	}
	
	@Column(name="MEMBER_END_TIME")
	public Date getMemberEndTime() {
		return memberEndTime;
	}
	public void setMemberEndTime(Date memberEndTime) {
		this.memberEndTime = memberEndTime;
	}
	
//	@Column(name="MEMBER_TOTAL_NUM",length=11)
//	public Integer getMemberTotalNum() {
//		return memberTotalNum;
//	}
//	public void setMemberTotalNum(Integer memberTotalNum) {
//		this.memberTotalNum = memberTotalNum;
//	}
	
	@Column(name="MEMBER_REMAIN_NUM",length=11)
	public Integer getMemberRemainNum() {
		return memberRemainNum;
	}
	public void setMemberRemainNum(Integer memberRemainNum) {
		this.memberRemainNum = memberRemainNum;
	}

	@Column(name = "TYPE", length = 2)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="MEMBER_DAYS",length=11)
	public Integer getMemberDays() {
		return memberDays;
	}
	public void setMemberDays(Integer memberDays) {
		this.memberDays = memberDays;
	}
	
	@Column(name="AVATAR")
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Column(name="STATUS",length=2)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name="BOX_ID",length=11)
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	@Column(name="COURSE_TYPE_IDS", length = 100)
	public String getCourseTypeIds() {
		return courseTypeIds;
	}

	public void setCourseTypeIds(String courseTypeIds) {
		this.courseTypeIds = courseTypeIds;
	}

	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Transient
	public String getMemberStartTimeFormat() {
		return DateUtil.format(memberStartTime, "yyyy/MM/dd");
	}
	public void setMemberStartTimeFormat(String memberStartTimeFormat) {
		this.memberStartTimeFormat = memberStartTimeFormat;
	}
	
	@Transient
	public String getMemberEndTimeFormat() {
		return DateUtil.format(memberEndTime, "yyyy/MM/dd");
	}
	public void setMemberEndTimeFormat(String memberEndTimeFormat) {
		this.memberEndTimeFormat = memberEndTimeFormat;
	}

	@Column(name = "NICK_NAME")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Transient
	public Integer getTimeStatus() {
		long datePoorDay = DateUtil.getDatePoorDay(new Date(), memberEndTime);
		if(memberType==1){
			if(datePoorDay<=10&&datePoorDay>=0){
				return 2;//即将过期
			}else if(datePoorDay<0){
				return 3;//已经过期
			}else{
				return 1;//大于10天正常
			}
		}else{
			//次卡会员，次数小于等于5次，或者到期日期小于等于10天则为即将过期
			if((memberRemainNum<=5&&memberRemainNum>0)||(datePoorDay<=10&&datePoorDay>=0)){
				return 2;//即将过期
			}else if(memberRemainNum<=0||datePoorDay<0){
				return 3;//已经过期
			}else{
				return 1;//正常
			}
		}
	}
	public void setTimeStatus(Integer timeStatus) {
		this.timeStatus = timeStatus;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Transient
	public int getAge() {

		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", sex=" + sex + ", height=" + height + ", weight=" + weight + ", pinCode=" + pinCode + ", email=" + email + ", memberType=" + memberType + ", memberStartTime=" + memberStartTime + ", memberEndTime=" + memberEndTime + ", memberDays=" + memberDays + ", memberRemainNum=" + memberRemainNum + ", avatar=" + avatar + ", status=" + status + ", boxId=" + boxId + ", memberStartTimeFormat=" + memberStartTimeFormat + ", memberEndTimeFormat=" + memberEndTimeFormat + ", timeStatus=" + timeStatus + "]";
	}

}
