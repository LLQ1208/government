package com.acsm.training.model;/**
 * Created by lq on 2018/1/18.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2018-01-18
 */
@Entity
@Table(name="t_member_card_template")
public class MemberCardTemplate implements Serializable{
    private static final long serialVersionUID = -233819894957364433L;

    private Integer id;
    private String templateName;
    private Integer templateType;//1 时限卡  2次卡
    private Integer userId;//老板id
    private Integer boutCount; //次卡 次数
    private Integer validity; //有效期 （天）
    private Integer money;//费用（rmb）
    private Integer expiryReminder;//到期提醒（天）
    private Integer isDeleted;
    private Integer boutCardType;//次卡类型  1团课卡  2私教卡
    private String courseTypeIds;//课程
    private String boxId;//训练馆
    private String emailNotice;//法律提醒   1 法律免责  2 会员卡开通  3到期通知  4即将过期提醒邮件
    private String remarks;//备注
    private Date insertTime;
    private Integer insertUser;

    private String courseTypeNames;

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="template_name")
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    @Column(name="template_type")
    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    @Column(name="user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name="validity")
    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    @Column(name="money")
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
    @Column(name="expiry_reminder")
    public Integer getExpiryReminder() {
        return expiryReminder;
    }

    public void setExpiryReminder(Integer expiryReminder) {
        this.expiryReminder = expiryReminder;
    }
    @Column(name="is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Column(name="course_type_ids")
    public String getCourseTypeIds() {
        return courseTypeIds;
    }

    public void setCourseTypeIds(String courseTypeIds) {
        this.courseTypeIds = courseTypeIds;
    }
    @Column(name="box_ids")
    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }
    @Column(name="email_notice")
    public String getEmailNotice() {
        return emailNotice;
    }

    public void setEmailNotice(String emailNotice) {
        this.emailNotice = emailNotice;
    }
    @Column(name="remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="insert_user")
    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }
    @Column(name="bout_count")
    public Integer getBoutCount() {
        return boutCount;
    }

    public void setBoutCount(Integer boutCount) {
        this.boutCount = boutCount;
    }
    @Column(name="bout_card_type")
    public Integer getBoutCardType() {
        return boutCardType;
    }

    public void setBoutCardType(Integer boutCardType) {
        this.boutCardType = boutCardType;
    }
    @Transient
    public String getCourseTypeNames() {
        return courseTypeNames;
    }

    public void setCourseTypeNames(String courseTypeNames) {
        this.courseTypeNames = courseTypeNames;
    }
}
