package com.acsm.training.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by xiaobing.liu on 2017/8/14.
 */
@Entity
@Table(name="T_MENU_NEW")
public class MenuNew implements Serializable{

	private static final long serialVersionUID = -4509381280786691232L;
	private int id;
    private String name;
    private String url;


    private int selected=0;
    private int parentId;

    @Id
    @GeneratedValue
    @Column(name="ID",length = 11)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="NAME", length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="URL",length = 50)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Transient
    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    @Column(name="PARENTID",length = 11)
    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", selected=" + selected +
                ", parentId=" + parentId +
                '}';
    }
}
