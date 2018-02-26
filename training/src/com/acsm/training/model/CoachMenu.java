package com.acsm.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaobing.liu on 2017/8/16.
 */
@Entity
@Table(name = "T_COACH_MENU")
public class CoachMenu {

    private int id;
    private int boxId;
    private int menuId;

    @Id
    @GeneratedValue
    @Column(name = "ID",  length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="MENU_ID", length = 11)
    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Column(name = "BOX_ID", length = 11)
    public int getBoxId() {
        return boxId;
    }

    public void setBoxId(int boxId) {
        this.boxId = boxId;
    }

    @Override
    public String toString() {
        return "CoachMenu{" +
                "id=" + id +
                ", coachId=" + boxId +
                ", menuId=" + menuId +
                '}';
    }
}
