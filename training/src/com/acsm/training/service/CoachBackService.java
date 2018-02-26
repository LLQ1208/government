package com.acsm.training.service;

import com.acsm.training.model.CoachBack.BoardPage;
import com.acsm.training.model.CourseSign;
import com.alibaba.fastjson.JSONObject;
import com.acsm.training.model.CoachBack.BoardModel;
import com.acsm.training.model.CoachBack.BoardPage;
import com.acsm.training.model.CoachCourseRest;
import com.acsm.training.model.CoachCourseSign;
import com.acsm.training.model.Content;
import com.acsm.training.model.CourseSign;


import java.util.List;

/**
 * Created by lq on 2017/12/21.
 */
public interface CoachBackService {

    public BoardPage queryBoardList(int wodContentId);

    //私教预约接口

    /**
     * 查询私教休息的表
     * @param id
     * @return
     */
    public CoachCourseRest queryCoachCourseRestById(int id);

    public CoachCourseRest queryCoachCourseRestByDate(Integer boxId,Integer bossId,Integer coachId,String date);
    /**
     * 查询休息的列表
     * @param boxId
     * @param coachId
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<CoachCourseRest> queryCoachCourseRestList(Integer boxId,Integer coachId, String coachType,
                                                          String beginTime,String endTime);

    /**
     * 更新私教休息表
     * @param coachCourseRest
     */
    public void updateCoachCourseRest(CoachCourseRest coachCourseRest);

    /**
     * 添加私教休息
     * @param coachCourseRest
     */
    public void addCoachCourseRest(CoachCourseRest coachCourseRest);
    //私教签到
    public void addCoachCourseSign(CoachCourseSign coachCourseSign);

    public void updateCoachCourseSign(CoachCourseSign coachCourseSign);

    public CoachCourseSign queryCoachCourseSign(Integer boxId,Integer coachId,String coachType,String date,Integer coachCourseId);

    /**
     * 教练团课签到
     * @param courseSign
     */
    public void addCourseSign(CourseSign courseSign);

    /**
     * 教练团课更新
     * @param courseSign
     */
    public void updateCourseSign(CourseSign courseSign);

    public CourseSign queryCourseSign(Integer courseId,String date);

    public JSONObject querySoprtDetail(Integer courseId,String date);

    public JSONObject queryContentMaxScoreOfMember(Integer contentId,Integer boxId);


    /**--------排行榜----------*/
    /**
     * 查询所有有成绩的populr wod
     * @param bossId
     * @param boxId
     * @return
     */
    public JSONObject queryListOfPopulrWod(Integer bossId,Integer boxId,Integer populorType);

    public JSONObject queryRankList(Integer boxId,Integer contentId);

    public JSONObject queryMemberScore(Integer boxId,Integer memberId,Integer contentType);

    public JSONObject queryMemberScorePop(Integer boxId,Integer memberId,Integer contentId);
    public JSONObject queryMemberScorePopByPage(Integer boxId,Integer memberId,Integer contentId,Integer page);

}
