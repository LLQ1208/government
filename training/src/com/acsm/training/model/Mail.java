package com.acsm.training.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 15:15 2018/1/24
 */
@Entity
@Table(name="t_mail")
public class Mail{
    private Integer id;
    private Integer bossUserId;
    private Integer mailSendRule;
    private Integer isDeleted;
    private String title;
    private String content;
    private Date insertTime;
    private Integer userId;
    private String userName;

    @Id
    @GeneratedValue
    @Column(name="ID",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="boss_user_id",length=11)
    public Integer getBossUserId() {
        return bossUserId;
    }

    public void setBossUserId(Integer bossUserId) {
        this.bossUserId = bossUserId;
    }

    @Column(name="mail_send_rule",length=11)
    public Integer getMailSendRule() {
        return mailSendRule;
    }

    public void setMailSendRule(Integer mailSendRule) {
        this.mailSendRule = mailSendRule;
    }

    @Column(name="is_deleted",length=2)
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name="title",length=255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="content",length=5000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name="insert_time")
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    @Column(name="user_id",length=11)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name="user_name",length=255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}