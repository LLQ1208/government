package com.acsm.training.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Yangbaojie on 2017/12/30.
 */
@Entity
@Table(name="T_EMPLOYEE_NEW")
public class EmployeeNew implements Serializable{

    private static final long serialVersionUID = -4509381280786691232L;
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private Integer boxId;
    private String logo;
    private Integer roleId;
    private String pwd;
    private String birthday;
    private String sex;
    private String remarks;
    private Integer allowDelete;
    private Integer isDeleted;
    @Id
    @GeneratedValue
    @Column(name="ID",length=11)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="NAME",length=36)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="BOX_ID")
    public Integer getBoxId() {
        return boxId;
    }
    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    @Column(name="PHONE")
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="EMAIL")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="LOGO")
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Transient
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Transient
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name="BIRTHDAY")
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Column(name="SEX")
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name="REMARKS")
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Transient
    public Integer getAllowDelete() {
        return allowDelete;
    }
    public void setAllowDelete(Integer allowDelete) {
        this.allowDelete = allowDelete;
    }
    @Column(name="IS_DELETE")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
