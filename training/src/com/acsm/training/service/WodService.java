package com.acsm.training.service;

import java.util.List;
import java.util.Map;

import com.acsm.training.model.User;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.page.WodModel;
import com.acsm.training.model.User;
import com.acsm.training.model.Wod;
import com.acsm.training.model.WodContent;
import com.acsm.training.model.WodSection;
import com.acsm.training.model.WodTopRelation;
import com.acsm.training.model.page.WodModel;
import com.acsm.training.model.page.WodPageModel;

/**
 * Created by lq on 2017/11/30.
 */
public interface WodService {
    public List<WodModel> queryWodList(Map map);

    public void deleteWod(int wodId);

    public WodModel queryWodModelByWodId(int wodId);

    public Wod queryWodById(int id);

    public void addWodSection(WodSection wodSection);

    public List<WodSection> queryWodScetionOfWod(int wodId);

    public Integer addWod(Wod wod,Integer crouseTypeId, List<Integer> boxIds);

    public WodSection queryWodSectionById(int wodSectionId);

    public  void updateWodSection(WodSection wodSection);
    /***
     * 删除wodSectoin
     */
    public void deleteWodSection(int wodSectionId);

    public WodContent addgeneralWodContent(WodContent wodContent, int contentId);


    public List<WodContent> queryWodContentOfSectionDesc(int wodSectoinId,int wodId);

    public WodContent queryWodContentById(int wodContentId);

    public WodContent addCustonmWodContent(WodContent wodContent, User user, int contentType);

    /**
     * 更新wod_content
     * @param wodContent
     * @return
     */
    public void updateWodContent(WodContent wodContent);

    /**
     * 删除wod_content
     * @param wodContentId
     */
    public void deleteWodContent(int wodContentId);
    /**
     * 重新排序
     */
    public void updateSortWodContent(int wodContentId,int oldWodSectionId,int newWodSectionId,Integer preWodContentId);
    //查询所有的动作
    public List<WodContent> queryWodContentListByWod(int wodId);
    /**
     * wod_section拖动排序
     * @param wodSectionId
     * @param newPreSectionId
     * @param newContentIds
     */
    public void updateSortWodSection(int wodSectionId,int newPreSectionId, String newContentIds);

    public List<WodTopRelation> queryWodTopRelations(int wodId);

    public WodTopRelation queryWodTopRelation(int boxId,int courseTypeId, String date);

    public WodPageModel queryWodPageModel(int wodId);

    public void deletedWodTopRelation(WodTopRelation wodTopRelation);

    public void updateWodTopRelation(Wod wod,Integer crouseTypeId, List<Integer> boxIds);



}
