package com.acsm.training.model;/**
 * Created by lq on 2017/12/24.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2017-12-24
 */
@Entity
@Table(name="t_coach_course_sign")
public class CoachCourseSign implements Serializable{
    private static final long serialVersionUID = -2946104388098200235L;

    private Integer id;
    private Integer boxId; //馆
    private Integer coachId;//教练
    private Integer bossId; //教练和boss只能有一个
    private Date date;//签到课程的日期
    private Integer isDelete;//0正常  1删除
    private Date insertTime;//签到日期
    private Integer coachCourseId;//私教课程id

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
    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    @Column(name="coach_course_id")
    public Integer getCoachCourseId() {
        return coachCourseId;
    }

    public void setCoachCourseId(Integer coachCourseId) {
        this.coachCourseId = coachCourseId;
    }
}
