package com.acsm.training.model;/**
 * Created by lq on 2017/12/23.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2017-12-23
 */
@Entity
@Table(name="t_coach_course_rest")
public class CoachCourseRest implements Serializable{
    private static final long serialVersionUID = 8315113016657402311L;

    private Integer id;
    private Integer boxId;//馆id
    private Integer coachId;//教练  //如果是老板的课这个字段是空的
    private Integer bossId;//老板id
    private Date date;//时间
    private Integer isDelete;//是否删除  0未删除 1删除
    private Date inserTime;

    private String weekTitle;//周几

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
    @Column(name="coach_id")
    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }
    @Column(name="boss_id")
    public Integer getBossId() {
        return bossId;
    }

    public void setBossId(Integer bossId) {
        this.bossId = bossId;
    }
    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Column(name="is_delete")
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    @Column(name="insert_time")
    public Date getInserTime() {
        return inserTime;
    }

    public void setInserTime(Date inserTime) {
        this.inserTime = inserTime;
    }
    @Transient
    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }
}
