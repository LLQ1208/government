package com.acsm.training.dao;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.SportResult;
import com.acsm.training.model.SportResult;

/**
 * Created by lq on 2017/12/18.
 */
public interface SportResultDao extends BaseDao<SportResult>{
    SportResult queryById(int sportResultId);

    List<SportResult> queryListByMemberAndContent(int boxId,int memberId,int contentId);

    /***
     * 查询这个动作所有的记录
     * @param boxId
     * @param contentId
     * @return
     */
    List<SportResult> queryListByContent(int boxId,int contentId);

    SportResult queryListByWodContent(int wodContentId, int memberId,int courseOrderId);

    List<Object[]> queryListByPage(int boxId,int memberId,int contentId,int begin, int num);


    List<SportResult> querySportResultByWodContent(int wodContentId);

    //排序

    /**
     * 按照时间排序
     * @param wodContentId
     * @return
     */
    List<Object[]> queryListOrderByTime(int wodContentId);

    /**
     * 按照体操排序
     * @param wodContentId
     * @return
     */
    List<Object[]> queryListOrderByGym(int wodContentId);

    List<Object[]> queryListOrderByRoundAndReps(int wodContentId);

    List<Object[]> queryListOrderByTotal(int wodContentId);

    List<Object[]> queryListOrderByAmrap(int wodContentId);

    List<Object[]> queryListOrderByDistance(int wodContentId);

    List<Object[]> queryListOrderByCalor(int wodContentId);

    List<Object[]> queryListOrderByWeight(int wodContentId);

	/**
	 * 个人 自定content  content 的 成绩记录列表
	 * @param wodContentId
	 * @param contentId
	 * @param memberId
	 * @return
	 */
	List<SportResult> querySportResultByContentAndMemberOrderbyDate(int wodContentId, int contentId, int memberId);
	
    List<SportResult> querySportResultByContentAndMemberOrderbyRerord(int contentId,int memberId,String order);

    List<SportResult> querySportResultByContentAndMemberOrderbyDate(int contentId,int memberId);
    
    List<SportResult> querySportResultBy1x1(int contentId, int memberId);

    List queryWodIdByMemberLoged(int memberId, int pageIndex, int pageSize);
	
    //排行榜
    //time排序
    List<Object[]> queryListOrderByTime(Integer contentId,Integer boxId,Integer sex);
    //AMRAP-Reps 排序
    List<Object[]> queryListOrderByReps(Integer contentId,Integer boxId,Integer sex);
    //AMRAP-Rounds and Reps 排序
    List<Object[]> queryListOrderByRoundReps(Integer contentId,Integer boxId,Integer sex);
    //AMRAP-Rounds x Reps 排序
    List<Object[]> queryListOrderBySetsReps(Integer contentId,Integer boxId,Integer sex);
    //Each Round
    List<Object[]> queryListOrderByEachRound(Integer contentId,Integer boxId,Integer sex);
    //Distance
    List<Object[]> queryListOrderByDistance(Integer contentId,Integer boxId,Integer sex);
    //Calories
    List<Object[]> queryListOrderByCalories(Integer contentId,Integer boxId,Integer sex);
    //weight
    List<Object[]> queryListOrderByWeight(Integer contentId,Integer boxId,Integer sex);

    /**---教练后台数据分析*/
    List<Object[]> queryMemberScoreGym(Integer boxId, Integer memberId, Integer contentType);
    List<Object[]> queryMemberScoreWeight(Integer boxId, Integer memberId, Integer contentType);
    List<Object[]> queryMemberScoreMetOrPopu(Integer boxId, Integer memberId, Integer contentType);

    List<Object[]> queryMemberScorePop(Integer boxId, Integer memberId, Integer contentId);
    List<Object[]> queryMemberScorePopByPage(Integer boxId, Integer memberId, Integer contentType,Integer beginIndex,Integer size);
}
