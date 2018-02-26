package com.acsm.training.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xiaobing.liu on 2017/8/15.
 */
@Entity
@Table(name = "T_ROLE_EMPLOYEE_MAPPING")
public class RoleEmployeeMapping implements Serializable{

	private static final long serialVersionUID = 5617807658037369285L;
	private int id;//编号
    private int roleId;//角色编号
    private int employeeId;//员工编号
    private Integer userType ;//用户类型 3教练 ,5员工

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

    @Column(name=" USER_TYPE")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
