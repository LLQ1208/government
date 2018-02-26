package com.acsm.training.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
@Entity
@Table(name = "T_ROLE_EMPLOYEE")
public class RoleEmployee implements Serializable{

	private static final long serialVersionUID = 5617807658037369285L;
	private int id;//编号
    private int roleId;//角色编号
    private int employeeId;//员工编号

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ROLE_ID", length = 11)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name="EMPLOYEE_ID")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
