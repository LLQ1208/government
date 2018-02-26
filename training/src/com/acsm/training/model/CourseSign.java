package com.acsm.training.model;/**
 * Created by lq on 2017/12/24.
 */

import javax.persistence.*;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2017-12-24
 */
@Entity
@Table(name="t_course_sign")
public class CourseSign {

    private Integer id;
    private Integer boxId;
    private Integer coachId;
    private Date date;
    private Integer isDelete;
    private Date insertTime;
    private Integer courseId;

    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="coach_id")
    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
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
    @Column(name="course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
