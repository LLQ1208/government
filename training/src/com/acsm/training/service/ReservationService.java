package com.acsm.training.service;

import java.util.List;

import com.acsm.training.model.SportResult;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.reservationModel.Weightlifting;
import com.acsm.training.model.SportResult;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.reservationModel.HistorySource;
import com.acsm.training.model.reservationModel.ReservationListModel;
import com.acsm.training.model.reservationModel.Weightlifting;

/**
 * Created by lq on 2017/12/18.
 */
public interface ReservationService {
    /**
     * 查询预约列表
     * @param boxId
     * @param courseId
     * @param date
     * @return
     */
    ReservationListModel queryReservationList(Integer boxId,Integer courseId,String date);


    ReservationListModel queryCoachReservationList(Integer boxId,Integer courseId,Integer coachId,String date);

    void saveSportResult(SportResult sportResult);

    Weightlifting queryWeightHistoryData(int boxId,int memberId, int contentId, int wodContentId);

    List<HistorySource> queryWeightHistoryByPage(int boxId,int memberId, int contentId,int page);


    Weightlifting queryGymHistoryData(int boxId,int memberId, int contentId, int wodContentId);

    SportResult querySportResultById(Integer sportResultId);

	int getRecordType(WodContent wodContent);
}
