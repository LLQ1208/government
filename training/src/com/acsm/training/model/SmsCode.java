package com.acsm.training.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_CODE")
public class SmsCode {
  private Long sms_code_id;
  private String phone;
  private String code;
  private Date insert_time;

  @Id
  @GeneratedValue
  @Column(name = "sms_code_id", length = 11)
  public Long getSms_code_id() {
    return sms_code_id;
  }

  public void setSms_code_id(Long sms_code_id) {
    this.sms_code_id = sms_code_id;
  }

  @Column(name = "phone", length = 50)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Column(name = "code", length = 50)
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Column(name = "insert_time")
  public Date getInsert_time() {
    return insert_time;
  }

  public void setInsert_time(Date insert_time) {
    this.insert_time = insert_time;
  }
}
