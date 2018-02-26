package com.acsm.training.model;/**
 * Created by lq on 2017/12/1.
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author lianglinqiang
 * @create 2017-12-01
 */
@Entity
@Table(name="t_wod_top_relation")
public class WodTopRelation implements Serializable{
    private static final long serialVersionUID = -4519426212775408689L;
    private Integer topRelationId;
    private Integer wodId;
    private Integer boxId;
    private Integer courseId;
    private Integer isDeleted;

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    @Column(name="is_deleted")
    public Integer getIsDeleted() {
        return isDeleted;
    }

    @Column(name="wod_id")
    public Integer getWodId() {
        return wodId;
    }

    public void setWodId(Integer wodId) {
        this.wodId = wodId;
    }
    @Column(name="box_id")
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }
    @Column(name="course_type_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Id
    @GeneratedValue
    @Column(name="top_relation_id",length=11)
    public Integer getTopRelationId() {
        return topRelationId;
    }

    public void setTopRelationId(Integer topRelationId) {
        this.topRelationId = topRelationId;
    }
}
