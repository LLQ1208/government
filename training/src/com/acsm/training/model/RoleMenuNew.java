package com.acsm.training.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Entity
@Table(name = "T_ROLE_MENU_NEW")
public class RoleMenuNew implements Serializable{
    
	private static final long serialVersionUID = 4139629935044637303L;
	private int id;
    private int roleId;
    private int menuId;

    @Id
    @GeneratedValue
    @Column(name = "ID", length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "ROLE_ID",length = 11)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name="MENU_ID", length = 11)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuNew{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
